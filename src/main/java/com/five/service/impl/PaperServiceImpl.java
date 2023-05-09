package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.PaperDao;
import com.five.entity.Paper;
import com.five.service.PaperService;
import org.springframework.stereotype.Service;

/**
 * (Paper)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements PaperService {

}

