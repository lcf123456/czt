package com.ztel.app.web.ctrl.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.perform.UserlevelService;
import com.ztel.app.service.perform.UserperformlevelService;
import com.ztel.app.vo.perform.DeptmonthLineVo;
import com.ztel.app.vo.perform.DeptmonthLineVoTmp;
import com.ztel.app.vo.perform.DeptmonthsumVo;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVoTmp;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/**
 * @author lcf
 * @since 2017年2月26日
 * 员工直接考核
 */
@Controller
@RequestMapping("/perform/userperformlevel")
public class UserperformlevelCtrl  extends BaseCtrl  {
	private static Logger logger = LogManager.getLogger(UserperformlevelCtrl.class);
	
	@Autowired
	private UserperformlevelService userperformlevelService = null;
	
	@Autowired
	private UserlevelService userlevelService = null;
	
	@RequestMapping("touserperformlevel")
	public String index(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_userperformlevel";
	}
	
	@RequestMapping("touserperformlevelreport")
	public String touserperformlevelreport(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_userperformlevelreport";
	}
	
	@RequestMapping(value="getUserperformlevelPageList")
	 @ResponseBody
	 public Map<String, Object> getUserperformlevelPageList(UserperformlevelVo userperformlevelVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String authority = request.getParameter("authority");//查看权限，general：只看本人及管辖用户的；leader:看所有
		 if(authority==null&&authority.equals(""))authority="general";
		 String searchdept = request.getParameter("searchdept");
		 
		 
		 Long userid = -1L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
		 }
		 
		 if(authority.equals("general")){
			 String useridstr = userid+",";
			 List<UserlevelVo> userlevelVoList = userlevelService.getUserlevelList(userid);//获取所管辖人员的id
			 if(userlevelVoList!=null&&userlevelVoList.size()>0){
				 for(int i=0;i<userlevelVoList.size();i++){
					 UserlevelVo userlevelVo =userlevelVoList.get(i);
					 useridstr = useridstr + userlevelVo.getUserid()+",";
				 }
			 }
			 useridstr ="("+ useridstr.substring(0,useridstr.length()-1)+")";
			 userperformlevelVo.setUseridstr(useridstr);
		 }
		 if(searchdept!=null&&!searchdept.equals(""))
			 userperformlevelVo.setDeptid(Integer.parseInt(searchdept));
		 
		 String checkdate_new = request.getParameter("checkdate_new");//考核日期
		 if(checkdate_new==null||checkdate_new.equals(""))checkdate_new=DateUtil.getyyyy_mm();
		 userperformlevelVo.setCheckdatestr(checkdate_new);
		

		 
		Pagination<?> page = this.getPagination(request);
		if (userperformlevelVo!=null) {
			 page.setParam(userperformlevelVo);
		}
		List<UserperformlevelVo> userperformlevelVoList = new ArrayList<UserperformlevelVo>();
		userperformlevelVoList = userperformlevelService.selectUserperformlevelPageList(page);
		
		 result.put("rows",userperformlevelVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	@RequestMapping(value="getAddUserlevelList")
	 @ResponseBody
	 public  List<UserperformlevelVoTmp> getUserlevelList(HttpServletRequest request)
	 {
		String checkdate_new = request.getParameter("checkdate_new");//考核日期
		 if(checkdate_new==null||checkdate_new.equals(""))checkdate_new=DateUtil.getyyyy_mm();
		 
		 Long userid = -1L;
		 int deptid = 0;
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
		 }
		
		 List<UserlevelVo> userlevelList=userlevelService.getUserlevelList(userid);
		 List<UserperformlevelVoTmp>  userperformlevelVoTmpList = userperformlevelService.selectAddUserListByCond(userlevelList,checkdate_new);
		 return userperformlevelVoTmpList;
	 }
	
	 /**
	  * 新增员工考核
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String scoremonth_new = request.getParameter("scoremonth_new");//考核日期
		 if(scoremonth_new==null||scoremonth_new.equals(""))scoremonth_new=DateUtil.getyyyy_mm()+"-01";
		 //if(scoremonth_new.length()==10)scoremonth_new = scoremonth_new.substring(0, 7);
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 Date scoredate = sdf.parse(scoremonth_new);
		 
		 Long userid = 0L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
			 username = userVo.getUsername();
		 }

		 
		//因为前台传值的局限性，要不传值为未修改前的rows数据，要不只传仅修改的数据，所以需要特殊处理合并
		List<UserperformlevelVo> addList = new ArrayList<UserperformlevelVo>();
		 List<UserperformlevelVoTmp> deptmonthLineVoList = new ArrayList<UserperformlevelVoTmp>();
		 String updated  = request.getParameter("updaterows");
		 String allrows  = request.getParameter("allrows");
		 if(updated != null && allrows != null){
			  	    //把json字符串转换成对象
			  	    List<UserperformlevelVoTmp> listUpdated = JSON.parseArray(updated, UserperformlevelVoTmp.class);
			  	  List<UserperformlevelVoTmp> listAllrows = JSON.parseArray(allrows, UserperformlevelVoTmp.class);
			  	  for(int i=0;i<listAllrows.size();i++){
			  		UserperformlevelVoTmp DeptmonthaddVo_all = listAllrows.get(i);
			  		if(listUpdated!=null&&listUpdated.size()>0){
			  			for(int j=0;j<listUpdated.size();j++){
			  				UserperformlevelVoTmp DeptmonthaddVo_update = listUpdated.get(j);
				  			if(DeptmonthaddVo_all.getUserid()==DeptmonthaddVo_update.getUserid())
				  				DeptmonthaddVo_all=DeptmonthaddVo_update;
				  		}
			  		}
				  		
				  		//addList.add(DeptmonthaddVo_all);
			  		UserperformlevelVo userperformlevelVo1 = new UserperformlevelVo();
				  		String userid1=DeptmonthaddVo_all.getUserid();
				  		String score1 = DeptmonthaddVo_all.getScore();
				  		String levelname1 = DeptmonthaddVo_all.getLevelname();
				  		String remarks1 = DeptmonthaddVo_all.getRemarks();
				  		if(score1.equals(""))score1="0";
				  		if(userid1.equals(""))userid1="0";
				  		userperformlevelVo1.setUserid(new Long(userid1));
				  		userperformlevelVo1.setScore(new BigDecimal(score1));
				  		userperformlevelVo1.setLevelname(levelname1);
				  		userperformlevelVo1.setRemarks(remarks1);
				  		userperformlevelVo1.setAppraiser(userid);
				  		userperformlevelVo1.setCheckdate(scoredate);
				  		addList.add(userperformlevelVo1);
			  	  }
			  	}

		 int size = 1;
		 try{
			// transverseAssessService.insertTransverseAssess(transverseAssessVo);
			 userperformlevelService.insertUserperformlevel(addList);
			 if(addList!=null&&addList.size()>0)size=addList.size();
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 map.put("obid", "0");
			 e.printStackTrace();
		 }
		 map.put("total", size+"");
		 return map;
	 }
	 
	 /**
	  * 删除
	  * @return
	  */
	 @RequestMapping(value="doDel")
	 @ResponseBody
	 public  Map<String, Object> doDel(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
			
		 String id = request.getParameter("id");
		 if(id==null||id.equals(""))id="0";

		 try{
			 userperformlevelService.doDel(new BigDecimal(id));
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
}
