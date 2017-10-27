package com.ztel.app.service.account;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ztel.app.vo.account.InvoiceExportVo;

public interface InvoiceExportVoService {

	public List<InvoiceExportVo> getInvoiceExportList(InvoiceExportVo invoiceExportVo);
	
	public List<InvoiceExportVo> getOrderDetail(InvoiceExportVo invoiceExportVo);
	
	public List<InvoiceExportVo> getExportDataInfoDetail(Map<String,Object> paraMap);
	
	public void doExportDataToTxt(Map<String,Object> paraMap,HttpServletResponse response);
	
	//public void doChangeMode(DistributionModeVo distributionModeVo);
}
