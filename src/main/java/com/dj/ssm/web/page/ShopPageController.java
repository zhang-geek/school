package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.service.BaseDataService;
import com.dj.ssm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopPageController {

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/toShowShop")
    public String toShowShop(Model model) throws Exception {
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
