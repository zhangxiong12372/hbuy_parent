package com.java.register.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/** 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar */
public class SmsUtil {

	static  String product = "Dysmsapi";//产品名称:云通信短信API产品,开发者无需替换
	static  String domain = "dysmsapi.aliyuncs.com";//产品域名,开发者无需替换、
	static  String accessKeyId = "LTAI4Ff44dJAwH9WAKHMuUhC"; //你保存的accessKeyId值
	static  String accessKeySecret = "MH8CEbFVEZrwbHnVmj3jKdCV0M0rkd";//accessKeySecret值

	    public static String sendSms(String Numbers,String code) throws ClientException{
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);
	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        request.setPhoneNumbers(Numbers);  //必填:待发送手机号13264698373
	        request.setSignName("易买网购物平台1");//必填:短信签名-可在短信控制台中找到
	        request.setTemplateCode("SMS_185841273"); //必填:短信模板-可在短信控制台中找到
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的为
	        request.setTemplateParam("{\"code\":"+code+"}");
	        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
	        //request.setSmsUpExtendCode("90997");
	        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	        request.setOutId("yourOutId");
	        //hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			String sendIsOk = sendSmsResponse.getCode();//成功：OK
			return sendIsOk;
	    }
}
