package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.ClaService;
import com.dj.ssm.service.RoleService;
import com.dj.ssm.service.UserRoleService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ClaService claService;

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
    public String toShow(Model model, @SessionAttribute(SystemConstant.SESSION_USER) User user, Integer classNum) {
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.eq("user_id", user.getId());
        UserRole userRole = userRoleService.getOne(query);
        List<Role> roleList = roleService.list();
        model.addAttribute("roleList", roleList);
        model.addAttribute("classNum", classNum);
        model.addAttribute("userRole",userRole);
        return "user/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) {
        List<UserClass> classList = claService.list(new QueryWrapper<UserClass>().eq("class_status", SystemConstant.CLASS_STATUS_NORMAL));
        model.addAttribute("classList", classList);
        return "user/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Model model, Integer id){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @RequestMapping("toAuth")
    public String toAuth(Integer id, Model model) throws Exception {
        UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", id));
        List<Role> roleList = roleService.list();
        if (null == userRole) {
            userRole = new UserRole().setUserId(id);
        }
        model.addAttribute("userRole", userRole);
        model.addAttribute("roleList", roleList);
        return "user/auth";
    }

    /**
     * 用户忘记密码页跳转  zjp
     * @return
     */
    @GetMapping("userUpdatePwd")
    public String userUpdatePwd(){
        return "user/updatepwd";
    }
}
