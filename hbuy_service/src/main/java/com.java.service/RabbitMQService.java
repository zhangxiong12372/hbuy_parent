package com.java.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020-04-01 19:06
 * @Description:
 */
public interface RabbitMQService {
    //将秒杀的商品数据加入到消息队列中
    void addRabbitMQToExCFormSeckill(Long secId, Long proId, Float secPrice, Long uid) throws Exception;

    //将秒杀的商品数据加入到消息队列中
    void addRabbitMQToExCFormSeckill(Long secId, Long proId, Float secPrice, Integer uid) throws Exception;

    //将购物车的商品数据加入到消息队列中
    void addRabbitMQToExCFormBuyCar(String proIds, Float zPrice, Long uid) throws Exception;
}