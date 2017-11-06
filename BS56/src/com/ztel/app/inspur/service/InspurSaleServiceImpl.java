package com.ztel.app.inspur.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.inspur.mybatis.InspurSalecustomerVoMapper;
import com.ztel.app.inspur.mybatis.InspurSaleitemVoMapper;
import com.ztel.app.inspur.mybatis.InspurSaleorderheadVoMapper;
import com.ztel.app.inspur.mybatis.InspurSaleorderheaddetailVoMapper;
import com.ztel.app.inspur.vo.InspurSalecustomerVo;
import com.ztel.app.inspur.vo.InspurSaleitemVo;
import com.ztel.app.inspur.vo.InspurSaleorderheadVo;
import com.ztel.app.inspur.vo.InspurSaleorderheaddetailVo;
import com.ztel.app.service.sale.SaleAllService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sale.SaleitemVo;
import com.ztel.app.vo.sys.UserVo;

@Service
public class InspurSaleServiceImpl implements InspurSaleService {

	@Autowired
	private InspurSalecustomerVoMapper inspurSalecustomerVoMapper = null;
	
	@Autowired
	private InspurSaleorderheadVoMapper inspurSaleorderheadVoMapper = null;
	
	@Autowired
	private InspurSaleorderheaddetailVoMapper inspurSaleorderheaddetailVoMapper = null;
	
	@Autowired
	private InspurSaleitemVoMapper nspurSaleitemVoMapper = null;
	
	@Autowired
	private SaleAllService saleAllService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@Override
	public List<InspurSalecustomerVo> selectInspurCustomerList() {
		// TODO Auto-generated method stub
		return inspurSalecustomerVoMapper.selectInspurCustomerList();
	}

