package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {
    //展示 查询全部
    IPage<Borrow> findAll(@Param("borrow") Borrow borrow, Page<Borrow> page,
                          @Param("roleId") Integer roleId, @Param("book") Book book,
                          @Param("user") User user) throws DataAccessException;
}
