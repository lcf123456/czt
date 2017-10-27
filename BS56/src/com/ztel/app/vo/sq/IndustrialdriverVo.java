package com.ztel.app.vo.sq;

import java.math.BigDecimal;

public class IndustrialdriverVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 对应卷烟厂家id
     */
    private BigDecimal factoryid;

    /**
     * 司机姓名
     */
    private String drivername;

    /**
     * 电话号码
     */
    private BigDecimal phonenum;

    /**
     * 卷烟厂家名称
     */
    private String factoryname;

    /**
     * 删除标志(10:在用   0:删除)
     */
    private String delstatus;

    /**
     * 用户密码
     */
    private String psw;

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
     * 对应卷烟厂家id
     * @return FACTORYID 对应卷烟厂家id
     */
    public BigDecimal getFactoryid() {
        return factoryid;
    }

    /**
     * 对应卷烟厂家id
     * @param factoryid 对应卷烟厂家id
     */
    public void setFactoryid(BigDecimal factoryid) {
        this.factoryid = factoryid;
    }

    /**
     * 司机姓名
     * @return DRIVERNAME 司机姓名
     */
    public String getDrivername() {
        return drivername;
    }

    /**
     * 司机姓名
     * @param drivername 司机姓名
     */
    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    /**
     * 电话号码
     * @return PHONENUM 电话号码
     */
    public BigDecimal getPhonenum() {
        return phonenum;
    }

    /**
     * 电话号码
     * @param phonenum 电话号码
     */
    public void setPhonenum(BigDecimal phonenum) {
        this.phonenum = phonenum;
    }

    /**
     * 卷烟厂家名称
     * @return FACTORYNAME 卷烟厂家名称
     */
    public String getFactoryname() {
        return factoryname;
    }

    /**
     * 卷烟厂家名称
     * @param factoryname 卷烟厂家名称
     */
    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname == null ? null : factoryname.trim();
    }

    /**
     * 删除标志(10:在用   0:删除)
     * @return DELSTATUS 删除标志(10:在用   0:删除)
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标志(10:在用   0:删除)
     * @param delstatus 删除标志(10:在用   0:删除)
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }

    /**
     * 用户密码
     * @return PSW 用户密码
     */
    public String getPsw() {
        return psw;
    }

    /**
     * 用户密码
     * @param psw 用户密码
     */
    public void setPsw(String psw) {
        this.psw = psw == null ? null : psw.trim();
    }

	public String getRemarks() {
		// TODO Auto-generated method stub
		return null;
	}
}