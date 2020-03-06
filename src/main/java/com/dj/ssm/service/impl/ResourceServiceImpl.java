package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.ResourceMapper;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> findByParentId(Integer id) throws Exception {
        return resourceMapper.findByParentId(id);
    }

    @Override
    public void updateIsDel(List<Integer> ids) throws Exception {
        resourceMapper.updateIsDel(ids);
    }

    @Override
    public List<Resource> findResourse(User user) throws Exception {
        return resourceMapper.findResourse(user);
    }
}
