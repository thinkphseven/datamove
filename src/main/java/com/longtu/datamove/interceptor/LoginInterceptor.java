package com.longtu.datamove.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Title: LoginInterceptor
 * @description:
 * @author: hk
 * @date: 2021-05-20 11:55
 **/
@Component
public class LoginInterceptor  implements HandlerInterceptor {

    @Autowired
    HttpSession httpSession;
    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       // System.out.println("开始拦截.........");
        //业务代码
        if (httpSession.getAttribute("user") == null) {
//            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            return true;
        }
        return true;
    }
}