package com.zyaire.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.dto.MenuListDto;
import com.zyaire.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2024-04-09 20:19:17
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long id);

    ResponseResult getMenuList(MenuListDto menuListDto);
}
