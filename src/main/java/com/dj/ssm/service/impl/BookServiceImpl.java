package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.BookMapper;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> getBook(Page page, Book book, String startTime, String endTime) {
        return bookMapper.getBook(page, book, startTime, endTime);
    }

    @Override
    public void updateIsDel(Integer[] ids, Integer isDel) throws Exception {
        bookMapper.updateIsDel(ids, isDel);
    }


    @Override
    public void updateTop(Book book) throws Exception {
        bookMapper.updateTop(book);
    }
}
