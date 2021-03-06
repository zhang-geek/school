package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 借书记录表 chengf
 */
@Controller
@RequestMapping("/borrow/")
public class BorrowPageController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 借书记录展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(Model model, @SessionAttribute(SystemConstant.SESSION_USER) User user){
       //通过用户角色关系表查出当前登陆人的对应角色
        UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>()
                .eq("user_id", user.getId()));
        model.addAttribute("userRole",userRole);
        return "borrow/show";
    }

    /**
     * 去缴费
     * @return
     */
    @RequestMapping("toPay")
    public String toPay(Integer id, Model model) throws Exception {
        //根据id查出此书全部信息 用户名 书名 书本类型 当前登录人的卡上余额
        Borrow borrowList = borrowService.findByid(id);
        model.addAttribute("borrowList", borrowList);
        return "borrow/pay";
    }

}
