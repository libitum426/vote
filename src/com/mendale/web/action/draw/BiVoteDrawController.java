/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: BiVoteDrawController.java
 * @Package com.mendale.web.action.draw
 * @Description: TODO
 * @author liuyang 
 * @date 2016年9月28日 上午10:51:08
 * @version V1.0  
 */
package com.mendale.web.action.draw;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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

/**   
 * 抽奖配置
 * @Title:     
 * @Description:  TODO   
 * @ClassName:  BiVoteDrawController     
 * @author: liuyang  
 * @date:   2016年9月28日 上午10:51:08   
 *      
 */
@Controller
@RequestMapping(value = "/drawz")
public class BiVoteDrawController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(BiVoteDrawController.class);
	
	@Autowired
	private BiVoteDrawService biVoteDrawService;
	
	@Autowired
	private BiVoteDrawconfigService biVoteDrawconfigService;
	
	/** 
	 * 抽奖配置列表
	 * @param query
	 * @param model
	 * @param session
	 * @return String  
	 */ 
	@RequestMapping(value = "/drawzList.do")
	public String drawzList(BiVoteDraw query, Model model, HttpSession session){
		BiVoteLogin user = (BiVoteLogin) session.getAttribute(ConstantUtil.LOGIN_USER);
		if(!"admin".equals(user.getCode())){
			query.setCreatedBy(user.getCode());
		}
		List<BiVoteDraw> biVoteDraws = biVoteDrawService.queryList(query);
		model.addAttribute("biVoteDraws", biVoteDraws);
		model.addAttribute("query", query);
		return "/draw/drawzlist";
	}
	
	/** 
	 * 跳转到编辑页面
	 * @param model
	 * @param session
	 * @param drawId
	 * @return String  
	 */ 
	@RequestMapping(value = "/toEditDrawz.do")
	public String toEditDraw(Model model, HttpSession session,String drawId){
		BiVoteDraw biVoteDraw = new BiVoteDraw();
		if(null != drawId){
			biVoteDraw = biVoteDrawService.getById(new Long(drawId));
		}
		model.addAttribute("biVoteDraw", biVoteDraw);
		return "/draw/editdrawz";
	}
	
	/** 
	 * 编辑抽奖配置
	 * @param biVoteDrawconfig
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/editDrawz.do")
	public AjaxJson editDraw(BiVoteDraw biVoteDraw, HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			if(null != biVoteDraw.getDrawId() && !"".equals(biVoteDraw.getDrawId())){
				biVoteDrawService.update(biVoteDraw);
			}else{
				biVoteDrawService.save(biVoteDraw);
			}
		} catch (Exception e) {
			result.fail("编辑抽奖配置出错，请联系管理员!");
			log.error("编辑抽奖配置出错, editDraw:", e);
		}
		return result;
	}
	
	/** 
	 * 批量启用或禁用抽奖配置
	 * @param userId
	 * @param flag
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/operaDrawz.do")
	public AjaxJson operaDrawz(String drawId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		String msg = "";
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = drawId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
				BiVoteDraw biVoteDraw = biVoteDrawService.getById(new Long(strs[j]));
				if("0".equals(flag) && 1 == biVoteDraw.getDrawType()){
					BiVoteDrawconfig tmpData = new BiVoteDrawconfig();
					tmpData.setDrawId(new Long(strs[j]));
					tmpData.setIsDelete("0");
					tmpData.setIsActive("0");
					int count =  biVoteDrawconfigService.queryCount(tmpData);
					if(count <= 0){
						msg=msg+biVoteDraw.getDrawTitle()+",没有奖项配置请先添加奖项然后启动;";
					}
				}
			}
			if(StringUtils.isNotEmpty(msg)){
				result.fail(msg);
				return result;
			}
			int i = biVoteDrawService.operaDrawz(str, flag);
			if (i > 0) {
				result.setMsg("操作成功，总共操作：" + i);
			} else {
				result.fail("操作失败!");
			}
		} catch (Exception e) {
			result.fail("操作出错，请联系管理员!");
			log.error("批量启用或禁用抽奖配置出错, operaDrawz:", e);
		}
		return result;
	}

}
