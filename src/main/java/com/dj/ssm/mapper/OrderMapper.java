package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrderMapper extends BaseMapper<Order> {

    IPage<Order> getOrder(@Param("page") Page page,
                          @Param("user") User user) throws DataAccessException;
}
