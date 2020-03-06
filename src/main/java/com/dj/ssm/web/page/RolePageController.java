package com.dj.ssm.web.page;


import com.dj.ssm.pojo.Role;
import com.dj.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 23:26
 */
@Controller
@RequestMapping("role")
public class RolePageController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("toShow")
    public String toShow() {
        return "role/show";
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "role/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Model model, Integer id) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/update";
    }
    @RequestMapping("toRelatedResource")
    public String toRelatedResource(Model model, Integer id) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/related_resource";
    }
}
