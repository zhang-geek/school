package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.RoleResource;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 22:24
 */
public interface RoleResourceService extends IService<RoleResource> {

    void relatedResource(Integer roleId, List<Integer> resourceIds) throws Exception;
}
