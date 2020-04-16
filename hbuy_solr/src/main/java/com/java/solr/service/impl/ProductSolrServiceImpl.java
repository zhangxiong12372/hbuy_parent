package com.java.solr.service.impl;

import com.java.model.ProductSolr;
import com.java.service.ProductSolrService;
import com.java.solr.mapper.ProductMapper;
import com.java.solr.model.Student;
import com.java.solr.utils.SolrUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ProductSolrServiceImpl implements ProductSolrService {

    //依赖Mapper代理对象
    @Resource
    private ProductMapper productMapper;

    //获取solr链接对象
    private HttpSolrClient solr = SolrUtil.getSolr();

    //将mysql中的商品数据添加到solr中
   @Scheduled(cron = "0/10 * * * * ? ") // 间隔10秒执行
    @Override
    public void addDateFromMysqlToSolr() throws Exception {
        //1.先将之前的都删除掉
        solr.deleteByQuery("*:*");
        //2.查询mysql中的商品数据
        List<ProductSolr> productSolrs = productMapper.queryAll();
        //3.完成向solr中的批量添加
        UpdateResponse updateResponse = solr.addBeans(productSolrs);
        //4.提交
        solr.commit();
    }

    @Override
    public List<ProductSolr> findProductBySolr(String solrPara) throws Exception {
        System.out.println(solrPara);
        //新建查询条件
        SolrQuery query = new SolrQuery();
        //查询条件
        query.set("q","title:"+solrPara);
        //分页
        query.setStart(0);
        query.setRows(3);
        //执行查询
        QueryResponse queryResponse = solr.query(query);
        List<ProductSolr> productSolrList = queryResponse.getBeans(ProductSolr.class);
        return productSolrList;
    }

}
