package com.dj.ssm.web.page;

import com.dj.ssm.service.RoleService;
import com.dj.ssm.service.UserRoleService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 21:01
 */
@Controller
@RequestMapping("user")
public class UserPageController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USER");
        return "user/login";
    }

}
