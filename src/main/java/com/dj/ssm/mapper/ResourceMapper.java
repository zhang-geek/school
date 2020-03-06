package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/31 13:28
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    void updateIsDel(@Param("ids") List<Integer> ids, @Param("isDel") Integer isDel) throws DataAccessException;

    List<Resource> findResourceByUserId(Integer userId) throws DataAccessException;
}
