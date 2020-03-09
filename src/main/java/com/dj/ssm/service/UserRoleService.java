package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.UserRole;
import org.springframework.dao.DataAccessException;


public interface UserRoleService extends IService<UserRole> {

    void updateByUserId(UserRole userRole) throws Exception;

    void updateAuth(UserRole userRole) throws Exception;

    UserRole findRoleById(Integer id) throws DataAccessException;
}
