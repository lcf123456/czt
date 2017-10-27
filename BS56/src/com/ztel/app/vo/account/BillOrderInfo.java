package com.ztel.app.vo.account;

//用于发票ｔｘｔ文件导出
public class BillOrderInfo {
	
	private String goodsname = "";             //货物名称
	private String unit = "条";                  //计量单位　　　　　　　
	private String norm = "84mm";                  //规格             
	private String quantity = "";              //数量
	private String amount = "";                 //金额
	private String taxtrate = "0.1700";               //税率
	private String taxindex = "0101";               //商品税目
	private String discountamount = "";         //折扣金额
	private String taxamount = "";              //税额
	private String discounttaxamount = "";      //折扣税额
	private String discountrate = "";           //折扣率
	private String price = "";                  //单价,计算方式是金额/数量/1.17
	private String pricemode = "";              //价格方式
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}
	public String getDiscountrate() {
		return discountrate;
	}
	public void setDiscountrate(String discountrate) {
		this.discountrate = discountrate;
	}
	public String getDiscounttaxamount() {
		return discounttaxamount;
	}
	public void setDiscounttaxamount(String discounttaxamount) {
		this.discounttaxamount = discounttaxamount;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getNorm() {
		return norm;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPricemode() {
		return pricemode;
	}
	public void setPricemode(String pricemode) {
		this.pricemode = pricemode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getTaxindex() {
		return taxindex;
	}
	public void setTaxindex(String taxindex) {
		this.taxindex = taxindex;
	}
	public String getTaxtrate() {
		return taxtrate;
	}
	public void setTaxtrate(String taxtrate) {
		this.taxtrate = taxtrate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}

