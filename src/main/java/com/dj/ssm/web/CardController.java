package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Card;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.CardService;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzy
 */
@RestController
@RequestMapping("/card/")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("toFindCard")
    public ResultModel<Object> show(@SessionAttribute(SystemConstant.SESSION_USER) User user) {
        QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
        cardQueryWrapper.eq("user_id", user.getId())
                .eq("card_status", SystemConstant.CARD_STATUS_USE);
        Card card = cardService.getOne(cardQueryWrapper);
        if (card != null) {
            return new ResultModel<Object>().error("您已办理过了！");
        }
        return new ResultModel<Object>().success("正在去办理的路上");
    }

    @PostMapping("show")
    public ResultModel<Object> show(Integer pageNo, @SessionAttribute(SystemConstant.SESSION_USER) User user) {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Page page = new Page();
            page.setCurrent(pageNo);
            page.setSize(SystemConstant.PAGE_SIZE);
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", user.getId());
            UserRole role = userRoleService.getOne(userRoleQueryWrapper);
            IPage<Card> list = cardService.getCard(page, role.getRoleId(), user);
            resultMap.put("cardList", list.getRecords());
            resultMap.put("totalPage", list.getPages());
            resultMap.put("userRole", role.getRoleId());
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    @PostMapping("addCard")
    public ResultModel<Object> addCard(Card card, @SessionAttribute(SystemConstant.SESSION_USER) User user){
        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        card.setUserId(user.getId())
                .setCreateTime(new Date())
                .setCardMoney(card.getCardMoney().subtract(SystemConstant.CARD_MONEY_TWO))
                .setCardNumber("DJ"+dateStr+user.getId());
        cardService.save(card);
        return new ResultModel<>().success("办理成功");
    }

    @PostMapping("updateCard")
    public ResultModel<Object> updateCard(Card card, BigDecimal cardMoneyOld){
        card.setCardMoney(card.getCardMoney().add(cardMoneyOld));
        cardService.updateById(card);
        return new ResultModel<>().success("OK");
    }



}
