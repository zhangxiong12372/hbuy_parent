package com.java.service;


import com.java.model.WebUsersEntity;

import java.util.Map;

/**
 * 
 * @author djin
 *    WebUsers业务层接口
 * @date 2020-03-09 10:05:55
 */
public interface WebUsersService extends BaseService<WebUsersEntity>{
    //登录
	Map<String,Object> loginUser(WebUsersEntity user) throws Exception;
	
}
