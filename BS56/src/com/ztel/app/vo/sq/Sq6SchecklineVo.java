package com.ztel.app.vo.sq;

import java.util.Date;

public class Sq6SchecklineVo {
    /**
     * 主键 
     */
    private Integer id;

    /**
     * 对应T_SQ_6SCHECKCONTENT 的id 
     */
    private Integer fid;

    /**
     * 车组CODE 
     */
    private String routecode;

    /**
     * 司机id 
     */
    private Long driverid;

    /**
     * 考核日期 
     */
    private Date assessdate;

    /**
     * 检查情况 
     */
    private String checknote;

    /**
     * 得分(总分：10) 
     */
    private Short score;

    /**
     * 车辆id 
     */
    private Integer vehicleid;

    /**
     * 收款员id 
     */
    private Integer cashierid;

    /**
     * 备注 
     */
    private String remarks;

    /**
     * 主键 
     * @return ID 主键 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键 
     * @param id 主键 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 对应T_SQ_6SCHECKCONTENT 的id 
     * @return FID 对应T_SQ_6SCHECKCONTENT 的id 
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 对应T_SQ_6SCHECKCONTENT 的id 
     * @param fid 对应T_SQ_6SCHECKCONTENT 的id 
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 车组CODE 
     * @return ROUTECODE 车组CODE 
     */
    public String getRoutecode() {
        return routecode;
    }

    /**
     * 车组CODE 
     * @param routecode 车组CODE 
     */
    public void setRoutecode(String routecode) {
        this.routecode = routecode == null ? null : routecode.trim();
    }

    /**
     * 司机id 
     * @return DRIVERID 司机id 
     */
    public Long getDriverid() {
        return driverid;
    }

    /**
     * 司机id 
     * @param driverid 司机id 
     */
    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    /**
     * 考核日期 
     * @return ASSESSDATE 考核日期 
     */
    public Date getAssessdate() {
        return assessdate;
    }

    /**
     * 考核日期 
     * @param assessdate 考核日期 
     */
    public void setAssessdate(Date assessdate) {
        this.assessdate = assessdate;
    }

    /**
     * 检查情况 
     * @return CHECKNOTE 检查情况 
     */
    public String getChecknote() {
        return checknote;
    }

    /**
     * 检查情况 
     * @param checknote 检查情况 
     */
    public void setChecknote(String checknote) {
        this.checknote = checknote == null ? null : checknote.trim();
    }

    /**
     * 得分(总分：10) 
     * @return SCORE 得分(总分：10) 
     */
    public Short getScore() {
        return score;
    }

    /**
     * 得分(总分：10) 
     * @param score 得分(总分：10) 
     */
    public void setScore(Short score) {
        this.score = score;
    }

    /**
     * 车辆id 
     * @return VEHICLEID 车辆id 
     */
    public Integer getVehicleid() {
        return vehicleid;
    }

    /**
     * 车辆id 
     * @param vehicleid 车辆id 
     */
    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    /**
     * 收款员id 
     * @return CASHIERID 收款员id 
     */
    public Integer getCashierid() {
        return cashierid;
    }

    /**
     * 收款员id 
     * @param cashierid 收款员id 
     */
    public void setCashierid(Integer cashierid) {
        this.cashierid = cashierid;
    }

    /**
     * 备注 
     * @return REMARKS 备注 
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注 
     * @param remarks 备注 
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}