package com.zyaire.controller;

import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.MenuListDto;
import com.zyaire.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult getMenuList(MenuListDto menuListDto){
        return menuService.getMenuList(menuListDto);
    }
}
