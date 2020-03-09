package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserClass;
import com.dj.ssm.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzk
 * @description 学生班级管理
 * @date 2020/3/9 15:01
 */
@RestController
@RequestMapping("cla")
public class ClaController {

    @Autowired
    private ClaService claService;

    @PostMapping("list")
    public ResultModel<Object> show(UserClass cla, Integer pageNo) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Page<UserClass> page = new Page<UserClass>()
                    .setCurrent(pageNo)
                    .setSize(SystemConstant.PAGE_SIZE);
            QueryWrapper<UserClass> queryWrapper = new QueryWrapper<UserClass>().orderByDesc("id");
            if (null != cla.getClassNum()) {
                queryWrapper.like("class_num", cla.getClassNum());
            }
            if (null != cla.getClassStatus() && !SystemConstant.CLASS_STATUS_INVALID.equals(cla.getClassStatus())) {
                queryWrapper.like("class_status", cla.getClassStatus());
            }
            IPage<UserClass> iPage = claService.page(page, queryWrapper);
            resultMap.put("list", iPage.getRecords());
            resultMap.put("totalNum", iPage.getPages());
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    @PostMapping("add")
    public ResultModel<Object> add(UserClass cla) {
        try {
            claService.save(cla);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    @PutMapping
    public ResultModel<Object> update(UserClass cla) {
        try {
            claService.updateById(cla);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResultModel<Object> del(@RequestParam("ids[]") ArrayList<Integer> ids) {
        try {
            claService.removeByIds(ids);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }
}
