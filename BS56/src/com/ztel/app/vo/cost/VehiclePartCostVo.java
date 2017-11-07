package com.ztel.app.vo.cost;

import java.math.BigDecimal;
import java.util.Date;

public class VehiclePartCostVo {
    /**
     * null
     */
    private Integer id;

    /**
     * 申报id
     */
    private BigDecimal did;

    /**
     * 配件id
     */
    private String partid;

    /**
     * 配件单位
     */
    private String partunit;

    /**
     * 配件数量
     */
    private BigDecimal partqty;

    /**
     * 配件单价
     */
    private BigDecimal partunitprice;

    /**
     * 费用小计
     */
    private BigDecimal amount;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 记录日期
     */
    private Date createdate;

    /**
     * 是否重复（10：未重复；20：重复）
     */
    private String isrepeat;

    /**
     * 有效期止
     */
    private Date enddate;

    /**
     * 配件位置（1：前左；2：前右；3：后左；4后右；5：其它）
     */
    private String partpost;

    /**
     * 车辆id
     */
    private Integer vehicleid;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 配件名称
     */
    private String partname;

    /**
     * null
     * @return ID null
     */
    public Integer getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDid() {
		return did;
	}

	public void setDid(BigDecimal did) {
		this.did = did;
	}

	/**
     * 配件id
     * @return PARTID 配件id
     */
    public String getPartid() {
        return partid;
    }

    /**
     * 配件id
     * @param partid 配件id
     */
    public void setPartid(String partid) {
        this.partid = partid == null ? null : partid.trim();
    }

    /**
     * 配件单位
     * @return PARTUNIT 配件单位
     */
    public String getPartunit() {
        return partunit;
    }

    /**
     * 配件单位
     * @param partunit 配件单位
     */
    public void setPartunit(String partunit) {
        this.partunit = partunit == null ? null : partunit.trim();
    }

    /**
     * 配件数量
     * @return PARTQTY 配件数量
     */
    public BigDecimal getPartqty() {
        return partqty;
    }

    /**
     * 配件数量
     * @param partqty 配件数量
     */
    public void setPartqty(BigDecimal partqty) {
        this.partqty = partqty;
    }

    /**
     * 配件单价
     * @return PARTUNITPRICE 配件单价
     */
    public BigDecimal getPartunitprice() {
        return partunitprice;
    }

    /**
     * 配件单价
     * @param partunitprice 配件单价
     */
    public void setPartunitprice(BigDecimal partunitprice) {
        this.partunitprice = partunitprice;
    }

    /**
     * 费用小计
     * @return AMOUNT 费用小计
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 费用小计
     * @param amount 费用小计
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    /**
     * 记录人
     * @return CREATEID 记录人
     */
    public Long getCreateid() {
        return createid;
    }

    /**
     * 记录人
     * @param createid 记录人
     */
    public void setCreateid(Long createid) {
        this.createid = createid;
    }

    /**
     * 记录日期
     * @return CREATEDATE 记录日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 记录日期
     * @param createdate 记录日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 是否重复（10：未重复；20：重复）
     * @return ISREPEAT 是否重复（10：未重复；20：重复）
     */
    public String getIsrepeat() {
        return isrepeat;
    }

    /**
     * 是否重复（10：未重复；20：重复）
     * @param isrepeat 是否重复（10：未重复；20：重复）
     */
    public void setIsrepeat(String isrepeat) {
        this.isrepeat = isrepeat == null ? null : isrepeat.trim();
    }

    /**
     * 有效期止
     * @return ENDDATE 有效期止
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 有效期止
     * @param enddate 有效期止
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 配件位置（1：前左；2：前右；3：后左；4后右；5：其它）
     * @return PARTPOST 配件位置（1：前左；2：前右；3：后左；4后右；5：其它）
     */
    public String getPartpost() {
        return partpost;
    }

    /**
     * 配件位置（1：前左；2：前右；3：后左；4后右；5：其它）
     * @param partpost 配件位置（1：前左；2：前右；3：后左；4后右；5：其它）
     */
    public void setPartpost(String partpost) {
        this.partpost = partpost == null ? null : partpost.trim();
    }

    /**
     * 车辆id
     * @return VEHICLEID 车辆id
     */
    public Integer getVehicleid() {
        return vehicleid;
    }

    /**
     * 车辆id
     * @param vehicleid 车辆id
     */
    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    /**
     * 车组CODE
     * @return ROUTECODE 车组CODE
     */
    public String getRoutecode() {
        return routecode;
    }

    /**
     * 车组CODE
     * @param routecode 车组CODE
     */
    public void setRoutecode(String routecode) {
        this.routecode = routecode == null ? null : routecode.trim();
    }

    /**
     * 配件名称
     * @return PARTNAME 配件名称
     */
    public String getPartname() {
        return partname;
    }

    /**
     * 配件名称
     * @param partname 配件名称
     */
    public void setPartname(String partname) {
        this.partname = partname == null ? null : partname.trim();
    }
}