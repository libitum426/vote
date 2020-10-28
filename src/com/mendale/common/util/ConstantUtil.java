package com.mendale.common.util;

/**
* <p> Title: 常量工具包 </p>
* <p> Description: </p>
* @作者 xiefc
* @创建时间 2014-6-14 上午8:20:16
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间    修改内容描述
* ----------------------------------------
* 1.00 xiefc 2014-6-14 上午8:20:16  初始化版本
* ----------------------------------------
* </pre>
*/
public class ConstantUtil {
	
	/**
	 * 用户登录标识
	 */
	public static final String LOGIN_USER = "USER";
	
	/**
	 * 默认用户密码
	 */
	public static final String DEFAULT_PASSWORD = "888888";
	
	/**
	 * 前段数据 标识符
	 */
	public static final String FRONT_PAGE = "pageInfo";
	
	/**
	 * MD5加密密钥
	 */
	public static final String SECRET_KEY = "mendale";
	
	
	/**
	 * 未配货
	 */
	public static final int ORDER_DELIVERY_NOPICK = 0;
	
	/**
	 * 通知配货
	 */
	public static final int ORDER_DELIVERY_NOTICE = 1;
	
	/**
	 * 生成拣货单
	 */
	public static final int ORDER_DELIVERY_CREATEPICK = 2;
	
	/**
	 * 备货
	 */
	public static final int ORDER_DELIVERY_PREPARE = 3;
	
	/**
	 * 拣货中
	 */
	public static final int ORDER_DELIVERY_PICKING = 4;
	
	/**
	 * 拣货完
	 */
	public static final int ORDER_DELIVERY_PICKED = 5;
	
	/**
	 * 验货中
	 */
	public static final int ORDER_DELIVERY_CHECK = 6;
	
	/**
	 * 已发货
	 */
	public static final int ORDER_DELIVERY_SENDED = 7;
	
	/**
	 * 已出库
	 */
	public static final int ORDER_DELIVERY_OUTSTORE = 8;

	/**
	 * 梦洁发货方编码
	 */
	public static final String SENDER_MENDALE = "7000";
	
	/**
	 * 寐品牌发货方编码
	 */
	public static final String SENDER_MINE = "7210";
	
	// 工厂(梦洁:7000,寐:7210)
	public static final String FACtORY = "7000";
	//库存地点(仓库:9001)
	public static final String STORGLOC = "9001";

	/**
	 * 默认品牌编码
	 */
	public static final String DEFAULT_BRAND_CODE = "01";
	
	/**
	 * 批量处理数
	 */
	public static final int BATCH_SIZE = 10000;
	
	/**
	 * 数据导入人标识
	 */
	public static final String SYS_IMPORTER = "sys_importer";
	
	/**
	 * 父节点打开图标
	 */
	public static final String TREE_PARENT_PNG_OPEN = "/statics/js/zTree/css/zTreeStyle/img/diy/1_open.png";
	
	/**
	 * 父节点关闭图标
	 */
	public static final String TREE_PARENT_PNG_CLOSE = "/statics/js/zTree/css/zTreeStyle/img/diy/1_close.png";
	
	/**
	 * 暂无图片小图标
	 */
	public static final String NO_IMAGE_SMALL = "/statics/images/1.jpg";
	
	/**
	 * 暂无图片大图标
	 */
	public static final String NO_IMAGE_BIG = "/statics/images/7.jpg";
}
