package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.CardMapper;
import com.dj.ssm.pojo.Card;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {
    @Autowired
    private CardMapper cardMapper;

    /**
     * 校园卡展示
     * @param page
     * @param roleId
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public IPage<Card> getCard(Page page, Integer roleId, User user) throws Exception {
        return cardMapper.getCard(page, roleId, user);
    }
}
