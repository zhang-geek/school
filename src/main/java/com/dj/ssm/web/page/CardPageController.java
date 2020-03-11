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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/card/")
public class CardPageController {

    @Autowired
    private CardService cardService;

    /**
     * 去展示页面
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(){
        return "card/show";
    }

    /**
     * 去办理页面
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "card/add";
    }

    /**
     * 去充值页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toAddMoney/{id}")
    public String toAddMoney(Model model, @PathVariable Integer id){
        QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
        cardQueryWrapper.eq("id", id);
        Card card = cardService.getOne(cardQueryWrapper);
        model.addAttribute("card", card);
        return "card/add_money";
    }


}
