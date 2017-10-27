package com.ztel.app.vo.safe;

public class FireYearVo {
    /**
     * 序号ID
     */
    private Long id;

    /**
     * 检测项
     */
    private Short testitem;

    /**
     * 检测结果(10,正常，20不正常)
     */
    private String ctype;

    /**
     * 检测问题
     */
    private String question;

    /**
     * 检测说明
     */
    private String checknote;

    /**
     * 检测时间
     */
    private String checktime;

    /**
     * 检测人
     */
    private String checkid;

    /**
     * 标识(0，未处理，10已处理)
     */
    private String status;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 检测项
     * @return TESTITEM 检测项
     */
    public Short getTestitem() {
        return testitem;
    }

    /**
     * 检测项
     * @param testitem 检测项
     */
    public void setTestitem(Short testitem) {
        this.testitem = testitem;
    }

    /**
     * 检测结果(10,正常，20不正常)
     * @return CTYPE 检测结果(10,正常，20不正常)
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * 检测结果(10,正常，20不正常)
     * @param ctype 检测结果(10,正常，20不正常)
     */
    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    /**
     * 检测问题
     * @return QUESTION 检测问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 检测问题
     * @param question 检测问题
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * 检测说明
     * @return CHECKNOTE 检测说明
     */
    public String getChecknote() {
        return checknote;
    }

    /**
     * 检测说明
     * @param checknote 检测说明
     */
    public void setChecknote(String checknote) {
        this.checknote = checknote == null ? null : checknote.trim();
    }

    /**
     * 检测时间
     * @return CHECKTIME 检测时间
     */
    public String getChecktime() {
        return checktime;
    }

    /**
     * 检测时间
     * @param checktime 检测时间
     */
    public void setChecktime(String checktime) {
        this.checktime = checktime == null ? null : checktime.trim();
    }

    /**
     * 检测人
     * @return CHECKID 检测人
     */
    public String getCheckid() {
        return checkid;
    }

    /**
     * 检测人
     * @param checkid 检测人
     */
    public void setCheckid(String checkid) {
        this.checkid = checkid == null ? null : checkid.trim();
    }

    /**
     * 标识(0，未处理，10已处理)
     * @return STATUS 标识(0，未处理，10已处理)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 标识(0，未处理，10已处理)
     * @param status 标识(0，未处理，10已处理)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}