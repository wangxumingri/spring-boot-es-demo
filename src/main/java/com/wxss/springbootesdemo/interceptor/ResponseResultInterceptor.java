package com.wxss.springbootesdemo.interceptor;

import com.wxss.springbootesdemo.base.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
//@Configuration
public class ResponseResultInterceptor implements HandlerInterceptor {
    private static final String RESULT_ANNO_FLAG = "RESULT_ANNO_FLAG";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(RESULT_ANNO_FLAG,clazz.getAnnotation(ResponseResult.class));
            }else if (method.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(RESULT_ANNO_FLAG,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
