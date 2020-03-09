package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.BorrowService;
import com.dj.ssm.service.OrderService;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrow/")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("list")
    public ResultModel<Object> list(Borrow borrow, Integer nowPage, Book book,
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
            IPage<Borrow> list = borrowService.findAll(borrow, page, role.getRoleId(), book, user);
            resultMap.put("list",list.getRecords());
            resultMap.put("totalPage", list.getPages());
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }
}
