package com.mendale.vo.baseData;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.mendale.common.base.BaseVO;

/**
 * 
* <p>用户信息的VO类</p>
* <p> Description: </p>
* @作者 chenliping
* @创建时间 2015-5-14
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   chenliping    2015-5-14   初始化版本
* ----------------------------------------
* </pre>
*
 */
public class User extends BaseVO implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
    /**
     * 用户ID       
     * db_column: USER_ID 
     */	
	private Long userId;
    /**
     * 员工号       
     * db_column: EMPLOYEE_NO 
     */	
	private String employeeNo;
    /**
     * 用户编码       
     * db_column: USER_CODE 
     */	
	private String userCode;
    /**
     * 用户姓名       
     * db_column: USER_NAME 
     */	
	private String userName;
    /**
     * 登陆用户名       
     * db_column: USER_LOGIN_NAME 
     */	
	private String userLoginName;
    /**
     * 密码       
     * db_column: PASSWORD 
     */	
	private String password;
    /**
     * 片区ID       
     * db_column: AREA_ID 
     */	
	private Long areaId;
    /**
     * 片区编码       
     * db_column: AREA_CODE 
     */	
	private String areaCode;
	private String areaName;
    /**
     * 网点ID       
     * db_column: BRANCH_ID 
     */	
	private Long branchId;
    /**
     * 网点编码       
     * db_column: BRANCH_CODE 
     */	
	private String branchCode;
	private String branchName;
    /**
     * 门店ID       
     * db_column: STORE_ID 
     */	
	private Long storeId;
    /**
     * 门店编码       
     * db_column: STORE_CODE 
     */	
	private String storeCode;
	private String storeName;
    /**
     * 品牌ID       
     * db_column: BRAND_ID 
     */	
	private Long brandId;
    /**
     * 品牌编码       
     * db_column: BRAND_CODE 
     */	
	private String brandCode;
    /**
     * 是否启用（0：否   1：是）       
     * db_column: IS_USE_FLG 
     */	
	private String isUseFlg;
    /**
     * 是否系统管理员（0：否   1：是）       
     * db_column: IS_SYSTEM_FLG 
     */	
	private String isSystemFlg;
    /**
     * 部门ID       
     * db_column: DEPARTMENT_ID 
     */	
	private Long departmentId;
    /**
     * 部门编码       
     * db_column: DEPARTMENT_CODE 
     */	
	private String departmentCode;
    /**
     * 用户类型（1：后台人员  2：渠道人员  3：供应商  4：前台用户）       
     * db_column: USER_TYPE 
     */	
	private String userType;
    /**
     * 供应商ID       
     * db_column: SUPPLIER_ID 
     */	
	private Long supplierId;
    /**
     * 供应商编码       
     * db_column: SUPPLIER_CODE 
     */	
	private String supplierCode;
	private String supplierName;
    /**
     * 最后登录时间       
     * db_column: LAST_LOGIN_TIME 
     */	
	private Date lastLoginTime;
	private String lastLoginTimeBegin;
	private String lastLoginTimeEnd;
    /**
     * 是否是默认密码（0：否   1：是）       
     * db_column: IS_DEFAULT_PASSWORD 
     */	
	private String isDefaultPassword;
    /**
     * 手机号码       
     * db_column: MOBILE 
     */	
	private String mobile;
    /**
     * 电子邮箱       
     * db_column: EMAIL 
     */	
	private String email;
    /**
     * 性别（0：保密   1：男   2：女）       
     * db_column: SEX 
     */	
	private String sex;
    /**
     * 地址       
     * db_column: ADDRESS 
     */	
	private String address;
    /**
     * 联系电话       
     * db_column: PHONE 
     */	
	private String phone;
    /**
     * 传真       
     * db_column: FAX 
     */	
	private String fax;
    /**
     * 身份证号码       
     * db_column: IDENTITY_NO 
     */	
	private String identityNo;
    /**
     * 岗位       
     * db_column: POST 
     */	
	private String post;
    /**
     * 职位       
     * db_column: POSITION 
     */	
	private String position;
    /**
     * 入职时间       
     * db_column: JOIN_DATE 
     */	
	private Date joinDate;
	private String joinDateBegin;
	private String joinDateEnd;
    /**
     * 离职时间       
     * db_column: LEAVE_DATE 
     */	
	private Date leaveDate;
	private String leaveDateBegin;
	private String leaveDateEnd;
    /**
     * 片区角色编码       
     * db_column: AREA_ROLE_CODE 
     */	
	private String areaRoleCode;
    /**
     * 片区角色名称       
     * db_column: AREA_ROLE_NAME 
     */	
	private String areaRoleName;
    /**
     * 删除标识(0：否   1：是)       
     * db_column: DELETE_FLG 
     */	
	private String deleteFlg;
    /**
     * 创建者       
     * db_column: CREATE_USER 
     */	
	private String createUser;
    /**
     * 创建时间       
     * db_column: CREATE_TIME 
     */	
	private Date createTime;
	private String createTimeBegin;
	private String createTimeEnd;
    /**
     * 更新者       
     * db_column: UPDATE_USER 
     */	
	private String updateUser;
    /**
     * 更新时间       
     * db_column: UPDATE_TIME 
     */	
	private Date updateTime;
	private String updateTimeBegin;
	private String updateTimeEnd;
	
	private String brandName;
	private String existsCode;
	private Long nonUserId;

	//工程原有功能模块需要使用到的属性-----------------------------------
	// 用户门店权限
	private String storeCodes;
	// 是否为超级管理员查询
	private Boolean isAdminQuery = false;
	//默认查询区域
	private String defcode;
	//默认查询区域名称
	private String defcodename;
	
	public Boolean getIsAdminQuery() {
		return isAdminQuery;
	}

	public void setIsAdminQuery(Boolean isAdminQuery) {
		this.isAdminQuery = isAdminQuery;
	}

	public String getStoreCodes() {
		return storeCodes;
	}

	public void setStoreCodes(String storeCodes) {
		this.storeCodes = storeCodes;
	}

	public User(){
	}

	public User(
		Long userId
	){
		this.userId = userId;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setEmployeeNo(String value) {
		this.employeeNo = value;
	}
	
	public String getEmployeeNo() {
		return this.employeeNo;
	}
	
	public void setUserCode(String value) {
		this.userCode = value;
	}
	
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserLoginName(String value) {
		this.userLoginName = value;
	}
	
	public String getUserLoginName() {
		return this.userLoginName;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setAreaId(Long value) {
		this.areaId = value;
	}
	
	public Long getAreaId() {
		return this.areaId;
	}
	
	public void setAreaCode(String value) {
		this.areaCode = value;
	}
	
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public void setBranchId(Long value) {
		this.branchId = value;
	}
	
	public Long getBranchId() {
		return this.branchId;
	}
	
	public void setBranchCode(String value) {
		this.branchCode = value;
	}
	
	public String getBranchCode() {
		return this.branchCode;
	}
	
	public void setStoreId(Long value) {
		this.storeId = value;
	}
	
	public Long getStoreId() {
		return this.storeId;
	}
	
	public void setStoreCode(String value) {
		this.storeCode = value;
	}
	
	public String getStoreCode() {
		return this.storeCode;
	}
	
	public void setBrandId(Long value) {
		this.brandId = value;
	}
	
	public Long getBrandId() {
		return this.brandId;
	}
	
	public void setBrandCode(String value) {
		this.brandCode = value;
	}
	
	public String getBrandCode() {
		return this.brandCode;
	}
	
	public void setIsUseFlg(String value) {
		this.isUseFlg = value;
	}
	
	public String getIsUseFlg() {
		return this.isUseFlg;
	}
	
	public void setIsSystemFlg(String value) {
		this.isSystemFlg = value;
	}
	
	public String getIsSystemFlg() {
		return this.isSystemFlg;
	}
	
	public void setDepartmentId(Long value) {
		this.departmentId = value;
	}
	
	public Long getDepartmentId() {
		return this.departmentId;
	}
	
	public void setDepartmentCode(String value) {
		this.departmentCode = value;
	}
	
	public String getDepartmentCode() {
		return this.departmentCode;
	}
	
	public void setUserType(String value) {
		this.userType = value;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public void setSupplierId(Long value) {
		this.supplierId = value;
	}
	
	public Long getSupplierId() {
		return this.supplierId;
	}
	
	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}
	
	public String getSupplierCode() {
		return this.supplierCode;
	}
	
	public void setLastLoginTime(Date value) {
		this.lastLoginTime = value;
	}
	
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTimeBegin(String value) {
		this.lastLoginTimeBegin = value;
	}
	
	public String getLastLoginTimeBegin() {
		return this.lastLoginTimeBegin;
	}
	
	public void setLastLoginTimeEnd(String value) {
		this.lastLoginTimeEnd = value;
	}
	
	public String getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}
	
	public void setIsDefaultPassword(String value) {
		this.isDefaultPassword = value;
	}
	
	public String getIsDefaultPassword() {
		return this.isDefaultPassword;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setSex(String value) {
		this.sex = value;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setFax(String value) {
		this.fax = value;
	}
	
	public String getFax() {
		return this.fax;
	}
	
	public void setIdentityNo(String value) {
		this.identityNo = value;
	}
	
	public String getIdentityNo() {
		return this.identityNo;
	}
	
	public void setPost(String value) {
		this.post = value;
	}
	
	public String getPost() {
		return this.post;
	}
	
	public void setPosition(String value) {
		this.position = value;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setJoinDate(Date value) {
		this.joinDate = value;
	}
	
	public Date getJoinDate() {
		return this.joinDate;
	}
	
	public void setJoinDateBegin(String value) {
		this.joinDateBegin = value;
	}
	
	public String getJoinDateBegin() {
		return this.joinDateBegin;
	}
	
	public void setJoinDateEnd(String value) {
		this.joinDateEnd = value;
	}
	
	public String getJoinDateEnd() {
		return this.joinDateEnd;
	}
	
	public void setLeaveDate(Date value) {
		this.leaveDate = value;
	}
	
	public Date getLeaveDate() {
		return this.leaveDate;
	}
	
	public void setLeaveDateBegin(String value) {
		this.leaveDateBegin = value;
	}
	
	public String getLeaveDateBegin() {
		return this.leaveDateBegin;
	}
	
	public void setLeaveDateEnd(String value) {
		this.leaveDateEnd = value;
	}
	
	public String getLeaveDateEnd() {
		return this.leaveDateEnd;
	}
	
	public void setDeleteFlg(String value) {
		this.deleteFlg = value;
	}
	
	public String getDeleteFlg() {
		return this.deleteFlg;
	}
	
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	public String getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTimeBegin(String value) {
		this.createTimeBegin = value;
	}
	
	public String getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeEnd(String value) {
		this.createTimeEnd = value;
	}
	
	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTimeBegin(String value) {
		this.updateTimeBegin = value;
	}
	
	public String getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeEnd(String value) {
		this.updateTimeEnd = value;
	}
	
	public String getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getExistsCode() {
		return existsCode;
	}

	public void setExistsCode(String existsCode) {
		this.existsCode = existsCode;
	}

	public Long getNonUserId() {
		return nonUserId;
	}

	public void setNonUserId(Long nonUserId) {
		this.nonUserId = nonUserId;
	}

	public String getAreaRoleCode() {
		return areaRoleCode;
	}

	public void setAreaRoleCode(String areaRoleCode) {
		this.areaRoleCode = areaRoleCode;
	}

	public String getAreaRoleName() {
		return areaRoleName;
	}

	public void setAreaRoleName(String areaRoleName) {
		this.areaRoleName = areaRoleName;
	}

	public String getDefcode() {
		return defcode;
	}

	public void setDefcode(String defcode) {
		this.defcode = defcode;
	}

	public String getDefcodename() {
		return defcodename;
	}

	public void setDefcodename(String defcodename) {
		this.defcodename = defcodename;
	}

	public String[] getStoreCodeArr() {
		if (StringUtils.isEmpty(storeCodes)) {
			return null;
		}
		return storeCodes.split(",");
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
}

