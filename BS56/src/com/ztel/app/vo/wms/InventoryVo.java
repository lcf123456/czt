package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class InventoryVo {
    /**
     * 盘点编号
     */
    private BigDecimal inventoryid;

    /**
     * 时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 盘点类型
     */
    private BigDecimal inventorytype;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 货主
     */
    private Object consignsor;

    /**
     * 0 未确认 10 已确认
     */
    private BigDecimal status;
    
    private String begdate;
    private String enddate;
    
    private BigDecimal createid;
    private String createname;
    

    /**
     * 时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderdate;

    public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public BigDecimal getCreateid() {
		return createid;
	}

	public void setCreateid(BigDecimal createid) {
		this.createid = createid;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

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

	/**
     * 盘点编号
     * @return INVENTORYID 盘点编号
     */
    public BigDecimal getInventoryid() {
        return inventoryid;
    }

    /**
     * 盘点编号
     * @param inventoryid 盘点编号
     */
    public void setInventoryid(BigDecimal inventoryid) {
        this.inventoryid = inventoryid;
    }

    /**
     * 时间
     * @return CREATETIME 时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 时间
     * @param createtime 时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 盘点类型
     * @return inventorytype 盘点类型
     */
    public BigDecimal getInventorytype() {
        return inventorytype;
    }

    /**
     * 盘点类型
     * @param inventorytype 盘点类型
     */
    public void setInventorytype(BigDecimal inventorytype) {
        this.inventorytype = inventorytype;
    }

    /**
     * 数量
     * @return QTY 数量
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 数量
     * @param qty 数量
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 货主
     * @return CONSIGNSOR 货主
     */
    public Object getConsignsor() {
        return consignsor;
    }

    /**
     * 货主
     * @param consignsor 货主
     */
    public void setConsignsor(Object consignsor) {
        this.consignsor = consignsor;
    }

    /**
     * 0 未确认 10 已确认
     * @return STATUS 0 未确认 10 已确认
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * 0 未确认 10 已确认
     * @param status 0 未确认 10 已确认
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }
}