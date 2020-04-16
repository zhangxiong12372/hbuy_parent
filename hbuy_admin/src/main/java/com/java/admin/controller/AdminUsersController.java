package com.java.admin.controller;

import com.java.admin.util.MD5;
import com.java.admin.util.VerifyCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.java.admin.model.AdminUsersEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 
 * @author djin
 *   AdminUsers控制器
 * @date 2020-03-07 10:04:53
 */
@Controller
@RequestMapping("/adminusers")
public class AdminUsersController extends BaseController<AdminUsersEntity>{

	//获取登录验证
	@RequestMapping("/getVerifyCode")
	public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {
		//产生验证码
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		//将验证码放入session容器中
		session.setAttribute("verifyCode",verifyCode.toLowerCase());
		//生成验证码图片
		VerifyCodeUtils.outputImage(200,45,response.getOutputStream(),verifyCode);
	}

	//验证用户输入的验证码
	@RequestMapping("/checkVerifyCode")
	public @ResponseBody String checkVerifyCode(HttpSession session, String verifyCodeIpt){
		//1.从session容器中取出之前装入的验证码
		String verifyCode = (String)session.getAttribute("verifyCode");
		//2.此时将用户输入的验证码与session中取出的验证码进行比较
		if(verifyCodeIpt.equals(verifyCode)){
			return "success";
		}else {
			return "fail";
		}
	}

	//执行登陆
	@RequestMapping("/login")
	public @ResponseBody String login(AdminUsersEntity user,HttpSession session){
		//加密密码
		user.setPwd(MD5.md5crypt(user.getPwd()));
		try {
			AdminUsersEntity loginUser = baseService.findObjectByPramas(user);
			if(loginUser!=null){
				session.setAttribute("loginUser",loginUser);
				return "success";
			}else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	//执行退出
	@RequestMapping("/exitUser")
	public @ResponseBody String exitUser(HttpSession session){
		try {
			//移除session容器中的用户登陆对象
			session.removeAttribute("loginUser");
			return "success";
		}catch (Exception e){
			return "error";
		}

	}
}
