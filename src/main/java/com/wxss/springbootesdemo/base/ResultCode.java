package com.wxss.springbootesdemo.base;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
public enum ResultCode {
    SUCCESS(2000, "处理成功", true),
    SYSTEM_ERROR(5000,"系统异常，请稍后重试",false),
    PARAM_INVALID(4000,"参数非法，请检查后重试",false),
    RESOURCE_NOT_FOUND(4004,"资源不存在",false);


    private Integer code;
    private String message;
    private Boolean success;

    ResultCode(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }
}
