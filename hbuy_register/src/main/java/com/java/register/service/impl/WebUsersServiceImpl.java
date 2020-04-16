package com.java.register.service.impl;


import com.java.model.WebUsersEntity;
import com.java.service.WebUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 
 * @author djin
 *    WebUsers业务层实现类
 * @date 2020-03-09 10:05:55
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {

    @Override
    public Map<String, Object> loginUser(WebUsersEntity user) throws Exception {
        return null;
    }
}
