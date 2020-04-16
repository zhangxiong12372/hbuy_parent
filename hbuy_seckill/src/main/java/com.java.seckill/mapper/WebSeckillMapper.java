package com.java.seckill.mapper;

import com.java.model.WebSeckillEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author djin
 *    WebSeckillMapper层
 * @date 2020-03-09 10:05:58
 */
public interface WebSeckillMapper{

    //1.将准备秒杀的商品提前10分钟放入到redis中，做秒杀准备
    @Select("SELECT id,num,productId FROM web_seckill where startTime >= NOW() and startTime <= DATE_ADD(NOW(),INTERVAL 10 MINUTE)")
    List<WebSeckillEntity> selectSecKillByTimes() throws Exception;

    //2.将秒杀的商品状态改为开始秒杀
    @Update("UPDATE web_seckill SET `status`='1' where id in(SELECT id FROM(SELECT id FROM web_seckill where startTime <= DATE_ADD(NOW(),INTERVAL 5 SECOND) and startTime >= DATE_SUB(NOW(),INTERVAL 5 SECOND) and STATUS='0') as secKill)")
    Integer updateUPSecKillStatus() throws Exception;

    //3.将秒杀商品的状态改为已结束
    @Update("UPDATE web_seckill SET `status`='2' where id in(SELECT id FROM(SELECT id FROM web_seckill where endTime <= DATE_ADD(NOW(),INTERVAL 5 SECOND) and endTime >= DATE_SUB(NOW(),INTERVAL 5 SECOND) and STATUS='1') as secKill)")
    Integer updateEndSecKillStatus() throws Exception;

    //4.根据主键查询单个秒杀数据
    @Select("SELECT productId,status,seckillPrice FROM web_seckill where id = #{secId}")
    WebSeckillEntity selectObjectById(Long secId) throws Exception;

    //加载开始的秒杀商品
    @Select("SELECT p.avatorImg,p.title,p.price,s.seckillPrice,s.endTime,s.id from web_seckill s LEFT JOIN web_product_detail p on s.productId = p.id where s.`status`='1'")
    List<Map<String,Object>> selectUPSecKill() throws Exception;
	
}
