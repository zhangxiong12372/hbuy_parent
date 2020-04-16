package com.java.service;

import com.java.model.ProductSolr;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-04-13 10:38
 * @Description:
 */
public interface ProductSolrService {
    //mysql中数据添加到solr中
    void addDateFromMysqlToSolr() throws Exception;
    //根据条件加载solr中数据
    List<ProductSolr> findProductBySolr(String solrPara) throws Exception;
}
