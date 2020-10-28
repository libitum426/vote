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
package com.mendale.web.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mendale.common.base.AjaxJson;
import com.mendale.common.base.BaseController;
import com.mendale.common.util.ConstantUtil;
import com.mendale.service.vote.VoteSystemService;
import com.mendale.vo.vote.BiVoteBrand;
import com.mendale.vo.vote.BiVoteCandidate;
import com.mendale.vo.vote.BiVoteDepart;
import com.mendale.vo.vote.BiVoteDeploy;
import com.mendale.vo.vote.BiVoteLogin;
import com.mendale.vo.vote.BiVoteUser;

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
@RequestMapping(value = "/system")
public class VoteSystemController extends BaseController {

	private Logger log = LoggerFactory.getLogger(VoteSystemController.class);

	@Autowired
	private VoteSystemService voteSystemService;

	/**
	 * 用户列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/userList.do")
	public String userList(BiVoteUser query, Model model, HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			query.setBrandCode(user.getBrandCode());
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteUser> biVoteUsers = voteSystemService.findUserList(query);
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("biVoteUsers", biVoteUsers);
		model.addAttribute("query", query);
		return "/system/userlist";
	}

	/**
	 * 跳转至新增投票配置页面
	 * 
	 * @param code
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/toAddDeploy.do")
	public String toAddDeploy(String deployId, Model model,HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		BiVoteDeploy biVoteDeploy = null;
		if (null != deployId && !"".equals(deployId)) {
			BiVoteDeploy temp = new BiVoteDeploy(new Long(deployId));
			biVoteDeploy = voteSystemService.getVoteDeployById(temp);
		}
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("biVoteDeploy", biVoteDeploy);
		return "/system/adddeploy";
	}
	
	/**
	 * 编辑和添加投票配置
	 * 
	 * @param biVoteDepart
	 * @param model
	 * @param session
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/addDeploy.do")
	public AjaxJson addDeploy(BiVoteDeploy biVoteDeploy, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		if (null != biVoteDeploy) {
			try {
				voteSystemService.saveOrUpdateDeploy(biVoteDeploy, user);
			} catch (Exception e) {
				log.error("编辑和添加部门出错，addDepart", e);
				result.fail("操作失败!");
			}
		} else {
			log.error("参数为空!");
			result.fail("操作失败!");
		}
		return result;
	}

	/**
	 * 考勤方法
	 * 
	 * @param userId
	 * @param reqeust
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/kaoqing.do")
	public AjaxJson kaoqing(String userId,String flag, HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			BiVoteUser biVoteUser = new BiVoteUser(new Long(userId));
			biVoteUser.setIsDelete(flag);
			voteSystemService.updateIsDelete(biVoteUser);
		} catch (Exception e) {
			result.fail("考勤提交出错，请联系管理员!");
			log.error("考勤提交出错, kaoqing:", e);
		}
		return result;
	}
	
	/** 
	 * 初始化投票人数据
	 * @param reqeust
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/initUserData.do")
	public AjaxJson initUserData(HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteUser temp = new BiVoteUser();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		try {
			if(null != user && !"".equals(user.getBrandCode())){
				temp.setBrandCode(user.getBrandCode());
			}
			voteSystemService.initUserData(temp);
		} catch (Exception e) {
			result.fail("初始化投票人数据出错，请联系管理员!");
			log.error("初始化投票人数据出错, initUserData:", e);
		}
		return result;
	}

	/**
	 * 批量更新用户权限
	 * 
	 * @param userId
	 *            批量用户ID
	 * @param flag
	 *            1 有权限 0 无权限
	 * @param reqeust
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/addVote.do")
	public AjaxJson addVote(String userId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = userId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = voteSystemService.updateUserVotes(str, flag);
			if (i > 0) {
				result.setMsg("更新成功，总共更新：" + i);
			} else {
				result.fail("更新失败!");
			}
		} catch (Exception e) {
			result.fail("权限更新出错，请联系管理员!");
			log.error("权限更新出错, addVote:", e);
		}
		return result;
	}

	/**
	 * 跳转至投票配置列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/deployList.do")
	public String deployList(BiVoteDeploy query, Model model, HttpSession session) {
		query.setIsDelete(new Long(0));
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			query.setBrandCode(user.getBrandCode());
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteDeploy> biVoteDeploys = voteSystemService
				.findVoteDeploys(query);
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("query", query);
		model.addAttribute("biVoteDeploys", biVoteDeploys);
		return "/system/deploylist";
	}

	/**
	 * 批量冻结和启动投票配置
	 * 
	 * @param userId
	 * @param flag
	 * @param reqeust
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/changeDeploy.do")
	public AjaxJson changeDeploy(String userId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = userId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = voteSystemService.updateVoteDeploys(str, flag);
			if (i > 0) {
				result.setMsg("更新成功，总共更新：" + i);
			} else {
				result.fail("更新失败!");
			}
		} catch (Exception e) {
			result.fail("投票配置更新出错，请联系管理员!");
			log.error("投票配置更新出错, changeDeploy:", e);
		}
		return result;
	}

	/**
	 * 品牌列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/brandList.do")
	public String brandList(BiVoteBrand query, Model model, HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		query.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			query.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(query);
		int count = voteSystemService.queryBrandCount(query);
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("count", count);
		model.addAttribute("query", query);
		return "/system/brandlist";
	}

	/**
	 * 转入添加编辑界面
	 * 
	 * @param query
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/toAddBrand.do")
	public String toAddBrand(BiVoteBrand biVoteBrand, Model model,
			HttpSession session) {
		if (null != biVoteBrand && null != biVoteBrand.getId()) {
			biVoteBrand = voteSystemService.queryBrandById(biVoteBrand);
		}
		model.addAttribute("biVoteBrand", biVoteBrand);
		return "/system/editbrand";
	}

	/**
	 * 编辑和添加品牌
	 * 
	 * @param biVoteBrand
	 * @param model
	 * @param session
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/addBrand.do")
	public AjaxJson addBrand(BiVoteBrand biVoteBrand, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		if (null != biVoteBrand) {
			try {
				voteSystemService.editBrand(biVoteBrand, user);
			} catch (Exception e) {
				log.error("编辑和添加品牌出错，addBrand", e);
				result.fail("操作失败!");
			}
		} else {
			log.error("参数为空!");
			result.fail("操作失败!");
		}
		return result;
	}

	/**
	 * 批量删除品牌数据
	 * 
	 * @param brandId
	 * @param model
	 * @param session
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBrand.do")
	public AjaxJson deleteBrand(String brandId, Model model, HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = brandId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = voteSystemService.updateBrands(str, user.getCode());
			if (i > 0) {
				result.setMsg("删除成功，总共删除：" + i + "条数据!");
			} else {
				result.fail("删除失败!");
			}
		} catch (Exception e) {
			result.fail("品牌数据批量删除出错，请联系管理员!");
			log.error("品牌数据批量删除出错, deleteBrand:", e);
		}
		return result;
	}

	/**
	 * 部门列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/deptList.do")
	public String deptList(BiVoteDepart query, Model model, HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		query.setIsDelete("0");
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			query.setBrandCode(user.getBrandCode());
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		List<BiVoteDepart> biVoteDeparts = voteSystemService
				.queryDepartList(query);
		int count = voteSystemService.queryDepartCount(query);
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("biVoteDeparts", biVoteDeparts);
		model.addAttribute("count", count);
		model.addAttribute("query", query);
		return "/system/departlist";
	}

	/**
	 * 转入添加编辑界面
	 * 
	 * @param query
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/toAddDepart.do")
	public String toAddDepart(BiVoteDepart biVoteDepart, Model model,
			HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		if (null != biVoteDepart && null != biVoteDepart.getId()) {
			biVoteDepart = voteSystemService.queryDepartById(biVoteDepart);
		}
		model.addAttribute("biVoteDepart", biVoteDepart);
		model.addAttribute("biVoteBrands", biVoteBrands);
		return "/system/editdepart";
	}

	/**
	 * 编辑和添加部门
	 * 
	 * @param biVoteDepart
	 * @param model
	 * @param session
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/addDepart.do")
	public AjaxJson addDepart(BiVoteDepart biVoteDepart, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		if (null != biVoteDepart) {
			try {
				voteSystemService.editDepart(biVoteDepart, user);
			} catch (Exception e) {
				log.error("编辑和添加部门出错，addDepart", e);
				result.fail("操作失败!");
			}
		} else {
			log.error("参数为空!");
			result.fail("操作失败!");
		}
		return result;
	}

	/**
	 * 批量删除品牌数据
	 * 
	 * @param departId
	 * @param model
	 * @param session
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDepart.do")
	public AjaxJson deleteDepart(String departId, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = departId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = voteSystemService.updateDeparts(str, user.getCode());
			if (i > 0) {
				result.setMsg("删除成功，总共删除：" + i + "条数据!");
			} else {
				result.fail("删除失败!");
			}
		} catch (Exception e) {
			result.fail("部门数据批量删除出错，请联系管理员!");
			log.error("部门数据批量删除出错, deleteDepart:", e);
		}
		return result;
	}
	
	//--------------------------------------投票人处理方法---------------------------------

	/**
	 * 跳转至批量导入用户页面
	 * 
	 * @param code
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/toExpUser.do")
	public String toExpUser(Model model, String url,String fileName) {
		model.addAttribute("url", url);
		model.addAttribute("fileName", fileName);
		return "/system/importUser";
	}

	/**
	 * 导入
	 * 
	 * @param excel
	 *            路径
	 * @return
	 */
	@RequestMapping(value = "/expUsers.do", method = RequestMethod.POST, headers = "Accept=text/plain;charset=UTF-8")
	@ResponseBody
	public String expUsers(MultipartFile excel, HttpSession session) {
		// BiVoteLogin user =
		// (BiVoteLogin)session.getAttribute(ConstantUtil.LOGIN_USER);
		AjaxJson result = new AjaxJson();
		try {
			Workbook wb = null;
			try {
				// 构造Workbook（工作薄）对象
				wb = Workbook.getWorkbook(excel.getInputStream());
			} catch (Exception e) {
				result.setMsg("请导入Excel 2003版本文件！");
				return result.getMsg();
			}

			if (wb == null) {
				result.setMsg("导入失败！<br/>请重新下载模板，并按正确格式填写内容，再重新导入！");
				return result.getMsg();
			}

			List<BiVoteUser> impUserList = new ArrayList<BiVoteUser>();

			// 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
			Sheet[] sheet = wb.getSheets();
			if (sheet != null && sheet.length > 0) {
				// 对每个工作表进行循环
				for (int i = 0; i < 1; i++) {
					// 得到当前工作表的行数
					int rowNum = sheet[i].getRows();
					if (rowNum > 0) {
						for (int j = 3; j < rowNum; j++) {
							BiVoteUser biVoteUser = new BiVoteUser();
							// biMjPlan.setCreateUser(loginUser.getUserCode());
							// 得到当前行的所有单元格
							int t = (int) ((Math.random() * 9 + 1) * 100000);
							biVoteUser.setPassword(t + "");
							Cell[] cells = sheet[i].getRow(j);
							if (cells != null && cells.length > 0) {
								// 期初入库单商品明细
								for (int k = 0; k < cells.length; k++) {
									// 读取当前单元格的值
									String cellValue = cells[k].getContents().trim();
									if (k == 0) {
										if (null != cellValue && !"".equals(cellValue)) {
											if (null != impUserList && impUserList.size() > 0) {
												for (BiVoteUser oldUser : impUserList) {
													if (cellValue.equals(oldUser.getCode())) {
														result.setMsg("导入失败！行号："+ (j + 1)+ ",手机号重复。");
														return result.getMsg();
													}
												}
												BiVoteUser user = new BiVoteUser();
												user.setCode(cellValue);
												List<BiVoteUser> oldBiVoteUserList = voteSystemService.findUserList(user);
												if(null != oldBiVoteUserList && oldBiVoteUserList.size() > 0 ){
													result.setMsg("导入失败！行号："+ (j + 1)+ ",手机号已存在。");
													return result.getMsg();
												}else{
													biVoteUser.setCode(cellValue);
												}
											} else {
												BiVoteUser user = new BiVoteUser();
												user.setCode(cellValue);
												List<BiVoteUser> oldBiVoteUserList = voteSystemService.findUserList(user);
												if(null != oldBiVoteUserList && oldBiVoteUserList.size() > 0 ){
													result.setMsg("导入失败！行号："+ (j + 1)+ ",手机号已存在。");
													return result.getMsg();
												}else{
													biVoteUser.setCode(cellValue);
												}
											}
										} else {
											result.setMsg("导入失败！行号：" + (j + 1) + ",手机号为空。");
											return result.getMsg();
										}
									} else if (k == 1) {
										biVoteUser.setBrandCode(cellValue);
									} else if (k == 2) {
										biVoteUser.setBrandName(cellValue);
									} else if (k == 3) {
										try {
											biVoteUser.setEntryDate(new Integer(cellValue));
										} catch (Exception e) {
											result.setMsg("导入失败！行号：" + (j + 1)
													+ ",入职日期格式填写错误。");
											return result.getMsg();
										}
									} else if (k == 4) {
										biVoteUser.setDepartCode(cellValue);
									} else if (k == 5) {
										biVoteUser.setDepartName(cellValue);
									} else if (k == 6) {
										if (null == cellValue
												|| "".equals(cellValue)) {
											biVoteUser.setIsVote("0");
										} else {
											biVoteUser.setIsVote(cellValue);
										}
									} else if (k == 7) {
										biVoteUser.setLineNum(cellValue);
									} else if (k == 8) {
										biVoteUser.setColumnNum(cellValue);
									} else if (k == 9) {
										biVoteUser.setName(cellValue);
									} else if (k == 10) {
										biVoteUser.setJobNum(cellValue);
									}
								}
							}
							impUserList.add(biVoteUser);
						}
					}
				}
			}
			try {
				voteSystemService.insertUserList(impUserList);
				result.setMsg("导入成功！");
			} catch (DataAccessException e) {
				result.setMsg(e.getMessage());
				e.printStackTrace();
				log.error("导入出错,imp", e);
			} catch (Exception e) {
				result.setMsg("导入失败！");
				e.printStackTrace();
				log.error("导入出错,imp", e);
			}

			// 最后关闭资源，释放内存
			wb.close();
		} catch (Exception e) {
			result.fail();
			e.printStackTrace();
			log.error("导入出错,imp", e);
		}
		return result.getMsg();
	}
	
