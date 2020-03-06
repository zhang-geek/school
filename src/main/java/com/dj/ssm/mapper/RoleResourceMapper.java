package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.RoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/31 13:27
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    void addResource(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> resourceIds) throws DataAccessException;
}
