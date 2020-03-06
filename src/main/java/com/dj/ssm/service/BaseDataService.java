package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.BaseData;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/29 20:46
 */
public interface BaseDataService extends IService<BaseData> {

    List<BaseData> findBaseByParentId(Integer parentId) throws Exception;

}
