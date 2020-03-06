package com.dj.ssm.web.page;

import com.dj.ssm.mapper.BaseDataMapper;
import com.dj.ssm.pojo.BaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopPageController {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @RequestMapping("/toShowShop")
    public String toShowShop(Model model){
        List<BaseData> baseDataList = baseDataMapper.findBaseByParentId(0);
        model.addAttribute("baseDataList", baseDataList);
        return "shop/show_shop";
    }
}
