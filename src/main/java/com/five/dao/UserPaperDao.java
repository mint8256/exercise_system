package com.five.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.five.entity.UserPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserPaper)表数据库访问层
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Mapper
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

}

