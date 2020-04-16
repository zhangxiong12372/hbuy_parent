package com.java.orders.service.impl;

import com.java.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020-04-01 19:06
 * @Description:
 */
@Service
@Transactional(readOnly = false)
public class RabbitMQServiceImpl implements RabbitMQService{
    //依赖引入消息队列模板对象
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //将秒杀的商品数据加入到消息队列中
    @Override
    public void addRabbitMQToExCFormSeckill(Long secId, Long proId, Float secPrice, Long uid) throws Exception {
        //设置装入的数据
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("secId",secId);
        dataMap.put("proId",proId);
        dataMap.put("secPrice",secPrice);
        dataMap.put("uid",uid);
        dataMap.put("flag",2);
        System.out.println("执行了此添加的方法。。");
        rabbitTemplate.convertAndSend("ex_hbuy_deckill",null,dataMap);
    }

    @Override
    public void addRabbitMQToExCFormSeckill(Long secId, Long proId, Float secPrice, Integer uid) throws Exception {

    }

    @Override
    public void addRabbitMQToExCFormBuyCar(String proIds, Float zPrice, Long uid) throws Exception {

    }

}
