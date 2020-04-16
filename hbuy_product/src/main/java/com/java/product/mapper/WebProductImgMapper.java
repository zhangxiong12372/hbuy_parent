package com.java.product.mapper;


import com.java.model.WebProductImgEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author djin
 *    WebProductImgMapper层
 * @date 2020-03-09 10:05:59
 */
@Repository
public interface WebProductImgMapper extends BaseMapper<WebProductImgEntity> {

	List<WebProductImgEntity> queryWebProductImgByProId(Integer productId) throws Exception;
}
