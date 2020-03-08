package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.BaseDataMapper;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/29 20:48
 */
@Service
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements BaseDataService {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public List<BaseData> findBaseByParentId(Integer parentId) throws Exception {
        return baseDataMapper.findBaseByParentId(parentId);
    }
}
