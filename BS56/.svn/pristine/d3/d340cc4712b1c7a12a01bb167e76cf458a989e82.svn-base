/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ztel.app.service.sys.UserVoService;
import com.ztel.app.util.BaseUtil;
import com.ztel.app.vo.User;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.ErrorCode;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.vo.Result;
import com.ztel.framework.web.annotation.PublicPage;
import com.ztel.framework.web.ctrl.BaseCtrl;
import com.ztel.framework.web.util.WebUtils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Zeal
 * @since 2017年2月26日
 */
@Controller
@PublicPage
public class IndexCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(IndexCtrl.class);
	
	
	@Autowired
	private UserVoService userVoService = null;
	
	/**
	 * 系统首页登录界面
	 * @param request
	 * @return
	 */
	@RequestMapping("")
	public String login(HttpServletRequest request) {
		return "/index/login";
	}
	
	@RequestMapping("toHome")
	public String toHome(HttpServletRequest request) {
		return "/index/mainhome";
	}
	
	/**
	 * 系统用户登录并设置session
	 * @param userVo
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public Map<String, Object> checkUser(UserVo userVo) {
		
		HttpSession session = getSession();
		
		Map<String, Object> result=new HashMap<String, Object>();  
		String usercode=userVo.getUsercode();
		String userpassword=userVo.getUserpsw();
		System.out.println("usercode="+usercode+",userpassword="+userpassword);
		result = userVoService.checkUser(usercode, userpassword);
		
		if(result!=null&&result.get("resultCode").equals("1"))
		{
			//设置用户session
			UserVo userVo_db = (UserVo)result.get("userVo");
			if(userVo_db!=null){
				session.setAttribute("userVo", userVo_db); 
				
			}
			System.out.println("开始设置session");
			UserVo user = (UserVo)session.getAttribute("userVo");
			System.out.println(user.getUsercode());
		}
		else
		{
			System.out.println(result.get("resultCode")+":"+result.get("msg"));
		}
		return result;
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/doLoginout", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {
      // 清除session
		getSession().removeAttribute("userVo");
         
		getSession().invalidate();
        // 拼接跳转页面路径
       return "/index/login";
    }
	
	/**
	 * 退出登录
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/sessionoutRun", method = RequestMethod.GET)
    public String sessionoutRun(HttpServletRequest request) {
         
       return "/pub/sessionrun";
    }
	
	@RequestMapping(value = "/getImage")  
    public void getImage(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {  
		BaseUtil baseUtil= new BaseUtil();
        // 禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
  
        response.setContentType("image/jpeg");  
        // 将图像输出到Servlet输出流中。  
        ServletOutputStream sos = response.getOutputStream();  
        ImageIO.write(baseUtil.getVerificationImg(request), "jpeg", sos);  
        sos.close();  
    }  
	
	@RequestMapping(value ="/imgCodeVerify")  
	@ResponseBody
	protected String verificationImg(HttpServletRequest request)  {
		String result = "";
		HttpSession session =request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode");
		String checkcode = request.getParameter("op");
		//PrintWriter out = response.getWriter();
		if(checkcode.equals(verificationCode)){
			result="1";
		}else{
			result="0";
		}
		return result;
	}
	
	//----------------------------------------
	//以下为曾文斌提供的demo	
	@RequestMapping("toExcel")
	public String toExcel(){
		return "/sys/v_upload2";
	}
	
	@RequestMapping("testIndex")
	public String index(HttpServletRequest request) {
		Pagination<?> page = this.getPagination(request);
		page.setUri(WebUtils.getRequestPath(request, "/queryOrders"));
		//Test
		page.setNumPerPage(1);
		//this.orderService.searchUserOrders(page);
		this.setPagination(request, page);
		return "/index/index";
	}
	
	@RequestMapping("test")
	public String test(HttpServletRequest request) {
		return "/index/top";
	}
	
	@RequestMapping("chooseUser")
	public String chooseUser(HttpServletRequest request) {
		String  roleid = request.getParameter("roleid");
		//操作类型opType：rolegrant角色授权；
		String  optype = request.getParameter("optype");
		if(roleid==null)roleid="";
		if(optype==null)optype="";
		System.out.println("indexCtr -------- optype="+optype+",roleid="+roleid);
		request.setAttribute("roleid", roleid);
		request.setAttribute("optype", optype);
		return "/pub/chooseUser";
	}
	
	@RequestMapping("/queryOrders")
	public String queryOrders(HttpServletRequest request, User param) {
		//TODO More validations to make sure the request parameters are security to avoid XSS,CSRF,SQL Injection
		Pagination<?> page = this.getPagination(request);
		page.setUri(WebUtils.getRequestPath(request, "/queryOrders"));
		page.setNumPerPage(1);
		page.setParam(param);
		//this.orderService.searchUserOrders(page);
		this.setPagination(request, page);
		return "/index/orderList";
	}
	
	@RequestMapping("/queryJsonOrders")
	@ResponseBody
	public Result<?> queryJsonOrders(HttpServletRequest request, String userName, String userMobile) {
		Pagination<?> page = this.getPagination(request);
		page.setNumPerPage(1);
		User userParam = new User();
		userParam.setUserName(userName);
		userParam.setUserMobile(userMobile);
		page.setParam(userParam);
		//this.orderService.searchUserOrders(page);
		return this.getResult(ErrorCode.NO_ERROR, page);
	}
	
	@RequestMapping("/jsonRequest")
	@ResponseBody
	public Result<?> queryJsonOrders(HttpServletRequest request, @RequestBody User userParam) {
		Pagination<?> page = this.getPagination(request);
		page.setNumPerPage(1);
		page.setParam(userParam);
		//this.orderService.searchUserOrders(page);
		return this.getResult(ErrorCode.NO_ERROR, page);
	}
	
    /**
     * 上传实名认证图片信息
     * @param request
     * @param file
     * @param fileType
     * @return
     */
    @RequestMapping(value="/uploadFile")
    @ResponseBody
    public Result<?> uploadFile(HttpServletRequest request, MultipartFile myFile) throws Exception {
    	if (logger.isInfoEnabled()) {
    		logger.info(myFile.getName() + "," + myFile.getSize());
    	}
    	String uploadPath = request.getServletContext().getRealPath("/upload");
    	File target = new File(uploadPath, myFile.getOriginalFilename() + '.' + System.currentTimeMillis());
    	myFile.transferTo(target);
    	return this.getResult(ErrorCode.NO_ERROR, "上传文件成功"+target.getAbsolutePath());
    }
    
    @RequestMapping(value = "/excel")
    @ResponseBody
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String fileName = "E" + System.currentTimeMillis();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", new StringBuffer("attachment").append( ";filename=" ).append(fileName).append(".xls").toString());
        WritableWorkbook book = null;
        try {
        	book = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sheet = book.createSheet("第1页", 0);
	        //写入表头
	        for(int i = 0; i < 10; i++){
	            sheet.addCell(new Label(i, 0, "表头"+System.currentTimeMillis()));
	        }
	        book.write();
        }
        finally {
        	if (book != null) {
        		book.close();
        	}
        }
    }
    
    
    public static HttpSession getSession() { 
        HttpSession session = null; 
        try { 
            session = getRequest().getSession(); 
        } catch (Exception e) {} 
            return session; 
    } 
        
    public static HttpServletRequest getRequest() { 
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
        return attrs.getRequest(); 
    } 
	
}
