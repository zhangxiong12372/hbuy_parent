package com.java.sso.service.impl;


import com.java.model.WebUsersEntity;
import com.java.service.WebUsersService;
import com.java.sso.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author djin
 *    WebUsers业务层实现类
 * @date 2020-03-09 10:05:55
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> loginUser(WebUsersEntity user) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        user.setPwd(MD5.md5crypt(user.getPwd()));
        WebUsersEntity loginUser = baseMapper.queryObjectByPramas(user);
        //登录成功
        if (loginUser!=null){
            map.put("code",0);
            map.put("loginUser",loginUser);
            //产生令牌
            String token = UUID.randomUUID().toString();
            map.put("token",token);
            //redis中存储用户数据
            ValueOperations vop = redisTemplate.opsForValue();
            vop.set("sessionId"+token,loginUser,20, TimeUnit.MINUTES);
        }else {
            map.put("code",200);
        }
        return map;
    }
}
