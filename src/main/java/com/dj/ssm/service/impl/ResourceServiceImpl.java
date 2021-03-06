package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.ResourceMapper;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.ResourceMapper;
import com.dj.ssm.mapper.RoleResourceMapper;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.RoleResource;
import com.dj.ssm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public void updateIsDel(List<Integer> ids, Integer isDel) throws Exception {
        resourceMapper.updateIsDel(ids, isDel);
        roleResourceMapper.delete(new QueryWrapper<RoleResource>().in("resource_id", ids));
    }

    @Override
    public List<Resource> findByParentId(Integer id) throws Exception {
        return resourceMapper.findByParentId(id);
    }


    @Override
    public List<Resource> findResourse(User user) throws Exception {
        return resourceMapper.findResourse(user);
    }

    @Override
    public List<Resource> findResourceByUserId(Integer userId) throws Exception {
        return resourceMapper.findResourceByUserId(userId);
    }

}
