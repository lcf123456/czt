package com.ztel.webservice.wms.vo;

/**
 * 一号工程订单数据接口明细信息实体
 * @author lcf
 *
 */
public class WMSBillscanLineVo {

	private String bdpcigname;// 卷烟名称
	private String bdpcigcode;//标准件烟卷烟代码(卷烟件码)
	private String bdbcigcode;//标准条烟卷烟代码(卷烟条码)   (只接入，暂没处理)
	private String bdbillpnum;//应出/入货量（件）
	private String bdbillallbnum;//应出/入货总量（条）
	public String getBdpcigname() {
		return bdpcigname;
	}
	public void setBdpcigname(String bdpcigname) {
		this.bdpcigname = bdpcigname;
	}
	public String getBdpcigcode() {
		return bdpcigcode;
	}
	public void setBdpcigcode(String bdpcigcode) {
		this.bdpcigcode = bdpcigcode;
	}
	public String getBdbcigcode() {
		return bdbcigcode;
	}
	public void setBdbcigcode(String bdbcigcode) {
		this.bdbcigcode = bdbcigcode;
	}
	public String getBdbillpnum() {
		return bdbillpnum;
	}
	public void setBdbillpnum(String bdbillpnum) {
		this.bdbillpnum = bdbillpnum;
	}
	public String getBdbillallbnum() {
		return bdbillallbnum;
	}
	public void setBdbillallbnum(String bdbillallbnum) {
		this.bdbillallbnum = bdbillallbnum;
	}
	
}
