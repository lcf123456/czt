package com.ztel.app.vo.gis;

import java.math.BigDecimal;
import java.util.Date;

public class TruckinfoVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 批次ＩＤ
     */
    private String batchId;

    /**
     * 车载设备号
     */
    private String lockNo;

    /**
     * 承运车船号
     */
    private String truckNum;

    /**
     * 发货单位代码
     */
    private String orgOrgCode;

    /**
     * 发货单位名称
     */
    private String orgOrgName;

    /**
     *  发货日期  YYYYMMDDHHMISS
     */
    private String outDate;

    /**
     * 运输单位代码
     */
    private String tranOrgCode;

    /**
     * 运输单位名称
     */
    private String tranOrgName;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 运输方式  01：汽车；02：火车；03：飞机；04:轮船；05：其他
     */
    private String transMode;

    /**
     * 是否解除绑定  00：结束；01：在途；11：启运前
     */
    private String isUnbind;

    /**
     * 司机手机号
     */
    private String mobileTel;

    /**
     * null
     */
    private Date updatetime;

    /**
     * 状态  10:最新数据  20:历史数据
     */
    private String rowstatus;

    /**
     * 插入时间
     */
    private Date itime;

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
     * 批次ＩＤ
     * @return BATCH_ID 批次ＩＤ
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 批次ＩＤ
     * @param batchId 批次ＩＤ
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * 车载设备号
     * @return LOCK_NO 车载设备号
     */
    public String getLockNo() {
        return lockNo;
    }

    /**
     * 车载设备号
     * @param lockNo 车载设备号
     */
    public void setLockNo(String lockNo) {
        this.lockNo = lockNo == null ? null : lockNo.trim();
    }

    /**
     * 承运车船号
     * @return TRUCK_NUM 承运车船号
     */
    public String getTruckNum() {
        return truckNum;
    }

    /**
     * 承运车船号
     * @param truckNum 承运车船号
     */
    public void setTruckNum(String truckNum) {
        this.truckNum = truckNum == null ? null : truckNum.trim();
    }

    /**
     * 发货单位代码
     * @return ORG_ORG_CODE 发货单位代码
     */
    public String getOrgOrgCode() {
        return orgOrgCode;
    }

    /**
     * 发货单位代码
     * @param orgOrgCode 发货单位代码
     */
    public void setOrgOrgCode(String orgOrgCode) {
        this.orgOrgCode = orgOrgCode == null ? null : orgOrgCode.trim();
    }

    /**
     * 发货单位名称
     * @return ORG_ORG_NAME 发货单位名称
     */
    public String getOrgOrgName() {
        return orgOrgName;
    }

    /**
     * 发货单位名称
     * @param orgOrgName 发货单位名称
     */
    public void setOrgOrgName(String orgOrgName) {
        this.orgOrgName = orgOrgName == null ? null : orgOrgName.trim();
    }

    /**
     *  发货日期  YYYYMMDDHHMISS
     * @return OUT_DATE  发货日期  YYYYMMDDHHMISS
     */
    public String getOutDate() {
        return outDate;
    }

    /**
     *  发货日期  YYYYMMDDHHMISS
     * @param outDate  发货日期  YYYYMMDDHHMISS
     */
    public void setOutDate(String outDate) {
        this.outDate = outDate == null ? null : outDate.trim();
    }

    /**
     * 运输单位代码
     * @return TRAN_ORG_CODE 运输单位代码
     */
    public String getTranOrgCode() {
        return tranOrgCode;
    }

    /**
     * 运输单位代码
     * @param tranOrgCode 运输单位代码
     */
    public void setTranOrgCode(String tranOrgCode) {
        this.tranOrgCode = tranOrgCode == null ? null : tranOrgCode.trim();
    }

    /**
     * 运输单位名称
     * @return TRAN_ORG_NAME 运输单位名称
     */
    public String getTranOrgName() {
        return tranOrgName;
    }

    /**
     * 运输单位名称
     * @param tranOrgName 运输单位名称
     */
    public void setTranOrgName(String tranOrgName) {
        this.tranOrgName = tranOrgName == null ? null : tranOrgName.trim();
    }

    /**
     * 司机姓名
     * @return DRIVER_NAME 司机姓名
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * 司机姓名
     * @param driverName 司机姓名
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    /**
     * 运输方式  01：汽车；02：火车；03：飞机；04:轮船；05：其他
     * @return TRANS_MODE 运输方式  01：汽车；02：火车；03：飞机；04:轮船；05：其他
     */
    public String getTransMode() {
        return transMode;
    }

    /**
     * 运输方式  01：汽车；02：火车；03：飞机；04:轮船；05：其他
     * @param transMode 运输方式  01：汽车；02：火车；03：飞机；04:轮船；05：其他
     */
    public void setTransMode(String transMode) {
        this.transMode = transMode == null ? null : transMode.trim();
    }

    /**
     * 是否解除绑定  00：结束；01：在途；11：启运前
     * @return IS_UNBIND 是否解除绑定  00：结束；01：在途；11：启运前
     */
    public String getIsUnbind() {
        return isUnbind;
    }

    /**
     * 是否解除绑定  00：结束；01：在途；11：启运前
     * @param isUnbind 是否解除绑定  00：结束；01：在途；11：启运前
     */
    public void setIsUnbind(String isUnbind) {
        this.isUnbind = isUnbind == null ? null : isUnbind.trim();
    }

    /**
     * 司机手机号
     * @return MOBILE_TEL 司机手机号
     */
    public String getMobileTel() {
        return mobileTel;
    }

    /**
     * 司机手机号
     * @param mobileTel 司机手机号
     */
    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel == null ? null : mobileTel.trim();
    }

    /**
     * null
     * @return UPDATETIME null
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * null
     * @param updatetime null
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 状态  10:最新数据  20:历史数据
     * @return ROWSTATUS 状态  10:最新数据  20:历史数据
     */
    public String getRowstatus() {
        return rowstatus;
    }

    /**
     * 状态  10:最新数据  20:历史数据
     * @param rowstatus 状态  10:最新数据  20:历史数据
     */
    public void setRowstatus(String rowstatus) {
        this.rowstatus = rowstatus == null ? null : rowstatus.trim();
    }

    /**
     * 插入时间
     * @return ITIME 插入时间
     */
    public Date getItime() {
        return itime;
    }

    /**
     * 插入时间
     * @param itime 插入时间
     */
    public void setItime(Date itime) {
        this.itime = itime;
    }
}