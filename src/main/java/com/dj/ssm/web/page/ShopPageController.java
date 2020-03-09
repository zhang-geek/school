package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.BaseDataService;
import com.dj.ssm.service.CardService;
import com.dj.ssm.service.ShopService;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopPageController {

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/toShowShop")
    public String toShowShop(Integer id, Model model, @SessionAttribute(SystemConstant.SESSION_USER) User user) throws Exception {
        //根据用户id查询正常使用的校园卡的信息
        QueryWrapper<Card> query = new QueryWrapper<>();
        query.eq("user_id", user.getId());
        List<Card> cardList = cardService.list(query);
        for (Card card : cardList) {
            if (card.getCardStatus().equals(SystemConstant.CARD_STATUS_USE)) {
                model.addAttribute("card", card);
            }
        }

        UserRole userRole = userRoleService.findRoleById(user.getId());

        model.addAttribute("userRole", userRole);
        model.addAttribute("user", user);

        Shop shop = shopService.findShopById(id);
        model.addAttribute("shop", shop);

        List<BaseData> baseDataList = baseDataService.findBaseByParentId(1);
        model.addAttribute("baseDataList", baseDataList);
        return "shop/show_shop";
    }

    @RequestMapping("/toAddShop")
    public String toAddShop() {

        return "shop/add_shop";
    }

    @RequestMapping("/toUpdateShop")
    public String toUpdate(Integer id, Model model){

        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Shop shop = shopService.getOne(queryWrapper);
        model.addAttribute("shop", shop);
        return "shop/update_shop";
    }
}
