package com.ocean.Interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ocean.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
public class MyInterceptor implements HandlerInterceptor{
 
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("=====>(1)在整个请求之后调用，即在dispatcherServlet渲染了对应的视图之后（主要是进行资源清理工作）");
    }
 
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("=====>(1)在请求处理之后调用，即在controller方法执行之后调用");
    }
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        System.out.println("=====>（1）在请求处理之前调用，即在Controller方法调用之前！");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(request.getRequestURI());
        if(request.getServletPath().equals("/toLogin")||request.getServletPath().equals("/user/login")||request.getServletPath().equals("/")){
           return true;
        }else {
            if(user == null){
                response.sendRedirect(request.getContextPath()+"/toLogin");
                return false;
            }
            return true;
        }
    }
 
}