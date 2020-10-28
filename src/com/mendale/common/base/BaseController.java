package com.mendale.common.base;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mendale.common.util.page.Page;
import com.mendale.common.util.page.PageQuery;



/**
 * 
* <p> controller层基类</p>
* <p> Description: </p>
* @作者 xzm
* @创建时间 Jul 26, 2014 10:53:18 PM
* @版本 1.00
* @修改记录
* <pre>
* 版本   修改人    修改时间        修改内容描述
* ----------------------------------------
* 1.00   xzm    Jul 26, 2014 10:53:18 PM   初始化版本
* ----------------------------------------
* </pre>
*
 */
@SuppressWarnings("all")
public abstract class BaseController {
	protected final static String CREATE = "创建";
	protected final static String UPDATE = "更新";
	protected final static String DELETE = "删除";
	
    private final static String SUCCESS = "成功";
    private final static String FAIL = "失败";
    

    protected Logger log = LoggerFactory.getLogger(getClass());
    
    public static ModelMap toModelMap(Page page,PageQuery pageRequest) {
    	 ModelMap model = new ModelMap();
         model.addAttribute("page", page);
         model.addAttribute("totalRows", new Integer(page.getTotalCount()));
         model.addAttribute("pageRequest", pageRequest);
         model.addAttribute("query", pageRequest);
        return model;
    }
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(ModelMap model,String message,boolean flag) {
		if(flag == true){
			model.addAttribute("msg", message.concat(SUCCESS));
		}else{
			model.addAttribute("msg", message.concat(FAIL));
		}
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes,String message,boolean flag) {
		if(flag == true){
			redirectAttributes.addFlashAttribute("msg",message.concat(SUCCESS));
		}else{
			redirectAttributes.addFlashAttribute("msg",message.concat(FAIL));
		}
	}
	
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}
}