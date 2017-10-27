package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

public class MoveareastockVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 原区域id
     */
    private BigDecimal sourceareaid;

    /**
     * 原区域名称
     */
    private String sourceareaname;

    /**
     * 目标区域id
     */
    private BigDecimal targetareaid;

    /**
     * 目标区域名称
     */
    private String targetareaname;

    /**
     * 移库数量(件)
     */
    private BigDecimal boxqty;

    /**
     * 移库数量(条)
     */
    private BigDecimal barqty;

    /**
     * 创建用户
     */
    private Long createuser;

    /**
     * 创建用户姓名
     */
    private String createusername;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 审核标志 ：10：新增 20：出库 30：收获确认
     */
    private BigDecimal auditflag;

    /**
     * 删除标志 0：删除 10：启用
     */
    private BigDecimal delstatus;

    /**
     * 出库人员id
     */
    private Long outcheckuser;

    /**
     * 出库人员
     */
    private String outcheckusername;

    /**
     * 出库日期
     */
    private Date outchecktime;

    /**
     * 收货人员id
     */
    private Long receivecheckuser;

    /**
     * 收货人员
     */
    private String receivecheckusername;

    /**
     * 收货日期
     */
    private Date receivechecktime;
    
    /**
     * 搜索结束时间
     */
    private Date endtime;
    
    /**
     * 搜索关键字
     */
    private String keyword;
    
    /**
     * 查询开始时间
     */
    private Date begintime;
    
    /**
     * 移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
     */
    private String movetype;
    
    /**
     * 审核标志 名称：10：新增 20：出库 30：收获确认
     */
    private String auditflagname;
    
    private String movetypename;
    private String createtimestr;
    private String outchecktimestr;
    private String receivechecktimestr;

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
     * 原区域id
     * @return SOURCEAREAID 原区域id
     */
    public BigDecimal getSourceareaid() {
        return sourceareaid;
    }

    /**
     * 原区域id
     * @param sourceareaid 原区域id
     */
    public void setSourceareaid(BigDecimal sourceareaid) {
        this.sourceareaid = sourceareaid;
    }

    /**
     * 原区域名称
     * @return SOURCEAREANAME 原区域名称
     */
    public String getSourceareaname() {
        return sourceareaname;
    }

    /**
     * 原区域名称
     * @param sourceareaname 原区域名称
     */
    public void setSourceareaname(String sourceareaname) {
        this.sourceareaname = sourceareaname == null ? null : sourceareaname.trim();
    }

    /**
     * 目标区域id
     * @return TARGETAREAID 目标区域id
     */
    public BigDecimal getTargetareaid() {
        return targetareaid;
    }

    /**
     * 目标区域id
     * @param targetareaid 目标区域id
     */
    public void setTargetareaid(BigDecimal targetareaid) {
        this.targetareaid = targetareaid;
    }

    /**
     * 目标区域名称
     * @return TARGETAREANAME 目标区域名称
     */
    public String getTargetareaname() {
        return targetareaname;
    }

    /**
     * 目标区域名称
     * @param targetareaname 目标区域名称
     */
    public void setTargetareaname(String targetareaname) {
        this.targetareaname = targetareaname == null ? null : targetareaname.trim();
    }

    /**
     * 移库数量(件)
     * @return BOXQTY 移库数量(件)
     */
    public BigDecimal getBoxqty() {
        return boxqty;
    }

    /**
     * 移库数量(件)
     * @param boxqty 移库数量(件)
     */
    public void setBoxqty(BigDecimal boxqty) {
        this.boxqty = boxqty;
    }

    /**
     * 移库数量(条)
     * @return BARQTY 移库数量(条)
     */
    public BigDecimal getBarqty() {
        return barqty;
    }

    /**
     * 移库数量(条)
     * @param barqty 移库数量(条)
     */
    public void setBarqty(BigDecimal barqty) {
        this.barqty = barqty;
    }

    /**
     * 创建用户
     * @return CREATEUSER 创建用户
     */
    public Long getCreateuser() {
        return createuser;
    }

    /**
     * 创建用户
     * @param createuser 创建用户
     */
    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    /**
     * 创建用户姓名
     * @return CREATEUSERNAME 创建用户姓名
     */
    public String getCreateusername() {
        return createusername;
    }

    /**
     * 创建用户姓名
     * @param createusername 创建用户姓名
     */
    public void setCreateusername(String createusername) {
        this.createusername = createusername == null ? null : createusername.trim();
    }

    /**
     * 创建时间
     * @return CREATETIME 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 审核标志 ：10：新增 20：出库 30：收获确认
     * @return AUDITFLAG 审核标志 ：10：新增 20：出库 30：收获确认
     */
    public BigDecimal getAuditflag() {
        return auditflag;
    }

    /**
     * 审核标志 ：10：新增 20：出库 30：收获确认
     * @param auditflag 审核标志 ：10：新增 20：出库 30：收获确认
     */
    public void setAuditflag(BigDecimal auditflag) {
        this.auditflag = auditflag;
    }

    /**
     * 删除标志 0：删除 10：启用
     * @return DELSTATUS 删除标志 0：删除 10：启用
     */
    public BigDecimal getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标志 0：删除 10：启用
     * @param delstatus 删除标志 0：删除 10：启用
     */
    public void setDelstatus(BigDecimal delstatus) {
        this.delstatus = delstatus;
    }

    /**
     * 出库人员id
     * @return OUTCHECKUSER 出库人员id
     */
    public Long getOutcheckuser() {
        return outcheckuser;
    }

    /**
     * 出库人员id
     * @param outcheckuser 出库人员id
     */
    public void setOutcheckuser(Long outcheckuser) {
        this.outcheckuser = outcheckuser;
    }

    /**
     * 出库人员
     * @return OUTCHECKUSERNAME 出库人员
     */
    public String getOutcheckusername() {
        return outcheckusername;
    }

    /**
     * 出库人员
     * @param outcheckusername 出库人员
     */
    public void setOutcheckusername(String outcheckusername) {
        this.outcheckusername = outcheckusername == null ? null : outcheckusername.trim();
    }

    /**
     * 出库日期
     * @return OUTCHECKTIME 出库日期
     */
    public Date getOutchecktime() {
        return outchecktime;
    }

    /**
     * 出库日期
     * @param outchecktime 出库日期
     */
    public void setOutchecktime(Date outchecktime) {
        this.outchecktime = outchecktime;
    }

    /**
     * 收货人员id
     * @return RECEIVECHECKUSER 收货人员id
     */
    public Long getReceivecheckuser() {
        return receivecheckuser;
    }

    /**
     * 收货人员id
     * @param receivecheckuser 收货人员id
     */
    public void setReceivecheckuser(Long receivecheckuser) {
        this.receivecheckuser = receivecheckuser;
    }

    /**
     * 收货人员
     * @return RECEIVECHECKUSERNAME 收货人员
     */
    public String getReceivecheckusername() {
        return receivecheckusername;
    }

    /**
     * 收货人员
     * @param receivecheckusername 收货人员
     */
    public void setReceivecheckusername(String receivecheckusername) {
        this.receivecheckusername = receivecheckusername == null ? null : receivecheckusername.trim();
    }

    /**
     * 收获日期
     * @return RECEIVECHECKTIME 收获日期
     */
    public Date getReceivechecktime() {
        return receivechecktime;
    }

    /**
     * 收获日期
     * @param receivechecktime 收获日期
     */
    public void setReceivechecktime(Date receivechecktime) {
        this.receivechecktime = receivechecktime;
    }

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
	 * @return 移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
	 */
	public String getMovetype() {
		return movetype;
	}

	/**
	 * 移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
	 * @param movetype 移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
	 */
	public void setMovetype(String movetype) {
		this.movetype = movetype;
	}

	/**
	 * 审核标志 名称：10：新增 20：出库 30：收获确认
	 * @return 审核标志 名称：10：新增 20：出库 30：收获确认
	 */
	public String getAuditflagname() {
		return auditflagname;
	}

	/**
	 * 审核标志 名称：10：新增 20：出库 30：收获确认
	 * @param auditflagname
	 */
	public void setAuditflagname(String auditflagname) {
		this.auditflagname = auditflagname;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public String getMovetypename() {
		return movetypename;
	}

	public void setMovetypename(String movetypename) {
		this.movetypename = movetypename;
	}

	public String getCreatetimestr() {
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public String getOutchecktimestr() {
		return outchecktimestr;
	}

	public void setOutchecktimestr(String outchecktimestr) {
		this.outchecktimestr = outchecktimestr;
	}

	public String getReceivechecktimestr() {
		return receivechecktimestr;
	}

	public void setReceivechecktimestr(String receivechecktimestr) {
		this.receivechecktimestr = receivechecktimestr;
	}
    
    
}