	/** 
	 * 跳转至用户编辑页面
	 * @param model
	 * @param userId
	 * @return String  
	 */ 
	@RequestMapping(value = "/toEditUser.do")
	public String toEditUser(Model model, String userId,HttpSession session) {
		BiVoteUser biVoteUser = voteSystemService.getById(new Long(userId));
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		model.addAttribute("biVoteUser", biVoteUser);
		model.addAttribute("biVoteBrands", biVoteBrands);
		return "/system/edituser";
	}
	
	/** 
	 * 投票人编辑
	 * @param biVoteUser
	 * @param model
	 * @param session
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/editUser.do")
	public AjaxJson editUser(BiVoteUser biVoteUser, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
//		BiVoteLogin user = (BiVoteLogin) session
//				.getAttribute(ConstantUtil.LOGIN_USER);
		try {
			voteSystemService.update(biVoteUser);
		} catch (Exception e) {
			result.fail("投票人编辑出错，请联系管理员!");
			log.error("投票人编辑出错, editUser:", e);
		}
		return result;
	}
	
	//----------------------------------------------候选人即被投票人处理方法---------------------------------------------
	/**
	 * 跳转至候选人列表页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/candidateList.do")
	public String candidateList(BiVoteCandidate query, Model model, HttpSession session) {
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			query.setBrandCode(user.getBrandCode());
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteCandidate> biVoteCandidates = voteSystemService
				.findVoteCandidates(query);
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		model.addAttribute("biVoteBrands", biVoteBrands);
		model.addAttribute("query", query);
		model.addAttribute("biVoteCandidates", biVoteCandidates);
		return "/system/candidatelist";
	}
	
	/**
	 * 批量更新候选人权限
	 * 
	 * @param userId
	 *            批量用户ID
	 * @param flag
	 *            0 有权限 1无权限
	 * @param reqeust
	 * @return AjaxJson
	 */
	@ResponseBody
	@RequestMapping(value = "/addCandidate.do")
	public AjaxJson addCandidate(String userId, String flag,
			HttpServletRequest reqeust) {
		AjaxJson result = new AjaxJson();
		try {
			List<String> str = new ArrayList<String>();
			String[] strs = userId.split(",");
			for (int j = 0; j < strs.length; j++) {
				str.add(strs[j]);
			}
			int i = voteSystemService.updateCandidateVotes(str, flag);
			if (i > 0) {
				result.setMsg("更新成功，总共更新：" + i);
			} else {
				result.fail("更新失败!");
			}
		} catch (Exception e) {
			result.fail("权限更新出错，请联系管理员!");
			log.error("权限更新出错, addVote:", e);
		}
		return result;
	}
	
