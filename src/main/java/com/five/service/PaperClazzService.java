package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.PaperClazz;
import com.five.vo.PaperClazzVo;

import java.util.List;

/**
 * (PaperClazz)表服务接口
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
public interface PaperClazzService extends IService<PaperClazz> {

    /**
     * 根据班级id获取试卷班级关联列表
     */
    List<PaperClazz> getListByClazzId(Long clazzId);

    /**
     * 根据试卷id获取试卷班级关联列表
     */
    List<PaperClazz> getListByPaperId(Long paperId);

    /**
     * 根据试卷id删除试卷班级关联信息
     *
     * @param paperId 试卷id
     */
    void deleteByPaperId(Long paperId);

    /**
     * 批量插入新的试卷班级关联信息
     */
    void insertBatch(Long paperId, List<Long> clazzIds);

    /**
     * 更新试卷班级的关联信息
     *
     * @param paperId 试卷id
     * @param clazzIds 关联的班级列表的ids
     */
    void updatePaperClazz(Long paperId, List<Long> clazzIds);

    /**
     * 根据试卷名称或班级名称获取其关联信息
     */
    List<PaperClazzVo> getPaperClazzVOList(String paperName, String clazzName);

    PaperClazzVo paperClazzToVo(PaperClazz paperClazz);
}

