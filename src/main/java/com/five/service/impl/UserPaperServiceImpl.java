package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserPaperDao;
import com.five.entity.*;
import com.five.enums.UserPaperStatusEnum;
import com.five.query.UserPaperQuery;
import com.five.service.*;
import com.five.util.SpringContextUtil;
import com.five.vo.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户做的试卷记录(UserPaper)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Service("userPaperService")
public class UserPaperServiceImpl extends ServiceImpl<UserPaperDao, UserPaper> implements UserPaperService {

    @Resource
    private UserPaperDao userPaperDao;
    @Resource
    private PaperClazzService paperClazzService;
    @Resource
    private UserClazzService userClazzService;
    @Resource
    private UserService userService;
    @Resource
    private ClazzService clazzService;
    @Resource
    private UserQuestionService userQuestionService;

    @Override
    public void deleteByPaperId(Long paperId) {
        userPaperDao.deleteByPaperId(paperId);
    }

    @Override
    public void createUserPaper(Paper paper) {

        Long paperId = paper.getPaperId();

        // 1. 首先获取相应的关联班级信息
        List<PaperClazz> paperClazzList = paperClazzService.getListByPaperId(paperId);
        List<Long> clazzIds = paperClazzList.stream().map(PaperClazz::getClazzId).collect(Collectors.toList());

        // 2. 然后根据班级id，获取全部的学生
        clazzIds.forEach(clazzId -> {
            // 获取班级的学生id
            List<UserClazz> userClazzList = userClazzService.getListByClazzId(clazzId);
            List<Long> userIds = userClazzList.stream().map(UserClazz::getUserId).collect(Collectors.toList());

            List<UserPaper> userPaperList = userIds.stream().filter(userService::isStudent).map((userId) -> {
                UserPaper userPaper = new UserPaper();
                userPaper.setUserId(userId);
                userPaper.setPaperId(paperId);
                userPaper.setPaperName(paper.getPaperName());
                userPaper.setQuestionCount(paper.getQuestionCount());
                userPaper.setPaperScore(paper.getPaperScore());
                userPaper.setStatus(0);
                return userPaper;
            }).collect(Collectors.toList());

            userPaperList.forEach(userPaper -> {

                userPaperDao.insert(userPaper);
                Long userPaperId = userPaper.getId();

                // 3. 添加用户对应的题目信息
                userQuestionService.createUserQuestion(userPaperId, paperId, paper.getQuestionListId());

            });
        });

    }

    @Override
    public MyPage<List<UserPaper>> getUserPaperList(UserPaperQuery userPaperQuery) {

        MyPage<List<UserPaper>> myPage = new MyPage<>();

        Page<UserPaper> page = new Page<>();
        page.setSize(userPaperQuery.getSize()).setCurrent(userPaperQuery.getPage());

        Long clazzId = clazzService.getClazzByName(userPaperQuery.getClazzName()).getClazzId();

        List<Long> userIds = userClazzService.getListByClazzId(clazzId)
                .stream()
                .map(UserClazz::getUserId)
                .filter(userService::isStudent)
                .collect(Collectors.toList());

        Paper paper = SpringContextUtil.getBean(PaperService.class).getByPaperName(userPaperQuery.getPaperName());
        Long paperId = paper.getPaperId();

        LambdaQueryWrapper<UserPaper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPaper::getPaperId, paperId)
                .in(UserPaper::getUserId, userIds)
                .ge(paper.getEndTime().isBefore(LocalDateTime.now()), UserPaper::getStatus, UserPaperStatusEnum.UNCORRECTED);

        page = userPaperDao.selectPage(page, queryWrapper);

        List<UserPaper> records = page.getRecords();
        long total = page.getTotal();
        myPage.setData(records);
        myPage.setTotal((int) total);

        return myPage;
    }
}

