package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.BorrowMapper;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    /**
     * 展示 查询全部
     * @param borrow
     * @param page
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public IPage<Borrow> findAll(Borrow borrow, Page<Borrow> page,
                                 Integer roleId, Book book, User user) throws Exception {
        return borrowMapper.findAll(borrow, page, roleId, book, user);
    }
}
