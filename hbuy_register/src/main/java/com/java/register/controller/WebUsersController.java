package com.java.register.controller;


import com.aliyuncs.exceptions.ClientException;
import com.java.model.WebUsersEntity;
import com.java.register.util.HtmlMail;
import com.java.register.util.MD5;
import com.java.register.util.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author djin
 *   WebUsers控制器
 * @date 2020-03-09 10:05:55
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{

	@Override
	public String saveT(WebUsersEntity entity) {
		entity.setPwd(MD5.md5crypt(entity.getPwd()));
		return super.saveT(entity);
	}

	//短信发送
	@RequestMapping("/sendSms")
	public @ResponseBody String sendSms(String phone, String code){
		try {
			return SmsUtil.sendSms(phone,code);
		} catch (ClientException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	//邮件发送
	@RequestMapping("/sendEmail")
	public @ResponseBody String sendEmail(String email,String msg){
		return HtmlMail.sendEmail(email,msg);
	}
}
