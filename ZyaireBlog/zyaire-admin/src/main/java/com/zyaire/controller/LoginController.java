package com.zyaire.controller;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.LoginUser;
import com.zyaire.domain.entity.Menu;
import com.zyaire.domain.entity.User;
import com.zyaire.domain.vo.AdminUserInfoVo;
import com.zyaire.domain.vo.RoutersVo;
import com.zyaire.domain.vo.UserInfoVo;
import com.zyaire.enums.AppHttpCodeEnum;
import com.zyaire.exception.SystemException;
import com.zyaire.service.BlogLoginService;
import com.zyaire.service.LoginService;
import com.zyaire.service.MenuService;
import com.zyaire.service.RoleService;
import com.zyaire.utils.BeanCopyUtils;
import com.zyaire.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();

        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long id = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(id);
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
