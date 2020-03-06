package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Resource;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/31 13:26
 */
public interface ResourceService extends IService<Resource> {
    void updateIsDel(List<Integer> ids, Integer isDel) throws Exception;

    List<Resource> findResourceByUserId(Integer userId) throws Exception;
}
