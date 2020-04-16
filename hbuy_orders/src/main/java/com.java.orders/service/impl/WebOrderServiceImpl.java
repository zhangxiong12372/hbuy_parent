package com.java.orders.service.impl;

import com.java.model.WebOrderEntity;
import com.java.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author djin
 *    WebOrder业务层实现类
 * @date 2020-03-09 10:05:59
 */
@Service
@Transactional
public class WebOrderServiceImpl extends BaseServiceImpl<WebOrderEntity> implements WebOrderService {

    //注入redis
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    @Override
    public void listenerOrder() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",404);
        //查询超时未支付的订单数据
        List<WebOrderEntity> orderses = webOrderMapper.listenerOrder();
        if(orderses!=null){
            //通过循环将订单状态修改，然后在redis中修改数据
            for (WebOrderEntity order:orderses) {
                WebOrderEntity updOrderPra = new WebOrderEntity();
                updOrderPra.setId(order.getId());
                updOrderPra.setOrderstatus("5");
                //做mysql数据库的订单状态修改
                Integer count = baseMapper.update(updOrderPra);
                if(count>0){
                    System.out.println("id为："+order.getId()+"订单状态已修改。。");
                    //操作redis
                    SetOperations sop = redisTemplate.opsForSet();
                    String key = "secKill_"+order.getSecid()+"_"+order.getProids();
                    String value = order.getSecid()+","+order.getUserid()+","+order.getProids();
                    Long reCount = 0l;
                    //删除redis中的set里面的秒杀中的用户数据
                    if(sop.isMember(key,value)){  //判断存在
                        reCount = sop.remove(key, value);  //删除已存在的秒杀中的数据
                    }
                    //将此秒杀的数据加回到redis的list中
                    ListOperations lop = redisTemplate.opsForList();
                    Long addCount = lop.leftPush(order.getSecid() + "_" + order.getProids(), order.getSecid() + "_" + order.getProids());
                    if(reCount>0&&addCount>0){
                        System.out.println(order.getSecid() + "_" + order.getProids()+"已加回去");
                    }
                }else {
                    System.out.println("id为："+order.getId()+"订单状态修改失败！！");
                }
            }
        }else {
            System.out.println("没有超时未支付的订单。。");
        }
    }
}
