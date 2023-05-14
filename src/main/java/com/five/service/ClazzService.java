package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.Clazz;
import com.five.vo.ClazzVo;
import com.five.vo.StudentVo;

import java.util.List;

/**
 * (Clazz)表服务接口
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
public interface ClazzService extends IService<Clazz> {
    /**
     * 获取班级列表
     *
     * @return 班级列表
     */
    List<ClazzVo> getClazzVoList();


    /**
     * 获取该教师下的班级信息
     */
    List<Clazz> getClazzList();

    /**
     * 根据clazzId查询班级学生
     *
     * @param clazzId 班级id
     * @return 查询到了学生列表
     */
    List<StudentVo> getStudentListById(Long clazzId);

    /**
     * 查询一个班级的人数
     *
     * @param clazzId 班级人数
     * @return 班级人数（除去老师，即-1）
     */
    Long count(Long clazzId);

    /**
     * 判断班级名是否存在，避免重复创建
     *
     * @param clazzName 班级名
     * @return 是否已经存在
     */
    boolean checkIsExist(String clazzName);

    /**
     * 根据班级名称查询班级
     *
     * @param clazzName 班级名
     */
    Clazz getClazzByName(String clazzName);
    /**
     * 新增班级
     *
     * @param clazz 需要新增的班级
     */
    void createClazz(Clazz clazz);

    /**
     * 获取下一个班级序号
     *
     * @param grade 年纪
     * @return 下一个班级序号
     */
    Integer nextClazzNumber(Integer grade);

    /**
     * 删除班级
     *
     * @param clazzId 需要删除的班级的clazzId
     */
    void deleteClazz(Long clazzId);

    /**
     * 查询到的班级列表
     *
     * @param clazz 查询条件
     */
    List<ClazzVo> queryList(Clazz clazz);

    /**
     * 将clazz对象转换为clazzVo对象
     */
    ClazzVo clazzToVo(Clazz clazz);
}

