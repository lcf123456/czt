package com.ztel.app.vo.sq;

import java.util.Date;

public class IndustrialscoreVo {
    /**
     * null
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 紧急度（1-5级）
     */
    private String clevel;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 记录日期
     */
    private Date createdate;

    /**
     * 处理部门
     */
    private Integer handledept;

    /**
     * 处理结果
     */
    private String handleresult;

    /**
     * 处理人员
     */
    private Long handleid;

    /**
     * 删除标志
     */
    private String delstatus;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 处理标志   0:未处理   10已处理
     */
    private String handleflag;

    /**
     * null
     * @return ID null
     */
    public Long getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 内容
     * @return CONTENT 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 紧急度（1-5级）
     * @return CLEVEL 紧急度（1-5级）
     */
    public String getClevel() {
        return clevel;
    }

    /**
     * 紧急度（1-5级）
     * @param clevel 紧急度（1-5级）
     */
    public void setClevel(String clevel) {
        this.clevel = clevel == null ? null : clevel.trim();
    }

    /**
     * 记录人
     * @return CREATEID 记录人
     */
    public Long getCreateid() {
        return createid;
    }

    /**
     * 记录人
     * @param createid 记录人
     */
    public void setCreateid(Long createid) {
        this.createid = createid;
    }

    /**
     * 记录日期
     * @return CREATEDATE 记录日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 记录日期
     * @param createdate 记录日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 处理部门
     * @return HANDLEDEPT 处理部门
     */
    public Integer getHandledept() {
        return handledept;
    }

    /**
     * 处理部门
     * @param handledept 处理部门
     */
    public void setHandledept(Integer handledept) {
        this.handledept = handledept;
    }

    /**
     * 处理结果
     * @return HANDLERESULT 处理结果
     */
    public String getHandleresult() {
        return handleresult;
    }

    /**
     * 处理结果
     * @param handleresult 处理结果
     */
    public void setHandleresult(String handleresult) {
        this.handleresult = handleresult == null ? null : handleresult.trim();
    }

    /**
     * 处理人员
     * @return HANDLEID 处理人员
     */
    public Long getHandleid() {
        return handleid;
    }

    /**
     * 处理人员
     * @param handleid 处理人员
     */
    public void setHandleid(Long handleid) {
        this.handleid = handleid;
    }

    /**
     * 删除标志
     * @return DELSTATUS 删除标志
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标志
     * @param delstatus 删除标志
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
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

    /**
     * 处理标志   0:未处理   10已处理
     * @return HANDLEFLAG 处理标志   0:未处理   10已处理
     */
    public String getHandleflag() {
        return handleflag;
    }

    /**
     * 处理标志   0:未处理   10已处理
     * @param handleflag 处理标志   0:未处理   10已处理
     */
    public void setHandleflag(String handleflag) {
        this.handleflag = handleflag == null ? null : handleflag.trim();
    }
}