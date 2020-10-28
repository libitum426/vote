package com.mendale.web.util;

import net.sf.json.JSONObject;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mendale.web.webservice.client.SMSService;

/**
 * <p>
 * Title: 发送消费短信信息
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @作者 dw
 * @创建时间 Aug 08, 2015 11:37:13 AM
 * @版本 1.00
 * @修改记录 <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
public class MessageSendUtil {

	private Logger logger = LoggerFactory.getLogger(MessageSendUtil.class);
	private final static String invokeSys = "VOTE";
	private final static String smsWsdl = "http://192.168.173.194/services/smsService?wsdl";

	/**
	 * 获取短信发送WEB SERVICE
	 * 
	 * @return
	 */
	private SMSService getSMSServiceClass() {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		factoryBean.setServiceClass(SMSService.class);
		factoryBean.setAddress(smsWsdl);
		SMSService service = (SMSService) factoryBean.create();

		return service;
	}

	/**
	 * 发送短信
	 * 
	 * @Title: sendCardMsg
	 * @Description: TODO
	 * @param division
	 *            事业部(梦洁、寐、新材料、梦洁宝贝)
	 * @param brandCode
	 *            品牌
	 * @param dept
	 *            部门
	 * @param sendType
	 *            短信类型，(1验证码、2消费通知、3祝福提醒等)
	 * @param phone
	 *            手机号码
	 * @param content
	 *            内容
	 * @param date
	 *            发送时间
	 * @param branchcode
	 *            网点编码
	 * @param storecode
	 *            门店编码
	 * @return
	 * @return: int
	 */
	public int sendCardMsg(String division, String brandCode, String dept,
			int sendType, String phone, String content, String date,
			String branchcode, String storecode) {
		int retflag = 0;
		try {
			// 获取短信发送WEBSERVICE
			SMSService smsService = getSMSServiceClass();
			// 手机号码验证
			if (phone.length() != 11) {
				logger.error("短信发送失败，手机号码格式有误！");
				retflag = -1111;
			} else {
				// 发送短信
				String resultJSON = smsService.send(invokeSys, division,
						brandCode, dept, sendType, phone, content, date,
						branchcode, storecode);
				// 字符串转换JSON对象
				JSONObject jsonObj = JSONObject.fromObject(resultJSON);
				// 发送结果
				retflag = Integer.parseInt(jsonObj.get("result").toString());
			}
		} catch (Exception e) {
			logger.error("短信发送失败,错误信息: " + e.getMessage());
			retflag = -1001; /* 发送失败 返回-1001 */
		}
		return retflag;
	}

	public boolean isNumeric(String str) {
		boolean result = false; /* true:手机号字符串是纯数字且11位,false:手机号字符串不是纯数字或不是11位 */
		int iNum = 0; /* 长度判断 */

		iNum = str.length();
		if (iNum != 11) {
			result = false;
		} else {
			result = str.matches("[0-9]+");
		}
		return result;
	}
}
