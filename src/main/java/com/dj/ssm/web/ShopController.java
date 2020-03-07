package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResultModel<Object> show(Shop shop, Integer pageNo, HttpSession session) {
        try {
            session.setAttribute("shop", shop);
            Map<String, Object> resultMap = new HashMap<>();
            Page<Shop> page = new Page<Shop>()
                    .setCurrent(pageNo)
                    .setSize(2);
            IPage<Shop> page1 = shopService.findShopAll(page, shop);
            List<Shop> shopList = page1.getRecords();
            resultMap.put("totalNum", page1.getPages());
            resultMap.put("shopList", shopList);
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }

    @PostMapping("/addShop")
    public ResultModel<Object> sddShop(Shop shop) {
        try {

            shopService.save(shop);
            return new ResultModel<>().success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }

    @RequestMapping("/findByShopName")
    public boolean findByShopName(String shopName) {
        try {
            Shop shop = shopService.findByShopName(shopName);
            return shop == null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/updateShop")
    public ResultModel<Object> update(Shop shop){
        try {
            shopService.updateById(shop);
            return new ResultModel<>().success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }


    @DeleteMapping("/delShop")
    public ResultModel<Object> delBook(Integer[] shopId) {
        try {

            shopService.updateIsDel(shopId);

            return new ResultModel<>().success("删除成功");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error("异常" + e.getMessage());
        }
    }

    @PutMapping("/updateStatus")
    public ResultModel<Object> updateStatus(Shop shop){
        try {

            shopService.updateById(shop);
            return new ResultModel<>().success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }
}
