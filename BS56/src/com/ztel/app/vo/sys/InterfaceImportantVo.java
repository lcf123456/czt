package com.ztel.app.vo.sys;

import java.util.Date;

public class InterfaceImportantVo {
    /**
     * id序号
     */
    private Integer id;

    /**
     * 部门id
     */
    private Integer deptid;

    /**
     * 报告事项
     */
    private String matters;

    /**
     * 处理办法
     */
    private String handlemeans;

    /**
     * 处理建议
     */
    private String suggestion;

    /**
     * 遗留问题
     */
    private String question;

    /**
     * 报告时间
     */
    private Date reportdate;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 记录日期
     */
    private Date createdate;

    /**
     * 领导是否可见（0：不可见  1：可见）
     */
    private String flag;

    /**
     * id序号
     * @return ID id序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * id序号
     * @param id id序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 部门id
     * @return DEPTID 部门id
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 部门id
     * @param deptid 部门id
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 报告事项
     * @return MATTERS 报告事项
     */
    public String getMatters() {
        return matters;
    }

    /**
     * 报告事项
     * @param matters 报告事项
     */
    public void setMatters(String matters) {
        this.matters = matters == null ? null : matters.trim();
    }

    /**
     * 处理办法
     * @return HANDLEMEANS 处理办法
     */
    public String getHandlemeans() {
        return handlemeans;
    }

    /**
     * 处理办法
     * @param handlemeans 处理办法
     */
    public void setHandlemeans(String handlemeans) {
        this.handlemeans = handlemeans == null ? null : handlemeans.trim();
    }

    /**
     * 处理建议
     * @return SUGGESTION 处理建议
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * 处理建议
     * @param suggestion 处理建议
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    /**
     * 遗留问题
     * @return QUESTION 遗留问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 遗留问题
     * @param question 遗留问题
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * 报告时间
     * @return REPORTDATE 报告时间
     */
    public Date getReportdate() {
        return reportdate;
    }

    /**
     * 报告时间
     * @param reportdate 报告时间
     */
    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
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
     * 领导是否可见（0：不可见  1：可见）
     * @return FLAG 领导是否可见（0：不可见  1：可见）
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 领导是否可见（0：不可见  1：可见）
     * @param flag 领导是否可见（0：不可见  1：可见）
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}