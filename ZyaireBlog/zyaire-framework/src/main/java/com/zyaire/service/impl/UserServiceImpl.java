package com.zyaire.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyaire.domain.ResponseResult;
import com.zyaire.domain.entity.User;
import com.zyaire.domain.vo.UserInfoVo;
import com.zyaire.mapper.UserMapper;
import com.zyaire.service.UserService;
import com.zyaire.utils.BeanCopyUtils;
import com.zyaire.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-03-15 20:44:10
 */
@Service("userService")

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        Long id = SecurityUtils.getUserId();
        User byId = getById(id);

        UserInfoVo vo = BeanCopyUtils.copyBean(byId, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }
}
