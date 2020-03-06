package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    List<Resource> findByParentId(Integer id) throws Exception;

    void updateIsDel(List<Integer> ids) throws Exception;

    List<Resource> findResourse(User user) throws Exception;
}
