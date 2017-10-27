package com.ztel.app.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SystemSessionInterceptor implements HandlerInterceptor {  
    private static final String LOGIN_URL="/sessionoutRun";  
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
          
  
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    	//System.out.println(">>>afterCompletion>>>>>>>在整个请求结束之后被调用）");
    }  
  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
        HttpSession session=request.getSession(true);  
        //System.out.println(">>>preHandle>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        //session中获取用户名信息  
        Object obj = session.getAttribute("userVo");  
        if (obj==null||"".equals(obj.toString())) {  
            response.sendRedirect(request.getSession().getServletContext().getContextPath()+LOGIN_URL);
                 return false;
           }
           return true;
     }
}