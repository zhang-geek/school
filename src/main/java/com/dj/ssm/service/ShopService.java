package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Shop;

public interface ShopService extends IService<Shop> {

    IPage<Shop> findShopAll(Page<?> page, Shop shop) throws Exception;
}
