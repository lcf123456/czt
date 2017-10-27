package com.ztel.app.vo.safe;

public class FireFacilitiesVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * 序号
     */
    private String serial;

    /**
     * 设施名称ID
     */
    private Integer deviceid;

    /**
     * 设施编号
     */
    private String devicecode;

    /**
     * 使用地点
     */
    private String addr;

    /**
     * 部门ID
     */
    private Integer deptid;

    /**
     * 状态（10:完好  20:一般  30:差）
     */
    private String ctype;

    /**
     * 重要程度（10：重要 20：不重要）
     */
    private String important;

    /**
     * 提醒标识（10：不提醒 20：提醒 ）
     */
    private String status;

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
     * 序号
     * @return SERIAL 序号
     */
    public String getSerial() {
        return serial;
    }

    /**
     * 序号
     * @param serial 序号
     */
    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    /**
     * 设施名称ID
     * @return DEVICEID 设施名称ID
     */
    public Integer getDeviceid() {
        return deviceid;
    }

    /**
     * 设施名称ID
     * @param deviceid 设施名称ID
     */
    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * 设施编号
     * @return DEVICECODE 设施编号
     */
    public String getDevicecode() {
        return devicecode;
    }

    /**
     * 设施编号
     * @param devicecode 设施编号
     */
    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    /**
     * 使用地点
     * @return ADDR 使用地点
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 使用地点
     * @param addr 使用地点
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * 部门ID
     * @return DEPTID 部门ID
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 部门ID
     * @param deptid 部门ID
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 状态（10:完好  20:一般  30:差）
     * @return CTYPE 状态（10:完好  20:一般  30:差）
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * 状态（10:完好  20:一般  30:差）
     * @param ctype 状态（10:完好  20:一般  30:差）
     */
    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    /**
     * 重要程度（10：重要 20：不重要）
     * @return IMPORTANT 重要程度（10：重要 20：不重要）
     */
    public String getImportant() {
        return important;
    }

    /**
     * 重要程度（10：重要 20：不重要）
     * @param important 重要程度（10：重要 20：不重要）
     */
    public void setImportant(String important) {
        this.important = important == null ? null : important.trim();
    }

    /**
     * 提醒标识（10：不提醒 20：提醒 ）
     * @return STATUS 提醒标识（10：不提醒 20：提醒 ）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 提醒标识（10：不提醒 20：提醒 ）
     * @param status 提醒标识（10：不提醒 20：提醒 ）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    /**
     * 设施名称
     */
    private String device;

    /**
     * 设施型号
     */
    private String model;
    /**
     * 部门名称
     */
    private String deptname;
    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
     * 设施名称
     * @return DEVICE 设施名称
     */
    public String getDevice() {
        return device;
    }

    /**
     * 设施名称
     * @param device 设施名称
     */
    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    /**
     * 设施型号
     * @return MODEL 设施型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设施型号
     * @param model 设施型号
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }
}