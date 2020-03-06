package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;
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


}
