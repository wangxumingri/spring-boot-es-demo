package com.wxss.springbootesdemo.service;

import java.io.IOException;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
public interface IEsService {
    /**
     * 创建索引
     * @param indexName
     * @param mappingJsonStr
     * @return
     */
    boolean createIndex(String indexName,String mappingJsonStr) throws IOException;

    /**
     * 根据索引名称删除索引库
     * @param indexName
     * @return
     */
    boolean deleteIndex(String indexName) throws IOException;

}
