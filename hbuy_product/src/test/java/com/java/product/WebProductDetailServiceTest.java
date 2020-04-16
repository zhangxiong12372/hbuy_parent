package com.java.product;

import com.java.model.WebProductDetailEntity;
import com.java.model.WebProductImgEntity;
import com.java.service.WebProductDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 *   商品详情业务层测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebProductDetailServiceTest {

    @Autowired
    private WebProductDetailService webProductDetailService;

    //测试查询所有
    @Test
    public void test01(){
        try {
            //执行获取所有的商品详情数据
            List<WebProductDetailEntity> productDetailEntities = webProductDetailService.findAll();
            //变量所有商品详情数据
            for (WebProductDetailEntity productDetailEntity:productDetailEntities) {
                System.out.println(productDetailEntity);
                //获取每一个商品详情的图片数据
                List<WebProductImgEntity> webProductImgEntities = productDetailEntity.getProductImgs();
                //变量每一个商品详情图片数据
                for (WebProductImgEntity webProductImgEntity:webProductImgEntities) {
                    System.out.println(webProductImgEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}