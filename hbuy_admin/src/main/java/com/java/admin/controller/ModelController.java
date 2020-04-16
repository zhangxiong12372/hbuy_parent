package com.java.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *   页面跳转的控制器
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    //去到后台管理平台首页
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    //去到后台用户管理首页
    @RequestMapping("/toAdminUsers")
    public String toAdminUsers(){
        return "adminusers";
    }

    //去到后台菜单管理页面
    @RequestMapping("/toAdminMenus")
    public String toAdminMenus(){
        return "adminmenus";
    }
    //去到欠前台菜单管理页面
    @RequestMapping("/toWebMenus")
    public String toWebMenus(){
        return "webmenu";
    }

}
