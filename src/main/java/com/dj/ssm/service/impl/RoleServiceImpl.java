package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.RoleMapper;
import com.dj.ssm.mapper.RoleResourceMapper;
import com.dj.ssm.mapper.UserRoleMapper;
import com.dj.ssm.pojo.Role;
import com.dj.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/31 13:26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateIsDel(Role role) throws Exception {
        roleMapper.deleteById(role.getId());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", role.getId());
        roleResourceMapper.delete(queryWrapper);
        userRoleMapper.delete(queryWrapper);
    }
}
