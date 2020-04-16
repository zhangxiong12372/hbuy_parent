package com.java.product.service.impl;

import com.java.product.utils.MongoDBUtil;
import com.java.service.DiscussService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-03-25 09:51
 * @Description:
 */
@Service
@Transactional(readOnly = false)
public class DiscussServiceImpl implements DiscussService {
    //mongoDB连接对象
    private MongoCollection<Document> collection = MongoDBUtil.getCollection();
    //分页查询商品评论数据
    @Override
    public List<Document> findPageDiscuss(Integer page, Integer limit) throws Exception {
        //分页查找
        FindIterable<Document> documents = collection.find().skip((page - 1) * limit).limit(limit);
        //新建list,将documents装入list
        List<Document> list = new ArrayList<>();
        documents.iterator().forEachRemaining(temp->list.add(temp));
        return list;
    }
}
