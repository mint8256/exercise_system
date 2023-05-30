package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.entity.UserQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserQuestion)表数据库访问层
 *
 * @author fly
 * @since 2023-05-09 15:38:11
 */
public interface UserQuestionDao extends BaseMapper<UserQuestion> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserQuestion> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserQuestion> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserQuestion> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserQuestion> entities);

    /**
     * 更新用户题目
     * @param userQuestion 用户题目作答情况
     */
    void updateUserQuestion(UserQuestion userQuestion);

}

