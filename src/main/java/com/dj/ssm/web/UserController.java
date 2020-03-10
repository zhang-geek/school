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
import com.dj.ssm.util.PasswordSecurityUtil;
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
@RequestMapping("/users/")
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

    @PostMapping("list")
    public ResultModel<Object> show(User user, Integer pageNo, @SessionAttribute(SystemConstant.SESSION_USER) User user1) {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Page<User> page = new Page<User>()
                    .setCurrent(pageNo)
                    .setSize(SystemConstant.PAGE_SIZE);
            UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", user1.getId()));
            IPage<User> iPage = userService.findAll(page, user, userRole.getRoleId(),user1);
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

    @PutMapping("updateUser")
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

    @PutMapping("auth")
    public ResultModel<Object> auth(UserRole userRole){
        try {
            userRoleService.updateAuth(userRole);
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


    /**
     * 重置忘记密码
     * @return
     */
    @PostMapping("updateUserPwd")
    public ResultModel<Object> updateUserPwd(String code, User user, String newPwd){
        if(StringUtils.isEmpty(code)){
            return new ResultModel<>().error("请填入验证码！！！");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return new ResultModel<>().error("请填入密码！！！");
        }
        if(StringUtils.isEmpty(newPwd)){
            return new ResultModel<>().error("请填入确认密码！！！");
        }
        if(newPwd.equals(user.getPassword())){
            return new ResultModel<>().error("两次密码不相同！！！");
        }
        if(StringUtils.isEmpty(user.getUserPhone())){
            return new ResultModel<>().error("请输入手机号");
        }
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_phone", user.getUserPhone());
            User byId = userService.getOne(queryWrapper);
            if(null == byId){
                return new ResultModel<>().error("暂无该用户！！！");
            }
            if(1 == byId.getIsDel()){
                return new ResultModel<>().error("暂无该用户！！！");
            }
            if(1 == byId.getUserStatus()){
                return new ResultModel<>().error("该用户已被冻结！！！");
            }
            // 300000 = 5分钟 验证码过期时间只有5分钟
            if(user.getCodeCreateTime().getTime() > (new Date().getTime() + 300000)){
                return new ResultModel<>().error("验证码以过期请重新获取验证码！！！");
            }
            if(!code.equals(byId.getCode())){
                return new ResultModel<>().error("验证码输入错误！！！");
            }
            byId.setPassword(user.getPassword());
            boolean b = userService.updateById(byId);
            if(b){
                return new ResultModel<>().success("修改成功");
            }
            return new ResultModel<>().error("修改失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultModel<>().error("请填入密码！！！");
    }

    /**
     * 给用户发送验证码
     * @return
     */
    @PostMapping("hairPhoneCode")
    private ResultModel<Object> hair(String userPhone){
        if(StringUtils.isEmpty(userPhone)){
            return new ResultModel<>().error("请输入手机号!!!");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", userPhone);
        User one = userService.getOne(queryWrapper);
        if(null == one){
            return new ResultModel<>().error("没有该用户!!!");
        }
        if(1 == one.getIsDel()){
            return new ResultModel<>().error("暂无该用户！！！");
        }
        if(1 == one.getUserStatus()){
            return new ResultModel<>().error("该用户已被冻结！！！");
        }
        // 验证码
        UUID uuid = UUID.randomUUID();
        String substring = uuid.toString().replace("-","").substring(18, 23);
        one.setCode(substring);
        one.setCreateTime(new Date());
        boolean b = userService.updateById(one);
        if(b){
            return new ResultModel<>().success("修改成功！！！");
        }
        return new ResultModel<>().error("修改失败，与数据库断开连接！！！");
    }
}
