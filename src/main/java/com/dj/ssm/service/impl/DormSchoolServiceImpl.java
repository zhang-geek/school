package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.DormSchoolMapper;
import com.dj.ssm.pojo.DormSchool;
import com.dj.ssm.service.DormSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DormSchoolServiceImpl extends ServiceImpl<DormSchoolMapper, DormSchool> implements DormSchoolService {

    @Autowired
    private DormSchoolMapper dormSchoolMapper;

    @Override
    public void addDormSchool(DormSchool dormSchool) {
        try {
            dormSchool.setCreateTime(new Date());
            int insert = dormSchoolMapper.insert(dormSchool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delDormSchool(Integer id) {
        try {
            DormSchool dormSchool = new DormSchool();
            dormSchool.setId(id);
            dormSchool.setIsDel(1);
            int insert = dormSchoolMapper.updateById(dormSchool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDormSchool(DormSchool dormSchool) {
        try {
            dormSchool.setCreateTime(new Date());
            int insert = dormSchoolMapper.updateById(dormSchool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DormSchool> findDormSchoolByIsDel() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_del",0);
        return dormSchoolMapper.selectList(queryWrapper);
    }
}
