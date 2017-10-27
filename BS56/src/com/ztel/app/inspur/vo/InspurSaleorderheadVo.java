package com.ztel.app.inspur.vo;

public class InspurSaleorderheadVo {
	//订单编号、订单日期、送货日期、客户id、专卖证、金额、数量、送货车辆
	//String db2OrderSql="select co_num,born_date,arr_date,cust_id,amt_sum,qty_sum,car_id "+
	private String coNum;//订单编号
	private String bornDate;//订单日期
	private String arrDate;//送货日期
	private String custId;//客户id
	private String amtSum;//金额
	private String qtySum;//数量
	private String carId;//送货车辆
	
	public String getCoNum() {
		return coNum;
	}
	public void setCoNum(String coNum) {
		this.coNum = coNum;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAmtSum() {
		return amtSum;
	}
	public void setAmtSum(String amtSum) {
		this.amtSum = amtSum;
	}
	public String getQtySum() {
		return qtySum;
	}
	public void setQtySum(String qtySum) {
		this.qtySum = qtySum;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	
}
