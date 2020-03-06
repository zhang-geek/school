package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.ShopMapper;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public IPage<Shop> findShopAll(Page<?> page, Shop shop) throws Exception {
        return shopMapper.findShopAll(page, shop);
    }
}
