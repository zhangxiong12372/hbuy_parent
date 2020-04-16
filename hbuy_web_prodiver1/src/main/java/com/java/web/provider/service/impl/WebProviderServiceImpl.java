package com.java.web.provider.service.impl;

import com.java.service.WebProviderService;
import org.springframework.stereotype.Service;

@Service
public class WebProviderServiceImpl implements WebProviderService {

    private Integer count = 0;

    //测试ribbon的负载均衡的搭建
    @Override
    public String test(String userName) throws Exception {
        System.out.println("这里是provider1执行的请求。。"+count);
        count++;
        return userName+"--provider1"+count;
    }
}
