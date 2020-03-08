package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.mapper.UserRoleMapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 20:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public IPage<User> findAll(Page<?> page, User user, Integer roleId, User user1) throws Exception {
        return userMapper.findAll(page, user, roleId, user1);
    }

    @Override
    public void insertUser(User user) throws Exception {
        userMapper.insert(user);
        UserRole userRole = new UserRole().setRoleId(user.getUserRole()).setUserId(user.getId());
        userRoleMapper.insert(userRole);
    }
}
