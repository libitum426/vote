/**  
 *Copyright © 2016梦洁. All rights reserved.
 *
 * @Title: VoteResultController.java
 * @Package com.mendale.web.action.system
 * @Description: TODO
 * @author liuyang 
 * @date 2016年8月26日 下午2:33:11
 * @version V1.0  
 */
package com.mendale.web.action.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mendale.common.VOTEUtils;
import com.mendale.common.base.BaseController;
import com.mendale.service.vote.VoteSystemService;
import com.mendale.vo.vote.BiVoteDeploy;

/**   
 *
 * @Title: 投票结果页面    
 * @Description:  TODO   
 * @ClassName:  VoteResultController     
 * @author: liuyang  
 * @date:   2016年8月26日 下午2:33:11   
 *      
 */
@Controller
@RequestMapping(value = "/result")
public class VoteResultController extends BaseController {
	
	private String biServerUrl = VOTEUtils.getPropValue("biServerUrl");
	
	@Autowired
	private VoteSystemService voteSystemService;
	
	/** 
	 * 投票结果监控
	 * @param model
	 * @param deployId
	 * @return String  
	 */ 
	@RequestMapping(value = "/monitor.do")
	public String monitor(ModelMap model,String deployId) {
		BiVoteDeploy biVoteDeploy = null;
		if (null != deployId && !"".equals(deployId)) {
			BiVoteDeploy temp = new BiVoteDeploy(new Long(deployId));
			biVoteDeploy = voteSystemService.getVoteDeployById(temp);
		}
		model.addAttribute("biVoteDeploy", biVoteDeploy);
		model.addAttribute("biServerUrl", biServerUrl);
		return "/result/monitor";
	}
	
	/** 
	 * 投票结果公布
	 * @param model
	 * @param deployId
	 * @return String  
	 */ 
	@RequestMapping(value = "/publish.do")
	public String publish(ModelMap model,String deployId) {
		BiVoteDeploy biVoteDeploy = null;
		if (null != deployId && !"".equals(deployId)) {
			BiVoteDeploy temp = new BiVoteDeploy(new Long(deployId));
			biVoteDeploy = voteSystemService.getVoteDeployById(temp);
		}
		model.addAttribute("biVoteDeploy", biVoteDeploy);
		model.addAttribute("biServerUrl", biServerUrl);
		return "/result/publish";
	}

}
