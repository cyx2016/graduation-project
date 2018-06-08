package com.cyx.common;

import com.cyx.controller.NewsKindController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器1
 *
 * @author   陈宇祥
 * @myblog
 * @create    2018年05月25日
 */

public class MyInterceptor1 implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor1.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        boolean flag = true;

        //Object obj=
        if(null ==request.getSession()){
            logger.info("跳转到登录页面...");
            response.sendRedirect(request.getContextPath()+"/login.html");
            return false;
        }else if(null == (Object) request.getSession().getAttribute("userrole")){
            logger.info("跳转到登录页面...");
            response.sendRedirect(request.getContextPath()+"/login.html");
            flag = false;
        }

        return flag;// 只有返回true才会继续向下执行，返回false取消当前请求
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
