package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BookMapper extends BaseMapper<Book> {
    IPage<Book> getBook(@Param("page") Page page, @Param("book") Book book,
                        @Param("startTime") String startTime,
                        @Param("endTime") String endTime) throws DataAccessException;
    void updateIsDel(@Param("ids") Integer[] ids, @Param("isDel") Integer isDel) throws DataAccessException;

    void updateTop(Book book) throws DataAccessException;
}
