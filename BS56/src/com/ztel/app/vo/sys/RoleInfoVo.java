package com.ztel.app.vo.sys;

import java.math.BigDecimal;
import java.util.Date;

public class RoleInfoVo {
    /**
     * 系统角色ID
     */
    private BigDecimal id;

    /**
     * 系统角色名称
     */
    private String rolename;

    /**
     * 最后更新时间
     */
    private Date updatetime;

    /**
     * 启用状10：启用；0：停用
     */
    private String status;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * @return ID null
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id null
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return ROLENAME null
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename null
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * @return UPDATETIME null
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime null
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return STATUS null
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status null
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return REMARKS null
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks null
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}