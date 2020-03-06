package com.dj.ssm.web.page;

import com.dj.ssm.pojo.Resource;
import com.dj.ssm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 23:27
 */
@Controller
@RequestMapping("resource")
public class ResourcePageController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("toShow")
    public String toShow() {
        return "resource/show";
    }
    @RequestMapping("toAdd")
    public String toAdd(Integer parentId, Model model) {
        Resource resource = resourceService.getById(parentId);
        model.addAttribute("resource", resource);
        model.addAttribute("parentId", parentId);
        return "resource/add";
    }
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id, Model model) {
        Resource resource = resourceService.getById(id);
        model.addAttribute("resource", resource);
        return "resource/update";
    }
}
