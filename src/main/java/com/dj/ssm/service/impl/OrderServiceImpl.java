package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.OrderMapper;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> getOrder(Page page, User user, UserRole userRole) throws Exception {
        return orderMapper.getOrder(page, user, userRole);
    }
}
