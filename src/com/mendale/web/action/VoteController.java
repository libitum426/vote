/**  
 *Copyright © 2015梦洁. All rights reserved.
 *
 * @Title: VoteController.java
 * @Package com.mendale.web.action
 * @Description: TODO
 * @author liuyang 
 * @date 2015年11月28日 上午11:21:05
 * @version V1.0  
 */
package com.mendale.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendale.common.base.AjaxJson;
import com.mendale.common.util.ConstantUtil;
import com.mendale.service.vote.VoteService;
import com.mendale.vo.vote.BiVoteCandidate;
import com.mendale.vo.vote.BiVoteDeploy;
import com.mendale.vo.vote.BiVoteLogin;
import com.mendale.vo.vote.BiVoteResult;
import com.mendale.vo.vote.BiVoteUser;
import com.mendale.web.util.MessageSendUtil;

/**
 * 投票控制器
 * 
 * @Title:
 * @Description: TODO
 * @ClassName: VoteController
 * @author: liuyang
 * @date: 2015年11月28日 上午11:21:05
 * 
 */
@Controller
public class VoteController {

	private Logger log = LoggerFactory.getLogger(VoteController.class);

	@Autowired
	private VoteService voteService;

	/**
	 * 进入投票系统
	 * 
	 * @param code
	 * @param userPwd
	 * @param session
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/login.do")
	public String login(String code, String userPwd, HttpSession session,
			HttpServletResponse response, Model model) {
		String returnStr = "/login";
		if (code == null || code.equals("") || userPwd == null
				|| userPwd.equals("")) {
			model.addAttribute("code", code);
			return returnStr;
		}
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setCode(code);
		biVoteUser.setPassword(userPwd);
		BiVoteUser voteUser = null;
		if ("18229775266".equals(code)) {
			model.addAttribute("tip", "老板，别投了，您投了谁还敢投！");
			return returnStr;
		}
		try {
			voteUser = voteService.findByVO(biVoteUser);
		} catch (Exception e) {
			log.error("查询投票人用户失败,login:", e);
			model.addAttribute("tip", "系统错误,请联系管理员");
			return returnStr;
		}
		if (voteUser == null) {
			model.addAttribute("tip", "账号或投票码错误，请重新输入");
			model.addAttribute("code", code);
			return returnStr;
		} else {
			if (null == voteUser.getIsAdmin()
					|| "1".equals(voteUser.getIsAdmin())) {
				BiVoteDeploy biVoteDeploy = new BiVoteDeploy();
				biVoteDeploy.setIsDelete(new Long(0));
				biVoteDeploy.setIsFlag(new Long(0));
				List<BiVoteDeploy> biVoteDeploys = voteService
						.findVoteDeploys(biVoteDeploy);
				model.addAttribute("deploys", biVoteDeploys);
				session.setAttribute("LOGIN_USER", voteUser);
				returnStr = "/index";
			}else if("0".equals(voteUser.getIsVote())){
				model.addAttribute("tip", "投票不是买白菜！投一次就可以了，机会有且只有一次哦！");
				model.addAttribute("code", code);
				return returnStr;
			}else {
				BiVoteDeploy biVoteDeploy = new BiVoteDeploy();
				biVoteDeploy.setBrandCode(voteUser.getBrandCode());
				biVoteDeploy.setIsDelete(new Long(0));
				biVoteDeploy.setIsFlag(new Long(0));
				List<BiVoteDeploy> biVoteDeploys = voteService
						.findVoteDeploys(biVoteDeploy);
				if (null != biVoteDeploys && biVoteDeploys.size() > 0) {
					if (biVoteDeploys.size() == 1) {
						biVoteDeploy = biVoteDeploys.get(0);
						BiVoteCandidate biVoteCandidate = new BiVoteCandidate();
						biVoteCandidate.setBrandCode(biVoteDeploy
								.getBrandCode());
						biVoteCandidate.setIsDelete(0);
						List<BiVoteCandidate> voteCandidates = voteService
								.findVoteCandidates(biVoteCandidate);
						List<String> deptNames = voteService
								.findDeptNameList(biVoteCandidate);
						model.addAttribute("deptNames", deptNames);
						model.addAttribute("voteCandidates", voteCandidates);
						model.addAttribute("deploy", biVoteDeploy);
						returnStr = "/vote";
					} else {
						model.addAttribute("deploys", biVoteDeploys);
						returnStr = "/appdeploylist";
					}
				} else {
					model.addAttribute("tip", "投票还未开始,请耐心等待开始!");
					model.addAttribute("code", code);
					return returnStr;
				}
				model.addAttribute("voteUser", voteUser);
			}
			session.setAttribute(ConstantUtil.LOGIN_USER, voteUser);
		}
		return returnStr;
	}

	/**
	 * 提交投票
	 * 
	 * @param userId
	 *            投票人ID
	 * @param deployId
	 *            投票名称ID
	 * @param candId
	 *            被投票人ID
	 * @param model
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/toVote.do")
	public AjaxJson vote(String userId, String deployId, String candId,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			BiVoteDeploy biVoteDeploy = new BiVoteDeploy(new Long(deployId));
			biVoteDeploy = voteService.getVoteDeployById(biVoteDeploy);
			Date startDate = biVoteDeploy.getStartDate();
			Date endDate = biVoteDeploy.getEndDate();
			Date sysDate = new Date();
			long s = sysDate.getTime() - startDate.getTime();
			long e = endDate.getTime() - sysDate.getTime();
			if (biVoteDeploy.getIsFlag().equals(new Long(1))
					|| biVoteDeploy.getIsDelete().equals(new Long(1)) || s < 0
					|| e < 0) {
				result.fail("该投票已过期，感谢您的参与!");
				return result;
			}

			String[] candIds = candId.split(",");
			List<BiVoteResult> biVoteResults = new ArrayList<BiVoteResult>();
			for (int i = 0; i < candIds.length; i++) {
				BiVoteResult biVoteResult = new BiVoteResult();
				biVoteResult.setUserId(new Long(userId));
				biVoteResult.setDeployId(new Long(deployId));
				biVoteResult.setCandiId(new Long(candIds[i]));
				biVoteResults.add(biVoteResult);
			}
			Object lock = new Object();
			synchronized (lock) {
				BiVoteUser biVoteUser = voteService.getById(new Long(userId));
				if (0 == biVoteUser.getEntryDate()) {
					result.fail("由于你过于鲜嫩可口，就不要参与老腊肉们的竞争了!");
				} else if ("0".equals(biVoteUser.getIsDelete())) {
					result.fail("不好意思，由于你的放荡不羁爱自由，你的投票权作废了。");
				} else if ("0".equals(biVoteUser.getIsVote())) {
					result.fail("投票不是买白菜！投一次就可以了，机会有且只有一次哦！");
				} else if ("18229775266".equals(biVoteUser.getCode())) {
					result.fail("老板，别投了，您投了谁还敢投！");
				} else {
					voteService.saveVoteResult(biVoteResults, new Long(userId));
				}
			}
		} catch (Exception e) {
			result.fail("投票提交出错，请联系管理员!");
			log.error("投票提交出错, vote:", e);
		}
		return result;
	}

	/**
	 * 用户列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/userList.do")
	public String userList(BiVoteUser query, Model model, HttpSession session) {
		query.setIsAdmin("0");
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		query.setBrandCode(user.getBrandCode());
		query.setDepartCode(user.getDepartCode());
		List<BiVoteUser> biVoteUsers = voteService.findUserList(query);
		BiVoteCandidate biVoteCandidate = new BiVoteCandidate();
		List<String> deptNames = voteService.findDeptNameList(biVoteCandidate);
		model.addAttribute("deptNames", deptNames);
		model.addAttribute("biVoteUsers", biVoteUsers);
		model.addAttribute("query", query);
		return "/userlist";
	}

	/**
	 * 查询用户个人信息
	 * 
	 * @param code
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/findUserInfo.do")
	public String findUserInfo(String code, Model model) {
		String returnStr = "/findinfo";
		if(null == code || "".equals(code)){
			model.addAttribute("tip", "无此账号，请重新输入");
			return returnStr;
		}
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setCode(code);
		BiVoteUser voteUser = voteService.findVoteUserByCode(biVoteUser);
		if (null == voteUser) {
			model.addAttribute("tip", "无此账号，请重新输入");
		} else {
			SimpleDateFormat formatdate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dataTime = formatdate.format(new Date());
			StringBuilder context = new StringBuilder();

			if ("1".equals(voteUser.getIsDelete())) {
				
			} else {
//				if ("18229775266".equals(voteUser.getCode())) {
//					context.append("老板，您好！“梦洁家纺2016年财富大会”有你更嗨皮，");
//					context.append(voteUser.getLineNum()).append("桌");
//					context.append(voteUser.getColumnNum()).append(
//							"座是您的地盘儿，快来找我吧!");
//				} else {
					context.append(voteUser.getName() + "，您好！");
					context.append("“梦洁家纺2016年年度财富大会”欢迎您，您此次的座位号：");
					context.append(voteUser.getLineNum()).append("桌;请对号入座!");
//				}

//				MessageSendUtil sendUtil = new MessageSendUtil();
				try {
//					int i = sendUtil.sendCardMsg("10", "1010", "IT10", 1,
//							voteUser.getCode(), context.toString(), dataTime,
//							null, null);
//					if (i != 0) {
//						log.error("短信发送失败!失败码为:" + i);
//					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("短信发送失败!findUserInfo:", e);
					e.printStackTrace();
				}
				voteUser.setIsDelete("1");
				voteService.updateIsDelete(voteUser);
			}
			model.addAttribute("voteUser", voteUser);
			returnStr = "/userinfo";
		}
		return returnStr;
	}

	/**
	 * 查找个人信息页面
	 * 
	 * @param code
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/findInfo.do")
	public String findInfo(String code, Model model) {
		return "/findinfo";
	}

	@RequestMapping(value = "/thanksVote.do")
	public String thanksVote(String code, Model model) {
		return "/thanksvote";
	}

	@RequestMapping(value = "/voteList.do")
	public String voteList(String code, Model model) {
		return "/votelist";
	}

	/**
	 * 根据工号查询照片
	 * 
	 * @param jobNum
	 * @param model
	 */
	@ResponseBody
	@RequestMapping(value = "/queryEmpPhoto.do")
	public void queryEmpPhoto(String jobNum, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		BiVoteCandidate biVoteCandidate = new BiVoteCandidate();
		biVoteCandidate.setJobNum(jobNum);
		resultmap = voteService.queryEmpPhotoByJobNum(biVoteCandidate);// 数据库查询
		if (null == resultmap || resultmap.isEmpty()) {
			BiVoteCandidate temp = new BiVoteCandidate();
			temp.setJobNum("bi");
			resultmap = voteService.queryEmpPhotoByJobNum(temp);// 数据库查询
		}
		oracle.sql.BLOB blob = (oracle.sql.BLOB) resultmap.get("EMP_PHOTO");

		response.setContentType("image/png;charset=UTF-8");

		response.setCharacterEncoding("UTF-8");

		InputStream inStream = null;

		OutputStream op = null;

		byte[] data;

		long nLen = 0;

		try {

			try {

				inStream = blob.getBinaryStream();

				nLen = blob.length();

				int nSize = (int) nLen;

				data = new byte[nSize];

				inStream.read(data);// 将输入流中的数据读到数组中

				op = response.getOutputStream();

				op.write(data);// 直接显示到网页上

				op.flush();

				op.close();

				inStream.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			log.error("获取图片数据失败,原因:", e1);
			System.out.println("" + e1.getMessage());

		}

	}

}
