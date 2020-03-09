package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.User;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 20:49
 */
public interface UserService extends IService<User> {

    IPage<User> findAll(Page<?> page, User user, Integer roleId, User user1) throws Exception;

    void insertUser(User user) throws Exception;

    void updateIsDel(Integer[] ids, Integer isDel) throws Exception;
}
