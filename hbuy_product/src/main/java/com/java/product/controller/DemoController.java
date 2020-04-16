package com.java.product.controller;

import com.java.model.WebProductDetailEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

/**
 *   测试的控制器
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/test01")
    public ModelAndView test01(ModelAndView modelAndView){
        modelAndView.setViewName("test01");
        //1.普通属性数据的传值
        modelAndView.addObject("userName","zhangsan");
        modelAndView.addObject("age",12);
        modelAndView.addObject("price",12.90d);
        modelAndView.addObject("createDate",new Date());
        modelAndView.addObject("char",'a');
        modelAndView.addObject("b1",false);
        //2.对象属性的传值
        WebProductDetailEntity productDetail = new WebProductDetailEntity();
        productDetail.setId(10001l);
        productDetail.setTitle("手机");
        productDetail.setSubtitle("OPPO手机");
        productDetail.setAvatorimg("1.jpg");
        productDetail.setPrice(3400f);
        productDetail.setColor("白色");
        productDetail.setNum(3200);
        productDetail.setHref("www.baidu.com");
        productDetail.setType("电子产品");
        productDetail.setUpdatetime(new Date());
        modelAndView.addObject("productDetail",productDetail);
        //3.list集合传值
        List<String> strs = Arrays.asList("1001","1002","1003","1004","1005");
        modelAndView.addObject("strs",strs);
        //4.map集合的传值
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName","lisi");
        map.put("create",new Date());
        map.put("char",'e');
        map.put("num",190);
        map.put("f1",123.90f);
        modelAndView.addObject("map",map);
        return modelAndView;
    }
}
