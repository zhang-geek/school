package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.User;

public interface OrderService extends IService<Order> {
    IPage<Order> getOrder(Page page, User user) throws Exception;
}
