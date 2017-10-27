package com.ztel.app.vo.safe;

import java.math.BigDecimal;

public class FireItemnoteVo {
    /**
     * 序号ID
     */
    private BigDecimal id;

    /**
     * 对应检查的父ID
     */
    private BigDecimal fid;

    /**
     * 系统ID
     */
    private Integer systemid;

    /**
     * 项目ID
     */
    private Integer projectid;

    /**
     * 检查情况（10：良好 20：不良）
     */
    private String status;

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
     * 对应检查的父ID
     * @return FID 对应检查的父ID
     */
    public BigDecimal getFid() {
        return fid;
    }

    /**
     * 对应检查的父ID
     * @param fid 对应检查的父ID
     */
    public void setFid(BigDecimal fid) {
        this.fid = fid;
    }

    /**
     * 系统ID
     * @return SYSTEMID 系统ID
     */
    public Integer getSystemid() {
        return systemid;
    }

    /**
     * 系统ID
     * @param systemid 系统ID
     */
    public void setSystemid(Integer systemid) {
        this.systemid = systemid;
    }

    /**
     * 项目ID
     * @return PROJECTID 项目ID
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * 项目ID
     * @param projectid 项目ID
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * 检查情况（10：良好 20：不良）
     * @return STATUS 检查情况（10：良好 20：不良）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 检查情况（10：良好 20：不良）
     * @param status 检查情况（10：良好 20：不良）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}