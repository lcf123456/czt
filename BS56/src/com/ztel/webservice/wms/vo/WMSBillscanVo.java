package com.ztel.webservice.wms.vo;
/**
 * 一号工程订单数据接口主信息实体
 * @author lcf
 *
 */
public class WMSBillscanVo {

	private String headmsgid;//头消息中:消息id
	private String headcommercecode;//头消息中:货主
	private String headwsmethod;//头消息中:方法名称  BillCreate：单据创建  BillConfirm：单据确认
	private String bbuuid;//消息体中：一号工程接口单据唯一标识号bb_uuid
	private String bbtruckno;//消息体中：车牌号  (只接入，暂没处理)
	private String bbcontactno;//消息体中：合同号
	private String bbscanno;//准运证号
	private String bbflowname;//供应商
	private String bbinputdate;//单据录入日期
	private String bbtotapnum;//应出/入库量（件）
	private String bbtotalallbnum;//应出/入库量（条）   (只接入，暂没处理)
	private String bbtotalallnum1;//应出/入库量（万支） (只接入，暂没处理)
	public String getHeadmsgid() {
		return headmsgid;
	}
	public void setHeadmsgid(String headmsgid) {
		this.headmsgid = headmsgid;
	}
	public String getHeadcommercecode() {
		return headcommercecode;
	}
	public void setHeadcommercecode(String headcommercecode) {
		this.headcommercecode = headcommercecode;
	}
	public String getHeadwsmethod() {
		return headwsmethod;
	}
	public void setHeadwsmethod(String headwsmethod) {
		this.headwsmethod = headwsmethod;
	}
	public String getBbuuid() {
		return bbuuid;
	}
	public void setBbuuid(String bbuuid) {
		this.bbuuid = bbuuid;
	}
	public String getBbtruckno() {
		return bbtruckno;
	}
	public void setBbtruckno(String bbtruckno) {
		this.bbtruckno = bbtruckno;
	}
	public String getBbcontactno() {
		return bbcontactno;
	}
	public void setBbcontactno(String bbcontactno) {
		this.bbcontactno = bbcontactno;
	}
	public String getBbscanno() {
		return bbscanno;
	}
	public void setBbscanno(String bbscanno) {
		this.bbscanno = bbscanno;
	}
	
	public String getBbinputdate() {
		return bbinputdate;
	}
	public void setBbinputdate(String bbinputdate) {
		this.bbinputdate = bbinputdate;
	}
	public String getBbtotapnum() {
		return bbtotapnum;
	}
	public void setBbtotapnum(String bbtotapnum) {
		this.bbtotapnum = bbtotapnum;
	}
	public String getBbtotalallbnum() {
		return bbtotalallbnum;
	}
	public void setBbtotalallbnum(String bbtotalallbnum) {
		this.bbtotalallbnum = bbtotalallbnum;
	}
	public String getBbtotalallnum1() {
		return bbtotalallnum1;
	}
	public void setBbtotalallnum1(String bbtotalallnum1) {
		this.bbtotalallnum1 = bbtotalallnum1;
	}
	public String getBbflowname() {
		return bbflowname;
	}
	public void setBbflowname(String bbflowname) {
		this.bbflowname = bbflowname;
	}
	
}
