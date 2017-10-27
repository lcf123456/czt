package com.ztel.app.vo.wms;

import java.math.BigDecimal;

public class ATSCellVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 巷道编号
     */
    private String lanewayno;

    /**
     * 储位编号(0011001001001   巷道 +左右 +列+层 +深度（固定001）)
     */
    private String cellno;

    /**
     * 名称
     */
    private Object cellname;

    /**
     * 距离
     */
    private BigDecimal distance;

    /**
     * 状态（正常 10，禁入 20，禁出30，弃用 40）
     */
    private BigDecimal status;

    /**
     * 工作状态（空闲 10、卷烟载货  20、 预定、30   ）
     */
    private BigDecimal workstatus;

    /**
     * 列
     */
    private BigDecimal col;

    /**
     * 层
     */
    private BigDecimal floor;

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
     * 巷道编号
     * @return LANEWAYNO 巷道编号
     */
    public String getLanewayno() {
        return lanewayno;
    }

    /**
     * 巷道编号
     * @param lanewayno 巷道编号
     */
    public void setLanewayno(String lanewayno) {
        this.lanewayno = lanewayno == null ? null : lanewayno.trim();
    }

    /**
     * 储位编号(0011001001001   巷道 +左右 +列+层 +深度（固定001）)
     * @return CELLNO 储位编号(0011001001001   巷道 +左右 +列+层 +深度（固定001）)
     */
    public String getCellno() {
        return cellno;
    }

    /**
     * 储位编号(0011001001001   巷道 +左右 +列+层 +深度（固定001）)
     * @param cellno 储位编号(0011001001001   巷道 +左右 +列+层 +深度（固定001）)
     */
    public void setCellno(String cellno) {
        this.cellno = cellno == null ? null : cellno.trim();
    }

    /**
     * 名称
     * @return CELLNAME 名称
     */
    public Object getCellname() {
        return cellname;
    }

    /**
     * 名称
     * @param cellname 名称
     */
    public void setCellname(Object cellname) {
        this.cellname = cellname;
    }

    /**
     * 距离
     * @return DISTANCE 距离
     */
    public BigDecimal getDistance() {
        return distance;
    }

    /**
     * 距离
     * @param distance 距离
     */
    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    /**
     * 状态（正常 10，禁入 20，禁出30，弃用 40）
     * @return STATUS 状态（正常 10，禁入 20，禁出30，弃用 40）
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * 状态（正常 10，禁入 20，禁出30，弃用 40）
     * @param status 状态（正常 10，禁入 20，禁出30，弃用 40）
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    /**
     * 工作状态（空闲 10、卷烟载货  20、 预定、30   ）
     * @return WORKSTATUS 工作状态（空闲 10、卷烟载货  20、 预定、30   ）
     */
    public BigDecimal getWorkstatus() {
        return workstatus;
    }

    /**
     * 工作状态（空闲 10、卷烟载货  20、 预定、30   ）
     * @param workstatus 工作状态（空闲 10、卷烟载货  20、 预定、30   ）
     */
    public void setWorkstatus(BigDecimal workstatus) {
        this.workstatus = workstatus;
    }

    /**
     * 列
     * @return COL 列
     */
    public BigDecimal getCol() {
        return col;
    }

    /**
     * 列
     * @param col 列
     */
    public void setCol(BigDecimal col) {
        this.col = col;
    }

    /**
     * 层
     * @return FLOOR 层
     */
    public BigDecimal getFloor() {
        return floor;
    }

    /**
     * 层
     * @param floor 层
     */
    public void setFloor(BigDecimal floor) {
        this.floor = floor;
    }
}