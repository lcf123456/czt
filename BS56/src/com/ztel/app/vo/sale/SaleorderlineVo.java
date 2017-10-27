package com.ztel.app.vo.sale;

import java.math.BigDecimal;

public class SaleorderlineVo {
    /**
     * null
     */
    private String id;

    /**
     * 主表订单号
     */
    private String orderno;

    /**
     * 品牌id
     */
    private String itemId;

    /**
     * 单位id
     */
    private BigDecimal uomId;

    /**
     * 条数
     */
    private BigDecimal qty;

    /**
     * 价格
     */
    private BigDecimal saleprice;

    /**
     * 备注
     */
    private Object remark;

    /**
     * 状态  10正常  0 退货
     */
    private BigDecimal orderstatus;

    /**
     * 总金额
     */
    private BigDecimal saleamount;

    /**
     * null
     * @return ID null
     */
    public String getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 主表订单号
     * @return ORDERNO 主表订单号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 主表订单号
     * @param orderno 主表订单号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * 品牌id
     * @return ITEM_ID 品牌id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 品牌id
     * @param itemId 品牌id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * 单位id
     * @return UOM_ID 单位id
     */
    public BigDecimal getUomId() {
        return uomId;
    }

    /**
     * 单位id
     * @param uomId 单位id
     */
    public void setUomId(BigDecimal uomId) {
        this.uomId = uomId;
    }

    /**
     * 条数
     * @return QTY 条数
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 条数
     * @param qty 条数
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 价格
     * @return SALEPRICE 价格
     */
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    /**
     * 价格
     * @param saleprice 价格
     */
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public Object getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(Object remark) {
        this.remark = remark;
    }

    /**
     * 状态  10正常  0 退货
     * @return ORDERSTATUS 状态  10正常  0 退货
     */
    public BigDecimal getOrderstatus() {
        return orderstatus;
    }

    /**
     * 状态  10正常  0 退货
     * @param orderstatus 状态  10正常  0 退货
     */
    public void setOrderstatus(BigDecimal orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * 总金额
     * @return SALEAMOUNT 总金额
     */
    public BigDecimal getSaleamount() {
        return saleamount;
    }

    /**
     * 总金额
     * @param saleamount 总金额
     */
    public void setSaleamount(BigDecimal saleamount) {
        this.saleamount = saleamount;
    }
}