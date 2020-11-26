package com.wxss.springbootesdemo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@RestControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger LOG  = LoggerFactory.getLogger(ResponseResultAdvice.class);

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResult.class;

    private static final String RESULT_ANNO_FLAG = "RESULT_ANNO_FLAG";

    /**
     * 判断类或者方法是否使用了 @ResponseResult
     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        boolean flag =  AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
//        return flag;
//    }
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
        HttpServletRequest currentRequest = requestAttributes.getRequest();
        ResponseResult responseResult = (ResponseResult)currentRequest.getAttribute(RESULT_ANNO_FLAG);
        return responseResult != null;
    }


    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof ResultVo) {
            return body;
        }
        return ResultBuilder.SUCCESS(body);
    }

    /**
     * 全局异常处理
     * @param e
     * @param webRequest
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultVo handlerException(Exception e, WebRequest webRequest){
        LOG.error("ResponseResultAdvice#handlerException 处理异常:{}",e.getMessage(),e);
        ResultVo<Object> failResultVo = ResultBuilder.FAIL();
        failResultVo.setMessage(e.getMessage());
        return failResultVo;
    }

    /**
     * 处理404
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVo<Object> handlerNoHandlerFoundException(Exception e, HttpServletRequest request){
        LOG.error("ResponseResultAdvice#handlerNoHandlerFoundException 处理异常:{}",e.getMessage(),e);
        ResultVo<Object> failResultVo = ResultBuilder.FAIL();
        failResultVo.setMessage(e.getMessage());
        return failResultVo;
    }

}
