package com.mendale.web.webservice.client;

import javax.jws.WebService;

import com.mendale.web.webservice.util.WsConstants;


/**
 * @author pengjinlong
 * 手机短信息Web Service接口
 */
@WebService(targetNamespace = WsConstants.NS)
public interface SMSService {

	
	/**
	 * 接收短消息
	 * @param phone 要接收的手机号
	 * @param date  接收短消息时间，格式：yyyy-MM-dd
	 * @return  短消息，Json字符串格式
	 * 	result：
			返回状态码
				0 返回成功
				-1 无短消息记录
				-4 接收时间验证失败
				-5 发送手机号码验证失败
	 * 	desc：状态描述
	 * 	delivertime：接收时间，格式yyyy-MM-dd HH:mm:ss
	 *  errormsg：错误信息
	 * 
	 */
	public String deliver(String phone, String date); 
		
	
	
	/**
	 * 发送短消息
	 * @param brandCode 品牌(梦洁、寐、新材料、梦洁宝贝、平美、Beself等)
	 * @param phone     发送到的手机号
	 * @param content   短信内容
	 * @param date      发送日期，格式yyyy-MM-dd HH:mm:ss，为空则立即发送；
	 * @param invokeSys 来源系统(DRP、CRM、官网商城、上门服务等)
	 * @param sendType  短信类型，(1验证码、2消费通知、3祝福提醒等)
	 * @param division  事业部(梦洁、寐、新材料、梦洁宝贝)
	 * @param dept      部门
	 * @param branch    网点
	 * @param store     门店
	 * @return          
	 * 发送结果，Json字符串格式
	 * 
	 * 	result：
			大汉三通返回状态码
				0 提交成功
				1 账号无效
				2 密码错误
				3 msgid太长，不得超过64位
				4 错误号码/限制运营商号码
				5 手机号码个数超过最大限制(500个)
				6 短信内容超过最大限制(350字)
				7 扩展子号码无效
				8 定时时间格式错误
				14 手机号码为空
				19 用户被禁发或禁用
				20 ip鉴权失败
				21 短信内容为空
				97 接入方式错误
				98 系统正忙
				99 消息格式错误
			
			鼎信通返回状态码
				0 提交成功
				-1 帐户密码错误
				
			系统状态码
				-2 品牌不存在
				-3 无默认的短信供应商
				-4 发送时间验证失败
				-5 发送手机号码验证失败
				-6 发送短消息内容验证失败
				-7 来源系统验证失败
				-8 短信类型验证失败
				-9 事业部验证失败
				-10部门验证失败
				-99 调用供应商API接口失败
	 * 	desc：状态描述
	 * 	msgid：短信编号
	 *  errormsg：错误信息
	 *  blacklist：黑名单号码
	 * 
	 */
	public String send(String invokeSys, String division, String brandCode, String dept, int sendType, String phone, String content
			, String date,  String branch, String store);
	
	
}
