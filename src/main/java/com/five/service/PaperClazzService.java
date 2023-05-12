package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.PaperClazz;

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

}

