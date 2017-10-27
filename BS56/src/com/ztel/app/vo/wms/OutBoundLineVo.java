package com.ztel.app.vo.wms;

import java.math.BigDecimal;

public class OutBoundLineVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 出库编号
     */
    private BigDecimal outboundid;

    /**
     * 品牌名称
     */
    private Object cigarettename;

    /**
     * 编码
     */
    private String cigarettecode;

    /**
     * 件烟数量
     */
    private BigDecimal boxqty;

    /**
     * 条烟数量
     */
    private BigDecimal itemqty;
    
    /**
     * 件条比例
     */
    private BigDecimal jtsize;

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
     * 出库编号
     * @return OUTBOUNDID 出库编号
     */
    public BigDecimal getOutboundid() {
        return outboundid;
    }

    /**
     * 出库编号
     * @param outboundid 出库编号
     */
    public void setOutboundid(BigDecimal outboundid) {
        this.outboundid = outboundid;
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
     * 件烟数量
     * @return BOXQTY 件烟数量
     */
    public BigDecimal getBoxqty() {
        return boxqty;
    }

    /**
     * 件烟数量
     * @param boxqty 件烟数量
     */
    public void setBoxqty(BigDecimal boxqty) {
        this.boxqty = boxqty;
    }

    /**
     * 条烟数量
     * @return ITEMQTY 条烟数量
     */
    public BigDecimal getItemqty() {
        return itemqty;
    }

    /**
     * 条烟数量
     * @param itemqty 条烟数量
     */
    public void setItemqty(BigDecimal itemqty) {
        this.itemqty = itemqty;
    }

    /**
     * 件条比例
     * @return
     */
	public BigDecimal getJtsize() {
		return jtsize;
	}

	/**
	 * 件条比例
	 * @param jtsize 件条比例
	 */
	public void setJtsize(BigDecimal jtsize) {
		this.jtsize = jtsize;
	}
    
    
}