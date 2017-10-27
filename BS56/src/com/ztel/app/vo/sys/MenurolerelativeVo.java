package com.ztel.app.vo.sys;

import java.math.BigDecimal;

public class MenurolerelativeVo {
    /**
     * 挂接id
     */
    private Integer id;

    /**
     * 菜单id
     */
    private Integer menuid;

    /**
     * 系统角色id
     */
    private Integer sysroleid;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 排序
     */
    private BigDecimal seq;

    /**
     * 0 非启动项  10:启动项
     */
    private String isstartup;

    /**
     * 挂接id
     * @return ID 挂接id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 挂接id
     * @param id 挂接id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 菜单id
     * @return MENUID 菜单id
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * 菜单id
     * @param menuid 菜单id
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * 系统角色id
     * @return SYSROLEID 系统角色id
     */
    public Integer getSysroleid() {
        return sysroleid;
    }

    /**
     * 系统角色id
     * @param sysroleid 系统角色id
     */
    public void setSysroleid(Integer sysroleid) {
        this.sysroleid = sysroleid;
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
     * 排序
     * @return SEQ 排序
     */
    public BigDecimal getSeq() {
        return seq;
    }

    /**
     * 排序
     * @param seq 排序
     */
    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    /**
     * 0 非启动项  10:启动项
     * @return ISSTARTUP 0 非启动项  10:启动项
     */
    public String getIsstartup() {
        return isstartup;
    }

    /**
     * 0 非启动项  10:启动项
     * @param isstartup 0 非启动项  10:启动项
     */
    public void setIsstartup(String isstartup) {
        this.isstartup = isstartup == null ? null : isstartup.trim();
    }
}