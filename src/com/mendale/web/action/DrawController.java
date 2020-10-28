/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: DrawController.java
 * @Package com.mendale.web.action
 * @Description: TODO
 * @author liuyang 
 * @date 2016年1月25日 上午9:50:28
 * @version V1.0  
 */
package com.mendale.web.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendale.common.base.AjaxJson;
import com.mendale.common.base.BaseController;
import com.mendale.service.vote.BiVoteDrawResultService;
import com.mendale.service.vote.BiVoteDrawService;
import com.mendale.service.vote.BiVoteDrawconfigService;
import com.mendale.service.vote.VoteSystemService;
import com.mendale.vo.vote.BiVoteDraw;
import com.mendale.vo.vote.BiVoteDrawResult;
import com.mendale.vo.vote.BiVoteDrawconfig;
import com.mendale.vo.vote.BiVoteUser;
import com.mendale.web.util.MessageSendUtil;

/**
 * 抽奖控制类
 * 
 * @Title:
 * @Description: TODO
 * @ClassName: DrawController
 * @author: liuyang
 * @date: 2016年1月25日 上午9:50:28
 * 
 */
@Controller
public class DrawController{

	private Logger log = LoggerFactory.getLogger(DrawController.class);
	@Autowired
	private BiVoteDrawconfigService biVoteDrawconfigService;
	@Autowired
	private VoteSystemService voteSystemService;
	@Autowired
	private BiVoteDrawResultService biVoteDrawResultService;
	@Autowired
	private BiVoteDrawService biVoteDrawService;

	private List<BiVoteUser> biVoteUsers = new ArrayList<BiVoteUser>();

