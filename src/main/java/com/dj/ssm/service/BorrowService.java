package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;

/**
 * 借书记录表 chengf 控制层接口
 */
public interface BorrowService extends IService<Borrow> {
    //展示 查询全部
    IPage<Borrow> findAll(Page<Borrow> page, Borrow borrow,
                          Integer roleId, Book book, User user) throws Exception;
    //根据id查全部
    Borrow findByid(Integer id) throws Exception;
}
