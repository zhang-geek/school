package com.dj.ssm.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.common.ResultModel;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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


}
