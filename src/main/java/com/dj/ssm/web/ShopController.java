package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Card;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.CardService;
import com.dj.ssm.service.OrderService;
import com.dj.ssm.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @Autowired
    private ShopService shopService;

    @Autowired
    private CardService cardService;

    /**
     * 商品展示
     * @param shop
     * @param pageNo
     * @param session
     * @return
     */
    @PostMapping
    public ResultModel<Object> show(Shop shop, Integer pageNo, HttpSession session) {
        try {
            session.setAttribute("shop", shop);
            Map<String, Object> resultMap = new HashMap<>();
            Page<Shop> page = new Page<Shop>()
                    .setCurrent(pageNo)
                    .setSize(SystemConstant.PAGE_SIZE);
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

    /**
     * 添加商品
     * @param shop
     * @return
     */
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

    /**
     * 商品名称去重
     * @param shopName
     * @return
     */
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

    /**
     * 修改商品
     * @param shop
     * @return
     */
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

    /**
     * 商品伪删除
     * @param shopId
     * @return
     */
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

    /**
     * 修改商品状态
     * @param shop
     * @return
     */
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

    /**
     * 商品置顶
     * @param shop
     * @return
     */
    @PutMapping("/updateFlag")
    public ResultModel<Object> updateFlag(Shop shop) {
        try {

            shop.setTopTime(shop.getFlag() == SystemConstant.SHOP_STICK ? new Date() : null);
            shopService.updateFlag(shop);

            return new ResultModel<>().success("冲向第一");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }

    /**
     * 购买商品
     * @param id
     * @param user
     * @return
     */
    @PostMapping("/addOrder")
    public ResultModel<Object> addOrder(Integer id,
                                        @SessionAttribute(SystemConstant.SESSION_USER) User user){
        try {

            Shop shop = shopService.findShopById(id);

            //从校园卡里扣费
            QueryWrapper<Card> query = new QueryWrapper<>();
            query.eq("user_id", user.getId());
            List<Card> cardList = cardService.list(query);

            for (Card card : cardList) {
                if (card.getCardStatus().equals(SystemConstant.CARD_STATUS_USE)) {
                    card.setCardMoney(card.getCardMoney().subtract(shop.getShopPrice()));
                    cardService.updateById(card);
                }
            }
            //生成订单，存入订单表
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            Order order =  new Order();
            order.setShopId(shop.getId())
                    .setUserId(user.getId())
                    .setShopPrice(shop.getShopPrice())
                    .setOrderNum("WM"+date+user.getId());
            shopService.addOrder(order);

            return new ResultModel<>().success("购买成功,请查看余额");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("系统异常" + e.getMessage());
        }
    }




}
