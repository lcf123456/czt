package com.ztel.app.vo.perform;

import java.math.BigDecimal;

public class DeptmonthLineVo {
    /**
     * 序号ID
     */
    private Long id;
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
    private BigDecimal scorevalue;
    /**
     * 汇总ID
     */
    private Long fid;

    /**
     * 类型：10:重点工作  20:周月日临时工作
     */
    private BigDecimal ctype;

    /**
     * 考核项ID：对应重点工作等具体内容ID
     */
    private Long evalitemid;

    /**
     * 完成情况
     */
    private String finishnote;

    /**
     * 自评分
     */
    private BigDecimal selfscore;

    /**
     * 他评得分
     */
    private BigDecimal otherscore;

    /**
     * 加扣分说明
     */
    private String note;

    /**
     * 权重
     */
    private BigDecimal weight;
    
    /**
     * 记录临时需要加的分，如果某项没有，则需要加上它的权重比重分，否则没有100分
     */
    private BigDecimal tmpscore;
    
    private String createname;

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
     * 汇总ID
     * @return FID 汇总ID
     */
    public Long getFid() {
        return fid;
    }

    /**
     * 汇总ID
     * @param fid 汇总ID
     */
    public void setFid(Long fid) {
        this.fid = fid;
    }

    /**
     * 类型：10:重点工作  20:周月日临时工作
     * @return CTYPE 类型：10:重点工作  20:周月日临时工作
     */
    public BigDecimal getCtype() {
        return ctype;
    }

    /**
     * 类型：10:重点工作  20:周月日临时工作
     * @param ctype 类型：10:重点工作  20:周月日临时工作
     */
    public void setCtype(BigDecimal ctype) {
        this.ctype = ctype;
    }

    /**
     * 考核项ID：对应重点工作等具体内容ID
     * @return EVALITEMID 考核项ID：对应重点工作等具体内容ID
     */
    public Long getEvalitemid() {
        return evalitemid;
    }

    /**
     * 考核项ID：对应重点工作等具体内容ID
     * @param evalitemid 考核项ID：对应重点工作等具体内容ID
     */
    public void setEvalitemid(Long evalitemid) {
        this.evalitemid = evalitemid;
    }

    /**
     * 完成情况
     * @return FINISHNOTE 完成情况
     */
    public String getFinishnote() {
        return finishnote;
    }

    /**
     * 完成情况
     * @param finishnote 完成情况
     */
    public void setFinishnote(String finishnote) {
        this.finishnote = finishnote == null ? null : finishnote.trim();
    }

    /**
     * 自评分
     * @return SELFSCORE 自评分
     */
    public BigDecimal getSelfscore() {
        return selfscore;
    }

    /**
     * 自评分
     * @param selfscore 自评分
     */
    public void setSelfscore(BigDecimal selfscore) {
        this.selfscore = selfscore;
    }

    /**
     * 他评得分
     * @return OTHERSCORE 他评得分
     */
    public BigDecimal getOtherscore() {
        return otherscore;
    }

    /**
     * 他评得分
     * @param otherscore 他评得分
     */
    public void setOtherscore(BigDecimal otherscore) {
        this.otherscore = otherscore;
    }

    /**
     * 加扣分说明
     * @return NOTE 加扣分说明
     */
    public String getNote() {
        return note;
    }

    /**
     * 加扣分说明
     * @param note 加扣分说明
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 权重
     * @return WEIGHT 权重
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 权重
     * @param weight 权重
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 
     * @return 工作内容或考核项目 
     */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content 工作内容或考核项目 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return 考核标准、考核进度
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * 
	 * @param process 考核标准、考核进度
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * 
	 * @return 类别名称：KPI指标，安全隐患、日常工作、临时性工作
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * 
	 * @param typename 类别名称：KPI指标，安全隐患、日常工作、临时性工作
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * 
	 * @return 分值
	 */
	public BigDecimal getScorevalue() {
		return scorevalue;
	}

	/**
	 * 
	 * @param scorevalue 分值
	 */
	public void setScorevalue(BigDecimal scorevalue) {
		this.scorevalue = scorevalue;
	}

	/**
	 * 
	 * @return 日常工作的Property 1：关键隐患控制 2：日常工作  3：临时性工作   4：日工作 5：周工作  6：月工作
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * 
	 * @param property 日常工作的Property 1：关键隐患控制 2：日常工作  3：临时性工作   4：日工作 5：周工作  6：月工作
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	public BigDecimal getTmpscore() {
		return tmpscore;
	}

	public void setTmpscore(BigDecimal tmpscore) {
		this.tmpscore = tmpscore;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}
    
    
}