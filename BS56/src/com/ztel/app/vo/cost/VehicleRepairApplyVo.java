package com.ztel.app.vo.cost;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VehicleRepairApplyVo {
    /**
     * null
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 车辆id
     */
    private Integer vehicleid;

    /**
     * 修理厂id
     */
    private Integer unitid;

    /**
     * 申报日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applydate;

    /**
     * 保养项目
     */
    private String mainitem;

    /**
     * 车辆安全员意见
     */
    private String safesuggestion;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 是否回单  10：未回单 20：已回单
     */
    private String isreply;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 打印标志
     */
    private String printflag;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 车管员
     */
    private Long vehiclectrid;

    /**
     * 车管员保养项目
     */
    private String vehiclemainitem;

    /**
     * 分部主任id
     */
    private Long leaderid;

    /**
     * 分部主任意见
     */
    private String leadersuggestion;

    /**
     * 审核标志 （10：未审核  20：车管员审核通过  30：车管员审核驳回  40：分部主任通过  50：分部主任驳回）
     */
    private String status;

    /**
     * 驾驶员id
     */
    private Long driverid;

    /**
     * 维修类型（10：维修 20：保养)
     */
    private String repairtype;

    /**
     * 是否重复维修 (0:不是   1:是)
     */
    private BigDecimal isrepeat;

    /**
     * 预计费用
     */
    private BigDecimal estimatecost;
    
    private String vehicleno;
    
    private String createname;
    
    private String unitname;
    
    private String statusname;
    
    private BigDecimal totalcost;
    
    private String repaircontent;
    
    private BigDecimal repairinfoid;

    private String repairinfo;
    
    private String begdate;
    
    private String enddate;
    
    private String param;
    
    private String vehicletype;
    
    private String drivername;
    
    private String leadername;
    
    private String vehiclectrname;
    
    private String buydate;
    
    private String enginenum;
    
    private String rdate;
    
    private BigDecimal mileage;
    
	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public String getEnginenum() {
		return enginenum;
	}

	public void setEnginenum(String enginenum) {
		this.enginenum = enginenum;
	}


	public String getLeadername() {
		return leadername;
	}

	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}

	public String getVehiclectrname() {
		return vehiclectrname;
	}

	public void setVehiclectrname(String vehiclectrname) {
		this.vehiclectrname = vehiclectrname;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getBegdate() {
		return begdate;
	}

	public void setBegdate(String begdate) {
		this.begdate = begdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public BigDecimal getRepairinfoid() {
		return repairinfoid;
	}

	public void setRepairinfoid(BigDecimal repairinfoid) {
		this.repairinfoid = repairinfoid;
	}

	public String getRepairinfo() {
		return repairinfo;
	}

	public void setRepairinfo(String repairinfo) {
		this.repairinfo = repairinfo;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public BigDecimal getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(BigDecimal totalcost) {
		this.totalcost = totalcost;
	}

	public String getRepaircontent() {
		return repaircontent;
	}

	public void setRepaircontent(String repaircontent) {
		this.repaircontent = repaircontent;
	}

	/**
     * null
     * @return ID null
     */
    public String getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 编码
     * @return CODE 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 修理厂id
     * @return UNITID 修理厂id
     */
    public Integer getUnitid() {
        return unitid;
    }

    /**
     * 修理厂id
     * @param unitid 修理厂id
     */
    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    /**
     * 申报日期
     * @return APPLYDATE 申报日期
     */
    public Date getApplydate() {
        return applydate;
    }

    /**
     * 申报日期
     * @param applydate 申报日期
     */
    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    /**
     * 保养项目
     * @return MAINITEM 保养项目
     */
    public String getMainitem() {
        return mainitem;
    }

    /**
     * 保养项目
     * @param mainitem 保养项目
     */
    public void setMainitem(String mainitem) {
        this.mainitem = mainitem == null ? null : mainitem.trim();
    }

    /**
     * 车辆安全员意见
     * @return SAFESUGGESTION 车辆安全员意见
     */
    public String getSafesuggestion() {
        return safesuggestion;
    }

    /**
     * 车辆安全员意见
     * @param safesuggestion 车辆安全员意见
     */
    public void setSafesuggestion(String safesuggestion) {
        this.safesuggestion = safesuggestion == null ? null : safesuggestion.trim();
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
     * 是否回单  10：未回单 20：已回单
     * @return ISREPLY 是否回单  10：未回单 20：已回单
     */
    public String getIsreply() {
        return isreply;
    }

    /**
     * 是否回单  10：未回单 20：已回单
     * @param isreply 是否回单  10：未回单 20：已回单
     */
    public void setIsreply(String isreply) {
        this.isreply = isreply == null ? null : isreply.trim();
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
     * 打印标志
     * @return PRINTFLAG 打印标志
     */
    public String getPrintflag() {
        return printflag;
    }

    /**
     * 打印标志
     * @param printflag 打印标志
     */
    public void setPrintflag(String printflag) {
        this.printflag = printflag == null ? null : printflag.trim();
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
     * 车管员
     * @return VEHICLECTRID 车管员
     */
    public Long getVehiclectrid() {
        return vehiclectrid;
    }

    /**
     * 车管员
     * @param vehiclectrid 车管员
     */
    public void setVehiclectrid(Long vehiclectrid) {
        this.vehiclectrid = vehiclectrid;
    }

    /**
     * 车管员保养项目
     * @return VEHICLEMAINITEM 车管员保养项目
     */
    public String getVehiclemainitem() {
        return vehiclemainitem;
    }

    /**
     * 车管员保养项目
     * @param vehiclemainitem 车管员保养项目
     */
    public void setVehiclemainitem(String vehiclemainitem) {
        this.vehiclemainitem = vehiclemainitem == null ? null : vehiclemainitem.trim();
    }

    /**
     * 分部主任id
     * @return LEADERID 分部主任id
     */
    public Long getLeaderid() {
        return leaderid;
    }

    /**
     * 分部主任id
     * @param leaderid 分部主任id
     */
    public void setLeaderid(Long leaderid) {
        this.leaderid = leaderid;
    }

    /**
     * 分部主任意见
     * @return LEADERSUGGESTION 分部主任意见
     */
    public String getLeadersuggestion() {
        return leadersuggestion;
    }

    /**
     * 分部主任意见
     * @param leadersuggestion 分部主任意见
     */
    public void setLeadersuggestion(String leadersuggestion) {
        this.leadersuggestion = leadersuggestion == null ? null : leadersuggestion.trim();
    }

    /**
     * 审核标志 （10：未审核  20：车管员审核通过  30：车管员审核驳回  40：分部主任通过  50：分部主任驳回）
     * @return STATUS 审核标志 （10：未审核  20：车管员审核通过  30：车管员审核驳回  40：分部主任通过  50：分部主任驳回）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 审核标志 （10：未审核  20：车管员审核通过  30：车管员审核驳回  40：分部主任通过  50：分部主任驳回）
     * @param status 审核标志 （10：未审核  20：车管员审核通过  30：车管员审核驳回  40：分部主任通过  50：分部主任驳回）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 驾驶员id
     * @return DRIVERID 驾驶员id
     */
    public Long getDriverid() {
        return driverid;
    }

    /**
     * 驾驶员id
     * @param driverid 驾驶员id
     */
    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    /**
     * 维修类型（10：维修 20：保养)
     * @return REPAIRTYPE 维修类型（10：维修 20：保养)
     */
    public String getRepairtype() {
        return repairtype;
    }

    /**
     * 维修类型（10：维修 20：保养)
     * @param repairtype 维修类型（10：维修 20：保养)
     */
    public void setRepairtype(String repairtype) {
        this.repairtype = repairtype == null ? null : repairtype.trim();
    }

    /**
     * 是否重复维修 (0:不是   1:是)
     * @return ISREPEAT 是否重复维修 (0:不是   1:是)
     */
    public BigDecimal getIsrepeat() {
        return isrepeat;
    }

    /**
     * 是否重复维修 (0:不是   1:是)
     * @param isrepeat 是否重复维修 (0:不是   1:是)
     */
    public void setIsrepeat(BigDecimal isrepeat) {
        this.isrepeat = isrepeat;
    }

    /**
     * 预计费用
     * @return ESTIMATECOST 预计费用
     */
    public BigDecimal getEstimatecost() {
        return estimatecost;
    }

    /**
     * 预计费用
     * @param estimatecost 预计费用
     */
    public void setEstimatecost(BigDecimal estimatecost) {
        this.estimatecost = estimatecost;
    }
}