package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.UserRoleMapper;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:24
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateAuth(UserRole userRole) throws Exception {
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userRole.getUserId()));
        userRoleMapper.insert(userRole);
    }
}
