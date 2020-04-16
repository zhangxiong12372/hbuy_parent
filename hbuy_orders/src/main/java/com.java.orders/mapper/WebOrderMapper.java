package com.java.orders.mapper;

import com.java.model.WebOrderEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author djin
 *    WebOrderMapper层
 * @date 2020-03-09 10:05:59
 */
@Repository
public interface WebOrderMapper extends BaseMapper<WebOrderEntity> {

    //监听未支付即将的订单
    @Select("SELECT * FROM web_order where endDate <= NOW() and orderStatus = 1 and flag = 2")
    List<WebOrderEntity> listenerOrder() throws Exception;
	
}
