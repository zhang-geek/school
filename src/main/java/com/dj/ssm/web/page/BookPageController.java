package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.BookService;
import com.dj.ssm.service.BorrowService;
import com.dj.ssm.service.ResourceService;
import com.dj.ssm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookPageController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BorrowService borrowService;

    @RequestMapping("toShow")
    public String toShow(Integer id, Model model,
                         @SessionAttribute(SystemConstant.SESSION_USER) User user){
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.eq("user_id", user.getId());
        UserRole userRole = userRoleService.getOne(query);
        model.addAttribute("type", id);
        model.addAttribute("userRole",userRole);
        return "book/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model){
        QueryWrapper<Resource> query = new QueryWrapper<>();
        query.eq("parent_id", SystemConstant.RESOURCE_PARENT_ID_36);
        List<Resource> resourseList = resourceService.list(query);
        model.addAttribute("resourseList", resourseList);
        return "book/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Model model, Integer id){
        Book book = bookService.getById(id);
        QueryWrapper<Resource> query = new QueryWrapper<>();
        query.eq("parent_id", SystemConstant.RESOURCE_PARENT_ID_36);
        List<Resource> resourseList = resourceService.list(query);
        model.addAttribute("resourseList", resourseList);
        model.addAttribute("book", book);
        return "book/update";
    }

    /**
     * 去借书 chengf
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toBorrow")
    public String toBorrow(Integer id, Model model) {
        //根据书得id查询书得全部信息
        Book book = bookService.getById(id);
        List<Resource> resourseList = resourceService.list(new QueryWrapper<Resource>().eq("parent_id", SystemConstant.RESOURCE_PARENT_ID_36));
        model.addAttribute("resourseList", resourseList);
        model.addAttribute("book", book);
        return "book/borrow";
    }
}
