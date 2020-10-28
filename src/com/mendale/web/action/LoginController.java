package com.mendale.web.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendale.common.base.AjaxJson;
import com.mendale.common.util.ConstantUtil;
import com.mendale.service.vote.BiVoteLoginService;
import com.mendale.vo.vote.BiVoteLogin;

/**
 * <p> Title: 用户登录</p>
 * <p> Description: </p>
 * @作者 jz
 * @创建时间 2016-1-4 下午2:42:53
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class); 
    @Autowired
    private BiVoteLoginService biVoteLoginService;
    
    /**
	 * 登录逻辑判断
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminLogin.do")
	public String login(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String userPwd,
			HttpSession session, Model model, String source) {
		String returnStr = "/adminLogin";
		String login_flag = "success";
//		if(StringUtils.isNotEmpty(source)) {
//			returnStr = "/vpn_login";
//		}
		
		try {
			BiVoteLogin user = (BiVoteLogin) session.getAttribute(ConstantUtil.LOGIN_USER);
			if (user == null) {
				if (StringUtils.isEmpty(userName)
						|| StringUtils.isEmpty(userPwd)) {
					model.addAttribute("tip", "登陆失败，请重新输入");
					login_flag = "fail";
					model.addAttribute("login_flag", login_flag);
					return returnStr;
				}

				user = new BiVoteLogin();
				user.setCode(userName);
				user.setIsDelete("0");
				if (!biVoteLoginService.isExistsUser(user)) {
					model.addAttribute("tip", "用户不存在，请联系管理员！");
					login_flag = "fail";
					model.addAttribute("login_flag", login_flag);
					return returnStr;
				}

				user.setPassword(userPwd);
				List<BiVoteLogin> lstUser = biVoteLoginService.checkUserPassword(user);
				if (lstUser == null || lstUser.size() == 0) {
					model.addAttribute("tip", "密码不正确！");
					model.addAttribute("userName", userName);
					login_flag = "fail";
					model.addAttribute("login_flag", login_flag);
					return returnStr;
				}
				user = lstUser.get(0);
			}
			
			session.setAttribute(ConstantUtil.LOGIN_USER, user);
			return "/index";
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.toString());
			login_flag = "fail";
			model.addAttribute("tip", "登陆失败，请重新输入！");
			model.addAttribute("login_flag", login_flag);
		}
		return returnStr;
	}

	/**
	 * 注销
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginout.do", method = RequestMethod.POST)
	public @ResponseBody AjaxJson loginOut(HttpSession session) throws Exception {
		AjaxJson result = new AjaxJson();
		// 用户会话清空 返回登录页面
		session.removeAttribute(ConstantUtil.LOGIN_USER);
		return result;
	}
}
