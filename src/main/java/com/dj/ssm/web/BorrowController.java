package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 借书记录表 chengf
 */
@RestController
@RequestMapping("/borrow/")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CardService CardService;

    /**
     * 借书记录展示
     * @param nowPage
     * @param borrow
     * @param book
     * @param user
     * @return
     */
    @PostMapping("list")
    public ResultModel<Object> list(Integer nowPage, Borrow borrow, Book book,
                                    @SessionAttribute(SystemConstant.SESSION_USER) User user) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            //分页
            Page<Borrow> page = new Page()
                    .setCurrent(nowPage)
                    .setSize(SystemConstant.PAGE_SIZE);
            //查询角色 用于角色权限控制
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", user.getId());
            UserRole role = userRoleService.getOne(queryWrapper);
            IPage<Borrow> list = borrowService.findAll(page, borrow, role.getRoleId(), book, user);
            resultMap.put("list",list.getRecords());
            resultMap.put("totalPage", list.getPages());
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 还书
     * @param borrow
     * @return
     */
    @PutMapping("repay")
    public ResultModel<Object> repay(Borrow borrow){
        try {
            //根据id查出要还书籍全部信息
            Borrow borrow1 = borrowService.getById(borrow.getId());
            //已逾期并且未缴费提示：已逾期，请缴费
            if(borrow1.getPay() == null && new Date().getTime() > borrow1.getRepayTime().getTime()) {
                return new ResultModel<>().error("已逾期，请缴费后进行还书操作");
            }
            //可以还书条件:已缴费和已逾期同时满足或未逾期
            if (borrow1.getPay() != null && borrow1.getRepayTime().getTime() < new Date().getTime()
            || borrow1.getRepayTime().getTime() > new Date().getTime()) {
                //伪删除：修改状态 1：已删除
                borrowService.removeById(borrow1.getId());
                //查出图书库存
                Book book1 = bookService.getOne(new QueryWrapper<Book>()
                                        .eq("id", borrow1.getBookId()));
                //将图书库存与借书数量进行相加 并修改图书库存
                Integer count = book1.getCount() + borrow1.getNumber();
                book1.setCount(count);
                bookService.updateById(book1);
                return new ResultModel<>().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
        return null;
    }

    /**
     * 缴费
     * @param borrow
     * @return
     */
    @PostMapping("updatePay")
    public ResultModel<Object> updatePay(Borrow borrow) {
        try {
            Borrow borrow1 = borrowService.findByid(borrow.getId());
            //当前时间小于还书日期：未逾期无需缴费
            if (new Date().getTime() < borrow1.getRepayTime().getTime()) {
                return new ResultModel<Object>().error("您未逾期，无需缴费");
            }
            //当前时间大于还书日期：已逾期但已缴费无需二次缴费
            if (borrow1.getPay() != null && borrow1.getRepayTime().getTime() < new Date().getTime()) {
                return new ResultModel<Object>().error("您已经缴过费了，无需二次缴费");
            }
            //逾期金额缴费
            borrow1.setPay(borrow1.getPenalty());
            borrowService.updateById(borrow1);
            //修改校园卡金额
            BigDecimal payMoney = borrow1.getCardMoney().subtract(borrow1.getPenalty());
            Card card = CardService.getOne(new QueryWrapper<Card>()
                    .eq("user_id", borrow1.getUserId()))
                    .setCardMoney(payMoney);
            CardService.updateById(card);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试" + e.getMessage());
        }
    }
}
