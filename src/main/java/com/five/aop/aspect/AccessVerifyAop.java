package com.five.aop.aspect;

import com.five.aop.annotation.AuthVerify;
import com.five.enums.RoleEnum;
import com.five.exception.NoAccessException;
import com.five.exception.ParamException;
import com.five.util.AuthUserContext;
import com.five.util.JwtUtils;
import com.five.util.ParamAssert;
import com.five.util.TokenInfo;
import com.five.vo.RCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class AccessVerifyAop {

    @Pointcut("@annotation(com.five.aop.annotation.AuthVerify)")
    private void tokenPointcut() {
    }

    @Before(value = "tokenPointcut() && @annotation(authVerify)")
    public void tokenVerify(AuthVerify authVerify) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest servletRequest = Objects.requireNonNull(requestAttributes).getRequest();

        String token = servletRequest.getHeader("token");
        ParamAssert.notNull(token, RCodeEnum.TOKEN_NOT_EXIST);

        // 拦截非法token
        if (!JwtUtils.isValid(token)) {
            throw new ParamException(RCodeEnum.INVALID_TOKEN);
        }
        TokenInfo tokenInfo = JwtUtils.getTokenInfo(token);
        log.info("token is {}", tokenInfo);
        // 鉴权
        checkRole(tokenInfo, authVerify);

        AuthUserContext.set(tokenInfo);
    }

    @After(value = "tokenPointcut() && @annotation(authVerify)")
    public void tokenVerifyAfter(AuthVerify authVerify) {
        // 对之前的ThreadLocal中的数据进行销毁，防止出现内存泄露
        AuthUserContext.clear();
    }



    /**
     * 鉴权
     *
     * @param tokenInfo    包含用户的role
     * @param authVerify 能访问的role集合
     */
    private void checkRole(TokenInfo tokenInfo, AuthVerify authVerify) {

        for (RoleEnum roleEnum : authVerify.roles()) {
            if (roleEnum.value().equals(tokenInfo.getRole())) {
                return;
            }
        }
        throw new NoAccessException(RCodeEnum.NO_ACCESS);
    }
}