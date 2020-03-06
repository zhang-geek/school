package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.RoleResourceMapper;
import com.dj.ssm.pojo.RoleResource;
import com.dj.ssm.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:26
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public void relatedResource(Integer roleId, List<Integer> resourceIds) throws Exception {
        roleResourceMapper.delete(new QueryWrapper<RoleResource>().eq("role_id", roleId));
        if (null == resourceIds || resourceIds.size() <= 0) {
            return;
        }
        roleResourceMapper.addResource(roleId,resourceIds);

    }
}
