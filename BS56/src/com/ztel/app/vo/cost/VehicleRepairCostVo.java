package com.ztel.app.vo.cost;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleRepairCostVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 申报id
     */
    private String did;

    /**
     * 车辆id
     */
    private Integer vehicleid;

    /**
     * 修理厂id
     */
    private BigDecimal unitid;

    /**
     * 修理类型
     */
    private String rtype;

    /**
     * 是否付款 0：未付款 1：已付款
     */
    private String ispay;

    /**
     * 地址
     */
    private String addr;

    /**
     * 材料费
     */
    private BigDecimal materamount;

    /**
     * 工时费
     */
    private BigDecimal workeamount;

    /**
     * 管理费
     */
    private BigDecimal manaamount;

    /**
     * 其它费
     */
    private BigDecimal othercost;

    /**
     * 销售配件费
     */
    private BigDecimal partcost;

    /**
     * 费用累计
     */
    private BigDecimal cost;

    /**
     * 轮胎费
     */
    private BigDecimal tyrecost;

    /**
     * 电瓶费
     */
    private BigDecimal batterycost;

    /**
     * 总费用（修理累计费用＋轮胎费＋电瓶费）
     */
    private BigDecimal totalcost;

    /**
     * 修理日期
     */
    private Date rdate;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 费用归属人
     */
    private Long belongid;

    /**
     * 费用归属部门
     */
    private Integer belongdeptid;

    /**
     * 费用归属岗位
     */
    private Integer belongpostid;

    /**
     * 环节id
     */
    private Integer segid;

    /**
     * 审核标志（10：未审核,20：审核通过,30：审核驳回,:40已结算）
     */
    private String status;

    /**
     * 审核人员
     */
    private Long auditid;

    /**
     * 审核日期
     */
    private Date auditdate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 审核意见
     */
    private String suggestion;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 里程数
     */
    private BigDecimal mileage;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * null
     * @return ID null
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 申报id
     * @return DID 申报id
     */
    public String getDid() {
        return did;
    }

    /**
     * 申报id
     * @param did 申报id
     */
    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
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
     * 修理厂id
     * @return UNITID 修理厂id
     */
    public BigDecimal getUnitid() {
        return unitid;
    }

    /**
     * 修理厂id
     * @param unitid 修理厂id
     */
    public void setUnitid(BigDecimal unitid) {
        this.unitid = unitid;
    }

    /**
     * 修理类型
     * @return RTYPE 修理类型
     */
    public String getRtype() {
        return rtype;
    }

    /**
     * 修理类型
     * @param rtype 修理类型
     */
    public void setRtype(String rtype) {
        this.rtype = rtype == null ? null : rtype.trim();
    }

    /**
     * 是否付款 0：未付款 1：已付款
     * @return ISPAY 是否付款 0：未付款 1：已付款
     */
    public String getIspay() {
        return ispay;
    }

    /**
     * 是否付款 0：未付款 1：已付款
     * @param ispay 是否付款 0：未付款 1：已付款
     */
    public void setIspay(String ispay) {
        this.ispay = ispay == null ? null : ispay.trim();
    }

    /**
     * 地址
     * @return ADDR 地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 地址
     * @param addr 地址
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * 材料费
     * @return MATERAMOUNT 材料费
     */
    public BigDecimal getMateramount() {
        return materamount;
    }

    /**
     * 材料费
     * @param materamount 材料费
     */
    public void setMateramount(BigDecimal materamount) {
        this.materamount = materamount;
    }

    /**
     * 工时费
     * @return WORKEAMOUNT 工时费
     */
    public BigDecimal getWorkeamount() {
        return workeamount;
    }

    /**
     * 工时费
     * @param workeamount 工时费
     */
    public void setWorkeamount(BigDecimal workeamount) {
        this.workeamount = workeamount;
    }

    /**
     * 管理费
     * @return MANAAMOUNT 管理费
     */
    public BigDecimal getManaamount() {
        return manaamount;
    }

    /**
     * 管理费
     * @param manaamount 管理费
     */
    public void setManaamount(BigDecimal manaamount) {
        this.manaamount = manaamount;
    }

    /**
     * 其它费
     * @return OTHERCOST 其它费
     */
    public BigDecimal getOthercost() {
        return othercost;
    }

    /**
     * 其它费
     * @param othercost 其它费
     */
    public void setOthercost(BigDecimal othercost) {
        this.othercost = othercost;
    }

    /**
     * 销售配件费
     * @return PARTCOST 销售配件费
     */
    public BigDecimal getPartcost() {
        return partcost;
    }

    /**
     * 销售配件费
     * @param partcost 销售配件费
     */
    public void setPartcost(BigDecimal partcost) {
        this.partcost = partcost;
    }

    /**
     * 费用累计
     * @return COST 费用累计
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 费用累计
     * @param cost 费用累计
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 轮胎费
     * @return TYRECOST 轮胎费
     */
    public BigDecimal getTyrecost() {
        return tyrecost;
    }

    /**
     * 轮胎费
     * @param tyrecost 轮胎费
     */
    public void setTyrecost(BigDecimal tyrecost) {
        this.tyrecost = tyrecost;
    }

    /**
     * 电瓶费
     * @return BATTERYCOST 电瓶费
     */
    public BigDecimal getBatterycost() {
        return batterycost;
    }

    /**
     * 电瓶费
     * @param batterycost 电瓶费
     */
    public void setBatterycost(BigDecimal batterycost) {
        this.batterycost = batterycost;
    }

    /**
     * 总费用（修理累计费用＋轮胎费＋电瓶费）
     * @return TOTALCOST 总费用（修理累计费用＋轮胎费＋电瓶费）
     */
    public BigDecimal getTotalcost() {
        return totalcost;
    }

    /**
     * 总费用（修理累计费用＋轮胎费＋电瓶费）
     * @param totalcost 总费用（修理累计费用＋轮胎费＋电瓶费）
     */
    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }

    /**
     * 修理日期
     * @return RDATE 修理日期
     */
    public Date getRdate() {
        return rdate;
    }

    /**
     * 修理日期
     * @param rdate 修理日期
     */
    public void setRdate(Date rdate) {
        this.rdate = rdate;
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
     * 费用归属人
     * @return BELONGID 费用归属人
     */
    public Long getBelongid() {
        return belongid;
    }

    /**
     * 费用归属人
     * @param belongid 费用归属人
     */
    public void setBelongid(Long belongid) {
        this.belongid = belongid;
    }

    /**
     * 费用归属部门
     * @return BELONGDEPTID 费用归属部门
     */
    public Integer getBelongdeptid() {
        return belongdeptid;
    }

    /**
     * 费用归属部门
     * @param belongdeptid 费用归属部门
     */
    public void setBelongdeptid(Integer belongdeptid) {
        this.belongdeptid = belongdeptid;
    }

    /**
     * 费用归属岗位
     * @return BELONGPOSTID 费用归属岗位
     */
    public Integer getBelongpostid() {
        return belongpostid;
    }

    /**
     * 费用归属岗位
     * @param belongpostid 费用归属岗位
     */
    public void setBelongpostid(Integer belongpostid) {
        this.belongpostid = belongpostid;
    }

    /**
     * 环节id
     * @return SEGID 环节id
     */
    public Integer getSegid() {
        return segid;
    }

    /**
     * 环节id
     * @param segid 环节id
     */
    public void setSegid(Integer segid) {
        this.segid = segid;
    }

    /**
     * 审核标志（10：未审核,20：审核通过,30：审核驳回,:40已结算）
     * @return STATUS 审核标志（10：未审核,20：审核通过,30：审核驳回,:40已结算）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 审核标志（10：未审核,20：审核通过,30：审核驳回,:40已结算）
     * @param status 审核标志（10：未审核,20：审核通过,30：审核驳回,:40已结算）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 审核人员
     * @return AUDITID 审核人员
     */
    public Long getAuditid() {
        return auditid;
    }

    /**
     * 审核人员
     * @param auditid 审核人员
     */
    public void setAuditid(Long auditid) {
        this.auditid = auditid;
    }

    /**
     * 审核日期
     * @return AUDITDATE 审核日期
     */
    public Date getAuditdate() {
        return auditdate;
    }

    /**
     * 审核日期
     * @param auditdate 审核日期
     */
    public void setAuditdate(Date auditdate) {
        this.auditdate = auditdate;
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
     * 审核意见
     * @return SUGGESTION 审核意见
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * 审核意见
     * @param suggestion 审核意见
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
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
     * 里程数
     * @return MILEAGE 里程数
     */
    public BigDecimal getMileage() {
        return mileage;
    }

    /**
     * 里程数
     * @param mileage 里程数
     */
    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    /**
     * 优惠金额
     * @return DISCOUNT 优惠金额
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 优惠金额
     * @param discount 优惠金额
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}