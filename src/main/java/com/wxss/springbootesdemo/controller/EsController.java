package com.wxss.springbootesdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxss.springbootesdemo.service.IEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@RestController
@RequestMapping("es")
public class EsController {
    @Autowired
    private IEsService esService;

    @PutMapping("index/create/{indexName}")
    public boolean createIndex(@PathVariable("indexName") String indexName, Map<String,Object> mappings) throws IOException {
        String mappingsJsonStr = JSONObject.toJSONString(mappings);
        return esService.createIndex(indexName, mappingsJsonStr);
    }
}
