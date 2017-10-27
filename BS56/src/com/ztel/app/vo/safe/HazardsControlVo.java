package com.ztel.app.vo.safe;

public class HazardsControlVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * 风险等级ID
     */
    private Integer levelid;

    /**
     * 控制措施
     */
    private String controlmeans;

    /**
     * 应急控制说明
     */
    private String note;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标识 10:正常  0:删除
     */
    private String delstatus;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 风险等级ID
     * @return LEVELID 风险等级ID
     */
    public Integer getLevelid() {
        return levelid;
    }

    /**
     * 风险等级ID
     * @param levelid 风险等级ID
     */
    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    /**
     * 控制措施
     * @return CONTROLMEANS 控制措施
     */
    public String getControlmeans() {
        return controlmeans;
    }

    /**
     * 控制措施
     * @param controlmeans 控制措施
     */
    public void setControlmeans(String controlmeans) {
        this.controlmeans = controlmeans == null ? null : controlmeans.trim();
    }

    /**
     * 应急控制说明
     * @return NOTE 应急控制说明
     */
    public String getNote() {
        return note;
    }

    /**
     * 应急控制说明
     * @param note 应急控制说明
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 备注信息
     * @return REMARKS 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注信息
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 删除标识 10:正常  0:删除
     * @return DELSTATUS 删除标识 10:正常  0:删除
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标识 10:正常  0:删除
     * @param delstatus 删除标识 10:正常  0:删除
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
}