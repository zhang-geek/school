package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    void updateByUserId(UserRole userRole) throws Exception;

}
