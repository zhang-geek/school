package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.BookService;
import com.dj.ssm.service.CardService;
import com.dj.ssm.service.ResourceService;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/card/")
public class CardPageController {

    @Autowired
    private CardService cardService;

    @RequestMapping("toShow")
    public String toShow(){
        return "card/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "card/add";
    }

    @RequestMapping("toAddMoney")
    public String toAddMoney(Model model, @SessionAttribute(SystemConstant.SESSION_USER) User user){
        QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
        cardQueryWrapper.eq("user_id", user.getId())
                .eq("card_status", SystemConstant.CARD_STATUS_USE);
        Card card = cardService.getOne(cardQueryWrapper);
        model.addAttribute("card", card);
        return "card/add_money";
    }




}
