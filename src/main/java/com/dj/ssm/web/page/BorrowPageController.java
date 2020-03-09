package com.dj.ssm.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/borrow/")
public class BorrowPageController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private BaseDataService baseDataService;
    @Autowired
    private UserService userService;
    @Autowired
    private  BookService bookService;

    /**
     * 借书记录展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow() {
        return "borrow/show";
    }

    /**
     * 去缴费
     * @return
     */
    @RequestMapping("toPay")
    public String toPay(Integer id, Model model) {
        //根据id查出此书全部信息
        Borrow borrow = borrowService.getById(id);
        User user = userService.getOne(new QueryWrapper<User>().eq("id", borrow.getUserId()));
        Book book = bookService.getOne(new QueryWrapper<Book>().eq("id", borrow.getBookId()));
        //图书类型
        List<Resource> typeList = resourceService.list(new QueryWrapper<Resource>()
                .eq("parent_id", SystemConstant.RESOURCE_PARENT_ID_36));
        //支付方式
        List<BaseData> payList = baseDataService.list(new QueryWrapper<BaseData>().eq("parent_id", SystemConstant.PARENT_ID_15));
        model.addAttribute("typeList", typeList);
        model.addAttribute("payList", payList);
        model.addAttribute("borrow", borrow);
        model.addAttribute("user", user);
        model.addAttribute("book", book);
        return "borrow/pay";
    }

}
