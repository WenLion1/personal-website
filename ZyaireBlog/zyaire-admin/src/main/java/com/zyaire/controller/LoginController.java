package com.zyaire.controller;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.LoginUser;
import com.zyaire.domain.entity.User;
import com.zyaire.enums.AppHttpCodeEnum;
import com.zyaire.exception.SystemException;
import com.zyaire.service.BlogLoginService;
import com.zyaire.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
}
