package com.bite.book.interceptor;

import com.bite.book.constant.Constants;
import com.bite.book.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //true  表示放行   false表示拦截
        log.info("LoginInterceptor preHandle....");
        //获取session, 并且判断session中存储的userinfo信息是否为空
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.USER_SESSION_KEY);
        if (userInfo==null || userInfo.getId()<=0){
            //用户未登录
            response.setStatus(401);
            return false;
        }
        return true;
    }

}
