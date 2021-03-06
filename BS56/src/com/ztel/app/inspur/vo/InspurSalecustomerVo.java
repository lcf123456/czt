package com.ztel.app.inspur.vo;

public class InspurSalecustomerVo {
//	String db2CustSql="select cust_id,license_code,cust_name,order_way,bank,account,tax_account, "+
//			"manager,PERIODS_ID,busi_addr,order_tel,receive_tel2,car_id,"+
//			"work_port,base_type,cust_short_id "+
//			"from db2inst2.V_SALE_CUSTOMER";
	private String custId;//id
	private String licenseCode;//专卖证
	private String custName;//零售户名称
	private String orderWay;//订货渠道订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
	private String bank;//银行
	private String account;//账户
	private String taxAccount;//税号
	private String manager;//负责人
	private String periodsId;//批次
	private String busiAddr;//送货地址
	private String orderTel;//负责人电话
	private String receiveTel2;//备用电话
	private String carId;//车组
	private String workPort;//市场类型 城镇C 乡村X
	private String baseType;//经营业态：Z食杂店B便利店S超市N商场Y烟酒商店F娱乐服务Q其他 
	private String custShortId;//010开头的专卖证号
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTaxAccount() {
		return taxAccount;
	}
	public void setTaxAccount(String taxAccount) {
		this.taxAccount = taxAccount;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getPeriodsId() {
		return periodsId;
	}
	public void setPeriodsId(String periodsId) {
		this.periodsId = periodsId;
	}
	public String getBusiAddr() {
		return busiAddr;
	}
	public void setBusiAddr(String busiAddr) {
		this.busiAddr = busiAddr;
	}
	public String getOrderTel() {
		return orderTel;
	}
	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}
	public String getReceiveTel2() {
		return receiveTel2;
	}
	public void setReceiveTel2(String receiveTel2) {
		this.receiveTel2 = receiveTel2;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getWorkPort() {
		return workPort;
	}
	public void setWorkPort(String workPort) {
		this.workPort = workPort;
	}
	public String getBaseType() {
		return baseType;
	}
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	public String getCustShortId() {
		return custShortId;
	}
	public void setCustShortId(String custShortId) {
		this.custShortId = custShortId;
	}
	
	
}
