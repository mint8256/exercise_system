package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.PaperClazzDao;
import com.five.entity.PaperClazz;
import com.five.service.PaperClazzService;
import org.springframework.stereotype.Service;

/**
 * (PaperClazz)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
@Service("paperClazzService")
public class PaperClazzServiceImpl extends ServiceImpl<PaperClazzDao, PaperClazz> implements PaperClazzService {

}

