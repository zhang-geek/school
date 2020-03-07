package com.dj.ssm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 20:54
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> findAll(Page<?> page, @Param("user") User user) throws DataAccessException;
}
