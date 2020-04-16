package com.java.model;

import org.apache.solr.client.solrj.beans.Field;


/**
 * @Auther: Administrator
 * @Date: 2020-04-13 10:28
 * @Description:
 */
public class ProductSolr {
    @Field
    private Long pid;
    @Field
    private String title;
    @Field
    private Float price;
    @Field
    private String avatorImg;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAvatorImg() {
        return avatorImg;
    }

    public void setAvatorImg(String avatorImg) {
        this.avatorImg = avatorImg;
    }
}
