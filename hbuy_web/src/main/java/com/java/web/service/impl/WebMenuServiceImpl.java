package com.java.web.service.impl;

import com.java.model.WebMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.web.service.WebMenuService;

import java.util.ArrayList;
import java.util.List;

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

    //重写菜单的加载所有的方法
    @Override
    public List<WebMenuEntity> findAll() throws Exception {
        //1.新建一个list菜单集合
        List<WebMenuEntity> webMenus = new ArrayList<WebMenuEntity>();
        //2.得到redis操作菜单list集合的对象
        ListOperations lop = redisTemplate.opsForList();
        //3.从redis中取菜单集合数据
        webMenus = lop.range("webMenus",0,-1);
        //4.判断webMenus中有没有数据
        if(webMenus.size()==0){
            //5.1.没有数据，从mysql中查询出数据
            webMenus = baseMapper.queryAll();
            System.out.println("此时数据走的是mysql数据库。。");
            //5.2.将查询出的数据装入到redis中,先进先出
            lop.rightPushAll("webMenus",webMenus);
        }else {
            System.out.println("此时数据走的是redis!!");
        }
        return webMenus;
    }
}
