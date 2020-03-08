package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/borrow/")
public class BorrowPageController {
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("toShow")
    public String toShow() {
        return "borrow/show";
    }
}
