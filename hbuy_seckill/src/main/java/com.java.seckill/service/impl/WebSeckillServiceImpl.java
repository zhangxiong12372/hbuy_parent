package com.java.seckill.service.impl;

import com.java.model.WebSeckillEntity;
import com.java.model.WebUsersEntity;
import com.java.seckill.mapper.WebSeckillMapper;
import com.java.service.RabbitMQService;
import com.java.service.WebSeckillService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   秒杀的业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class WebSeckillServiceImpl implements WebSeckillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private WebSeckillMapper webSeckillMapper;

    @Autowired
    private RabbitMQService rabbitMQService;

    //将秒杀数据放入到redis中
    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    @Override
    public Map<String, Object> addSecKillToRedis() throws Exception {
        //新建返回的结果
        Map<String, Object> map = new HashMap<String, Object>();
        //1.将符合秒杀的商品从mysql中查询出来
        List<WebSeckillEntity> seckillEntityList = webSeckillMapper.selectSecKillByTimes();
        //2.判断符合秒杀的数据存在
        if(seckillEntityList!=null){
            //3.把存在的商品数据装入到redis中
           for (WebSeckillEntity  seckill:seckillEntityList) {
               //3.1只装入1次就行，不需要反复装入
               String key = seckill.getId()+"_"+seckill.getProductid();
               //3.2判断redis中是否存在此秒杀商品数，存在则以前装入了，就不再装入
               //否则，就装入
               if(!redisTemplate.hasKey(key)){
                   map.put("code",200);
                   map.put("msg","则此次已装入秒杀商品"+key);
                   System.out.println("则此次已装入秒杀商品"+key);
                   //3.3获取redis操作list的模板
                   ListOperations lop = redisTemplate.opsForList();
                   for(int i=0;i<seckill.getNum();i++){
                       //装入到redis中
                       lop.leftPush(key,seckill.getId()+","+seckill.getProductid());
                   }
               }else {
                   map.put("code",500);
                   map.put("msg","之前已装入过秒杀商品"+key);
                   System.out.println("之前已装入过秒杀商品"+key);
               }
           }
        }else {
            map.put("code",404);
            map.put("msg","没有符合秒杀的数据");
            System.out.println("没有符合秒杀的数据被放进redis中。。");
        }
        return map;
    }

    //2.将秒杀的商品状态改为开始秒杀（准备0------>开始1）
    //修改秒杀的商品的状态(准备-->开始)   假如   13：59：55 <= 14：00：00 <= 14：00：05
    //系统时间14：00：05  最多服务器有5次机会去修改状态updateUPSecKillStatus
    @Scheduled(cron = "0/2 * * * * ? ") // 间隔2秒执行
    @Override
    public String updateUPSecKillStatus() throws Exception {
        //1.执行改状态
        if(webSeckillMapper.updateUPSecKillStatus()>0){
            System.out.println("秒杀状态修改成功，（准备0------>开始1）。。");
            return "success";
        }else {
            System.out.println("秒杀状态修改失败，（准备0------>开始1）！！");
            return "fail";
        }
    }

    //3.将秒杀商品的状态改为已结束(开始1-->结束2)
    //修改秒杀的商品的状态(开始-->结束)   假如   13：59：55 <= 14：00：00 <= 14：00：05
    //系统时间14：00：05  最多服务器有5次机会去修改状态updateEndSecKillStatus
    @Scheduled(cron = "0/2 * * * * ? ") // 间隔2秒执行
    @Override
    public String updateEndSecKillStatus() throws Exception {
        //1.执行改状态
        if(webSeckillMapper.updateEndSecKillStatus()>0){
            System.out.println("秒杀状态修改成功，（开始1-->结束2）。。");
            return "success";
        }else {
            System.out.println("秒杀状态修改失败，（开始1-->结束2）！！");
            return "fail";
        }
    }

    /**
     *   用户执行秒杀（操作的是redis数据库）,测试
     * @param token  用户登陆令牌
     * @param secId  秒杀id
     * @return  操作结果
     */
    @Override
    public Map<String, Object> doSeckill(String token, Long secId) throws Exception {
        //1.新建操作的结果集合
        Map<String, Object> map = new HashMap<String, Object>();
        //若令牌不为null，到redis中找用户数据
        ValueOperations vop = redisTemplate.opsForValue();
        WebUsersEntity loginUser = ((WebUsersEntity)vop.get("sessionId" + token));
        //2.判断用户是否登陆
        if(loginUser!=null){
            //3.根据秒杀id查询该秒杀的数据
            WebSeckillEntity seckill = webSeckillMapper.selectObjectById(secId);
            if(seckill!=null) {
                //4.判断是否开始秒杀
                if (seckill.getStatus().equals("0")) {
                    map.put("code", "300");
                    map.put("msg", "商品还未开始秒杀！！");
                }
                if (seckill.getStatus().equals("2")) {
                    map.put("code", "302");
                    map.put("msg", "商品秒杀已结束！！");
                }
                if (seckill.getStatus().equals("1")) {  //可以秒杀
                    //5.1.得到操作list的模板对象
                    ListOperations lop = redisTemplate.opsForList();
                    //5.2.得到操作此商品秒杀的list的key
                    String listkey = secId + "_" + seckill.getProductid();
                    //5.2.判断此时list中有没有商品元素被秒杀
                    //根据key删除list中的一个元素，删除成功则返回值不为null，
                    //删除失败返回值为null，也说明此list中已没有元素
                    Object obj = lop.leftPop(listkey);
                    if (obj != null) {  //可以删除，说明还可以进行秒杀
                        //6.判断此用户是否重复秒杀
                        //6.1获取操作set的模板
                        SetOperations sop = redisTemplate.opsForSet();
                        //6.2构建set的key值
                        String setkey = "secKill_" + secId + "_" + seckill.getProductid();
                        //6.3构建set元素中的value值
                        String setvalue = secId + "," + loginUser.getId() + "," + seckill.getProductid();
                        //7.判断set中是否存在此元素
                        if (!sop.isMember(setkey, setvalue)) {   //不存在，直接加
                            //7.1不存在，可以秒杀
                            sop.add(setkey, setvalue);
                            //uid secId productId cost flag
                            rabbitMQService.addRabbitMQToExCFormSeckill(secId,seckill.getProductid(),seckill.getSeckillprice(),loginUser.getId());
                            map.put("code", 200);
                            map.put("msg", "恭喜你，秒杀成功！！");
                        } else {  //存在，不能重复秒杀
                            //7.2存在，不能重复秒杀
                            lop.leftPush(listkey, obj);   //把商品重新加回商品的list中
                            map.put("code", "600");
                            map.put("msg", "很抱歉，不能重复秒杀！！");
                        }

                    } else {  //无法删除，说明商品已秒杀完
                        map.put("code", "500");
                        map.put("msg", "很抱歉，商品已被秒杀完！！");
                    }
                }
            }else {
                map.put("code","404");
                map.put("msg","没有查询到此秒杀商品！！");
            }
        }else {
            map.put("code","400");
            map.put("msg","用户未登录不能秒杀！！");
        }
        return map;
    }

    /**
     *   加载开始秒杀的商品数据
     * @return  秒杀的商品数据
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> findUPSecKill() throws Exception {
        return webSeckillMapper.selectUPSecKill();
    }
}
