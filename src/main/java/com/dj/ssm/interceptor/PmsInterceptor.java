package com.dj.ssm.interceptor;

import com.dj.ssm.common.SystemConstant;
import com.dj.ssm.pojo.Resource;
import com.dj.ssm.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangzk
 * @description TODO
 * @date 2020/2/4 15:26
 */
@Component
public class PmsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SystemConstant.SESSION_USER);
        List<Resource> resources = (List<Resource>) session.getAttribute(SystemConstant.SESSION_USER_RESOURCES);
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        for (Resource resource : resources) {
            if (uri.equals(ctx + resource.getUrl())) {
                return true;
            }
        }
        response.sendRedirect(ctx + "/index/to403");
        return false;
    }
}
