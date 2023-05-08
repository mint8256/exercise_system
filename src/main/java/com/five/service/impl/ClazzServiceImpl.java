package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.ClazzDao;
import com.five.entity.Clazz;
import com.five.service.ClazzService;
import org.springframework.stereotype.Service;

/**
 * (Clazz)表服务实现类
 *
 * @author fly
 * @since 2023-05-08 14:13:58
 */
@Service("clazzService")
public class ClazzServiceImpl extends ServiceImpl<ClazzDao, Clazz> implements ClazzService {

}

