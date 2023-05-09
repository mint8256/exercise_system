package com.five.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.five.dao.UserPaperDao;
import com.five.entity.UserPaper;
import com.five.service.UserPaperService;
import org.springframework.stereotype.Service;

/**
 * 用户做的试卷记录(UserPaper)表服务实现类
 *
 * @author fly
 * @since 2023-05-09 15:46:16
 */
@Service("userPaperService")
public class UserPaperServiceImpl extends ServiceImpl<UserPaperDao, UserPaper> implements UserPaperService {

}

