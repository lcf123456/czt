package com.ztel.app.vo.sq;

import java.math.BigDecimal;

public class RouteMonthstarVo {
	/**
     * 序号ID
     */
    private BigDecimal id;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 车组星级ID
     */
    private Integer rstarid;

    /**
     * 车组累计得分
     */
    private BigDecimal rtotalscore;

    /**
     * 司机ID
     */
    private Long driverid;
    /**
     * 司机姓名
     */
    
    private String drivername;
    /**
     * 司机星级ID
     */
    private Integer dstarid;
    
    private String dstarname;
    /**
     * 司机累计得分
     */
    private BigDecimal dtotalscore;

    /**
     * 收款员ID
     */
    private Long cashierid;

    

	
    /**
     * 备注
     */
    private String remarks;

    /**
     * 司机得牌信息
     */
    private String dcardinfo;

    /**
     * 司机得牌标志
     */
    private String dcardflag;

    /**
     * 收款员得牌信息
     */
    private String ccardinfo;

    /**
     * 收款员得牌标志
     */
    private String ccardflag;
    
    /**
     * 判断查询的是司机还是收款员  10司机  20 收款员
     */
    private String ctype;
   
    /**
     * 收款员姓名

     */
  
    private String  cashiername;
    
    /**
     * 收款员星级ID
     */
    private Integer cstarid;
    
    
    private String cstarname;
    
    /**
     * 收款员累计得分

     */
    private BigDecimal ctotalscore;

    
    /**
     * 评定时间
     */
    private String assesstime;
    
    /**
     * 报表合计
     */
    private Integer starid;
    private String starname;
    private Integer drivernum;
    private Integer cashiernum;
    private Integer people;
    private Integer peoplesum;
    private String proportion;
    

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Integer getPeoplesum() {
		return peoplesum;
	}

	public void setPeoplesum(Integer peoplesum) {
		this.peoplesum = peoplesum;
	}

	public Integer getStarid() {
		return starid;
	}

	public void setStarid(Integer starid) {
		this.starid = starid;
	}

	public String getStarname() {
		return starname;
	}

	public void setStarname(String starname) {
		this.starname = starname;
	}

	public Integer getDrivernum() {
		return drivernum;
	}

	public void setDrivernum(Integer drivernum) {
		this.drivernum = drivernum;
	}

	public Integer getCashiernum() {
		return cashiernum;
	}

	public void setCashiernum(Integer cashiernum) {
		this.cashiernum = cashiernum;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	/**
     * 序号ID
     * @return ID 序号ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
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
     * 车组星级ID
     * @return RSTARID 车组星级ID
     */
    public Integer getRstarid() {
        return rstarid;
    }

    /**
     * 车组星级ID
     * @param rstarid 车组星级ID
     */
    public void setRstarid(Integer rstarid) {
        this.rstarid = rstarid;
    }

    /**
     * 车组累计得分
     * @return RTOTALSCORE 车组累计得分
     */
    public BigDecimal getRtotalscore() {
        return rtotalscore;
    }

    /**
     * 车组累计得分
     * @param rtotalscore 车组累计得分
     */
    public void setRtotalscore(BigDecimal rtotalscore) {
        this.rtotalscore = rtotalscore;
    }

    /**
     * 司机ID
     * @return DRIVERID 司机ID
     */
    public Long getDriverid() {
        return driverid;
    }

    /**
     * 司机ID
     * @param driverid 司机ID
     */
    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    /**
     * 司机星级ID
     * @return DSTARID 司机星级ID
     */
    public Integer getDstarid() {
        return dstarid;
    }

    /**
     * 司机星级ID
     * @param dstarid 司机星级ID
     */
    public void setDstarid(Integer dstarid) {
        this.dstarid = dstarid;
    }

    /**
     * 司机累计得分
     * @return DTOTALSCORE 司机累计得分
     */
    public BigDecimal getDtotalscore() {
        return dtotalscore;
    }

    /**
     * 司机累计得分
     * @param dtotalscore 司机累计得分
     */
    public void setDtotalscore(BigDecimal dtotalscore) {
        this.dtotalscore = dtotalscore;
    }

    /**
     * 收款员ID
     * @return CASHIERID 收款员ID
     */
    public Long getCashierid() {
        return cashierid;
    }

    /**
     * 收款员ID
     * @param cashierid 收款员ID
     */
    public void setCashierid(Long cashierid) {
        this.cashierid = cashierid;
    }

    /**
     * 收款员星级ID
     * @return CSTARID 收款员星级ID
     */
    public Integer getCstarid() {
        return cstarid;
    }

    /**
     * 收款员星级ID
     * @param cstarid 收款员星级ID
     */
    public void setCstarid(Integer cstarid) {
        this.cstarid = cstarid;
    }

    /**
     * 收款员累计得分
     * @return CTOTALSCORE 收款员累计得分
     */
    public BigDecimal getCtotalscore() {
        return ctotalscore;
    }

    /**
     * 收款员累计得分
     * @param ctotalscore 收款员累计得分
     */
    public void setCtotalscore(BigDecimal ctotalscore) {
        this.ctotalscore = ctotalscore;
    }

    /**
     * 评定时间
     * @return ASSESSTIME 评定时间
     */
    public String getAssesstime() {
        return assesstime;
    }

    /**
     * 评定时间
     * @param assesstime 评定时间
     */
    public void setAssesstime(String assesstime) {
        this.assesstime = assesstime == null ? null : assesstime.trim();
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
     * 司机得牌信息
     * @return DCARDINFO 司机得牌信息
     */
    public String getDcardinfo() {
        return dcardinfo;
    }

    /**
     * 司机得牌信息
     * @param dcardinfo 司机得牌信息
     */
    public void setDcardinfo(String dcardinfo) {
        this.dcardinfo = dcardinfo == null ? null : dcardinfo.trim();
    }

    /**
     * 司机得牌标志
     * @return DCARDFLAG 司机得牌标志
     */
    public String getDcardflag() {
        return dcardflag;
    }

    /**
     * 司机得牌标志
     * @param dcardflag 司机得牌标志
     */
    public void setDcardflag(String dcardflag) {
        this.dcardflag = dcardflag == null ? null : dcardflag.trim();
    }

    /**
     * 收款员得牌信息
     * @return CCARDINFO 收款员得牌信息
     */
    public String getCcardinfo() {
        return ccardinfo;
    }

    /**
     * 收款员得牌信息
     * @param ccardinfo 收款员得牌信息
     */
    public void setCcardinfo(String ccardinfo) {
        this.ccardinfo = ccardinfo == null ? null : ccardinfo.trim();
    }

    /**
     * 收款员得牌标志
     * @return CCARDFLAG 收款员得牌标志
     */
    public String getCcardflag() {
        return ccardflag;
    }

    /**
     * 收款员得牌标志
     * @param ccardflag 收款员得牌标志
     */
    public void setCcardflag(String ccardflag) {
        this.ccardflag = ccardflag == null ? null : ccardflag.trim();
    }

public String getDrivername() {
	return drivername;
}

public void setDrivername(String drivername) {
	this.drivername = drivername;
} 



public String getDstarname() {
	return dstarname;
}

public void setDstarname(String dstarname) {
	this.dstarname = dstarname;
}



public String getCstarname() {
	return cstarname;
}

public void setCstarname(String cstarname) {
	this.cstarname = cstarname;
}


public String getCashiername() {
	return cashiername;
}

public void setCashiername(String cashiername) {
	this.cashiername = cashiername;
}

}