	/**
	 * 导入候选人信息
	 * 
	 * @param excel
	 *            路径
	 * @return
	 */
	@RequestMapping(value = "/expCandidate.do", method = RequestMethod.POST, headers = "Accept=text/plain;charset=UTF-8")
	@ResponseBody
	public String expCandidate(MultipartFile excel, HttpSession session) {
		AjaxJson result = new AjaxJson();
		try {
			Workbook wb = null;
			try {
				// 构造Workbook（工作薄）对象
				wb = Workbook.getWorkbook(excel.getInputStream());
			} catch (Exception e) {
				result.setMsg("请导入Excel 2003版本文件！");
				return result.getMsg();
			}

			if (wb == null) {
				result.setMsg("导入失败！<br/>请重新下载模板，并按正确格式填写内容，再重新导入！");
				return result.getMsg();
			}

			List<BiVoteCandidate> impCandidateList = new ArrayList<BiVoteCandidate>();

			// 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
			Sheet[] sheet = wb.getSheets();
			if (sheet != null && sheet.length > 0) {
				// 对每个工作表进行循环
				for (int i = 0; i < 1; i++) {
					// 得到当前工作表的行数
					int rowNum = sheet[i].getRows();
					if (rowNum > 0) {
						for (int j = 3; j < rowNum; j++) {
							BiVoteCandidate biVoteCandidate = new BiVoteCandidate();
							biVoteCandidate.setCreationDate(new Date());
							biVoteCandidate.setLastUpdateDate(new Date());
							// 得到当前行的所有单元格
							Cell[] cells = sheet[i].getRow(j);
							if (cells != null && cells.length > 0) {
								// 期初入库单商品明细
								for (int k = 0; k < cells.length; k++) {
									// 读取当前单元格的值
									String cellValue = cells[k].getContents().trim();
									if (k == 0) {
										biVoteCandidate.setBrandCode(cellValue);
									} else if (k == 1) {
										biVoteCandidate.setBrandName(cellValue);
									} else if (k == 2) {
										biVoteCandidate.setDeptCode(cellValue);
									} else if (k == 3) {
										biVoteCandidate.setDeptName(cellValue);
									} else if (k == 4) {
										
										if (null != cellValue && !"".equals(cellValue)) {
											if (null != impCandidateList && impCandidateList.size() > 0) {
												for (BiVoteCandidate oldCandidate : impCandidateList) {
													if (cellValue.equals(oldCandidate.getJobNum())) {
														result.setMsg("导入失败！行号："+ (j + 1)+ ",工号重复。");
														return result.getMsg();
													}
												}
												BiVoteCandidate temp = new BiVoteCandidate();
												temp.setJobNum(cellValue);
												List<BiVoteCandidate> tempCandidateList = voteSystemService.findVoteCandidates(temp);
												if(null != tempCandidateList && tempCandidateList.size() > 0 ){
													result.setMsg("导入失败！行号："+ (j + 1)+ ",工号已存在。");
													return result.getMsg();
												}else{
													biVoteCandidate.setJobNum(cellValue);
												}
											} else {
												BiVoteCandidate temp = new BiVoteCandidate();
												temp.setJobNum(cellValue);
												List<BiVoteCandidate> tempCandidateList = voteSystemService.findVoteCandidates(temp);
												if(null != tempCandidateList && tempCandidateList.size() > 0 ){
													result.setMsg("导入失败！行号："+ (j + 1)+ ",工号已存在。");
													return result.getMsg();
												}else{
													biVoteCandidate.setJobNum(cellValue);
												}
											}
										} else {
											result.setMsg("导入失败！行号：" + (j + 1) + ",工号为空。");
											return result.getMsg();
										}
										
									} else if (k == 5) {
										biVoteCandidate.setName(cellValue);
									} else if (k == 6) {
										if (null == cellValue
												|| "".equals(cellValue)) {
											biVoteCandidate.setIsDelete(0);
										} else {
											biVoteCandidate.setIsDelete(Integer.parseInt(cellValue));
										}
									}
								}
							}
							impCandidateList.add(biVoteCandidate);
						}
					}
				}
			}
			try {
				if(null != impCandidateList && impCandidateList.size() > 0){
					voteSystemService.insertCandidateList(impCandidateList);
				}
				result.setMsg("导入成功！");
			} catch (DataAccessException e) {
				result.setMsg(e.getMessage());
				e.printStackTrace();
				log.error("导入出错,imp", e);
			} catch (Exception e) {
				result.setMsg("导入失败！");
				e.printStackTrace();
				log.error("导入出错,imp", e);
			}

			// 最后关闭资源，释放内存
			wb.close();
		} catch (Exception e) {
			result.fail();
			e.printStackTrace();
			log.error("导入出错,imp", e);
		}
		return result.getMsg();
	}
	
