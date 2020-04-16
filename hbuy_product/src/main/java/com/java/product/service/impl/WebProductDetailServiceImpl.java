package com.java.product.service.impl;

import com.java.model.WebProductDetailEntity;
import com.java.service.WebProductDetailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author djin
 *    WebProductDetail业务层实现类
 * @date 2020-03-09 10:05:59
 */
@Service
@Transactional
public class WebProductDetailServiceImpl extends BaseServiceImpl<WebProductDetailEntity> implements WebProductDetailService {

    @Autowired
    private Configuration configuration;
    @Scheduled(cron = "0 0 0/2 * * ?")
    @Override
    public void makeProDuctDetail() throws Exception {
        //定义输出流对象
        FileWriter fw = null;
        //获取所有商品详情
        List<WebProductDetailEntity> productDetails = baseMapper.queryAll();
        //定义生成文件夹
        File file = new File("D:\\software\\productDetail");
        if (!file.exists()){
            file.mkdirs();
        }
        //遍历商品详情
        for (WebProductDetailEntity productDetail: productDetails){
            //定义生成静态页面的目标文件夹路径
            String filePath = "D:\\software\\productDetail\\"+productDetail.getId()+".html";
            //生成目标文件
            File newFile = new File(filePath);
            fw = new FileWriter(newFile);
            //根据ftl模板得到生成静态页面的模板对象
            Template template = configuration.getTemplate("product.ftl");
            //通过目标文件的输入流对象和数据生成静态文件
            template.process(productDetail,fw);
            fw.close();  //关闭资源
        }
    }
}
