package com.zyaire.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.constants.SystemConstants;
import com.zyaire.domain.entity.Menu;
import com.zyaire.mapper.MenuMapper;
import com.zyaire.service.MenuService;
import com.zyaire.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-09 20:23:12
 */
@Service("menuService")

public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {

        // 如果是管理员，返回所有权限
        if (id.equals(1L)){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);

            List<Menu> list = list(wrapper);
            List<String> perms = list.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());

            return perms;
        }

        // 如果不是管理员，返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long id) {
        MenuMapper baseMapper = getBaseMapper();
        List<Menu> menus = null;

        // 判断是否为管理员
        if (SecurityUtils.isAdmin()){
            // 如果是，返回所有符合要求的menu
            menus = baseMapper.selectAllRouterMenu();
        } else {
            // 否则，返回当前用户具有的menu
            menus = baseMapper.selectRouterMenuTreeByUserId(id);
        }

        List<Menu> menuTree = builderMenuTree(menus, 0L);

        return menuTree;
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());

        return menuTree;
    }

    /**
     * 获取存入参数的子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(menu1 -> menu1.getParentId().equals(menu.getId()))
                .map(menu1 -> menu1.setChildren(getChildren(menu1, menus)))
                .collect(Collectors.toList());

        return childrenList;
    }
}
