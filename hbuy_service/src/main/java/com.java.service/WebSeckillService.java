package com.java.service;


import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 *    WebSeckill业务层接口
 * @date 2020-03-09 10:05:58
 */
public interface WebSeckillService {

    //将秒杀数据放入到redis中
     Map<String, Object> addSecKillToRedis() throws Exception ;

    //将秒杀的商品状态改为开始秒杀（准备0------>开始1）
     String updateUPSecKillStatus() throws Exception ;

    //将秒杀商品的状态改为已结束(开始1-->结束2)
     String updateEndSecKillStatus() throws Exception ;
    /**
     *   用户执行秒杀（操作的是redis数据库）,测试
     * @param token  用户登陆令牌
     * @param secId  秒杀id
     * @return  操作结果
     */
    Map<String, Object> doSeckill(String token, Long secId) throws Exception;
    /**
     *   加载开始秒杀的商品数据
     * @return  秒杀的商品数据
     * @throws Exception
     */
     List<Map<String, Object>> findUPSecKill() throws Exception;


}
