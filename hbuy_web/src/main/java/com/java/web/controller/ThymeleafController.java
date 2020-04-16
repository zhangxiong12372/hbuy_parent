package com.java.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020-03-10 09:16
 * @Description:
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping(value = "/getlucky")
    public ModelAndView getlucky(ModelAndView mv) {
        mv.setViewName("/lucky"); //设置模板为根目录的lucky.html
        String sWelcomeWords="欢迎使用Thymeleaf!";
        mv.addObject("welcomewords",sWelcomeWords); //将sWelcomeWords变量的值，绑定到该模板的welcomewords标签上
        return mv; //返回并渲染
    }

    @RequestMapping(value = "/getlucky2")
    public String getlucky2(Map<String,Object> map){
        String sWelcomeWords="欢迎使用Thymeleaf!,map";
        map.put("welcomewords",sWelcomeWords); //直接将sWelcomeWords的值添加到map对象中
        return "/lucky"; //使用lucky模板并返回
    }

    @RequestMapping("/getlucky3")
    public String getlucky3(Model model) { //自动带入Model对象
        model.addAttribute("welcomewords", "欢迎使用Thymeleaf!model"); //设置Model对象的welcomewords属性的值为sWelcomeWords
        return "/lucky"; //使用lucky模板并返回
    }
    @RequestMapping("/getlucky4")
    public String getlucky4(HttpServletRequest request) { //自动带入Model对象
        request.setAttribute("welcomewords","欢迎使用Thymeleaf!request");
        request.setAttribute("url","https://www.baidu.com/");
        return "/lucky"; //使用lucky模板并返回
    }
}