	/** 
	 * 跳转至候选人编辑页面
	 * @param model
	 * @param userId
	 * @return String  
	 */ 
	@RequestMapping(value = "/toEditCandidate.do")
	public String toEditCandidate(Model model, String id,HttpSession session) {
		BiVoteCandidate biVoteCandidate = voteSystemService.getVoteCandidateById(new Long(id));
		BiVoteLogin user = (BiVoteLogin) session
				.getAttribute(ConstantUtil.LOGIN_USER);
		BiVoteBrand biVoteBrand = new BiVoteBrand();
		biVoteBrand.setIsDelete("0");
		if (null != user.getBrandCode() && !"".equals(user.getBrandCode())) {
			biVoteBrand.setCode(user.getBrandCode());
		}
		List<BiVoteBrand> biVoteBrands = voteSystemService
				.queryBrandList(biVoteBrand);
		model.addAttribute("biVoteCandidate", biVoteCandidate);
		model.addAttribute("biVoteBrands", biVoteBrands);
		return "/system/editcandidate";
	}
	
	/** 
	 * 编辑候选人
	 * @param biVoteUser
	 * @param model
	 * @param session
	 * @return AjaxJson  
	 */ 
	@ResponseBody
	@RequestMapping(value = "/editCandidate.do")
	public AjaxJson editCandidate(BiVoteCandidate biVoteCandidate, Model model,
			HttpSession session) {
		AjaxJson result = new AjaxJson();
		try {
			voteSystemService.updateVoteCandidateById(biVoteCandidate);
		} catch (Exception e) {
			result.fail("投票人编辑出错，请联系管理员!");
			log.error("投票人编辑出错, editUser:", e);
		}
		return result;
	}

}
