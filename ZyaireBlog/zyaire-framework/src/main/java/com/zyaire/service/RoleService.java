package com.zyaire.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyaire.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-04-09 20:28:26
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
