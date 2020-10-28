/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawconfigController.java
 * @Package com.mendale.web.action.draw
 * @Description: TODO
 * @author liuyang 
 * @date 2016年1月22日 下午2:14:23
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
import com.mendale.service.vote.BiVoteDrawconfigService;
import com.mendale.vo.vote.BiVoteDraw;
import com.mendale.vo.vote.BiVoteDrawconfig;
import com.mendale.vo.vote.BiVoteLogin;
import com.mendale.vo.vote.BiVoteUser;

/**   
 * 奖项配置控制类
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawconfigController     
 * @author: liuyang  
 * @date:   2016年1月22日 下午2:14:23   
 *      
 */
@Controller
@RequestMapping(value = "/draw")
public class BiVoteDrawconfigController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(BiVoteDrawconfigController.class);
	
	@Autowired
	private BiVoteDrawconfigService biVoteDrawconfigService;
	@Autowired
	private BiVoteDrawService biVoteDrawService;
	
	/** 
	 * 奖项配置列表
	 * @param query
	 * @param model
	 * @param session
	 * @return String  
	 */ 
	@RequestMapping(value = "/drawconfigList.do")
	public String drawconfigList(BiVoteDrawconfig query, Model model, HttpSession session){
		BiVoteLogin user = (BiVoteLogin) session.getAttribute(ConstantUtil.LOGIN_USER);
		if(!"admin".equals(user.getCode())){
			query.setCreatedBy(user.getCode());
		}
		List<BiVoteDrawconfig> biVoteDrawconfigs = biVoteDrawconfigService.queryList(query);
		BiVoteDraw tempdata = new BiVoteDraw();
		tempdata.setIsDelete(new Long(0));
		List<BiVoteDraw> biVoteDraws = biVoteDrawService.queryList(tempdata);
		model.addAttribute("biVoteDraws", biVoteDraws);
		model.addAttribute("biVoteDrawconfigs", biVoteDrawconfigs);
		model.addAttribute("query", query);
		return "/draw/drawlist";
	}
	
	/** 
	 * 跳转到编辑页面
	 * @param model
	 * @param session
	 * @param drawId
	 * @return String  
	 */ 
	@RequestMapping(value = "/toEditDraw.do")
	public String toEditDraw(Model model, HttpSession session,String drawId){
		BiVoteDrawconfig biVoteDrawconfig = new BiVoteDrawconfig();
		if(null != drawId){
			biVoteDrawconfig = biVoteDrawconfigService.getById(new Long(drawId));
		}
		BiVoteDraw tempdata = new BiVoteDraw();
		tempdata.setIsDelete(new Long(0));
		List<BiVoteDraw> biVoteDraws = biVoteDrawService.queryList(tempdata);
		model.addAttribute("biVoteDraws", biVoteDraws);
		model.addAttribute("drawConfig", biVoteDrawconfig);
		return "/draw/editdraw";
	}
	
	/** 
	 * 编辑奖项配置
	 * @param biVoteDrawconfig
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/editDraw.do")
	public AjaxJson editDraw(BiVoteDrawconfig biVoteDrawconfig, HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			if(null != biVoteDrawconfig.getId() && !"".equals(biVoteDrawconfig.getId())){
				biVoteDrawconfigService.update(biVoteDrawconfig);
			}else{
				biVoteDrawconfigService.save(biVoteDrawconfig);
			}
		} catch (Exception e) {
			result.fail("编辑奖项配置出错，请联系管理员!");
			log.error("考编辑奖项配置出错, editDraw:", e);
		}
		return result;
	}
	
	/** 
	 * 批量启用或禁用奖项配置
	 * @param userId
	 * @param flag
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/operaDrawconfig.do")
	public AjaxJson operaDrawconfig(String userId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = userId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = biVoteDrawconfigService.operaDrawconfig(str, flag);
			if (i > 0) {
				result.setMsg("操作成功，总共操作：" + i);
			} else {
				result.fail("操作失败!");
			}
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("批量启用或禁用奖项配置出错, operaDrawconfig:", e);
		}
		return result;
	}
	
}
