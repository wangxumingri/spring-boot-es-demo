package com.wxss.springbootesdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxss.springbootesdemo.base.ResponseResult;
import com.wxss.springbootesdemo.base.ResultBuilder;
import com.wxss.springbootesdemo.base.ResultVo;
import com.wxss.springbootesdemo.service.IEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@RestController
@RequestMapping("es")
@ResponseResult
public class EsController {
    @Autowired
    private IEsService esService;

    @PutMapping("index/create/{name}")
    public boolean createIndex(@PathVariable("name") String indexName, @RequestBody Map<String,Object> mappings) throws IOException {
        String mappingsJsonStr = JSONObject.toJSONString(mappings);
        return esService.createIndex(indexName, mappingsJsonStr);
    }

    @DeleteMapping("index/delete/{name}")
    public boolean deleteIndex(@PathVariable("name") String index) throws IOException {
        return esService.deleteIndex(index);
    }


    @PostMapping("doc/add")
    public ResultVo<String> addDocument(@RequestParam String index, @RequestBody Map<String,Object> content) throws IOException {
        return ResultBuilder.SUCCESS(esService.addDocument(index,null,content));
    }

    @GetMapping("doc/get/{id}")
    public Map<String, Object> getDocument(@RequestParam String index,@RequestParam(required = false) String type,@PathVariable("id") String docId) throws IOException {
        Map<String, Object> document = esService.getDocument(index, type, docId);
        return document;
    }

//    ResultVo<String>
    @PostMapping("doc/update/{id}")
    public boolean updateDocument(@RequestParam String index,@PathVariable("id") String docId,@RequestBody Map<String,Object> content) throws IOException {
        return esService.updateDocument(index,docId,content);
    }

    @DeleteMapping("doc/delete/{id}")
    public boolean deleteDocument(@RequestParam String index,@PathVariable("id") String docId) throws IOException {
        return esService.deleteDocument(index,docId);
    }


}
