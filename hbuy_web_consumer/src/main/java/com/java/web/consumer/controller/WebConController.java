package com.java.web.consumer.controller;

import com.java.model.WebBannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *   web模块消费者的控制器
 */
@Controller
@RequestMapping("/webConController")
public class WebConController {

    //依赖注入spring的客户端模板
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test/{userName}")
    public @ResponseBody String testConRibbon(@PathVariable("userName") String userName){
        return restTemplate.getForObject("http://web-provider/webProController/test/"+userName,String.class);
    }

    @RequestMapping("/loadAllBanner")
    public @ResponseBody List<WebBannerEntity> loadAllBanner(){
        return restTemplate.getForObject("http://web-provider/webbanner/loadAll",List.class);
    }
}
