package com.ztel.app.service.account.Impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.account.InvoiceExportVoMapper;
import com.ztel.app.service.account.InvoiceExportVoService;
import com.ztel.app.vo.account.BillCustomerInfo;
import com.ztel.app.vo.account.BillOrderInfo;
import com.ztel.app.vo.account.InvoiceExportVo;
import com.ztel.framework.util.DateUtil;
@Service
public class InvoiceExportVoServiceImpl implements InvoiceExportVoService {
	
	@Autowired
	private InvoiceExportVoMapper invoiceExportVoMapper = null;

	@Override
	public List<InvoiceExportVo> getInvoiceExportList(InvoiceExportVo invoiceExportVo) {
		// TODO Auto-generated method stub
		return invoiceExportVoMapper.getInvoiceExportCustomer(invoiceExportVo);
	}

	@Override
	public List<InvoiceExportVo> getOrderDetail(InvoiceExportVo invoiceExportVo) {
		// TODO Auto-generated method stub
		return invoiceExportVoMapper.getOrderDetail(invoiceExportVo);
	}

	@Override
	public List<InvoiceExportVo> getExportDataInfoDetail(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return invoiceExportVoMapper.getExportDataInfo(paraMap);
	}

	@Override
	public void doExportDataToTxt(Map<String, Object> paraMap, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tokenStr = "~~";
		String time = DateUtil.getyyyy_mm_dd_hh_mi_s();
		String mod = paraMap.get("billtype").toString();
		String begindate=paraMap.get("begdate").toString();
		String head = "commonsingle";
		String type = "1";//普票
		if(mod.equals("20")) {
			head="vatsingle";
			type = "0";
		}	
		response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/vnd.ms-excel");
		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment;filename="+head+time+".txt");
        //response.setHeader("Content-disposition", new StringBuffer("attachment").append( ";filename=" ).append(head+time).append(".txt").toString());
		
		//response.setHeader("Content-disposition", "attachment;filename="+head+time+".txt");
		List<InvoiceExportVo> dataList=getExportDataInfoDetail(paraMap);
		
		String headStr = "SJJK0101~~销售单据传入~~销售业务";
        long second = DateUtil.getSecond();
        String billdate = DateUtil.transferyyyy_mm_ddToyyyyMMDD(begindate);
        
        String itemname="",itemprice="",orderqty="",amount="",taxname="",addressphone="",taxno="",bankaccount="",id="";
			
		LinkedHashMap<BillCustomerInfo, ArrayList<BillOrderInfo>> billmap = new LinkedHashMap<BillCustomerInfo, ArrayList<BillOrderInfo>>();
		
		id="";String idtmp="";
		if(dataList!=null&&dataList.size()>0)
		{	
			int linecount = 0 ;	
			int headcount = 1000 ;
			InvoiceExportVo row = new InvoiceExportVo();
			int alen=dataList.size();
			ArrayList items = new ArrayList();
			for(int i=0;i<alen;i++)
			{	
				row = dataList.get(i);
				itemname=row.getItemname();
				itemprice=row.getItemprice().toString();
				orderqty=row.getItemqty().toString();
				amount=row.getItemamount().toString();
				
				taxname=row.getName();
				addressphone="";	
				bankaccount="";	
				taxno=row.getId().toString();	
				
				BillOrderInfo bo = new BillOrderInfo();
				bo.setAmount(amount);
				bo.setGoodsname(itemname);
				bo.setPrice(itemprice);
				bo.setQuantity(orderqty);
				
				
			    linecount=linecount+1;			
			    
			    id=row.getId().toString();	
			    if(i+1<alen)idtmp=dataList.get(i+1).getId().toString();
			    else idtmp="";		
			    
			    if(!idtmp.equals(id)){
			    	if(linecount==9){
			    		headcount ++;
			    		BillCustomerInfo bc = new BillCustomerInfo();
						String saleno = second+String.valueOf(headcount);
						if(saleno.length()>20) saleno = saleno.substring(0,20);
						bc.setTaxname(taxname);
						bc.setTaxno(taxno);
						bc.setAddressphone(addressphone);
						bc.setBankaccount(bankaccount);
						bc.setItemnum(String.valueOf(linecount-1));
						bc.setBillnum(saleno);
						bc.setBilldate(billdate);
						billmap.put(bc,items);	
						
						//同时将items初始化
						items = new ArrayList();	
						linecount = 1 ;	
			    	}
			    	    items.add(bo);
						headcount ++;
						BillCustomerInfo bc = new BillCustomerInfo();
						String saleno = second+String.valueOf(headcount);
						if(saleno.length()>20) saleno = saleno.substring(0,20);
						bc.setTaxname(taxname);
						bc.setTaxno(taxno);
						bc.setAddressphone(addressphone);
						bc.setBankaccount(bankaccount);
						bc.setItemnum(String.valueOf(linecount));
						bc.setBillnum(saleno);
						bc.setBilldate(billdate);
						billmap.put(bc,items);
						
						//同时将items初始化
						items = new ArrayList();	
						linecount = 0 ;	
			    }else{
			    	if(linecount==9){	
			    		headcount ++;
			    		BillCustomerInfo bc = new BillCustomerInfo();
						String saleno = second+String.valueOf(headcount);
						if(saleno.length()>20) saleno = saleno.substring(0,20);
						bc.setTaxname(taxname);
						bc.setTaxno(taxno);
						bc.setAddressphone(addressphone);
						bc.setBankaccount(bankaccount);
						bc.setItemnum(String.valueOf(linecount-1));
						bc.setBillnum(saleno);
						bc.setBilldate(billdate);
						billmap.put(bc,items);	
						
						//同时将items初始化
						items = new ArrayList();	
						linecount = 1 ;	
			    	}
			    	items.add(bo);
			    }
			}	
		}
		Iterator it = billmap.keySet().iterator();
		PrintWriter printWriter=null ;
		//ServletOutputStream sos=null ;
		try {
			//printWriter = response.getWriter();
			ServletOutputStream sos=	response.getOutputStream();
		
			sos.write(headStr.getBytes("UTF-8"));
			sos.write("\r\n".getBytes("UTF-8"));
			//out.println(headStr);
	  		while(it.hasNext()){
	  			BillCustomerInfo bc = (BillCustomerInfo)it.next();
	  			String headline = bc.getBillnum()+ tokenStr + bc.getItemnum()+ tokenStr +bc.getTaxname()+tokenStr+bc.getTaxno()+  tokenStr +bc.getAddressphone()+ tokenStr + tokenStr +bc.getBankaccount()+ 
	  							tokenStr +"" +tokenStr +""+ tokenStr +""+ tokenStr +bc.getBilldate()+ tokenStr +"";
	  			sos.write(headline.getBytes("UTF-8"));
	  			sos.write("\r\n".getBytes("UTF-8"));
	  			//out.println(headline);
//	  			printWriter.write(headline);
	  			ArrayList<BillOrderInfo> items = (ArrayList<BillOrderInfo>)billmap.get(bc);
	  			for(int i=0 ; i<items.size(); i++){
	  				BillOrderInfo bo = (BillOrderInfo)items.get(i);
	  				String itemline = bo.getGoodsname()+tokenStr+bo.getUnit()+tokenStr+bo.getNorm()+tokenStr+bo.getQuantity()+tokenStr+bo.getAmount()+tokenStr+bo.getTaxtrate()+tokenStr+bo.getTaxindex()+tokenStr+bo.getDiscountamount()+tokenStr+bo.getTaxamount()+tokenStr+bo.getDiscounttaxamount()+tokenStr+bo.getDiscountrate()+tokenStr+bo.getPrice()+tokenStr;
	  				sos.write(itemline.getBytes("UTF-8"));
	  				sos.write("\r\n".getBytes("UTF-8"));
	  				//out.println(itemline);
//	  				printWriter.write(itemline);
	  			} 			
	  		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
