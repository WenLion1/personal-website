package com.zyaire.service;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
