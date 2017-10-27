package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

public class ATSCellInfoVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * null
     */
    private String cellno;

    /**
     * 10 机械拆垛  0 人工拆垛
     */
    private BigDecimal dismantle;

    /**
     * 托盘号 
     */
    private String palletno;

    /**
     * 10组盘  20 在途  30 上架
     */
    private BigDecimal status;

    /**
     * 入库单
     */
    private BigDecimal inboundid;

    /**
     * 生成时间
     */
    private Date createtime;

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
     * @return CELLNO null
     */
    public String getCellno() {
        return cellno;
    }

    /**
     * null
     * @param cellno null
     */
    public void setCellno(String cellno) {
        this.cellno = cellno == null ? null : cellno.trim();
    }

    /**
     * 10 机械拆垛  0 人工拆垛
     * @return DISMANTLE 10 机械拆垛  0 人工拆垛
     */
    public BigDecimal getDismantle() {
        return dismantle;
    }

    /**
     * 10 机械拆垛  0 人工拆垛
     * @param dismantle 10 机械拆垛  0 人工拆垛
     */
    public void setDismantle(BigDecimal dismantle) {
        this.dismantle = dismantle;
    }

    /**
     * 托盘号 
     * @return PALLETNO 托盘号 
     */
    public String getPalletno() {
        return palletno;
    }

    /**
     * 托盘号 
     * @param palletno 托盘号 
     */
    public void setPalletno(String palletno) {
        this.palletno = palletno == null ? null : palletno.trim();
    }

    /**
     * 10组盘  20 在途  30 上架
     * @return STATUS 10组盘  20 在途  30 上架
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * 10组盘  20 在途  30 上架
     * @param status 10组盘  20 在途  30 上架
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    /**
     * 入库单
     * @return INBOUNDID 入库单
     */
    public BigDecimal getInboundid() {
        return inboundid;
    }

    /**
     * 入库单
     * @param inboundid 入库单
     */
    public void setInboundid(BigDecimal inboundid) {
        this.inboundid = inboundid;
    }

    /**
     * 生成时间
     * @return CREATETIME 生成时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 生成时间
     * @param createtime 生成时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}