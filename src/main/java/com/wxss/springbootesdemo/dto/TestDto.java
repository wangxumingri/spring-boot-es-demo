package com.wxss.springbootesdemo.dto;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
public class TestDto {
    private String name;

    public TestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
