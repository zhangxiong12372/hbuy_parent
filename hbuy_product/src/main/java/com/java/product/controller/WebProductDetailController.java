package com.java.product.controller;


import com.java.model.WebProductDetailEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author djin
 *   WebProductDetail控制器
 * @date 2020-03-09 10:05:59
 */
@Controller
@RequestMapping("/webproductdetail")
public class WebProductDetailController extends BaseController<WebProductDetailEntity>{
	@RequestMapping("/makeProductDetailHtml")
	public @ResponseBody String makeProductDetailHtml(){
		try {
			webProductDetailService.makeProDuctDetail();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fails";
		}
	}
}
