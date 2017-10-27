package com.ztel.app.vo.cost;

import java.math.BigDecimal;

public class SPLTypeStockVo {
    /**
     * id序号
     */
    private BigDecimal id;
    
    private Integer maintypeid;

    /**
     * 类别id
     */
    private Integer typeid;

    private String typename;
    private String maintypename;
    /**
     * 设备数量
     */
    private BigDecimal quantity;

    /**
     * 设备计量单位
     */
    private String unit;

    /**
     * 设备剩余数量
     */
    private BigDecimal surplusqty;
    
    /**
     * 申请数量
     */
    private BigDecimal applyqty;

    /**
     * 设备单价
     */
    private BigDecimal price;

    /**
     * 总金额
     */
    private BigDecimal totalamount;
    
    private String param;
    
    private String splcode;
    private String splname;
    private String suppliername;
    private BigDecimal splprice;
    private BigDecimal splqty;
    private BigDecimal splamount;

    public String getSplcode() {
		return splcode;
	}

	public void setSplcode(String splcode) {
		this.splcode = splcode;
	}

	public String getSplname() {
		return splname;
	}

	public void setSplname(String splname) {
		this.splname = splname;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public BigDecimal getSplprice() {
		return splprice;
	}

	public void setSplprice(BigDecimal splprice) {
		this.splprice = splprice;
	}

	public BigDecimal getSplqty() {
		return splqty;
	}

	public void setSplqty(BigDecimal splqty) {
		this.splqty = splqty;
	}

	public BigDecimal getSplamount() {
		return splamount;
	}

	public void setSplamount(BigDecimal splamount) {
		this.splamount = splamount;
	}

	public Integer getMaintypeid() {
		return maintypeid;
	}

	public void setMaintypeid(Integer maintypeid) {
		this.maintypeid = maintypeid;
	}

	public String getMaintypename() {
		return maintypename;
	}

	public void setMaintypename(String maintypename) {
		this.maintypename = maintypename;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/**
     * id序号
     * @return ID id序号
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * id序号
     * @param id id序号
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 类别id
     * @return TYPEID 类别id
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 类别id
     * @param typeid 类别id
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 设备数量
     * @return QUANTITY 设备数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 设备数量
     * @param quantity 设备数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 设备剩余数量
     * @return SURPLUSQTY 设备剩余数量
     */
    public BigDecimal getSurplusqty() {
        return surplusqty;
    }

    /**
     * 设备剩余数量
     * @param surplusqty 设备剩余数量
     */
    public void setSurplusqty(BigDecimal surplusqty) {
        this.surplusqty = surplusqty;
    }

    /**
     * 设备计量单位
     * @return UNIT 设备计量单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设备计量单位
     * @param unit 设备计量单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 申请数量
     * @return APPLYQTY 申请数量
     */
    public BigDecimal getApplyqty() {
        return applyqty;
    }

    /**
     * 申请数量
     * @param applyqty 申请数量
     */
    public void setApplyqty(BigDecimal applyqty) {
        this.applyqty = applyqty;
    }

    /**
     * 设备单价
     * @return PRICE 设备单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设备单价
     * @param price 设备单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 总金额
     * @return TOTALAMOUNT 总金额
     */
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    /**
     * 总金额
     * @param totalamount 总金额
     */
    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }
}