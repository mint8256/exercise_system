package com.five.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.PaperClazzDao;
import com.five.entity.Clazz;
import com.five.entity.Paper;
import com.five.entity.PaperClazz;
import com.five.service.PaperClazzService;
import com.five.util.SpringContextUtil;
import com.five.vo.PaperClazzVo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (PaperClazz)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
@Service("paperClazzService")
public class PaperClazzServiceImpl extends ServiceImpl<PaperClazzDao, PaperClazz> implements PaperClazzService {

    @Resource
    private PaperClazzDao paperClazzDao;
    @Resource
    private MapperFacade mapperFacade;
    @Override
    public List<PaperClazz> getListByClazzId(Long clazzId) {

        LambdaQueryWrapper<PaperClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaperClazz::getClazzId, clazzId);

        return this.list(queryWrapper);
    }

    @Override
    public List<PaperClazz> getListByPaperId(Long paperId) {
        LambdaQueryWrapper<PaperClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaperClazz::getPaperId, paperId);

        return this.list(queryWrapper);
    }

    @Override
    public void deleteByPaperId(Long paperId) {

        LambdaQueryWrapper<PaperClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaperClazz::getPaperId, paperId);

        this.remove(queryWrapper);
    }

    public void insertBatch(Long paperId, List<Long> clazzIds) {
        // 批量添加新的关联信息
        List<PaperClazz> paperClazzList = clazzIds.stream().map((cid) -> {
            PaperClazz paperClazz = new PaperClazz();
            paperClazz.setClazzId(cid);
            paperClazz.setPaperId(paperId);
            return paperClazz;
        }).collect(Collectors.toList());

        paperClazzDao.insertBatch(paperClazzList);
    }

    @Override
    public void updatePaperClazz(Long paperId, List<Long> clazzIds) {

        // 1. 删除原来的关联信息
        deleteByPaperId(paperId);
        // 2. 批量添加新的关联信息
        insertBatch(paperId, clazzIds);
    }

    @Override
    public List<PaperClazzVo> getPaperClazzVOList(String paperName, String clazzName) {

        LambdaQueryWrapper<PaperClazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(clazzName), PaperClazz::getClazzId, SpringContextUtil.getBean(ClazzServiceImpl.class).getClazzByName(clazzName).getClazzId());
        queryWrapper.eq(StrUtil.isNotBlank(paperName), PaperClazz::getPaperId, SpringContextUtil.getBean(PaperServiceImpl.class).getByPaperName(paperName).getPaperId());

        List<PaperClazz> list = this.list(queryWrapper);

        return list.stream().map(this::paperClazzToVo).collect(Collectors.toList());
    }

    @Override
    public PaperClazzVo paperClazzToVo(PaperClazz paperClazz) {
        PaperClazzVo vo = mapperFacade.map(paperClazz, PaperClazzVo.class);
        Clazz clazz = SpringContextUtil.getBean(ClazzServiceImpl.class).getById(paperClazz.getClazzId());
        vo.setClazzName(clazz.getClazzName());
        Paper paper = SpringContextUtil.getBean(PaperServiceImpl.class).getById(paperClazz.getPaperId());
        vo.setPaperName(paper.getPaperName());
        return vo;
    }
}

