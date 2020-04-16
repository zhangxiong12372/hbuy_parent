package com.java.solr.controller;


import com.java.service.ProductSolrService;
import com.java.model.ProductSolr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *   搜索引擎的控制器
 */
@Controller
@RequestMapping("/solr")
public class SolrController {

    //依赖业务层对象
    @Autowired
    private ProductSolrService productSolrService;


   /* @RequestMapping("/import")
    public @ResponseBody String import(){
        try {
            productSolrService.addDateFromMysqlToSolr();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }*/
   //根据条件加载solr中数据
   @RequestMapping("/loadProductBySolr")
   public @ResponseBody List<ProductSolr> loadProductBySolr(String solrPra){
       try {
           return productSolrService.findProductBySolr(solrPra);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
}
