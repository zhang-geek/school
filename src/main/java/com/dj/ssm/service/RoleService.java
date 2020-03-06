package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Role;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/31 13:25
 */
public interface RoleService extends IService<Role> {
    void updateIsDel(Role role) throws Exception;
}
