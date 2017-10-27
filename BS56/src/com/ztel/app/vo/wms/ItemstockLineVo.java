package com.ztel.app.vo.wms;

import java.math.BigDecimal;

public class ItemstockLineVo {
    /**
     * 标识id
     */
    private BigDecimal id;

    /**
     * 父id
     */
    private BigDecimal parentid;

    /**
     * 品牌名称
     */
    private Object cigarettename;

    /**
     * 编码
     */
    private String cigarettecode;
    /**
     * 件烟数量(入数量)
     */
    private BigDecimal inboxqty;
    /**
     * 件烟数量(出数量)
     */
    private BigDecimal outboxqty;
    /**
     * 件烟数量(剩余数量)
     */
    private BigDecimal boxqty;
    
    /**
     * 条烟数量(入数量)
     */
    private BigDecimal initemqty;
    
    /**
     * 条烟数量(出数量)
     */
    private BigDecimal outitemqty;

    /**
     * 条烟数量(剩余数量)
     */
    private BigDecimal itemqty;

    /**
     * 货主
     */
    private Object consignsor;

    /**
     * 标识id
     * @return ID 标识id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * 标识id
     * @param id 标识id
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 父id
     * @return PARENTID 父id
     */
    public BigDecimal getParentid() {
        return parentid;
    }

    /**
     * 父id
     * @param parentid 父id
     */
    public void setParentid(BigDecimal parentid) {
        this.parentid = parentid;
    }

    /**
     * 品牌名称
     * @return CIGARETTENAME 品牌名称
     */
    public Object getCigarettename() {
        return cigarettename;
    }

    /**
     * 品牌名称
     * @param cigarettename 品牌名称
     */
    public void setCigarettename(Object cigarettename) {
        this.cigarettename = cigarettename;
    }

    /**
     * 编码
     * @return CIGARETTECODE 编码
     */
    public String getCigarettecode() {
        return cigarettecode;
    }

    /**
     * 编码
     * @param cigarettecode 编码
     */
    public void setCigarettecode(String cigarettecode) {
        this.cigarettecode = cigarettecode == null ? null : cigarettecode.trim();
    }

    /**
     * 件烟数量(剩余数量)
     * @return BOXQTY 件烟数量(剩余数量)
     */
    public BigDecimal getBoxqty() {
        return boxqty;
    }

    /**
     * 件烟数量(剩余数量)
     * @param boxqty 件烟数量(剩余数量)
     */
    public void setBoxqty(BigDecimal boxqty) {
        this.boxqty = boxqty;
    }

    /**
     * 条烟数量(剩余数量)
     * @return ITEMQTY 条烟数量(剩余数量)
     */
    public BigDecimal getItemqty() {
        return itemqty;
    }

    /**
     * 条烟数量(剩余数量)
     * @param itemqty 条烟数量(剩余数量)
     */
    public void setItemqty(BigDecimal itemqty) {
        this.itemqty = itemqty;
    }

    /**
     * 件烟数量(入数量)
     * @return INBOXQTY 件烟数量(入数量)
     */
    public BigDecimal getInboxqty() {
        return inboxqty;
    }

    /**
     * 件烟数量(入数量)
     * @param inboxqty 件烟数量(入数量)
     */
    public void setInboxqty(BigDecimal inboxqty) {
        this.inboxqty = inboxqty;
    }

    /**
     * 条烟数量(入数量)
     * @return INITEMQTY 条烟数量(入数量)
     */
    public BigDecimal getInitemqty() {
        return initemqty;
    }

    /**
     * 条烟数量(入数量)
     * @param initemqty 条烟数量(入数量)
     */
    public void setInitemqty(BigDecimal initemqty) {
        this.initemqty = initemqty;
    }

    /**
     * 件烟数量(出数量)
     * @return OUTBOXQTY 件烟数量(出数量)
     */
    public BigDecimal getOutboxqty() {
        return outboxqty;
    }

    /**
     * 件烟数量(出数量)
     * @param outboxqty 件烟数量(出数量)
     */
    public void setOutboxqty(BigDecimal outboxqty) {
        this.outboxqty = outboxqty;
    }

    /**
     * 条烟数量(出数量)
     * @return OUTITEMQTY 条烟数量(出数量)
     */
    public BigDecimal getOutitemqty() {
        return outitemqty;
    }

    /**
     * 条烟数量(出数量)
     * @param outitemqty 条烟数量(出数量)
     */
    public void setOutitemqty(BigDecimal outitemqty) {
        this.outitemqty = outitemqty;
    }

    /**
     * 货主
     * @return CONSIGNSOR 货主
     */
    public Object getConsignsor() {
        return consignsor;
    }

    /**
     * 货主
     * @param consignsor 货主
     */
    public void setConsignsor(Object consignsor) {
        this.consignsor = consignsor;
    }
}