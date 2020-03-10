package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;

public interface OrderService extends IService<Order> {
    IPage<Order> getOrder(Page page, User user, UserRole userRole) throws Exception;
}
