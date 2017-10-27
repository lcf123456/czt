package com.ztel.app.vo.wms;

import java.math.BigDecimal;

public class ConsignorVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * null
     */
    private String code;

    /**
     * null
     */
    private Object comapany;

    /**
     * null
     */
    private Object contact;

    /**
     * null
     */
    private String phone;

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
     * null
     * @return CODE null
     */
    public String getCode() {
        return code;
    }

    /**
     * null
     * @param code null
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * null
     * @return COMAPANY null
     */
    public Object getComapany() {
        return comapany;
    }

    /**
     * null
     * @param comapany null
     */
    public void setComapany(Object comapany) {
        this.comapany = comapany;
    }

    /**
     * null
     * @return CONTACT null
     */
    public Object getContact() {
        return contact;
    }

    /**
     * null
     * @param contact null
     */
    public void setContact(Object contact) {
        this.contact = contact;
    }

    /**
     * null
     * @return PHONE null
     */
    public String getPhone() {
        return phone;
    }

    /**
     * null
     * @param phone null
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}