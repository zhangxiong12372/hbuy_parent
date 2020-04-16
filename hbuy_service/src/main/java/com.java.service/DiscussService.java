package com.java.service;

import org.bson.Document;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-03-25 09:44
 * @Description:商品评论
 */
public interface DiscussService {
    //评论的分页查询
  List<Document> findPageDiscuss(Integer page,Integer limit) throws Exception;
}
