package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.UserRole;
import org.springframework.dao.DataAccessException;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    void updateByUserId(UserRole userRole) throws Exception;

    UserRole findRoleById(Integer id) throws DataAccessException;

}
