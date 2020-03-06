package com.dj.ssm.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.ResourceService;
import com.dj.ssm.service.UserRoleService;
import com.dj.ssm.service.UserService;
import com.dj.ssm.util.PasswordSecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/16 13:03
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        List<Resource> resources = (List<Resource>) session.getAttribute(SystemConstant.SESSION_USER_RESOURCES);
        // 创建简单授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Resource resource : resources) {
            simpleAuthorizationInfo.addStringPermission(resource.getUrl());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            String username = (String) authenticationToken.getPrincipal();
            String password = new String((char[]) authenticationToken.getCredentials());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.or(i -> i.eq("username", username)
                    .or().eq("user_email", username)
                    .or().eq("user_phone", username));
            User user1 = userService.getOne(queryWrapper);
            if (user1 == null || !PasswordSecurityUtil.checkPassword(password, user1.getPassword(), user1.getSalt())) {
                return null;
            }
            if (user1.getIsDel().equals(SystemConstant.IS_DEL)) {
                throw new DisabledAccountException("用户已被删除，请联系管理员~");
            }
            user1.setLastLoginTime(new Date());
            userService.updateById(user1);
            List<Resource> resources = resourceService.findResourceByUserId(user1.getId());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(SystemConstant.SESSION_USER, user1);
            session.setAttribute(SystemConstant.SESSION_USER_RESOURCES, resources);
            return new SimpleAuthenticationInfo(username, password, getName());
        } catch (AuthenticationException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
