package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.UserRole;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:24
 */
public interface UserRoleService extends IService<UserRole> {
    void updateAuth(UserRole userRole) throws Exception;
}
