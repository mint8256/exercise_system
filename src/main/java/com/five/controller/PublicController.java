package com.five.controller;

import com.five.entity.User;
import com.five.exception.BaseException;
import com.five.service.UserService;
import com.five.util.JwtUtils;
import com.five.util.TokenInfo;
import com.five.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description: 公共的controller
 *
 * @author fly
 * @since 2023/5/8 18:42
 */
@RestController
@Slf4j
public class PublicController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public R<String> login(@RequestBody User login) {

        log.debug("登陆的用户信息为：{}", login);

        User user = userService.login(login);

        TokenInfo tokenInfo = new TokenInfo.Builder()
                .userId(user.getUserId())
                .role(user.getRole())
                .realName(user.getRealName())
                .userIdentifier(user.getUserIdentifier())
                .build();

        String token = JwtUtils.generateToken(tokenInfo);

        return R.success("登录成功", token);
    }

    @PostMapping("register")
    public R<User> register(@RequestBody User register) {

        if (userService.usernameIsExist(register.getUsername())) {
            throw new BaseException("用户名已经被注册，换个用户名试试");
        }

        User user = userService.register(register);

        return R.success("注册成功", null);
    }

}
