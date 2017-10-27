package com.ztel.app.vo.produce;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVo {
    /**
     * null
     */
    private String billcode;

    /**
     * null
     */
    private String companycode;

    /**
     * null
     */
    private String companyname;

    /**
     * null
     */
    private String batchcode;

    /**
     * null
     */
    private BigDecimal synseq;

    /**
     * null
     */
    private BigDecimal orderquantity;

    /**
     * null
     */
    private BigDecimal ordermoney;

    /**
     * null
     */
    private String customercode;

    /**
     * null
     */
    private String customername;

    /**
     * null
     */
    private String address;

    /**
     * null
     */
    private String telephone;

    /**
     * null
     */
    private BigDecimal priority;

    /**
     * null
     */
    private String regioncode;

    /**
     * null
     */
    private String tasknumbers;

    /**
     * null
     */
    private String taskboxies;

    /**
     * null
     */
    private String state;

    /**
     * 送货顺序
     */
    private BigDecimal devseq;

    /**
     * id序列
     */
    private BigDecimal id;

    /**
     * null
     */
    private Date createtime;

    /**
     * 订单日期
     */
    private Date orderdate;

    /**
     * null
     * @return BILLCODE null
     */
    public String getBillcode() {
        return billcode;
    }

    /**
     * null
     * @param billcode null
     */
    public void setBillcode(String billcode) {
        this.billcode = billcode == null ? null : billcode.trim();
    }

    /**
     * null
     * @return COMPANYCODE null
     */
    public String getCompanycode() {
        return companycode;
    }

    /**
     * null
     * @param companycode null
     */
    public void setCompanycode(String companycode) {
        this.companycode = companycode == null ? null : companycode.trim();
    }

    /**
     * null
     * @return COMPANYNAME null
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * null
     * @param companyname null
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    /**
     * null
     * @return BATCHCODE null
     */
    public String getBatchcode() {
        return batchcode;
    }

    /**
     * null
     * @param batchcode null
     */
    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode == null ? null : batchcode.trim();
    }

    /**
     * null
     * @return SYNSEQ null
     */
    public BigDecimal getSynseq() {
        return synseq;
    }

    /**
     * null
     * @param synseq null
     */
    public void setSynseq(BigDecimal synseq) {
        this.synseq = synseq;
    }

    /**
     * null
     * @return ORDERQUANTITY null
     */
    public BigDecimal getOrderquantity() {
        return orderquantity;
    }

    /**
     * null
     * @param orderquantity null
     */
    public void setOrderquantity(BigDecimal orderquantity) {
        this.orderquantity = orderquantity;
    }

    /**
     * null
     * @return ORDERMONEY null
     */
    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    /**
     * null
     * @param ordermoney null
     */
    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    /**
     * null
     * @return CUSTOMERCODE null
     */
    public String getCustomercode() {
        return customercode;
    }

    /**
     * null
     * @param customercode null
     */
    public void setCustomercode(String customercode) {
        this.customercode = customercode == null ? null : customercode.trim();
    }

    /**
     * null
     * @return CUSTOMERNAME null
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * null
     * @param customername null
     */
    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    /**
     * null
     * @return ADDRESS null
     */
    public String getAddress() {
        return address;
    }

    /**
     * null
     * @param address null
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * null
     * @return TELEPHONE null
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * null
     * @param telephone null
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * null
     * @return PRIORITY null
     */
    public BigDecimal getPriority() {
        return priority;
    }

    /**
     * null
     * @param priority null
     */
    public void setPriority(BigDecimal priority) {
        this.priority = priority;
    }

    /**
     * null
     * @return REGIONCODE null
     */
    public String getRegioncode() {
        return regioncode;
    }

    /**
     * null
     * @param regioncode null
     */
    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode == null ? null : regioncode.trim();
    }

    /**
     * null
     * @return TASKNUMBERS null
     */
    public String getTasknumbers() {
        return tasknumbers;
    }

    /**
     * null
     * @param tasknumbers null
     */
    public void setTasknumbers(String tasknumbers) {
        this.tasknumbers = tasknumbers == null ? null : tasknumbers.trim();
    }

    /**
     * null
     * @return TASKBOXIES null
     */
    public String getTaskboxies() {
        return taskboxies;
    }

    /**
     * null
     * @param taskboxies null
     */
    public void setTaskboxies(String taskboxies) {
        this.taskboxies = taskboxies == null ? null : taskboxies.trim();
    }

    /**
     * null
     * @return STATE null
     */
    public String getState() {
        return state;
    }

    /**
     * null
     * @param state null
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 送货顺序
     * @return DEVSEQ 送货顺序
     */
    public BigDecimal getDevseq() {
        return devseq;
    }

    /**
     * 送货顺序
     * @param devseq 送货顺序
     */
    public void setDevseq(BigDecimal devseq) {
        this.devseq = devseq;
    }

    /**
     * id序列
     * @return ID id序列
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * id序列
     * @param id id序列
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * null
     * @return CREATETIME null
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * null
     * @param createtime null
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 订单日期
     * @return ORDERDATE 订单日期
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * 订单日期
     * @param orderdate 订单日期
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }
}