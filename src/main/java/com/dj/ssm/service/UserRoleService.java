package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.UserRole;


public interface UserRoleService extends IService<UserRole> {

    void updateByUserId(UserRole userRole) throws Exception;

    void updateAuth(UserRole userRole) throws Exception;
}
