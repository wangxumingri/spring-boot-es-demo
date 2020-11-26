package com.wxss.springbootesdemo.controller;

import com.wxss.springbootesdemo.base.ResponseResult;
import com.wxss.springbootesdemo.base.ResultBuilder;
import com.wxss.springbootesdemo.base.ResultVo;
import com.wxss.springbootesdemo.dto.TestDto;
import com.wxss.springbootesdemo.exception.IndexOperatorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@RestController
@RequestMapping("/error")
//@ResponseStatus(value = HttpStatus.OK, reason = "Java的异常")
public class TestController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/to500")
    public HashMap<String, Object> to500() throws Exception {
        int i = 1/0;
        return new HashMap<>();
    }


    @PostMapping("helloJava")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Java method 的异常")
    public HashMap<String, Object> helloJava() throws Exception {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("a",1);
        return  objectObjectHashMap;
    }

    @PostMapping("helloJavaError")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Java method 的异常")
    public HashMap<String, Object> helloJavaError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new IndexOperatorException("索引操作异常");
    }

    @GetMapping("success")
    @ResponseResult
    public HashMap<String, Object> success() throws Exception {
        return new HashMap<>();
    }

    @GetMapping("rawSuccess")
    @ResponseResult
    public ResultVo<TestDto> rawSuccess() throws Exception {
        return ResultBuilder.SUCCESS(new TestDto("返回ResultVo"));

    }

    @GetMapping("/result")
    @ResponseResult
    public ResultVo<Map<String, Object>> helloResult() {
        return ResultBuilder.SUCCESS(INFO);
    }


}
