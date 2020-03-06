package com.dj.ssm.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangzk
 * @description index页面跳转
 * @date 2020/1/29 21:02
 */
@Controller
@RequestMapping("index")
public class IndexPageController {

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index/index";
    }
    @RequestMapping("toTop")
    public String toTop() {
        return "index/top";
    }
    @RequestMapping("toLeft")
    public String toLeft() {
        return "index/left";
    }
    @RequestMapping("toRight")
    public String toRight() {
        return "index/right";
    }
    @RequestMapping("to403")
    public String to403() {
        return "index/403";
    }

}
