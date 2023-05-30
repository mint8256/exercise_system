package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.Paper;
import com.five.entity.UserPaper;
import com.five.entity.UserQuestion;
import com.five.query.UserPaperQuery;
import com.five.vo.MyPage;
import com.five.vo.UserPaperDetail;

import java.util.List;

/**
 * 用户做的试卷记录(UserPaper)表服务接口
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
public interface UserPaperService extends IService<UserPaper> {

    /**
     * 删除学生的做题信息
     *
     * @param paperId 试卷id
     */
    void deleteByPaperId(Long paperId);

    /**
     * 添加用户的做题记录（在教师修改试卷状态为自动修改，将状态设为未开始）
     *
     * @param paper 试卷
     */
    void createUserPaper(Paper paper);

    /**
     * 根据查询信息获取用户的答卷
     */
    MyPage<List<UserPaper>> getUserPaperList(UserPaperQuery userPaperQuery);

    /**
     * 获取用户试卷详情
     */
    UserPaperDetail getUserPaperDetail(Long userPaperId);

    /**
     * 根据试卷id和userIds获取试卷列表
     */
    List<UserPaper> getUserPaperList(Long paperId,List<Long> userIds);

    /**
     * 学生获取自己的试卷列表
     * @param userPaperQuery 分页参数
     * @return 分页试卷列表信息
     */
    MyPage<List<UserPaper>> getPaperList(UserPaperQuery userPaperQuery);

    /**
     *  获取学生测试的剩余时间
     * @param paperId 试卷id
     * @return 考试剩余时间
     */
    Integer getUserPaperRemaining(Long paperId);

    /**
     * 学生开始考试（改变状态）
     * @param paperId 试卷id
     */
    void startExam(Long paperId);

    /**
     * 学生提交试卷
     * @param paperId 试卷id
     */
    void submitPaper(Long paperId);

    /**
     * 学生提交一道题目的作答信息
     * @param userQuestion 题目作答数据
     */
    void submitQuestion(UserQuestion userQuestion);
}

