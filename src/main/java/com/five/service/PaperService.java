package com.five.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.five.entity.Paper;
import com.five.vo.PaperDetail;
import com.five.vo.PaperVo;

import java.util.List;

/**
 * (Paper)表服务接口
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
public interface PaperService extends IService<Paper> {

    /**
     * 获取当前老师的全部试卷信息，并按照创建时间进行倒序排序
     */
    List<PaperVo> getAllPaper();

    /**
     * 删除试卷
     *
     * @param paperId 试卷id
     */
    void deletePaper(Long paperId);

    PaperVo paperToVo(Paper paper);

    PaperDetail paperToDetail(Paper paper);

    /**
     * 更新试卷信息（包括名称，关联班级之类的）
     */
    void updatePaper(PaperVo paperVo);

    /**
     * 保存试卷信息
     *
     * @param paperVo 试卷信息（包括关联的班级）
     */
    Long savePaper(PaperVo paperVo);

    /**
     * 更改试卷的状态，由未发布转换为发布
     *
     * @param paperId 试卷id
     */
    void updateStatus(Long paperId);

    /**
     * 判断试卷名称是否已经存在
     */
    Paper getByPaperName(String paperName);

    /**
     * 判断试卷名称是否已经存在
     */
    boolean paperNameIsExist(String paperName);

    /**
     * 获取试卷的下一个序号
     */
    Integer nextPaperNumber(Long questionListId);
}

