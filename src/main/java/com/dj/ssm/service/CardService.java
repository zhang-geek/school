package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Card;
import com.dj.ssm.pojo.User;

import java.util.List;

public interface CardService extends IService<Card> {

    /**
     * 校园卡展示
     * @param page
     * @param roleId
     * @param user
     * @return
     * @throws Exception
     */
    IPage<Card> getCard(Page page, Integer roleId, User user) throws Exception;
}
