package com.mendale.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mendale.common.base.BaseController;

/**
 * <p> Title: 模板下载Controller</p>
 * <p> Description: </p>
 * @作者 曹波
 * @创建时间 2014-12-27
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(TemplateController.class);
	
	/**
	 * 进入列表页面
	 * @param goods 查询对象
	 * @param model model对象
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/download.do")
	public String download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=utf-8");
	        request.setCharacterEncoding("UTF-8");
	        
	        String ctxPath = request.getSession().getServletContext().getRealPath("/")
	        					+ "\\" + "statics\\";
	        String downLoadPath = ctxPath + fileName;
	        
	        long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("下载出错,download", e);
		} finally {
            if (bis != null) {
            	bis.close();
            }
            if (bos != null) {
            	bos.close();
            }
        }
		return null;
	}
}
