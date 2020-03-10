package com.dj.ssm.shiro;


import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzk
 * @description shiro配置
 * @date 2020/2/16 13:05
 */
@Configuration
@DependsOn("shiroRealm")
public class ShiroConfiguration {

    /**
     * 自定义域
     */
    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * 安全管理器
     * @return 安全管理器
     */
    @Bean
    DefaultSecurityManager securityManager() {
        DefaultWebSecurityManager securutyManager = new DefaultWebSecurityManager();
        securutyManager.setRealm(shiroRealm);
        return securutyManager;
    }

    /**
     * shiro过滤器工厂
     * @param securityManager
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录失败的url
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        //登陆成功的url
        shiroFilterFactoryBean.setSuccessUrl("/index/toIndex");
        //无权限的url
        shiroFilterFactoryBean.setUnauthorizedUrl("index/to403");
        Map<String, String> filters = new HashMap<>();
        //无需认证的url
        filters.put("/static/**", "anon");
        filters.put("/users/login", "anon");
        filters.put("/user/toAdd", "anon");
        filters.put("/users/add", "anon");
        filters.put("/users/deDuplicate", "anon");
        //退出的url
        filters.put("/logout", "logout");
        //需要认证的url
        filters.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }

}
