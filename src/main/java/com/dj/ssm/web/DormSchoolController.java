package com.dj.ssm.web;

import com.dj.ssm.common.ResultModel;
import com.dj.ssm.pojo.DormSchool;
import com.dj.ssm.service.DormSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 宿舍逻辑controller
@RestController
@RequestMapping("/dorm/")
public class DormSchoolController {

    @Autowired
    private DormSchoolService dormSchoolService;

    /**
     * 添加宿舍
     * @param dormSchool
     * @return
     */
    @PostMapping("addDormSchool")
    public ResultModel<Object> show(DormSchool dormSchool) {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            dormSchoolService.addDormSchool(dormSchool);
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 删除宿舍
     * @param id
     * @return
     */
    @PostMapping("delDormSchool")
    public ResultModel<Object> show(Integer id) {
        try {
            if (StringUtils.isEmpty(id)){
                throw new Exception("error");
            }
            Map<String, Object> resultMap = new HashMap<String, Object>();
            dormSchoolService.delDormSchool(id);
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 修改宿舍信息
     * @param dormSchool
     * @return
     */
    @PostMapping("updateDormSchool")
    public ResultModel<Object> updateDormSchool(DormSchool dormSchool) {
        try {
            if (StringUtils.isEmpty(dormSchool.getId())){
                throw new Exception("error");
            }
            Map<String, Object> resultMap = new HashMap<String, Object>();
            dormSchoolService.updateDormSchool(dormSchool);
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 查询所有未删除的宿舍
     * @return
     */
    @PostMapping("findDormSchoolByIsDel")
    public ResultModel<Object> findDormSchoolByIsDel() {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            List<DormSchool> dormSchools = dormSchoolService.findDormSchoolByIsDel();
            resultMap.put("rows", dormSchools);
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

}
