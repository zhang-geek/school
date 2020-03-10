package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Book;
import com.dj.ssm.pojo.Role;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserRole;
import com.dj.ssm.service.UserRoleService;
import com.dj.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangzk
 * @description 用户相关业务
 * @date 2020/1/29 20:49
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 登录，使用shiro
     * @param user
     * @return
     */
    @GetMapping("login")
    public ResultModel<Object> login(User user) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            subject.login(usernamePasswordToken);
            return new ResultModel<>().success();
        } catch (UnknownAccountException e) {
            return new ResultModel<>().error("用户名或密码错误");
        } catch (AuthenticationException e) {
            return new ResultModel<>().error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    @PostMapping("/list")
    public ResultModel<Object> show(User user, Integer pageNo,
                                    @SessionAttribute(SystemConstant.SESSION_USER) User user1,
                                    Integer roleId) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Page<User> page = new Page<User>()
                    .setCurrent(pageNo)
                    .setSize(SystemConstant.PAGE_SIZE);
            IPage<User> iPage = userService.findAll(page, user, roleId,user1);
            List<User> userList = iPage.getRecords();
            resultMap.put("list", userList);
            resultMap.put("totalNum", iPage.getPages());
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }


    /**
     * 增加
     */
    @PostMapping("add")
    public ResultModel<Object> add(User user){
        try {
            user.setCreateTime(new Date()).setIsDel(SystemConstant.IS_NOT_DEL)
                    .setUserStatus(SystemConstant.USER_STATUS).setIsVerify(SystemConstant.ISVERIFY);
            userService.insertUser(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    /**
     * 去重
     * @param username
     * @param id
     * @return
     */
    @PostMapping("deDuplicate")
    public Boolean deDuplicate(String username, Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (null != id) {
            queryWrapper.ne("id", id);
        }

        if (!StringUtils.isEmpty(username)) {
            queryWrapper.nested(i -> i.eq("username", username)
                    .or().eq("user_phone", username)
                    .or().eq("user_email", username));
        }
        List<User> list = userService.list(queryWrapper);
        return list.size() <= 0;
    }

    @PutMapping
    public ResultModel<Object> updateUser(User user){
        try {
            user.setUpdateTime(new Date());
            userService.updateById(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常，请稍后重试！" + e.getMessage());
        }
    }

    @PutMapping("del")
    public ResultModel<Object> del(Integer[] ids, Integer isDel) {
        try {
            userService.updateIsDel(ids, isDel);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<Object>().error("系统出错了，请稍后重试");
        }
    }

}
