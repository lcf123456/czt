package com.ztel.app.vo.cost;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleWorkCostVo {
    /**
     * null
     */
    private Integer id;

    /**
     * 对应申报id
     */
    private BigDecimal fid;

    /**
     * 修理工位
     */
    private String repairstation;

    /**
     * 修理内容
     */
    private String repaircontent;

    /**
     * 修理工时
     */
    private BigDecimal repairtime;

    /**
     * 费用小计
     */
    private BigDecimal amount;

    /**
     * 内部id
     */
    private Long inid;

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
     * 审核标志
     */
    private String status;

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

    /**
     * 对应申报id
     * @return FID 对应申报id
     */
    public BigDecimal getFid() {
        return fid;
    }

    /**
     * 对应申报id
     * @param fid 对应申报id
     */
    public void setFid(BigDecimal fid) {
        this.fid = fid;
    }

    /**
     * 修理工位
     * @return REPAIRSTATION 修理工位
     */
    public String getRepairstation() {
        return repairstation;
    }

    /**
     * 修理工位
     * @param repairstation 修理工位
     */
    public void setRepairstation(String repairstation) {
        this.repairstation = repairstation == null ? null : repairstation.trim();
    }

    /**
     * 修理内容
     * @return REPAIRCONTENT 修理内容
     */
    public String getRepaircontent() {
        return repaircontent;
    }

    /**
     * 修理内容
     * @param repaircontent 修理内容
     */
    public void setRepaircontent(String repaircontent) {
        this.repaircontent = repaircontent == null ? null : repaircontent.trim();
    }

    /**
     * 修理工时
     * @return REPAIRTIME 修理工时
     */
    public BigDecimal getRepairtime() {
        return repairtime;
    }

    /**
     * 修理工时
     * @param repairtime 修理工时
     */
    public void setRepairtime(BigDecimal repairtime) {
        this.repairtime = repairtime;
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
     * 内部id
     * @return INID 内部id
     */
    public Long getInid() {
        return inid;
    }

    /**
     * 内部id
     * @param inid 内部id
     */
    public void setInid(Long inid) {
        this.inid = inid;
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
     * 审核标志
     * @return STATUS 审核标志
     */
    public String getStatus() {
        return status;
    }

    /**
     * 审核标志
     * @param status 审核标志
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}