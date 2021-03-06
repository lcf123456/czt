package com.ztel.app.vo.safe;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleAccidentVo {
    /**
     * 序号ID
     */
    private Long id;
    private String keyword;
    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
     * 驾驶员ID/驾驶姓名
     */
    private Long driverid;
    private String drivername;

    /**
     * 车辆ID/车牌号码
     */
    private Integer vehicleid;
    private String vehicleno;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 发生日期
     */
    private Date happendate;

    /**
     * 发生时间段ID
     */
    private Integer happentime;

    /**
     * 发生金额
     */
    private BigDecimal amount;

    /**
     * 发生地点ID/地点
     */
    private Integer localeid;
    public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	private String locale; 

    /**
     * 气候ID/气候
     */
    private Integer weatherid;
    private String weather;

    /**
     * 伤亡情况伤
     */
    private Short injurynum;

    /**
     * 伤亡情况亡
     */
    private Short deadnum;

    /**
     * 车辆损毁状况
     */
    private String damagenote;

    /**
     * 驾驶员情绪
     */
    private String emotion;

    /**
     * 事故描述
     */
    private String note;

    /**
     * 创建人
     */
    private Long createid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 事故分析
     */
    private String analy;

    /**
     * 分析人员
     */
    private Long analyst;

    /**
     * 整改措施
     */
    private String rectify;

    /**
     * 整改记录人
     */
    private Long recorder;

    /**
     * 车速
     */
    private Short speed;

    /**
     * 事故标识 （10：事故录入 20：分析完成 30：整改完成）
     */
    private String status;

    /**
     * 事故登记
     */
    private String register;

    /**
     * 事故责任
     */
    private String liable;

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
     * 驾驶员ID
     * @return DRIVERID 驾驶员ID
     */
    public Long getDriverid() {
        return driverid;
    }

    /**
     * 驾驶员ID
     * @param driverid 驾驶员ID
     */
    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    /**
     * 车辆ID
     * @return VEHICLEID 车辆ID
     */
    public Integer getVehicleid() {
        return vehicleid;
    }

    /**
     * 车辆ID
     * @param vehicleid 车辆ID
     */
    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
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
     * 发生日期
     * @return HAPPENDATE 发生日期
     */
    public Date getHappendate() {
        return happendate;
    }

    /**
     * 发生日期
     * @param happendate 发生日期
     */
    public void setHappendate(Date happendate) {
        this.happendate = happendate;
    }

    /**
     * 发生时间段ID
     * @return HAPPENTIME 发生时间段ID
     */
    public Integer getHappentime() {
        return happentime;
    }

    /**
     * 发生时间段ID
     * @param happentime 发生时间段ID
     */
    public void setHappentime(Integer happentime) {
        this.happentime = happentime;
    }

    /**
     * 发生金额
     * @return AMOUNT 发生金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 发生金额
     * @param amount 发生金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 发生地点ID
     * @return LOCALEID 发生地点ID
     */
    public Integer getLocaleid() {
        return localeid;
    }

    /**
     * 发生地点ID
     * @param localeid 发生地点ID
     */
    public void setLocaleid(Integer localeid) {
        this.localeid = localeid;
    }

    /**
     * 气候ID
     * @return WEATHERID 气候ID
     */
    public Integer getWeatherid() {
        return weatherid;
    }

    /**
     * 气候ID
     * @param weatherid 气候ID
     */
    public void setWeatherid(Integer weatherid) {
        this.weatherid = weatherid;
    }

    /**
     * 伤亡情况伤
     * @return INJURYNUM 伤亡情况伤
     */
    public Short getInjurynum() {
        return injurynum;
    }

    /**
     * 伤亡情况伤
     * @param injurynum 伤亡情况伤
     */
    public void setInjurynum(Short injurynum) {
        this.injurynum = injurynum;
    }

    /**
     * 伤亡情况亡
     * @return DEADNUM 伤亡情况亡
     */
    public Short getDeadnum() {
        return deadnum;
    }

    /**
     * 伤亡情况亡
     * @param deadnum 伤亡情况亡
     */
    public void setDeadnum(Short deadnum) {
        this.deadnum = deadnum;
    }

    /**
     * 车辆损毁状况
     * @return DAMAGENOTE 车辆损毁状况
     */
    public String getDamagenote() {
        return damagenote;
    }

    /**
     * 车辆损毁状况
     * @param damagenote 车辆损毁状况
     */
    public void setDamagenote(String damagenote) {
        this.damagenote = damagenote == null ? null : damagenote.trim();
    }

    /**
     * 驾驶员情绪
     * @return EMOTION 驾驶员情绪
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * 驾驶员情绪
     * @param emotion 驾驶员情绪
     */
    public void setEmotion(String emotion) {
        this.emotion = emotion == null ? null : emotion.trim();
    }

    /**
     * 事故描述
     * @return NOTE 事故描述
     */
    public String getNote() {
        return note;
    }

    /**
     * 事故描述
     * @param note 事故描述
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 创建人
     * @return CREATEID 创建人
     */
    public Long getCreateid() {
        return createid;
    }

    /**
     * 创建人
     * @param createid 创建人
     */
    public void setCreateid(Long createid) {
        this.createid = createid;
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
     * 事故分析
     * @return ANALY 事故分析
     */
    public String getAnaly() {
        return analy;
    }

    /**
     * 事故分析
     * @param analy 事故分析
     */
    public void setAnaly(String analy) {
        this.analy = analy == null ? null : analy.trim();
    }

    /**
     * 分析人员
     * @return ANALYST 分析人员
     */
    public Long getAnalyst() {
        return analyst;
    }

    /**
     * 分析人员
     * @param analyst 分析人员
     */
    public void setAnalyst(Long analyst) {
        this.analyst = analyst;
    }

    /**
     * 整改措施
     * @return RECTIFY 整改措施
     */
    public String getRectify() {
        return rectify;
    }

    /**
     * 整改措施
     * @param rectify 整改措施
     */
    public void setRectify(String rectify) {
        this.rectify = rectify == null ? null : rectify.trim();
    }

    /**
     * 整改记录人
     * @return RECORDER 整改记录人
     */
    public Long getRecorder() {
        return recorder;
    }

    /**
     * 整改记录人
     * @param recorder 整改记录人
     */
    public void setRecorder(Long recorder) {
        this.recorder = recorder;
    }

    /**
     * 车速
     * @return SPEED 车速
     */
    public Short getSpeed() {
        return speed;
    }

    /**
     * 车速
     * @param speed 车速
     */
    public void setSpeed(Short speed) {
        this.speed = speed;
    }

    /**
     * 事故标识 （10：事故录入 20：分析完成 30：整改完成）
     * @return STATUS 事故标识 （10：事故录入 20：分析完成 30：整改完成）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 事故标识 （10：事故录入 20：分析完成 30：整改完成）
     * @param status 事故标识 （10：事故录入 20：分析完成 30：整改完成）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 事故登记
     * @return REGISTER 事故登记
     */
    public String getRegister() {
        return register;
    }

    /**
     * 事故登记
     * @param register 事故登记
     */
    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
    }

    /**
     * 事故责任
     * @return LIABLE 事故责任
     */
    public String getLiable() {
        return liable;
    }

    /**
     * 事故责任
     * @param liable 事故责任
     */
    public void setLiable(String liable) {
        this.liable = liable == null ? null : liable.trim();
    }
}