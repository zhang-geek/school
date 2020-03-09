package com.dj.ssm.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.Shop;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 购买商品记录
     * @param user
     * @return
     */
    @PostMapping("/list")
    public ResultModel<Object> show(@SessionAttribute(SystemConstant.SESSION_USER) User user,
                                    Integer nowPage) {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();

            Page page = new Page();
            page.setCurrent(nowPage);
            page.setSize(SystemConstant.PAGE_SIZE);
            IPage<Order> list = orderService.getOrder(page, user);
            resultMap.put("list", list.getRecords());
            resultMap.put("totalPage", list.getPages());

            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }
}
