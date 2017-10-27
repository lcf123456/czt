package com.ztel.app.vo.safe;

import java.math.BigDecimal;

public class HazardsInfoVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * l分值
     */
    private BigDecimal lvalue;

    /**
     * l名称(事故发生的可能性)
     */
    private String lname;

    /**
     * e分值
     */
    private BigDecimal evalue;

    /**
     * e名称(暴露的频繁程度)
     */
    private String ename;

    /**
     * c分值
     */
    private BigDecimal cvalue;

    /**
     * c名称（发生事故产生的后果）
     */
    private String cname;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标识 10:正常  0:删除
     */
    private String delstatus;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * l分值
     * @return LVALUE l分值
     */
    public BigDecimal getLvalue() {
        return lvalue;
    }

    /**
     * l分值
     * @param lvalue l分值
     */
    public void setLvalue(BigDecimal lvalue) {
        this.lvalue = lvalue;
    }

    /**
     * l名称(事故发生的可能性)
     * @return LNAME l名称(事故发生的可能性)
     */
    public String getLname() {
        return lname;
    }

    /**
     * l名称(事故发生的可能性)
     * @param lname l名称(事故发生的可能性)
     */
    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    /**
     * e分值
     * @return EVALUE e分值
     */
    public BigDecimal getEvalue() {
        return evalue;
    }

    /**
     * e分值
     * @param evalue e分值
     */
    public void setEvalue(BigDecimal evalue) {
        this.evalue = evalue;
    }

    /**
     * e名称(暴露的频繁程度)
     * @return ENAME e名称(暴露的频繁程度)
     */
    public String getEname() {
        return ename;
    }

    /**
     * e名称(暴露的频繁程度)
     * @param ename e名称(暴露的频繁程度)
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * c分值
     * @return CVALUE c分值
     */
    public BigDecimal getCvalue() {
        return cvalue;
    }

    /**
     * c分值
     * @param cvalue c分值
     */
    public void setCvalue(BigDecimal cvalue) {
        this.cvalue = cvalue;
    }

    /**
     * c名称（发生事故产生的后果）
     * @return CNAME c名称（发生事故产生的后果）
     */
    public String getCname() {
        return cname;
    }

    /**
     * c名称（发生事故产生的后果）
     * @param cname c名称（发生事故产生的后果）
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
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
     * 删除标识 10:正常  0:删除
     * @return DELSTATUS 删除标识 10:正常  0:删除
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标识 10:正常  0:删除
     * @param delstatus 删除标识 10:正常  0:删除
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
}