	/**
	 * 抽奖配置列表
	 * 
	 * @param biVoteDrawconfig
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/drawList.do")
	public String drawList(BiVoteDraw biVoteDraw, Model model) {
		biVoteDraw.setIsFlag(new Long("0"));
		biVoteDraw.setIsDelete(new Long("0"));
		List<BiVoteDraw> draws = biVoteDrawService.queryList(biVoteDraw);
		model.addAttribute("draws", draws);
		return "/drawlist";
	}

	/**
	 * 抽奖配置列表
	 * 
	 * @param biVoteDrawconfig
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/comeDraw.do")
	public String comeDraw(String drawId, Model model) {
		model.addAttribute("drawId", drawId);
		return "/comedraw";
	}

	/**
	 * 获取数据
	 * 
	 * @param drawId
	 * @param model
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/getComeDrawData.do")
	public AjaxJson getComeDrawData(String drawId, Model model) {
		AjaxJson result = new AjaxJson();
		BiVoteDrawconfig temp = new BiVoteDrawconfig();
		BiVoteDrawconfig temp1 = new BiVoteDrawconfig();
		temp.setIsActive("0");
		temp.setIsDelete("0");
		temp.setIsDraw("1");
		temp.setDrawId(new Long(drawId));
		temp1.setIsActive("0");
		temp1.setIsDelete("0");
		temp1.setDrawId(new Long(drawId));
		List<BiVoteDrawconfig> drawconfigs = biVoteDrawconfigService
				.queryList(temp);
		List<BiVoteDrawconfig> oldConfigs = biVoteDrawconfigService
				.queryList(temp1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("results", drawconfigs);
		map.put("oldConfigs", oldConfigs);
		result.setAttributes(map);
		return result;
	}

	/**
	 * 保存抽奖结果
	 * 
	 * @param model
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/updateComeDrawData.do")
	public AjaxJson updateComeDrawData(String drawId, Model model) {
		AjaxJson result = new AjaxJson();
		BiVoteDrawconfig drawconfig = biVoteDrawconfigService.getById(new Long(
				drawId));
		if ("1".equals(drawconfig.getIsActive())) {
			result.fail("该奖项已抽完或已禁用,重新刷新数据!");
			return result;
		}
		long oldNum = drawconfig.getNum();
		drawconfig.setNum(oldNum + 1);
		try {
			biVoteDrawconfigService.update(drawconfig);
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("操作出错, updateDrawData:", e);
		}
		return result;
	}

	/**
	 * 奖项配置列表
	 * 
	 * @param biVoteDrawconfig
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/drawConfigList.do")
	public String drawConfigList(BiVoteDrawconfig biVoteDrawconfig, Model model) {
		biVoteDrawconfig.setIsActive("0");
		biVoteDrawconfig.setIsDelete("0");
		List<BiVoteDrawconfig> drawconfigs = biVoteDrawconfigService
				.queryList(biVoteDrawconfig);
		model.addAttribute("drawconfigs", drawconfigs);
		return "/drawconfiglist";
	}

	/**
	 * 进入抽奖页面
	 * 
	 * @param drawId
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/operaDraw.do")
	public String operaDraw(String drawId, Model model) {
		if (null == drawId || "".equals(drawId)) {
			return "/drawList.do";
		}
		BiVoteDrawconfig drawconfig = biVoteDrawconfigService.getById(new Long(
				drawId));
		if ("1".equals(drawconfig.getIsActive())) {
			return "/drawList.do";
		}
		model.addAttribute("drawconfig", drawconfig);
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setIsDraw("0");
		biVoteUser.setIsDelete("1");
		biVoteUsers = voteSystemService.findUserList(biVoteUser);
		model.addAttribute("biVoteUsers", biVoteUsers);
		return "/operadraw";
	}
	
	/**
	 * 进入抽奖页面
	 * 
	 * @param drawId
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/operaMoreDraw.do")
	public String operaMoreDraw(String drawId, Model model) {
		if (null == drawId || "".equals(drawId)) {
			return "/drawList.do";
		}
		BiVoteDrawconfig drawconfig = biVoteDrawconfigService.getById(new Long(
				drawId));
		if ("1".equals(drawconfig.getIsActive())) {
			return "/drawList.do";
		}
		model.addAttribute("drawconfig", drawconfig);
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setIsDraw("0");
		biVoteUser.setIsDelete("1");
		biVoteUsers = voteSystemService.findUserList(biVoteUser);
		model.addAttribute("biVoteUsers", biVoteUsers);
		return "/operamoredraw";
	}

	/**
	 * 获取数据
	 * 
	 * @param model
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/getDrawData.do")
	public AjaxJson getDrawData(String drawId, Model model,HttpServletRequest request) {
		request.getSession().removeAttribute("biVoteUsers");
		AjaxJson result = new AjaxJson();
		BiVoteDrawconfig drawconfig = biVoteDrawconfigService.getById(new Long(
				drawId));
		if ("1".equals(drawconfig.getIsActive())) {
			result.fail("该奖项未启动!");
			return result;
		}
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setIsDraw("0");
		biVoteUser.setIsDelete("1");
		biVoteUsers = voteSystemService.findDrawUserList(biVoteUser);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("drawconfig", drawconfig);
		map.put("biVoteUsers", biVoteUsers);
		result.setAttributes(map);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/randomDraw.do")
	public AjaxJson randomDraw(Integer everyNum,Model model,HttpServletRequest request) {
		AjaxJson result = new AjaxJson();
		List<BiVoteUser> biVoteUsers = new ArrayList<BiVoteUser>();
		BiVoteUser biVoteUser = new BiVoteUser();
		biVoteUser.setIsDraw("0");
		biVoteUser.setIsDelete("1");
		List<BiVoteUser> voteUsers = voteSystemService.findDrawUserList(biVoteUser);
		Integer weightSum = voteSystemService.findWeightSum(biVoteUser);
		for (int i = 1; i <= everyNum; i++) {
			Random random = new Random();
			Integer n = random.nextInt(weightSum); // n in [0, weightSum)
			Integer m = 0;
			for (BiVoteUser wc : voteUsers) {
				if (m <= n && n < m + wc.getWeightNum()) {
					biVoteUsers.add(wc);
					voteUsers.remove(wc);
					break;
				}
				m += wc.getWeightNum();
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("biVoteUsers", biVoteUsers);
		result.setAttributes(map);
		return result;
	}

	/**
	 * 保存抽奖结果
	 * 
	 * @param model
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDrawData.do")
	public AjaxJson updateDrawData(String drawId, String userIds, Model model) {
		AjaxJson result = new AjaxJson();
		BiVoteDrawconfig drawconfig = biVoteDrawconfigService.getById(new Long(
				drawId));
		if ("1".equals(drawconfig.getIsActive())) {
			result.fail("该奖项已抽完或已禁用,重新刷新数据!");
			return result;
		}

		try {
			List<String> str = new ArrayList<String>();
			String[] strs = userIds.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}

			if (drawconfig.getDrawNum() > drawconfig.getEveryNum()
					* drawconfig.getNum() + str.size()) {
				drawconfig.setNum(drawconfig.getNum() + 1);
			} else {
				drawconfig.setNum(drawconfig.getNum() + 1);
				drawconfig.setIsActive("1");
			}

			List<BiVoteUser> biVoteUsers = biVoteDrawResultService
					.insertDrawRest(str, drawconfig);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("biVoteUsers", biVoteUsers);
			result.setAttributes(map);
			if (null != biVoteUsers && biVoteUsers.size() > 0) {
				result.setObj(biVoteUsers);
				for (BiVoteUser drawUser : biVoteUsers) {
					StringBuilder context = new StringBuilder();
					context.append(drawUser.getName());
					context.append("：您的运气太好啦！中了《");
					context.append(drawconfig.getName());
					context.append("》哦,领奖码为：");
					context.append(drawUser.getPassword());
					context.append("；请牢记并保存好该号码，凭手机号和领奖码到领奖台来领取奖品吧！");
					this.sendMessages(drawUser.getCode(), context.toString());
				}

			} else {
				result.fail("有中奖人员已中一次奖项，请刷新后重新抽奖!");
			}
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("操作出错, updateDrawData:", e);
		}
		return result;
	}

	/**
	 * 查询中奖人员信息
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/queryDrawUsers.do")
	public String queryDrawUsers(Model model) {
		List<BiVoteUser> biVoteUsers = biVoteDrawResultService
				.queryDrawUsersResult();
		model.addAttribute("biVoteUsers", biVoteUsers);
		return "/drawUserList";
	}
	
	/** 
	 * 中奖人员领奖
	 * @param drawResultId
	 * @param model
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/updateDrawResult.do")
	public AjaxJson updateDrawResult(Long drawResultId,Model model) {
		AjaxJson result = new AjaxJson();
		BiVoteDrawResult drawResult = new BiVoteDrawResult(drawResultId);
		drawResult.setDrawStatus(new Long(1));
		boolean flag = biVoteDrawResultService.update(drawResult);
		if(flag){
			result.setMsg("领奖成功!");
		}else{
			result.fail("操作失败!");
		}
		return result;
	}

	/**
	 * 导出
	 * 
	 * @param excel
	 *            路径
	 * @return
	 */
	@RequestMapping(value = "/exp.do")
	public void exp(ModelMap model, HttpServletResponse response) {
		List<BiVoteUser> biVoteUsers = biVoteDrawResultService
				.queryDrawUsersResult();
		try {
			OutputStream os4 = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment; filename=mendaleVote.xls");
			WritableWorkbook wwb = null;
			wwb = Workbook.createWorkbook(os4);
			if (wwb != null) {
				if (null != biVoteUsers && biVoteUsers.size() > 0) {
					WritableSheet ws = wwb.createSheet("中奖人员名单", 0);
					Label labelC = null;
					labelC = new Label(0, 0, "品牌名称");
					ws.addCell(labelC);
					labelC = new Label(1, 0, "部门名称");
					ws.addCell(labelC);
					labelC = new Label(2, 0, "手机号");
					ws.addCell(labelC);
					labelC = new Label(3, 0, "姓名");
					ws.addCell(labelC);
					labelC = new Label(4, 0, "工号");
					ws.addCell(labelC);
					labelC = new Label(5, 0, "中奖等级");
					ws.addCell(labelC);
					labelC = new Label(6, 0, "领奖码");
					ws.addCell(labelC);
					for (int i = 0; i < biVoteUsers.size(); i++) {
						BiVoteUser user = biVoteUsers.get(i);
						labelC = new Label(0, i + 1, user.getBrandName());
						ws.addCell(labelC);
						labelC = new Label(1, i + 1, user.getDepartName());
						ws.addCell(labelC);
						labelC = new Label(2, i + 1, user.getCode());
						ws.addCell(labelC);
						labelC = new Label(3, i + 1, user.getName());
						ws.addCell(labelC);
						labelC = new Label(4, i + 1, user.getJobNum());
						ws.addCell(labelC);
						labelC = new Label(5, i + 1, user.getDrawName());
						ws.addCell(labelC);
						labelC = new Label(6, i + 1, user.getPassword());
						ws.addCell(labelC);
					}
				}
			}

			try {
				// 关闭资源，释放内存
				wwb.write();
				wwb.close();
				os4.flush();
				os4.close();
			} catch (Exception e) {
				// e.printStackTrace();
				log.error("导出出错！,exp", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("导出出错,exp", e);
		}
	}

	/**
	 * 发送短信方法
	 * 
	 * @param code
	 * @param content
	 *            void
	 */
	private void sendMessages(String code, String content) {
		SimpleDateFormat formatdate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dataTime = formatdate.format(new Date());
		MessageSendUtil sendUtil = new MessageSendUtil();
		try {
			int i = sendUtil.sendCardMsg("10", "1010", "IT10", 1, code,
					content, dataTime, null, null);
			if (i != 0) {
				log.error("短信发送失败!失败码为:" + i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("短信发送失败!findUserInfo:", e);
			e.printStackTrace();
		}
	}

}
