package com.five;

import com.five.service.ClazzService;
import com.five.util.AuthUserContext;
import com.five.util.TokenInfo;
import com.five.vo.ClazzVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/14 14:31
 */
@SpringBootTest
public class ClazzServiceTest {

    @Resource
    ClazzService clazzService;

    @Test
    public void test1() {

        TokenInfo tokenInfo = new TokenInfo.Builder().realName("啦啦").userId(1L).role(1).build();

        AuthUserContext.set(tokenInfo);

//        Clazz clazz = new Clazz();
//        clazz.setClazzName("三年二班");
//        clazz.setGrade(3);
//
//        clazzService.createClazz(clazz);

        List<ClazzVo> clazzList = clazzService.getClazzVoList();
        System.out.println(clazzList);
    }

}
