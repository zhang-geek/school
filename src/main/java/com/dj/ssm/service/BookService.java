package com.dj.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.Book;

public interface BookService extends IService<Book>{
    /**
     * 图书列表
     * @param page
     * @param book
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    IPage<Book> getBook(Page page, Book book, String startTime, String endTime) throws Exception;

    /**
     * 批量删
     * @param ids
     * @param isDel
     * @throws Exception
     */
    void updateIsDel(Integer[] ids, Integer isDel) throws Exception;

    /**
     * 置顶
     * @param book
     * @throws Exception
     */
    void updateTop(Book book) throws Exception;
}
