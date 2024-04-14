package com.zyaire.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyaire.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-09 20:28:24
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleKeyByUserId(Long id);
}
