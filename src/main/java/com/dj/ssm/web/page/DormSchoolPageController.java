package com.dj.ssm.web.page;

import com.dj.ssm.pojo.DormSchool;
import com.dj.ssm.service.DormSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 页面跳转  宿舍controller
@Controller
@RequestMapping("/dormPage/")
public class DormSchoolPageController {

    @Autowired
    private DormSchoolService dormSchoolService;

    @RequestMapping("toUpdateById")
    public String toUpdateByDormId( Model model,Integer id){
        DormSchool byId = dormSchoolService.getById(id);
        model.addAttribute("byId", byId);
        return "";
    }
}
