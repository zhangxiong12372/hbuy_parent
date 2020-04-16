package com.java.product.controller;

import com.java.service.DiscussService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020-03-25 10:09
 * @Description:
 */
@Controller
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    //分页加载评论
    @RequestMapping("/loadPageDiscuss")
    public @ResponseBody List<Document> loadPageDiscuss(Integer page, Integer limit){
        try {
            return discussService.findPageDiscuss(page,limit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
