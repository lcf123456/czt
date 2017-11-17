package com.ztel.app.web.ctrl.produce;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.produce.SortTroughService;
import com.ztel.app.service.wms.MantissaService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.MantissaVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/produce/sorttrough")
public class SortTroughCtrl extends BaseCtrl {
	
	
	@Autowired
	private SortTroughService sortTroughVoService = null;
	@Autowired
	PubService pservide=null;
	@Autowired
	private MantissaService  mantissaService = null;
	@RequestMapping("")
	public String index(HttpServletRequest request) {
		return "/produce/index";
	}
	@RequestMapping(value="/detail/{type}/{ctype}/{groupno}")
	public String index(HttpServletRequest request,@PathVariable(value="type") String ty,@PathVariable(value="ctype") String cty,@PathVariable(value="groupno") String groupno) {
		request.setAttribute("type", ty);
		request.setAttribute("ctype", cty);
		request.setAttribute("groupno", groupno);
		return "/produce/v_sorttrough";
	}
		
	@RequestMapping("toSorttrough")
	public String index1(HttpServletRequest request) {
			
		return "/produce/v_sorttroughinfo";
	}
	
	@RequestMapping("toMantissa")
	public String toMantissa(HttpServletRequest request) {
		
		return "/produce/v_mantissa";
	}
	@RequestMapping("getSortTroughinfo")
	 @ResponseBody
	public  Map<String, Object> GetSortTrough(HttpServletRequest request,SortTroughVo vo)
	{
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		 //System.out.println("------");
		 if (vo!=null) {
			 //System.out.println("roleinfo="+roleinfo.getRolename()+","+roleinfo.getId()); 
			 page.setParam(vo);
		}
		 
		 StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
		 if(vo.getTroughtype().toString().equals("10"))
		 {
		 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
		 if(vo.getCigarettetype().toString().equals("20"))
		 {
			 vo.setGroupno(null);
		 }
		 }
		 else
		 {
			 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj)); 
			 vo.setGroupno(null);
		 }
		 
		 String orderdate=request.getParameter("orderdate");
		 
		 List<SortTroughVo>  troughList=new ArrayList<SortTroughVo>();
		 troughList=sortTroughVoService.getSortTroughSummaryByCond(storageAreaInOutVo, vo,orderdate);
		 
		// List<SortTroughVo> paras = sortTroughVoService.getSortTroughPageList(page);
		 result.put("rows",troughList);  
		 result.put("total",page.getTotalCount());  

		 return result;
	}
	
	@RequestMapping("UpdateMainssa")
	 @ResponseBody
	public String UpdateMainssa(HttpServletRequest request,SortTroughVo vo)
	{
		MantissaVo mVo=new MantissaVo();
		
		if(vo.getTroughtype().toString().equals("10"))
		{
		mVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
		}
		else
		{
			mVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj));
		
		}
		mVo.setCigarettename(vo.getCigarettename());
		mVo.setCigarettecode(vo.getCigarettecode());
		mVo.setTroughnum(new BigDecimal(vo.getTroughnum()));
		mVo.setQty(vo.getFillqty());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String orderdate=request.getParameter("orderdate");
		try {
			mVo.setOrderdate(df.parse(orderdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mVo.setCreatedate(new Date());
		
		mVo.setId( new BigDecimal(pservide.getSequence("S_wms_mantissa").toString()));
		mantissaService.insertRecord(mVo);
		//vo.setLastmantissa(vo.getMantissa());
		//sortTroughVoService.updateByPrimaryKeySelective(vo);
		
		return "10";
	}
	
	/**
	  * 获取通道信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getSorttroughList")
	 @ResponseBody
	 public  Map<String, Object> getSorttroughList(SortTroughVo sorttroughVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (sorttroughVo!=null) {
			 page.setParam(sorttroughVo);
		}
		 List<SortTroughVo> paras = sortTroughVoService.getSorttroughList(sorttroughVo);
		 StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
		 if(sorttroughVo.getTroughtype().toString().equals("10"))
		 {
		 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
		 }
		 else
		 {
			 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj)); 
		 }
		 
		 String orderdate=request.getParameter("orderdate");
		 
		 List<SortTroughVo>  troughList=new ArrayList<SortTroughVo>();
		 troughList=sortTroughVoService.getSortTroughSummaryByCond(storageAreaInOutVo, sorttroughVo,orderdate);
		
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 
	 /**
		 * 获分拣通道盘点信息
		 * @param atsCellInfoDetailVo
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getSortTroughInfoSummary")
		 @ResponseBody
		 public Map<String, Object> getSortTroughInfoSummary(StorageAreaInOutVo storageAreaInOutVo,HttpServletRequest request) throws Exception{
			 Map<String, Object> result=new HashMap<String, Object>();  
			 
			 //最近一次盘点信息查询条件
//			 InventoryLineVo inventoryLineVo=new InventoryLineVo();
//			 inventoryLineVo.setInventorytype(new BigDecimal(10));
//			 inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
			 
			 //通道尾数查询条件
			 SortTroughVo sortTroughVo=new SortTroughVo();
			 sortTroughVo.setCigarettetype(new BigDecimal(20));
			 sortTroughVo.setTroughtype(new BigDecimal(10));
			 
			 //调拨移库信息
			 //StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
			 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
			 
			 String orderdate=request.getParameter("orderdate");
			 
			 List<SortTroughVo>  troughList=new ArrayList<SortTroughVo>();
			 troughList=sortTroughVoService.getSortTroughSummaryByCond(storageAreaInOutVo, sortTroughVo,orderdate);
			
			 result.put("rows",troughList);  
			 result.put("total",troughList.size());  
			 
			return result;
		}
		
		/**
		 * 获重力式货架盘点信息
		 * @param atsCellInfoDetailVo
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getShelfInfoSummary")
		@ResponseBody
		public Map<String, Object> getShelfInfoSummary(StorageAreaInOutVo storageAreaInOutVo,HttpServletRequest request) throws Exception{
			Map<String, Object> result=new HashMap<String, Object>();  
			
			//最近一次盘点信息查询条件
//			 InventoryLineVo inventoryLineVo=new InventoryLineVo();
//			 inventoryLineVo.setInventorytype(new BigDecimal(10));
//			 inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
			
			//通道尾数查询条件
			SortTroughVo sortTroughVo=new SortTroughVo();
			sortTroughVo.setCigarettetype(new BigDecimal(20));
			sortTroughVo.setTroughtype(new BigDecimal(20));
			
			//调拨移库信息
			//StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
			storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj));
			
			String orderdate=request.getParameter("orderdate");
			
			List<SortTroughVo>  troughList=new ArrayList<SortTroughVo>();
			troughList=sortTroughVoService.getSortTroughSummaryByCond(storageAreaInOutVo, sortTroughVo,orderdate);
			
			result.put("rows",troughList);  
			result.put("total",troughList.size());  
			
			return result;
		}
}
