package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopService extends IService<Shop> {

    IPage<Shop> findShopAll(Page<?> page, Shop shop) throws Exception;

    Shop findByShopName(String shopName) throws Exception;

    void updateIsDel(Integer[] shopId) throws Exception;

    void updateStatus(Integer id, Integer shopStatus) throws Exception;

}
