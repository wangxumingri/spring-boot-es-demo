package com.wxss.springbootesdemo.base;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
public class ResultBuilder<T> {
    private ResultBuilder(){

    }

    public static <T> ResultVo<T> SUCCESS(){
        return new ResultVo<>(ResultCode.SUCCESS,null);
    }

    public static <T> ResultVo<T> SUCCESS(T data){
        return new ResultVo<>(ResultCode.SUCCESS,data);
    }

    public static <T> ResultVo<T> SUCCESS(ResultCode resultCode,T data){
        return new ResultVo<>(resultCode,data);
    }

    public static <T> ResultVo<T> FAIL(){
        return new ResultVo<>(ResultCode.SYSTEM_ERROR);
    }

    public static <T> ResultVo<T> FAIL(ResultCode resultCode){
        return new ResultVo<>(resultCode);
    }
}
