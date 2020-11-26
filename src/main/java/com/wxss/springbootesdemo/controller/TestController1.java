package com.wxss.springbootesdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: wxss
 * @Date: 2020/11/26
 * @Description:
 */
@Controller
@RequestMapping("page")
public class TestController1 {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
