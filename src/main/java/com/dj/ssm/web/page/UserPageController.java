package com.dj.ssm.web.page;

import com.dj.ssm.pojo.Role;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.RoleService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/1/29 21:01
 */
@Controller
@RequestMapping("/user/")
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USER");
        return "user/login";
    }

    /**
     * 用户展示
     * @param model
     * @return
     */
    @RequestMapping("toShow")
    public String toShow(Model model) {
        List<Role> roleList = roleService.list();
        model.addAttribute("roleList", roleList);
        return "user/show";
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Model model, Integer id){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * 用户忘记密码页跳转  zjp
     * @return
     */
    @GetMapping("userUpdatePwd")
    public String userUpdatePwd(){
        return "user/user_update_pwd";
    }
}
