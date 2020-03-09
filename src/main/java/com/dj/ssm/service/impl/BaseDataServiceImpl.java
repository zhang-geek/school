package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.BaseDataMapper;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.service.BaseDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/29 20:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements BaseDataService {
}
