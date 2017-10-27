package com.ztel.webservice;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.service.wms.Impl.InBoundServiceImpl;
import com.ztel.app.vo.wms.InBoundVo;

@WebService
@Service
public class WMSBillServiceImpl implements WMSBillService{
	@Autowired 
	static InBoundService service;
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
		
		StringBuffer buffer=new StringBuffer(); 
	
//			try {
//				readToBuffer(buffer,"e:\\BillScan_1446434336309_rfid.xml");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				 xml=buffer.toString();
//				Document document = DocumentHelper.parseText(xml);
//				Element root=document.getRootElement();
//				Element head=root.element("head");
//				String ws_mark=head.elementText("ws_mark");
//				InBoundVo vo=new InBoundVo();
//				vo.setRemarks("ddddd");
//				//vo.setQty();
//				vo.setNavicert("11111111111111");
//				vo.setContractno("1222222222");
//				vo.setConsignsor("ddddd");
//				vo.setSupplier("ddddddddd22");
//				service.InsertInBound(vo);
//				int j=0;
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		return xml;
	}
	
}
