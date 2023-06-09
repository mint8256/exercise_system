package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.entity.UserPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户做的试卷记录(UserPaper)表数据库访问层
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
public interface UserPaperDao extends BaseMapper<UserPaper> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPaper> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserPaper> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPaper> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserPaper> entities);

    /**
     * 根据试卷id删除用户做题信息
     * @param paperId 试卷id
     */
    void deleteByPaperId(@Param("paperId")Long paperId);

    /**
     * 根据学生id和试卷id更新用户对应的试卷的状态。
     * @param userPaper
     */
    void updatePaperStatus(UserPaper userPaper);

}

