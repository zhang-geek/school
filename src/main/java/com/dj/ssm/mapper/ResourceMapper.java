package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> findByParentId(Integer id) throws DataAccessException;

    void updateIsDel(List<Integer> ids) throws DataAccessException;

    List<Resource> findResourse(User user) throws DataAccessException;
}
