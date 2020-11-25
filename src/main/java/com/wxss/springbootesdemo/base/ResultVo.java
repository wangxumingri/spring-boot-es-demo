package com.wxss.springbootesdemo.base;

import java.io.Serializable;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
public class ResultVo<T> implements Serializable {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    public ResultVo() {
    }

    public ResultVo(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.success = resultCode.getSuccess();
    }


    public ResultVo(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
