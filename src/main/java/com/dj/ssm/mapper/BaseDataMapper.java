package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.BaseData;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/29 20:41
 */
public interface BaseDataMapper extends BaseMapper<BaseData> {

    List<BaseData> findBaseByParentId(Integer parentId) throws DataAccessException;
}
