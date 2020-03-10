package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Role;
import com.dj.ssm.service.RoleResourceService;
import com.dj.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 23:24
 */
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 角色展示
     * @param pageNo 当前页码
     * @return
     */
    @RequestMapping("show")
    public ResultModel<Object> show(Integer pageNo) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Page<Role> page = new Page<Role>().setSize(SystemConstant.PAGE_SIZE).setCurrent(pageNo);
            page = (Page<Role>) roleService.page(page);
            resultMap.put("list", page.getRecords());
            resultMap.put("totalNum", page.getPages());
            return new ResultModel<Object>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 角色名去重
     * @param roleName
     * @param id
     * @return
     */
    @RequestMapping("deDuplicate")
    public Boolean deDuplicate(String roleName, Integer id) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (null != id) {
            queryWrapper.ne("id", id);
        }
        Role role = roleService.getOne(queryWrapper.eq("role_name", roleName));
        return role == null;
    }

    /**
     * 角色新增
     * @param role
     * @return
     */
    @RequestMapping("add")
    public ResultModel<Object> add(Role role) {
        try {
            role.setIsDel(SystemConstant.IS_NOT_DEL);
            roleService.save(role);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 角色修改
     * @param role
     * @return
     */
    @RequestMapping("update")
    public ResultModel<Object> update(Role role) {
        try {
            roleService.updateById(role);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 角色删除（逻辑删除）
     * @param role
     * @return
     */
    @RequestMapping("updateIsDel")
    public ResultModel<Object> updateIsDel(Role role) {
        try {
            roleService.updateIsDel(role);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 关联资源
     * @param roleId 角色id
     * @param resourceIds 资源id集合
     * @return ResultModel
     */
    @RequestMapping("relatedResource")
    public ResultModel<Object> relatedResource(Integer roleId, @RequestParam("resourceIds") List<Integer> resourceIds) {
        try {
            roleResourceService.relatedResource(roleId, resourceIds);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }
}
