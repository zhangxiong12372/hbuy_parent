package com.java.web.provider.controller;

import com.java.service.WebProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *   web模块的提供者控制器
 */
@Controller
@RequestMapping("/webProController")
public class WebProController {

    @Autowired
    private WebProviderService webProviderService;

    @RequestMapping("/test/{userName}")
    public @ResponseBody String test(@PathVariable("userName") String userName){
        try {
            return webProviderService.test(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
