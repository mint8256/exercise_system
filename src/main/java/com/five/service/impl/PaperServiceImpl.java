package com.five.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.ClazzDao;
import com.five.dao.PaperDao;
import com.five.dao.QuestionListDao;
import com.five.entity.*;
import com.five.enums.PaperStatusEnum;
import com.five.service.*;
import com.five.util.AuthUserContext;
import com.five.util.IdentifierGenerator;
import com.five.util.SpringContextUtil;
import com.five.vo.PaperDetail;
import com.five.vo.PaperVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Paper)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements PaperService {

    @Resource
    private PaperDao paperDao;

    @Resource
    private MapperFacade mapperFacade;

    @Resource
    private PaperClazzService paperClazzService;

    @Resource
    private UserQuestionService userQuestionService;
    @Resource
    private ClazzDao clazzDao;

    @Resource
    private UserPaperService userPaperService;
    @Resource
    private QuestionListDao questionListDao;
    @Resource
    private QuestionService questionService;

    @Override
    public List<PaperVo> getAllPaper() {

        Long userId = AuthUserContext.userId();

        LambdaQueryWrapper<Paper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Paper::getUserId, userId);
        queryWrapper.orderByDesc(Paper::getStatus);
        queryWrapper.orderByDesc(Paper::getSignTime);

        List<Paper> papers = paperDao.selectList(queryWrapper);

        return papers.stream().map(this::paperToVo).collect(Collectors.toList());
    }

    @Override
    public void deletePaper(Long paperId) {
        // 1. 删除与试卷关联的班级信息
        paperClazzService.deleteByPaperId(paperId);
        // 2. 删除学生的做题信息
        userPaperService.deleteByPaperId(paperId);
        // 3. 删除试卷本身
        paperDao.deleteById(paperId);
    }

    public PaperVo paperToVo(Paper paper) {

        Long paperId = paper.getPaperId();

        PaperVo vo = mapperFacade.map(paper, PaperVo.class);

        List<PaperClazz> paperClazzList = paperClazzService.getListByPaperId(paperId);

        List<Long> clazzIds = paperClazzList.stream().map(PaperClazz::getClazzId).collect(Collectors.toList());
        List<Clazz> clazzList = clazzDao.selectBatchIds(clazzIds);

        vo.setClazzList(clazzList);

        return vo;
    }

    @Override
    public PaperDetail paperToDetail(Paper paper) {

        PaperVo paperVo = this.paperToVo(paper);

        PaperDetail detail = mapperFacade.map(paperVo, PaperDetail.class);

        List<Question> questionList = questionService.getByQuestionListId(detail.getQuestionListId());

        detail.setQuestionList(questionList);

        return detail;
    }


    @Override
    public void updatePaper(PaperVo paperVo) {

        // 1. 更新试卷自己的信息
        Paper paper = mapperFacade.map(paperVo, Paper.class);
        paperDao.updateById(paper);

        // 2. 更新试卷班级关联信息
        List<Long> clazzIds = paperVo.getClazzList().stream().map(Clazz::getClazzId).collect(Collectors.toList());
        paperClazzService.updatePaperClazz(paper.getPaperId(), clazzIds);

    }

    @Override
    @Transactional
    public void savePaper(PaperVo paperVo) {

        Long questionListId = paperVo.getQuestionListId();
        QuestionList questionList = questionListDao.selectById(questionListId);

        // 1. 保存试卷自己的信息
        Paper paper = mapperFacade.map(paperVo, Paper.class);
        paper.setUserId(AuthUserContext.userId());
        Long schoolId = SpringContextUtil.getBean(UserServiceImpl.class).getSchoolByUserId(AuthUserContext.userId()).getSchoolId();
        paper.setStatus(PaperStatusEnum.NOT_RELEASE.value());
        paper.setPaperScore(questionList.getScore());
        paper.setQuestionCount(questionList.getQuestionCount());

        //计算并设置持续时间，单位：s
        Duration between = Duration.between(paperVo.getStartTime(), paperVo.getEndTime());
        long seconds = between.toSeconds();
        paper.setDuration(seconds);

        paper.setPaperIdentifier(IdentifierGenerator.genPaperIdentifier(paperVo.getPaperDifficulty(), schoolId,
                questionList.getQuestionListNumber(), nextPaperNumber(questionListId)));

        paperDao.insert(paper);

        // 2. 添加试卷班级关联信息
        List<Long> clazzIds = paperVo.getClazzList().stream().map(Clazz::getClazzId).collect(Collectors.toList());
        paperClazzService.insertBatch(paper.getPaperId(), clazzIds);

    }

    @Override
    public void updateStatus(Long paperId) {

        // 1. 更改试卷本身的状态
        Paper paper = new Paper();
        paper.setPaperId(paperId);
        paper.setStatus(PaperStatusEnum.RELEASED.value());
        paperDao.updateById(paper);

        // 2. 添加到学生的试题列表中，并设置状态为未开始
        paper = paperDao.selectById(paperId);
        userPaperService.createUserPaper(paper);

    }

    @Override
    public Paper getByPaperName(String paperName) {
        LambdaQueryWrapper<Paper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Paper::getPaperName, paperName);
        return paperDao.selectOne(queryWrapper);
    }

    @Override
    public boolean paperNameIsExist(String paperName) {
        return getByPaperName(paperName) != null;
    }

    @Override
    public Integer nextPaperNumber(Long questionListId) {

        LambdaQueryWrapper<Paper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Paper::getQuestionListId, questionListId);

        List<Paper> list = this.list(queryWrapper);

        return list.size() + 1;
    }
}