	/**
	 * 营销接口零售户同步
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> doSyncCustomer(UserVo userVo){
		Map<String, Object> result=new HashMap<String, Object>();  
		int rsRowNum = 0;
		String tempsql="";     
		int custCount= 0;
		String resultmsg = "";
		try{
			if(userVo==null){
				userVo = new UserVo();
				userVo.setId(1L);
				userVo.setUsername("系统管理员");
			}
		operationlogService.insertLog(userVo, "BS56/inspur/toSynccustomer", "营销接口接收零售户数据", "1、开始接收", "");
		List<InspurSalecustomerVo> customerList = inspurSalecustomerVoMapper.selectInspurCustomerList();
		if(customerList!=null&&customerList.size()>0){
			custCount=customerList.size();
			saleAllService.deletecustomer();
			for(int i=0;i<customerList.size();i++){
				InspurSalecustomerVo SalecustomerVo = customerList.get(i);
				
				String topsql = "insert into T_SALE_CUSTOMER(id,code,shortname,name,industry,contactaddress,contactphone,bakphone,"+
						"contact,account,routecode,licensecode,markettype,orderbatch,orderWay,delstatus,shipper_id,createtime)";
				
				tempsql += "union  select "+ SalecustomerVo.getCustId() + ",'"+SalecustomerVo.getCustShortId()+"','"+SalecustomerVo.getCustName()+"','"+SalecustomerVo.getCustName()+"','" 
				+ SalecustomerVo.getBaseType()+"','" + SalecustomerVo.getBusiAddr() + "','" + SalecustomerVo.getOrderTel() + "','" + SalecustomerVo.getReceiveTel2() + "','" +
				SalecustomerVo.getManager()+"','" + SalecustomerVo.getAccount() + "','" + SalecustomerVo.getCarId() + "','" + SalecustomerVo.getCustId() + "','" + 
				SalecustomerVo.getWorkPort()+"','" + SalecustomerVo.getPeriodsId() +"','" + SalecustomerVo.getOrderWay()+ "',10,11430101,sysdate from dual ";

	            if (((rsRowNum % 1000) == 0 && rsRowNum!=0) || rsRowNum==custCount-1)
	            {
	                tempsql = tempsql.substring(5);
	               // boolean insertResult = hw.execSQL(topsql +tempsql);
	                saleAllService.insertfromdb2toora(topsql +tempsql);
//	                if (insertResult == false)
//	                {
//	                    System.out.println(topsql + tempsql);
//	                }
	                tempsql = "";
	            }
	            rsRowNum++;
	            
			}
			resultmsg="同步成功！";
			operationlogService.insertLog(userVo, "BS56/inspur/toSynccustomer", "营销接口接收零售户数据", "2、成功插入"+custCount+"条记录到本地数据库表t_wms_sale！", "");
		}else{
			operationlogService.insertLog(userVo, "BS56/inspur/toSynccustomer", "营销接口接收零售户数据", "2、没有查询到零售户数据！", "");
		}
		}catch(Exception e){
			resultmsg="同步失败";
			operationlogService.insertLog(userVo, "BS56/inspur/toSynccustomer", "营销接口接收零售户数据", "接收数据出现异常！", "");
			e.printStackTrace();
		}
		result.put("custCount", custCount+"");
		result.put("resultmsg", resultmsg);
		return result;
	}
	
	/**
	 * 订单同步
	 * @param userVo
	 * @param orderdate
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> doSyncOrder(UserVo userVo,String orderdate){
		Map<String, Object> result=new HashMap<String, Object>();  
		int rsRowNum = 0;
		String tempsql="";     
		int Count_head= 0;
		int Count_line= 0;
		String resultmsg = "";//提示消息
		try{
			if(userVo==null){
				userVo = new UserVo();
				userVo.setId(1L);
				userVo.setUsername("系统管理员");
			}
			//orderdate = "20171026";
			//订单头处理
		operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单头数据", "1、开始接收", "");
		String db2sql = "select co_num,born_date,arr_date,cust_id,amt_sum,qty_sum,car_id"+
		"   from db2inst2.V_SALE_ORDER_HEAD where  born_date='"+orderdate+"'";
		List<InspurSaleorderheadVo> orderheadList = inspurSaleorderheadVoMapper.selectInspursaleorderheadList(db2sql);
		if(orderheadList!=null&&orderheadList.size()>0){
			Count_head=orderheadList.size();
			saleAllService.deletesaleorderhead("delete from T_SALE_ORDER_HEAD where shipdate=to_date('"+orderdate+"','YYYYMMDD')");//插入之前先删除订单日期的数据
			for(int i=0;i<orderheadList.size();i++){
				InspurSaleorderheadVo InspurSaleorderheadVo = orderheadList.get(i);
				
				String topsql = "insert into T_SALE_ORDER_HEAD(ORG_CODE,ORDERNO,CREATETIME,SHIPDATE,CUSTOMER_ID,TOTALAMOUNT,TOTALQTY,ROUTECODE) ";
				
				tempsql += "union  select '11430101','"+InspurSaleorderheadVo.getCoNum()+"',sysdate,to_date('"+orderdate+"','yyyy-mm-dd'),'" 
						+ InspurSaleorderheadVo.getCustId()+ "'," + InspurSaleorderheadVo.getAmtSum()+ "," + InspurSaleorderheadVo.getQtySum() 
						+  ",'" + InspurSaleorderheadVo.getCarId() + "' from dual ";

	            if (((rsRowNum % 1000) == 0 && rsRowNum!=0) || rsRowNum==Count_head-1)
	            {
	                tempsql = tempsql.substring(5);
	               // boolean insertResult = hw.execSQL(topsql +tempsql);
	                saleAllService.insertsaleorderhead(topsql +tempsql);
//	                if (insertResult == false)
//	                {
//	                    System.out.println(topsql + tempsql);
//	                }
	                tempsql = "";
	            }
	            rsRowNum++;
	            
			}
			resultmsg=Count_head+"条订单头同步成功！ ";
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单头数据", "2、成功插入"+Count_head+"条记录到本地数据库表T_SALE_ORDER_HEAD！", "");
			
			//---------------------
			//同步订单明细开始，只有在有订单头的情况下，才同步明细
			rsRowNum = 0;
			tempsql="";    
			Count_line= 0;
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单明细数据", "1、开始接收", "");
//			db2sql = "select line.co_num,item_id,line.qty_ord,line.price,line.amt,qty_need from db2inst2.V_SALE_ORDER_DETAIL line,"
//					+"db2inst2.V_SALE_ORDER_HEAD head where line.co_num=head.co_num and head.born_date='"+orderdate+"'";
			List<InspurSaleorderheaddetailVo> orderheaddetailList = inspurSaleorderheaddetailVoMapper.selectInspursaleorderheaddetailList(orderdate);
			if(orderheaddetailList!=null&&orderheaddetailList.size()>0){
				Count_line=orderheaddetailList.size();
				//删除明细
				saleAllService.deletesaleorderline("delete from T_SALE_ORDER_LINE where orderno in (select orderno from T_SALE_ORDER_HEAD where shipdate=to_date('"+orderdate+"','YYYYMMDD'))");
				for(int i=0;i<orderheaddetailList.size();i++){
					InspurSaleorderheaddetailVo inspurSaleorderheaddetailVo = orderheaddetailList.get(i);
					String topsql = "insert into T_SALE_ORDER_LINE(id,orderno,item_id,qty,saleprice,saleamount,qtyneed) ";
					tempsql += "union  select '"+inspurSaleorderheaddetailVo.getCoNum()+"','"+inspurSaleorderheaddetailVo.getCoNum()+"','"+
					inspurSaleorderheaddetailVo.getItemId()+"',"+inspurSaleorderheaddetailVo.getQtyOrd()+"," 
							+ inspurSaleorderheaddetailVo.getPrice()+"," + inspurSaleorderheaddetailVo.getAmt() + "," + inspurSaleorderheaddetailVo.getQtyNeed()+ " from dual ";
					if (((rsRowNum % 1000) == 0 && rsRowNum!=0) || rsRowNum==Count_line-1)
		            {
		                tempsql = tempsql.substring(5);
		                saleAllService.insertsaleorderline(topsql +tempsql);
		                tempsql = "";
		            }
		            rsRowNum++;
				}
				resultmsg=resultmsg+Count_line+"条订单明细同步成功！";
				operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单明细数据", "2、成功插入"+Count_line+"条记录到本地数据库表T_SALE_ORDER_LINE！", "");
			}
			//---------------------
			
		}else{
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单头数据", "2、没有查询到订单头数据！", "");
		}
		}catch(Exception e){
			resultmsg="订单同步失败";
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncorder", "营销接口接收订单头数据", "接收数据出现异常！", "");
			e.printStackTrace();
		}
		//result.put("custCount", custCount+"");
		result.put("resultmsg", resultmsg);
		return result;
	}
	
	/**
	 * 营销接口商品信息同步
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> doSyncItem(UserVo userVo){
		Map<String, Object> result=new HashMap<String, Object>();  
		int rsRowNum = 0;
		String tempsql="";     
		int custCount= 0;
		String resultmsg = "";
		try{
			if(userVo==null){
				userVo = new UserVo();
				userVo.setId(1L);
				userVo.setUsername("系统管理员");
			}
		operationlogService.insertLog(userVo, "BS56/inspur/toSyncitem", "营销接口接收商品数据", "1、开始接收", "");
		String sqlstr="select item_id,item_name,short_name,kind,brdowner_id,pack_bar,spec,is_abnormal,um_id,um_name "+
				"from db2inst2.V_SALE_ITEM";
		List<InspurSaleitemVo> inspurSaleitemList = nspurSaleitemVoMapper.selectInspursaleitemList(sqlstr);
		if(inspurSaleitemList!=null&&inspurSaleitemList.size()>0){
			custCount=inspurSaleitemList.size();
			//saleAllService.updateitemAllrowstatus();//更新全部商品为删除状态
			saleAllService.deleteitemAll("delete from T_SALE_ITEM");//删除商品
			for(int i=0;i<inspurSaleitemList.size();i++){
				InspurSaleitemVo inspurSaleitemVo = inspurSaleitemList.get(i);
				String itemid = inspurSaleitemVo.getItemId();//t_sale_item:商品id/code
				String itemName = inspurSaleitemVo.getItemName();//t_sale_item:商品名称
				String shortName = inspurSaleitemVo.getShortName();//t_sale_item:商品简称
				String kind = inspurSaleitemVo.getKind();//t_sale_item:ABC类
				String brdownerId = inspurSaleitemVo.getBrdownerId();//t_sale_item:manufacturer_id制造商
				String packBar = inspurSaleitemVo.getPackBar();//t_sale_item:卷烟码
				String spec = inspurSaleitemVo.getSpec();//t_sale_item:spec规格
				String isAbnormal = inspurSaleitemVo.getIsAbnormal();//shiptype类型：0为正常烟，1为异性烟
				String umId = inspurSaleitemVo.getUmId();//t_sale_item:基本计量单位
				String umName= inspurSaleitemVo.getUmName();//t_sale_item:基本计量单位名称
				SaleitemVo saleitemVo = saleAllService.selectItembyPrimaryKey(itemid);
				SaleitemVo saleitemVo2 = new SaleitemVo();
				saleitemVo2.setId(itemid);
				saleitemVo2.setItemname(itemName);
				saleitemVo2.setShortname(shortName);
				saleitemVo2.setKind(kind);
				saleitemVo2.setManufacturerId(brdownerId);
				saleitemVo2.setPackBar(packBar);
				saleitemVo2.setSpec(spec);
				saleitemVo2.setShiptype(isAbnormal);
				saleitemVo2.setBaseuomId(umId);
				saleitemVo2.setBaseuomName(umName);
				saleitemVo2.setItemno(itemid);
				saleitemVo2.setShipperId(new BigDecimal("11430101"));
				saleitemVo2.setRowstatus(new BigDecimal("10"));
				if(saleitemVo!=null&&saleitemVo.getId()!=null&&!saleitemVo.getId().equals("")){
					saleAllService.updateItembyPrimaryKey(saleitemVo2);
					//saleitemVo2.seti
				}
				else{
					saleAllService.insertItembyPrimaryKey(saleitemVo2);
				}
	            
			}
			resultmsg="同步成功！";
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncitem", "营销接口接收商品数据", "2、成功插入"+custCount+"条记录到本地数据库表t_wms_item！", "");
		}else{
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncitem", "营销接口接收商品数据", "2、没有查询到商品数据！", "");
		}
		}catch(Exception e){
			resultmsg="同步失败";
			operationlogService.insertLog(userVo, "BS56/inspur/toSyncitem", "营销接口接收商品数据", "接收数据出现异常！", "");
			e.printStackTrace();
		}
		result.put("custCount", custCount+"");
		result.put("resultmsg", resultmsg);
		return result;
	}
}
