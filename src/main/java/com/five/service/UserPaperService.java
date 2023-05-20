package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.Paper;
import com.five.entity.UserPaper;
import com.five.query.UserPaperQuery;
import com.five.vo.MyPage;

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

}

