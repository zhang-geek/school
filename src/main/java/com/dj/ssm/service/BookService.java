package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Book;

public interface BookService extends IService<Book>{
    IPage<Book> getBook(Page page, Book book, String startTime, String endTime) throws Exception;

    void updateIsDel(Integer[] ids, Integer isDel) throws Exception;

    void updateTop(Book book) throws Exception;
}
