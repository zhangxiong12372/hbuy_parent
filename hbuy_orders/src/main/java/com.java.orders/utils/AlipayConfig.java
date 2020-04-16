package com.java.orders.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  pguedo1553@sandbox.com  111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {


	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900723090";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeRuBnJ/XKWJx67vmph/aRAxpODz5MozP+qQ6qmMiepXbN2C5g5JxsFyZHetUVItqwO/PsfFZhAF12XLq1EF/KzcgteayYgm6CyA5hobspfAIw3ToW2SqURC6X2n420CpNRBJ+rCzjgP710lJtJ4TBZ46vm2m+kW0nQSk6CfIgCCIwVOTUKi7SRWmKkxzl0E60MLNjCadR+upLAYTj1MhuYMZWP3C2CJA1fagkBEXCcRrQh6asZ6hYkECQeO9Me0ZKBSDQPHx+Z3xPlWms6ESoUghYHPX0/XiQLSAmOOAKwnwAG51lHuvXpiHY8igwMT2sEXGGetcXxzr2nS87HqTrAgMBAAECggEAHbjrnROI+aWoRQGpiqeIqvStNOlx5oxjUDhJE8o7KmeiJseo9fMYWMgEHTo4145DCQNJLCixQ+gApSCGw0e+5WO9aqTFv6IGxLJy2Oy5K/IoqypwJShE8bDYGxYhy4NN3lKhMn58s4Be4KTNa8gXCwa6acnCktHWcJ+w8UA64r63vtQpi42Eb4dj1YqpZaTxgR/aHC9aXdn59GdmI671xt7KYwnpc7juxDZ87VX6GpDZf1bvcuFUR9F5nAABoh7QqKmZaelInTA7TMQgEruXzCNJchxthGxN5SKV5I83dqjBvOVcAGHePhRyjje+I0f7ku5f7LrTxgzda/D09VTcqQKBgQDrSCCDNrQYR8gXTk7+AGLT6ABM4jyatMOs+ATe9pc1lNWDqYe+0VSSFcIz58fh0Okoh1Xdoznd9dmCrSejWNeJbzv9OzCEix2VniQ4OFzCz5WpAnkAl+3BcdTKAxo1EQx9BSV74r2cU5hDh+Lj86toi8n+hr2LOEotVMhfnc5ahQKBgQCsNtsS+MbxQ6pJKuMFqHPM1gVGUqzWbD4g9tnmHg8NeejBoLWFeDzgADaSwRhP4T6kTGOLzRFva0Sow8/RKObuGhQK3BwewWkiZH1i0cMOXTd7fbTGBe3S1lzu1edQw2pQJ1lgGvrvX7iSBmCSKDfkv+M2HJ3a94Akjx+H6Cj0rwKBgB8yly0O6d52Vi/Q8GR5xKD4mGGZdhPFeFTNJkgyRmaSb6Vfq2I/+4jLXc7oIvbsbp4zI82ZwSiPWuIa97CPLTVKDJhNaueIUsnuCDt0LcifIechLs5HVv1ZjK+krarvsco6DnhY3m+GHiCGOi2jt8zLDGO1lhiJxDwyi8cx85TJAoGAVwPYqbtd+iXwcsvirccxy7wQKjo37pf+xHU2IFnrzSld/AgR8ACS8bPp3zkF6car+iRG4CqbtqqCpKtb5BlApeozWlFhPAEJ+EDCQh4u6qEbGaCoqK3yPuflSSB3oo06uIlJVZAMXssE3XOvc+oyY7i5O2w2Lp+D9fUDP46Esy0CgYEAxpix/h8EUV50JHhGfyEtXzR017fOeVXlR7vdBTuT/9uuUQ4n3T5+P+d7o4ZD12WO3gANvAtv7r3yuWw3CyM62WLyZQtn7sYhSro5Yn0+lctb5UHHNwy4xs9eoSi+wwsEq3YkYyOOgKpG8TsNzzW0fwq7KxqpLKojQc6PZXHWXDs=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnkbgZyf1yliceu75qYf2kQMaTg8+TKMz/qkOqpjInqV2zdguYOScbBcmR3rVFSLasDvz7HxWYQBddly6tRBfys3ILXmsmIJugsgOYaG7KXwCMN06FtkqlEQul9p+NtAqTUQSfqws44D+9dJSbSeEwWeOr5tpvpFtJ0EpOgnyIAgiMFTk1Cou0kVpipMc5dBOtDCzYwmnUfrqSwGE49TIbmDGVj9wtgiQNX2oJARFwnEa0IemrGeoWJBAkHjvTHtGSgUg0Dx8fmd8T5VprOhEqFIIWBz19P14kC0gJjjgCsJ8ABudZR7r16Yh2PIoMDE9rBFxhnrXF8c69p0vOx6k6wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.114.45.21/orders/myOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

