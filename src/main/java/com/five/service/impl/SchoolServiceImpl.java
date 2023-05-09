package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.SchoolDao;
import com.five.entity.School;
import com.five.service.SchoolService;
import org.springframework.stereotype.Service;

/**
 * (School)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 13:45:25
 */
@Service("schoolService")
public class SchoolServiceImpl extends ServiceImpl<SchoolDao, School> implements SchoolService {

}

