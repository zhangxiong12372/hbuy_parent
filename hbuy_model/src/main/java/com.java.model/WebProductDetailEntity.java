package com.java.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author djin
 *    WebProductDetail实体类
 * @date 2020-03-09 10:05:59
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class WebProductDetailEntity implements Serializable{

	  private static final long serialVersionUID = 1L;
	
      //商品id
	  private Long id;
      //
	  private String title;
      //
	  private String subtitle;
      //
	  private Float price;
      //
	  private String color;
      //
	  private String type;
      //
	  private Integer num;
      //
	  private Long typeid;
      //跳转地址
	  private String href;
      //最近修改时间
      @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
	  private Date updatetime;
      //
	  private String avatorimg;

	  private List<WebProductImgEntity> productImgs;

	  /**
	   * 设置：商品id
	   */
	  public void setId(Long id) {
		  this.id = id;
	  }
	  /**
	   * 获取：商品id
	   */
	  public Long getId() {
	   	  return id;
	  }
	  /**
	   * 设置：
	   */
	  public void setTitle(String title) {
		  this.title = title;
	  }
	  /**
	   * 获取：
	   */
	  public String getTitle() {
	   	  return title;
	  }
	  /**
	   * 设置：
	   */
	  public void setSubtitle(String subtitle) {
		  this.subtitle = subtitle;
	  }
	  /**
	   * 获取：
	   */
	  public String getSubtitle() {
	   	  return subtitle;
	  }
	  /**
	   * 设置：
	   */
	  public void setPrice(Float price) {
		  this.price = price;
	  }
	  /**
	   * 获取：
	   */
	  public Float getPrice() {
	   	  return price;
	  }
	  /**
	   * 设置：
	   */
	  public void setColor(String color) {
		  this.color = color;
	  }
	  /**
	   * 获取：
	   */
	  public String getColor() {
	   	  return color;
	  }
	  /**
	   * 设置：
	   */
	  public void setType(String type) {
		  this.type = type;
	  }
	  /**
	   * 获取：
	   */
	  public String getType() {
	   	  return type;
	  }
	  /**
	   * 设置：
	   */
	  public void setNum(Integer num) {
		  this.num = num;
	  }
	  /**
	   * 获取：
	   */
	  public Integer getNum() {
	   	  return num;
	  }
	  /**
	   * 设置：
	   */
	  public void setTypeid(Long typeid) {
		  this.typeid = typeid;
	  }
	  /**
	   * 获取：
	   */
	  public Long getTypeid() {
	   	  return typeid;
	  }
	  /**
	   * 设置：跳转地址
	   */
	  public void setHref(String href) {
		  this.href = href;
	  }
	  /**
	   * 获取：跳转地址
	   */
	  public String getHref() {
	   	  return href;
	  }
	  /**
	   * 设置：最近修改时间
	   */
	  public void setUpdatetime(Date updatetime) {
		  this.updatetime = updatetime;
	  }
	  /**
	   * 获取：最近修改时间
	   */
	  public Date getUpdatetime() {
	   	  return updatetime;
	  }
	  /**
	   * 设置：
	   */
	  public void setAvatorimg(String avatorimg) {
		  this.avatorimg = avatorimg;
	  }
	  /**
	   * 获取：
	   */
	  public String getAvatorimg() {
	   	  return avatorimg;
	  }

	public List<WebProductImgEntity> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(List<WebProductImgEntity> productImgs) {
		this.productImgs = productImgs;
	}

	@Override
	public String toString() {
		return "WebProductDetailEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", subtitle='" + subtitle + '\'' +
				", price=" + price +
				", color='" + color + '\'' +
				", type='" + type + '\'' +
				", num=" + num +
				", typeid=" + typeid +
				", href='" + href + '\'' +
				", updatetime=" + updatetime +
				", avatorimg='" + avatorimg + '\'' +
				'}';
	}
}
