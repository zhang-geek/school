package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.RoleResource;
import com.dj.ssm.service.ResourceService;
import com.dj.ssm.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/1 23:24
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 资源展示（有回显）
     * @param roleId
     * @return
     */
    @RequestMapping("show")
    public ResultModel<Object> show(Integer roleId) {
        try {
            List<Resource> resourceList = resourceService.list();
            if (roleId != null) {
                Wrapper<RoleResource> tWrapper = new QueryWrapper<RoleResource>().eq("role_id", roleId);
                List<RoleResource> list = roleResourceService.list(tWrapper);
                for (RoleResource roleResource : list) {
                    for (Resource resource : resourceList) {
                        if (resource.getId().equals(roleResource.getResourceId())) {
                            resource.setChecked(true);
                            break;
                        }
                    }
                }
            }
            return new ResultModel<>().success(resourceList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 左侧ztree菜单展示
     * @param resources
     * @return
     */
    @RequestMapping("showMenu")
    public ResultModel<Object> leftShow(@SessionAttribute(SystemConstant.SESSION_USER_RESOURCES) List<Resource> resources) {
        try {
            List<Resource> menuList = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource.getResourceType().equals(SystemConstant.RESOURCE_TYPE_URL)) {
                    menuList.add(resource);
                }
            }
            return new ResultModel<>().success(menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 资源删除，删除父级会同时删除所有子集
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ResultModel<Object> del(Integer id){
        try {
            List<Integer> ids = new ArrayList<>();
            ids.add(id);
            getId(ids, id);
            resourceService.removeByIds(ids);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

    /**
     * 获取父级id下的所有子级id集合
     * @param ids
     * @param id
     */
    public void getId(List<Integer> ids, Integer id){
        try {
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>().eq("parent_id", id);
            List<Resource> baseDataList = resourceService.list(queryWrapper);
            for (Resource baseData : baseDataList) {
                ids.add(baseData.getId());
                getId(ids, baseData.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 资源新增
     * @param resource
     * @return
     */
    @RequestMapping("add")
    public ResultModel<Object> add(Resource resource) {
        try {
            resource.setIsDel(SystemConstant.IS_DEL);
            resourceService.save(resource);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 资源修改
     * @param resource
     * @return
     */
    @RequestMapping("update")
    public ResultModel<Object> update(Resource resource) {
        try {
            resourceService.updateById(resource);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 资源名、url去重
     * @param resource
     * @return boolean
     */
    @RequestMapping("deDuplicate")
    public Boolean deDuplicate(Resource resource) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        if (null != resource.getId()) {
            queryWrapper.ne("id", resource.getId());
        }
        if (null != resource.getResourceName()) {
            queryWrapper.eq("resource_name", resource.getResourceName());
        }
        if (null != resource.getUrl()) {
            queryWrapper.eq("url", resource.getUrl());
        }
        Resource resource1 = resourceService.getOne(queryWrapper);
        return resource1 == null;
    }

}
