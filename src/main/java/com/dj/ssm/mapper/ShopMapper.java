package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ShopMapper extends BaseMapper<Shop> {

    IPage<Shop> findShopAll(Page<?> page, Shop shop) throws DataAccessException;

    Shop findByShopName(String shopName) throws DataAccessException;

    void updateIsDel(@Param("shopId") Integer[] shopId) throws DataAccessException;

    void updateFlag(Shop shop) throws DataAccessException;

    Shop findShopById(Integer id) throws DataAccessException;

    void addOrder(Order order) throws DataAccessException;
}
