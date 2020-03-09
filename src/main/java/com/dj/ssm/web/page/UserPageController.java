package com.dj.ssm.web.page;

import com.dj.ssm.pojo.Role;
import com.dj.ssm.service.RoleService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    private RoleService roleService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 用户展示
     * @param model
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(Model model, Integer classNum) {
        List<Role> roleList = roleService.list();
        model.addAttribute("roleList", roleList);
        model.addAttribute("classNum", classNum);
        return "user/show";
    }
}
