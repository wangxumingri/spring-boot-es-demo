package com.wxss.springbootesdemo.base;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标记注解，被标记的类或方法需要处理返回值
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@Target({TYPE,METHOD})
@Retention(RUNTIME)
@Documented
@ResponseBody
public @interface ResponseResult {
}
