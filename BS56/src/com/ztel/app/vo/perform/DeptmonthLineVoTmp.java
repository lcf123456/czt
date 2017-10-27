package com.ztel.app.vo.perform;


public class DeptmonthLineVoTmp {
    /**
     * 序号ID
     */
    private String id;
    /**
     * 工作内容或考核项目 
     */
    private String content;
    /**
     * 考核标准、考核进度
     */
    private String process;
    /**
     * 类别名称：KPI指标，安全隐患、日常工作、临时性工作
     */
	private String typename;
	
	/**
	 * 日常工作的Property 1：关键隐患控制 2：日常工作  3：临时性工作   4：日工作 5：周工作  6：月工作
	 */
	private String property;
	/**
	 * 分值(在考核新增时用到)
	 */
    private String scorevalue;
    /**
     * 汇总ID
     */
    private String fid;

    /**
     * 类型：10:重点工作  20:周月日临时工作
     */
    private String ctype;

    /**
     * 考核项ID：对应重点工作等具体内容ID
     */
    private String evalitemid;

    /**
     * 完成情况
     */
    private String finishnote;

    /**
     * 自评分
     */
    private String selfscore;

    /**
     * 他评得分
     */
    private String otherscore;

    /**
     * 加扣分说明
     */
    private String note;

    /**
     * 权重
     */
    private String weight;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getScorevalue() {
		return scorevalue;
	}

	public void setScorevalue(String scorevalue) {
		this.scorevalue = scorevalue;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getEvalitemid() {
		return evalitemid;
	}

	public void setEvalitemid(String evalitemid) {
		this.evalitemid = evalitemid;
	}

	public String getFinishnote() {
		return finishnote;
	}

	public void setFinishnote(String finishnote) {
		this.finishnote = finishnote;
	}

	public String getSelfscore() {
		return selfscore;
	}

	public void setSelfscore(String selfscore) {
		this.selfscore = selfscore;
	}

	public String getOtherscore() {
		return otherscore;
	}

	public void setOtherscore(String otherscore) {
		this.otherscore = otherscore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

    
    
}