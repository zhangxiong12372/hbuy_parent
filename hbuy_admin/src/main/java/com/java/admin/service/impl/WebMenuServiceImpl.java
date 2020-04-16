package com.java.admin.service.impl;

import com.java.model.WebMenuEntity;
import com.java.admin.service.WebMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author djin
 *    WebMenu业务层实现类
 * @date 2020-03-09 10:06:00
 */
@Service
@Transactional
public class WebMenuServiceImpl extends BaseServiceImpl<WebMenuEntity> implements WebMenuService {
    //依赖注入redis的模板对象
    @Autowired
    private RedisTemplate redisTemplate;
    //重写添加的方法
    @Override
    public String save(WebMenuEntity webMenuEntity) throws Exception {
        //1.数据库添加成功
        if(baseMapper.insert(webMenuEntity)>0){
            //2.得到操作list集合的对象
            ListOperations lop = redisTemplate.opsForList();
            //3.往redis中的list加入元素
            lop.rightPush("webMenus",webMenuEntity);
            return "saveSuccess";
        }else {
            return "fail";
        }
    }
}
