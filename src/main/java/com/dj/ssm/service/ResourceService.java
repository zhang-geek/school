package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    List<Resource> findByParentId(Integer id) throws Exception;

    void updateIsDel(List<Integer> ids, Integer isDel) throws Exception;

    List<Resource> findResourse(User user) throws Exception;

    List<Resource> findResourceByUserId(Integer userId) throws Exception;
}
