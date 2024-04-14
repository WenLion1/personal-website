package com.zyaire.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyaire.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-09 20:19:09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long id);
}
