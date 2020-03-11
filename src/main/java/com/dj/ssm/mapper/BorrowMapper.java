package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Borrow;
import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * 借书记录表 chengf 持久层接口
 */
public interface BorrowMapper extends BaseMapper<Borrow> {
    /**
     * //展示 查询全部
     * @param page
     * @param borrow
     * @param roleId
     * @param book
     * @param user
     * @return
     * @throws DataAccessException
     */
    IPage<Borrow> findAll(Page<Borrow> page, @Param("borrow") Borrow borrow,
                          @Param("roleId") Integer roleId, @Param("book") Book book,
                          @Param("user") User user) throws DataAccessException;

    /**
     * 根据id查全部
     * @param id
     * @return
     * @throws DataAccessException
     */
    Borrow findByid(Integer id) throws DataAccessException;
}
