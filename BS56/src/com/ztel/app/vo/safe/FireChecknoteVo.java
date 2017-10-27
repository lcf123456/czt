package com.ztel.app.vo.safe;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FireChecknoteVo {
    /**
     * 序号ID
     */
    private BigDecimal id;

    /**
     * 创建人
     */
    private Long createid;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createtime;

    /**
     * 检查类型ID
     */
    private Integer typeid;

    /**
     * 备注信息
     */
    private String remarks;
    private String createname;
    public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	/**
     * 序号ID
     * @return ID 序号ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 创建人
     * @return CREATEID 创建人
     */
    public Long getCreateid() {
        return createid;
    }

    /**
     * 创建人
     * @param createid 创建人
     */
    public void setCreateid(Long createid) {
        this.createid = createid;
    }

    /**
     * 创建时间
     * @return CREATETIME 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 检查类型ID
     * @return TYPEID 检查类型ID
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 检查类型ID
     * @param typeid 检查类型ID
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 备注信息
     * @return REMARKS 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注信息
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}