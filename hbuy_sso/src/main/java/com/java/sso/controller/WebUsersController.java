package com.java.sso.controller;



import com.java.model.WebUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *   WebUsers控制器
 * @date 2020-03-09 10:05:55
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/loginUser")
    public @ResponseBody Map<String, Object> loginUser(WebUsersEntity user, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = webUsersService.loginUser(user);
            Integer code = (Integer) map.get("code");
            if (code==0){//成功
                String token = (String) map.get("token");
                //新建Cookie
                Cookie cookie = new Cookie("token",token);
                //设置cookie路径和有效时间
                cookie.setPath("/webusers");
                cookie.setMaxAge(20*60);
                //将cookie存放在客户端
                response.addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    //获取登录模块令牌
    @RequestMapping("/getToken")
    public @ResponseBody String getToken(HttpServletRequest request){
        String token = null;
        //得到客户端中Cookie数组
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            //定义token的Cookie
            Cookie cookieToken = null;
            for(Cookie cookie : cookies){
                if ("token".equals(cookie.getName())){
                    cookieToken = cookie;
                    break;
                }
            }
            if (cookieToken!=null){
                token = cookieToken.getValue();
            }
        }
        return token;
    }

    //取到redis中的数据
    @RequestMapping("/getRedisLoginUser")
    public @ResponseBody WebUsersEntity getRedisLoginUser(String token){
        //获取redis中字符串的模板
        ValueOperations vop = redisTemplate.opsForValue();
        //登陆用户
        WebUsersEntity loginUser = null;
        loginUser = (WebUsersEntity) vop.get("sessionId"+token);
        return loginUser;
    }

    /**
     * 用户注销
     * @return
     */
    @RequestMapping("/exitUser")
    public @ResponseBody boolean exitUser(HttpServletRequest request,HttpServletResponse response){
        Boolean deleteRedis = false;
        //1.删除令牌
        String token = null;
        //得到客户端中Cookie数组
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            //定义token的Cookie
            Cookie cookieToken = null;
            for(Cookie cookie : cookies){
                if ("token".equals(cookie.getName())){
                    cookieToken = cookie;
                    break;
                }
            }
            if (cookieToken!=null){
                token = cookieToken.getValue();
                //删除redis中用户数据
                deleteRedis = redisTemplate.delete("sessionId" + token);
                //清除cookie
                Cookie cookie = new Cookie("token","");
                cookie.setPath("/webusers");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return deleteRedis;
    }
}
