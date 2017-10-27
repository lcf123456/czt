package com.ztel.webservice;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.webservice.wms.vo.WMSBillscanLineVo;
import com.ztel.webservice.wms.vo.WMSBillscanVo;

@WebService
@Service
public class WMSBillServiceImpl implements WMSBillService{
	@Autowired 
	private InBoundService inBoundService = null;;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

	public  String BillScan(String xml)
	{
		return xml;
	}
	
	/**
	 * 发送接收反馈
	 * @return
	 */
	private String returnResponse(String msg_id,String ws_mark,String ws_method,String state_code){
		String xml="<?xmlversion=\"1.0\" encoding=\"GBK\"?><dataset><head><msg_id>"+msg_id+"</msg_id><state_code>"+state_code+"</state_code>"+
				"<state_desc>状态描述</state_desc><ws_mark>"+ws_mark+"</ws_mark><ws_method>"+ws_method+"</ws_method><ws_param></ws_param>"+
				"<curr_time></curr_time><curr_user></curr_user></head></dataset>";
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<dataset>"+
				"<head>"+
				"<msg_id>"+msg_id+"</msg_id>"+
				"<state_code>"+state_code+"</state_code>"+
				"<state_desc>状态描述</state_desc>"+
				"<ws_mark>"+ws_mark+"</ws_mark>"+
				"<ws_method>"+ws_method+"</ws_method>"+
				"<ws_param/>"+
				"<curr_time/>"+
				"<curr_user/>"+
				"</head>"+
				"</dataset>";
		
		return xml;
	}
	
	public  String WMSBillService(String xml)
	{
		
		String retrunXml= xml;
		//此处添加日志：开始从一号工程接收数据----------------------
		UserVo userVo = new UserVo();
		userVo.setId(1L);
		userVo.setUsername("系统管理员");
		
		//StringBuffer buffer=new StringBuffer(); 
	
//			try {
//				readToBuffer(buffer,"f:\\a.xml");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			try {
				// xml=buffer.toString();
				 operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据", "1、开始接收", xml);
				 
				Document document = DocumentHelper.parseText(xml);
				Element root=document.getRootElement();
				Element head=root.element("head");//1级根下
					//头消息
					String msg_id=head.elementText("msg_id");//消息id
					String ws_mark=head.elementText("ws_mark");//消息id
					String commerce_code=head.elementText("commerce_code");//货主
					String ws_method = head.elementText("ws_method");//BillCreate：单据创建  BillConfirm：单据确认
					//String curr_time = head.elementText("curr_time");//发送时间or单据创建时间，接口文档中未明确给出
					
				Element datalist = root.element("datalist");//1级根下datalist
					Element data = datalist.element("data");//1级根下datalist下2级data
					//单据主表信息
					String bb_uuid = data.elementText("bb_uuid");//系统唯一标识
					String bb_truck_no = data.elementText("bb_truck_no");//车号
					//准运证和合同是一对一的。现在都是用准考证开单，一车可以有多个准考证，同样也可能有多个单据。
					String bb_contact_no = data.elementText("bb_contact_no");//单据合同号
					String bb_scan_no = data.elementText("bb_scan_no");//准运证编号（实物扫描编号）
					String bb_flow_name = data.elementText("bb_flow_name");//供应商
					String bb_input_date = data.elementText("bb_input_date");//单据录入日期
					String bb_total_pnum = data.elementText("bb_total_pnum");//应出/入库量（件）
					String bb_total_all_bnum = data.elementText("bb_total_all_bnum");//应出/入库量（条）我们系统暂时有件即可,所以暂时没用条
					String bb_total_all_num1 = data.elementText("bb_total_all_num1");//应出/入库量（万支） 我们系统暂时有件即可,所以暂时没用万支
					
					BigDecimal totalpnum = new BigDecimal("0");
					if(bb_total_pnum!=null&&!bb_total_pnum.equals(""))totalpnum= new BigDecimal(bb_total_pnum);
					
					//String bb_total_bnum = data.elementText("bb_total_bnum");//应出/入库量（条）
					//String bb_total_all_scan_bnum = data.elementText("bb_total_all_scan_bnum");//实际扫码总量（条）
					//String bb_total_all_scan_num1 = data.elementText("bb_total_all_scan_num1");//实际扫码总量（万支）
					//String bb_total_price = data.elementText("bb_total_price");//总金额元
					
					
					List<WMSBillscanLineVo> wMSBillscanLineVoList= new ArrayList<WMSBillscanLineVo>();
					Element list = data.element("list");//开始读明细信息
	//				Element data_1 = root.element("data_1");//明细信息列表
					Iterator iterss = list.elementIterator("data_1");
					while (iterss.hasNext()) {
	                    Element recordEless = (Element) iterss.next();
	                    String bd_pcig_name = recordEless.elementTextTrim("bd_pcig_name"); // 卷烟名称
	                    String bd_pcig_code = recordEless.elementTextTrim("bd_pcig_code"); //>标准件烟卷烟代码(卷烟件码)
	                    String bd_bcig_code = recordEless.elementTextTrim("bd_bcig_code"); //标准条烟卷烟代码(卷烟条码)
	                    String bd_bill_pnum =  recordEless.elementTextTrim("bd_bill_pnum"); //应出/入货量（件）
	                    WMSBillscanLineVo wMSBillscanLineVo = new WMSBillscanLineVo(); 
	                    wMSBillscanLineVo.setBdpcigname(bd_pcig_name);
	                    wMSBillscanLineVo.setBdpcigcode(bd_pcig_code);
	                    wMSBillscanLineVo.setBdbcigcode(bd_bcig_code);
	                    wMSBillscanLineVo.setBdbillpnum(bd_bill_pnum);
	                    wMSBillscanLineVoList.add(wMSBillscanLineVo);
					}
					
					WMSBillscanVo wMSBillscanVo = new WMSBillscanVo();
					wMSBillscanVo.setHeadmsgid(msg_id);
					wMSBillscanVo.setHeadcommercecode(commerce_code);
					wMSBillscanVo.setHeadwsmethod(ws_method);
					wMSBillscanVo.setBbuuid(bb_uuid);
					wMSBillscanVo.setBbtruckno(bb_truck_no);
					wMSBillscanVo.setBbcontactno(bb_contact_no);
					wMSBillscanVo.setBbscanno(bb_scan_no);
					wMSBillscanVo.setBbflowname(bb_flow_name);
					wMSBillscanVo.setBbinputdate(bb_input_date);
					wMSBillscanVo.setBbtotapnum(bb_total_pnum);
					wMSBillscanVo.setBbtotalallbnum(bb_total_all_bnum);
					wMSBillscanVo.setBbtotalallnum1(bb_total_all_num1);
					//此处添加日志：从一号工程接收数据完成----------------------

					operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据", "2、"+bb_uuid+"接收完成", "");
					int result = 1;
					//单据创建
					if(ws_method!=null&&!ws_method.equals("")&&ws_method.equals("BillCreate")){
						//获取S_WMS_INOUTBOUND序列值
						result = inBoundService.doInsertInBoundAndLineList(wMSBillscanVo, wMSBillscanLineVoList);
					}
					String state_code = "000";
					if(result==0)state_code="001";
					retrunXml = returnResponse(msg_id,ws_mark,ws_method,state_code);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return retrunXml;
	}
	
}
