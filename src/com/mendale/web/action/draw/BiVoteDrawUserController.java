/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawUserController.java
 * @Package com.mendale.web.action.draw
 * @Description: TODO
 * @author liuyang 
 * @date 2016年9月30日 下午3:29:55
 * @version V1.0  
 */
package com.mendale.web.action.draw;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mendale.common.base.AjaxJson;
import com.mendale.common.base.BaseController;
import com.mendale.common.util.ConstantUtil;
import com.mendale.service.vote.BiVoteDrawService;
import com.mendale.service.vote.BiVoteDrawUserService;
import com.mendale.vo.vote.BiVoteDraw;
import com.mendale.vo.vote.BiVoteDrawUser;
import com.mendale.vo.vote.BiVoteLogin;

/**   
 *
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawUserController     
 * @author: liuyang  
 * @date:   2016年9月30日 下午3:29:55   
 *      
 */
@Controller
@RequestMapping(value = "/drawuser")
public class BiVoteDrawUserController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(BiVoteDrawUserController.class);
	
	@Autowired
	private BiVoteDrawUserService biVoteDrawUserService;
	
	@Autowired
	private BiVoteDrawService biVoteDrawService;
	
	/** 
	 * 抽奖配置列表
	 * @param query
	 * @param model
	 * @param session
	 * @return String  
	 */ 
	@RequestMapping(value = "/drawUserList.do")
	public String drawUserList(BiVoteDrawUser query, Model model, HttpSession session){
		BiVoteLogin user = (BiVoteLogin) session.getAttribute(ConstantUtil.LOGIN_USER);
		if(!"admin".equals(user.getCode())){
			query.setCreatedBy(user.getCode());
		}
		List<BiVoteDrawUser> biVoteDrawUsers = biVoteDrawUserService.queryList(query);
		model.addAttribute("biVoteDrawUsers", biVoteDrawUsers);
		model.addAttribute("query", query);
		return "/draw/drawuserlist";
	}
	
	/** 
	 * 跳转到编辑页面
	 * @param model
	 * @param session
	 * @param drawId
	 * @return String  
	 */ 
	@RequestMapping(value = "/toEditDrawUser.do")
	public String toEditDrawUser(Model model, HttpSession session,String userId){
		BiVoteDrawUser drawUser = new BiVoteDrawUser();
		if(null != userId){
			drawUser = biVoteDrawUserService.getById(new Long(userId));
		}
		BiVoteDraw tempDraw = new BiVoteDraw();
		tempDraw.setIsDelete(new Long(0));
		tempDraw.setIsFlag(new Long(0));
		List<BiVoteDraw> draws = biVoteDrawService.queryList(tempDraw);
		model.addAttribute("drawUser", drawUser);
		model.addAttribute("draws", draws);
		return "/draw/editdrawuser";
	}
	
	/** 
	 * 编辑抽奖配置
	 * @param biVoteDrawconfig
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/editDrawUser.do")
	public AjaxJson editDrawUser(BiVoteDrawUser biVoteDrawUser, HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			if(null != biVoteDrawUser.getUserId() && !"".equals(biVoteDrawUser.getUserId())){
				biVoteDrawUserService.update(biVoteDrawUser);
			}else{
				biVoteDrawUserService.save(biVoteDrawUser);
			}
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("编辑抽奖用户出错, editDrawUser:", e);
		}
		return result;
	}
	
	/** 
	 * 批量启用或禁用抽奖用户
	 * @param userId
	 * @param flag
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/operaDrawUser.do")
	public AjaxJson operaDrawUser(String drawId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = drawId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = biVoteDrawUserService.operaDrawUser(str, flag);
			if (i > 0) {
				result.setMsg("操作成功，总共操作：" + i);
			} else {
				result.fail("操作失败!");
			}
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("批量启用或禁用抽奖用户出错, operaDrawUser:", e);
		}
		return result;
	}

}
