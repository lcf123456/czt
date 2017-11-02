package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OutBoundVo {
    /**
     * 出库编号
     */
    private BigDecimal outboundid;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 时间
     */
    private Date createtime;
    
    /**
     * 订单日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date orderdate;
    
    /**
     * 出库日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date outtime;

    /**
     * 出库类型(10订单出库 20 调拨出库)
     */
    private BigDecimal outboundtype;

    /**
     * 用户编号
     */
    private BigDecimal userid;

    /**
     * 备注
     */
    private Object remarks;

    /**
     * 10:在用   0:删除
     */
    private BigDecimal delstatus;

    /**
     * 车组号
     */
    private String vehicleno;

    /**
     * 货主
     */
    private Object consignsor;

    /**
     * 搜索结束时间
     */
    private Date endtime;
    
    /**
     * 搜索关键字
     */
    private String keyword;
    
    /**
     * 查询开始时间
     */
    private Date begintime;
    
    /**
     * 创建用户名
     */
    private String createusername;
    
    /**
     * 准运证
     */
    private String navicert;

    /**
     * 合同号（退货入库 放订单号  ）
     */
    private String contractno;
    
    /**
     * 出库编号
     * @return OUTBOUNDID 出库编号
     */
    public BigDecimal getOutboundid() {
        return outboundid;
    }

    /**
     * 出库编号
     * @param outboundid 出库编号
     */
    public void setOutboundid(BigDecimal outboundid) {
        this.outboundid = outboundid;
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
     * 出库类型(10订单出库 20 调拨出库)
     * @return OUTBOUNDTYPE 出库类型(10订单出库 20 调拨出库)
     */
    public BigDecimal getOutboundtype() {
        return outboundtype;
    }

    /**
     * 出库类型(10订单出库 20 调拨出库)
     * @param outboundtype 出库类型(10订单出库 20 调拨出库)
     */
    public void setOutboundtype(BigDecimal outboundtype) {
        this.outboundtype = outboundtype;
    }

    /**
     * 用户编号
     * @return USERID 用户编号
     */
    public BigDecimal getUserid() {
        return userid;
    }

    /**
     * 用户编号
     * @param userid 用户编号
     */
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    /**
     * 备注
     * @return REMARKS 备注
     */
    public Object getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

  
    
    /**
     * 10:在用   0:删除
     * @return DELSTATUS 10:在用   0:删除
     */
    public BigDecimal getDelstatus() {
		return delstatus;
	}

    /**
     * 10:在用   0:删除
     * @param delstatus 10:在用   0:删除
     */
	public void setDelstatus(BigDecimal delstatus) {
		this.delstatus = delstatus;
	}

	/**
     * 车组号
     * @return VEHICLENO 车组号
     */
    public String getVehicleno() {
        return vehicleno;
    }

    /**
     * 车组号
     * @param vehicleno 车组号
     */
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
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
     * 订单日期
     * @return
     */
	public Date getOrderdate() {
		return orderdate;
	}

	/**
	 * 订单日期
	 * @param orderdate 订单日期
	 */
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	/**
	 * 出库日期
	 * @return
	 */
	public Date getOuttime() {
		return outtime;
	}

	/**
	 * 出库日期 
	 * @param outtime 出库日期
	 */
	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	/**
	 * 搜索结束时间
	 * @return
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * 搜索结束时间
	 * @param endtime 搜索结束时间
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * 搜索关键字
	 * @return 搜索关键字
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 搜索关键字
	 * @param keyword 搜索关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 创建用户名
	 * @return
	 */
	public String getCreateusername() {
		return createusername;
	}

	/**
	 * 创建用户名
	 * @param createusername 创建用户名
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	 /**
     * 准运证
     * @return NAVICERT 准运证
     */
    public String getNavicert() {
        return navicert;
    }

    /**
     * 准运证
     * @param navicert 准运证
     */
    public void setNavicert(String navicert) {
        this.navicert = navicert == null ? null : navicert.trim();
    }

    /**
     * 合同号（退货入库 放订单号  ）
     * @return CONTRACTNO 合同号
     */
    public String getContractno() {
        return contractno;
    }

    /**
     * 合同号（退货入库 放订单号  ）
     * @param contractno 合同号
     */
    public void setContractno(String contractno) {
        this.contractno = contractno == null ? null : contractno.trim();
    }
	
}