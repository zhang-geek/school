package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Card;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CardMapper extends BaseMapper<Card> {
    /**
     * 校园卡展示
     * @param page
     * @param roleId
     * @param user
     * @return
     * @throws DataAccessException
     */
    IPage<Card> getCard(Page page, @Param("role") Integer roleId, @Param("user") User user) throws DataAccessException;
}
