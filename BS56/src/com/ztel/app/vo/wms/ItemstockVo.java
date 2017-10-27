package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

public class ItemstockVo {
    /**
     * 标识id
     */
    private BigDecimal id;   

    /**
     * 盘点日期
     */
    private Date createtime;

    /**
     * 盘点id(对应盘点表T_WMS_INVENTORY的id)
     */
    private BigDecimal inventoryid;

    /**
     * 记录人id
     */
    private Long createid;

    /**
     * 记录人
     */
    private String createname;
    
    /**
     * 搜索开始时间
     */
    private Date begintime;
    
    /**
     * 搜索结束时间
     */
    private Date endtime;

    /**
     * 标识id
     * @return ID 标识id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * 标识id
     * @param id 标识id
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 盘点日期
     * @return CREATETIME 盘点日期
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 盘点日期
     * @param createtime 盘点日期
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 盘点id(对应盘点表T_WMS_INVENTORY的id)
     * @return INVENTORYID 盘点id(对应盘点表T_WMS_INVENTORY的id)
     */
    public BigDecimal getInventoryid() {
        return inventoryid;
    }

    /**
     * 盘点id(对应盘点表T_WMS_INVENTORY的id)
     * @param inventoryid 盘点id(对应盘点表T_WMS_INVENTORY的id)
     */
    public void setInventoryid(BigDecimal inventoryid) {
        this.inventoryid = inventoryid;
    }

    /**
     * 记录人id
     * @return CREATEID 记录人id
     */
    public Long getCreateid() {
        return createid;
    }

    /**
     * 记录人id
     * @param createid 记录人id
     */
    public void setCreateid(Long createid) {
        this.createid = createid;
    }

    /**
     * 记录人
     * @return CREATENAME 记录人
     */
    public String getCreatename() {
        return createname;
    }

    public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
     * 记录人
     * @param createname 记录人
     */
    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }
    
    
}