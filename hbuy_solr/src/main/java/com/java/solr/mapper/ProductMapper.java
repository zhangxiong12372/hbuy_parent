package com.java.solr.mapper;

import com.java.model.ProductSolr;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-04-13 10:35
 * @Description:
 */
public interface ProductMapper {
    //查询所有
    @Select("select id as pid,title,price,avatorImg from web_product_detail")
    List<ProductSolr> queryAll() throws Exception;
}
