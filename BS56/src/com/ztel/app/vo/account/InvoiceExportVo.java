package com.ztel.app.vo.account;

import java.math.BigDecimal;

public class InvoiceExportVo {
    private BigDecimal id;
    private String code;
    private String name;
    private BigDecimal qty;
    private BigDecimal amount;
    
    private String orderdate;
    private String itemcode;
    private String itemname;
    private String itemuom;
    private BigDecimal itemqty;
    private BigDecimal itemprice;
    private BigDecimal itemamount;
    
    private String begdate;
    private String enddate;
    private String param;
    private String billtype;
    
	public String getBegdate() {
		return begdate;
	}
	public void setBegdate(String begdate) {
		this.begdate = begdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemuom() {
		return itemuom;
	}
	public void setItemuom(String itemuom) {
		this.itemuom = itemuom;
	}
	public BigDecimal getItemqty() {
		return itemqty;
	}
	public void setItemqty(BigDecimal itemqty) {
		this.itemqty = itemqty;
	}
	public BigDecimal getItemamount() {
		return itemamount;
	}
	public void setItemamount(BigDecimal itemamount) {
		this.itemamount = itemamount;
	}
	public BigDecimal getItemprice() {
		return itemprice;
	}
	public void setItemprice(BigDecimal itemprice) {
		this.itemprice = itemprice;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

}