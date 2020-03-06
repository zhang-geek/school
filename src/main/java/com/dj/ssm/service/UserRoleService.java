package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.UserRole;

<<<<<<< HEAD

public interface UserRoleService extends IService<UserRole> {

    void updateByUserId(UserRole userRole) throws Exception;
=======
/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:24
 */
public interface UserRoleService extends IService<UserRole> {
    void updateAuth(UserRole userRole) throws Exception;
>>>>>>> 6740e5e7713c9616241224f5487f40fad38db3c6
}
