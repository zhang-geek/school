package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.BookService;
import com.dj.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;

    /**
     * 图书展示
     * @param book
     * @param nowPage
     * @return
     */
    @PostMapping("/list")
    public ResultModel<Object> show(Book book, Integer nowPage) {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();

            Page page = new Page();
            page.setCurrent(nowPage);
            page.setSize(SystemConstant.PAGE_SIZE);
            IPage<Book> list = bookService.getBook(page, book);
            resultMap.put("list", list.getRecords());
            resultMap.put("totalPage", list.getPages());

            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 图书去重
     * @param book
     * @return
     */
    @PostMapping("distinct")
    public Boolean distinct (Book book) {
        QueryWrapper<Book> query = new QueryWrapper<>();
        if (null != book.getId()) {
            query.ne("id", book.getId());
        }
        query.eq("book_name", book.getBookName());
        Book book1 = bookService.getOne(query);
        return null != book1 ? false : true;
    }


    /**
     * 图书新增
     * @param user
     * @param book
     * @return
     */
    @PostMapping("save")
    public ResultModel<Object> save(@SessionAttribute(SystemConstant.SESSION_USER) User user, Book book) {
        try {
            book.setStatus(SystemConstant.BOOK_STATUS_0).setIsDel(SystemConstant.IS_NOT_DEL)
                    .setUserId(user.getId());
            bookService.save(book);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 图书伪删除
     * @param ids
     * @param isDel
     * @return
     */
    @DeleteMapping("delete")
    public ResultModel<Object> delete(Integer[] ids, Integer isDel) {
        try {
            bookService.updateIsDel(ids, isDel);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 图书上下架以及修改
     * @param book
     * @return
     */
    @PutMapping("update")
    public ResultModel<Object> updateStatus(Book book) {
        try {
            bookService.updateById(book);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 置顶与取消置顶
     * @param book
     * @param top
     * @return
     */
    @PutMapping("top")
    public ResultModel<Object> updateTop(Book book, Integer top) {
        try {
            if (top == 0) {
                book.setTopTime(new Date());
            } else {
                book.setTopTime(null);
            }
            bookService.updateTop(book);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 借书 chengf
     * @param book
     * @param borrow
     * @param user
     * @return
     */
    @PostMapping("borrow")
    public ResultModel<Object> borrow(Book book, Borrow borrow,
                                           @SessionAttribute(SystemConstant.SESSION_USER) User user){
        try {
            //输入框不能为空
            if (StringUtils.isEmpty(book.getCount())) {
                return new ResultModel<Object>().error("请输入你要借几本书");
            }
            //查询当前书本的全部信息
            Book book1 = bookService.getById(book.getId());
            //库存不足的判断
            if (book1.getCount().equals(SystemConstant.COUNT_0 )|| book1.getCount() < book.getCount()) {
                return new ResultModel<Object>().error("库存不足，请联系图书管理员");
            }
            //时间加减
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());//设置起时间
            cal.add(Calendar.DATE, 15);
            System.out.println("输出::"+cal.getTime());
            //新增借书记录表
            borrow.setBookId(book.getId())
                    .setUserId(user.getId())
                    .setType(book1.getType())
                    .setBorrowTime(new Date())
                    .setNumber(book.getCount())
                    .setRepayTime(cal.getTime())
                    .setAuthor(book1.getAuthor())
                    .setIsDel(SystemConstant.IS_NOT_DEL);
            borrowService.save(borrow);
            //图书库存对应减少
            Integer count = book1.getCount() - borrow.getNumber();
            book.setCount(count);
            bookService.updateById(book);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

}
