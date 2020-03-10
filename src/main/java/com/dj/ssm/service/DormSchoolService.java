package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.DormSchool;

import java.util.List;

public interface DormSchoolService extends IService<DormSchool> {


    void addDormSchool(DormSchool dormSchool);

    void delDormSchool(Integer id);

    void updateDormSchool(DormSchool dormSchool);

    List<DormSchool> findDormSchoolByIsDel();
}
