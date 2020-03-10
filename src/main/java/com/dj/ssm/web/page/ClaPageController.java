package com.dj.ssm.web.page;

import com.dj.ssm.pojo.UserClass;
import com.dj.ssm.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/3/9 15:05
 */
@Controller
@RequestMapping("cla")
public class ClaPageController {

    @Autowired
    private ClaService claService;

    @RequestMapping("toShow")
    public String toShow() {
        return "user_class/show";
    }
    @RequestMapping("toAdd")
    public String toAdd() {
        return "user_class/add";
    }
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id, Model model) {
        UserClass cla = claService.getById(id);
        model.addAttribute("cla", cla);
        return "user_class/update";
    }
}
