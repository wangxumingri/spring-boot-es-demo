package com.wxss.springbootesdemo.service;

import java.io.IOException;
import java.util.Map;

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

    /**
     * 添加文档
     * @param index
     * @param type
     * @param docContent
     */
    String addDocument(String index, String type,Map<String, Object> docContent) throws IOException;

    /**
     * 查询文档
     * @param index
     * @param type
     * @param docId
     * @return
     * @throws IOException
     */
    Map<String,Object> getDocument(String index,String type,String docId) throws IOException;

    /**
     * 更新文档
     * @param index
     * @param docId
     * @param content
     * @return
     */
    boolean updateDocument(String index,String docId,Map<String,Object> content) throws IOException;

    /**
     * 删除文档
     * @param index
     * @param docId
     * @return
     */
    boolean deleteDocument(String index,String docId) throws IOException;
}
