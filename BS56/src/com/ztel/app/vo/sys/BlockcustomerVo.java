package com.ztel.app.vo.sys;

import java.math.BigDecimal;
import java.util.Date;

public class BlockcustomerVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 零售户id
     */
    private BigDecimal custid;

    /**
     * 零售户code
     */
    private String custcode;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String telnum;

    /**
     * 店面
     */
    private String custname;

    /**
     * 送货地址
     */
    private String deliveryaddr;

    /**
     * 送货车组
     */
    private String routecode;

    /**
     * 上黑名单原因
     */
    private String blockreason;

    /**
     * 预计时间
     */
    private String blocktimerange;

    /**
     * 类型
     */
    private Integer blocktype;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * null
     */
    private String remarks;

    /**
     * 异动原因id
     */
    private Integer reasonid;

    /**
     * 来源说明： 0：触摸屏
     */
    private String sourceid;

    /**
     * 实际情况
     */
    private String actualsitu;

    /**
     * 处理情况
     */
    private String handlesitu;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 处理人员
     */
    private Long handleuser;

    /**
     * 处理时间
     */
    private Date handledate;

    /**
     * 处理标志：0：未处理 10：处理
     */
    private String handleflag;

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
     * 零售户id
     * @return CUSTID 零售户id
     */
    public BigDecimal getCustid() {
        return custid;
    }

    /**
     * 零售户id
     * @param custid 零售户id
     */
    public void setCustid(BigDecimal custid) {
        this.custid = custid;
    }

    /**
     * 零售户code
     * @return CUSTCODE 零售户code
     */
    public String getCustcode() {
        return custcode;
    }

    /**
     * 零售户code
     * @param custcode 零售户code
     */
    public void setCustcode(String custcode) {
        this.custcode = custcode == null ? null : custcode.trim();
    }

    /**
     * 联系人
     * @return CONTACTER 联系人
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * 联系人
     * @param contacter 联系人
     */
    public void setContacter(String contacter) {
        this.contacter = contacter == null ? null : contacter.trim();
    }

    /**
     * 联系电话
     * @return TELNUM 联系电话
     */
    public String getTelnum() {
        return telnum;
    }

    /**
     * 联系电话
     * @param telnum 联系电话
     */
    public void setTelnum(String telnum) {
        this.telnum = telnum == null ? null : telnum.trim();
    }

    /**
     * 店面
     * @return CUSTNAME 店面
     */
    public String getCustname() {
        return custname;
    }

    /**
     * 店面
     * @param custname 店面
     */
    public void setCustname(String custname) {
        this.custname = custname == null ? null : custname.trim();
    }

    /**
     * 送货地址
     * @return DELIVERYADDR 送货地址
     */
    public String getDeliveryaddr() {
        return deliveryaddr;
    }

    /**
     * 送货地址
     * @param deliveryaddr 送货地址
     */
    public void setDeliveryaddr(String deliveryaddr) {
        this.deliveryaddr = deliveryaddr == null ? null : deliveryaddr.trim();
    }

    /**
     * 送货车组
     * @return ROUTECODE 送货车组
     */
    public String getRoutecode() {
        return routecode;
    }

    /**
     * 送货车组
     * @param routecode 送货车组
     */
    public void setRoutecode(String routecode) {
        this.routecode = routecode == null ? null : routecode.trim();
    }

    /**
     * 上黑名单原因
     * @return BLOCKREASON 上黑名单原因
     */
    public String getBlockreason() {
        return blockreason;
    }

    /**
     * 上黑名单原因
     * @param blockreason 上黑名单原因
     */
    public void setBlockreason(String blockreason) {
        this.blockreason = blockreason == null ? null : blockreason.trim();
    }

    /**
     * 预计时间
     * @return BLOCKTIMERANGE 预计时间
     */
    public String getBlocktimerange() {
        return blocktimerange;
    }

    /**
     * 预计时间
     * @param blocktimerange 预计时间
     */
    public void setBlocktimerange(String blocktimerange) {
        this.blocktimerange = blocktimerange == null ? null : blocktimerange.trim();
    }

    /**
     * 类型
     * @return BLOCKTYPE 类型
     */
    public Integer getBlocktype() {
        return blocktype;
    }

    /**
     * 类型
     * @param blocktype 类型
     */
    public void setBlocktype(Integer blocktype) {
        this.blocktype = blocktype;
    }

    /**
     * 创建时间
     * @return CREATEDATE 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 创建时间
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * null
     * @return REMARKS null
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * null
     * @param remarks null
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 异动原因id
     * @return REASONID 异动原因id
     */
    public Integer getReasonid() {
        return reasonid;
    }

    /**
     * 异动原因id
     * @param reasonid 异动原因id
     */
    public void setReasonid(Integer reasonid) {
        this.reasonid = reasonid;
    }

    /**
     * 来源说明： 0：触摸屏
     * @return SOURCEID 来源说明： 0：触摸屏
     */
    public String getSourceid() {
        return sourceid;
    }

    /**
     * 来源说明： 0：触摸屏
     * @param sourceid 来源说明： 0：触摸屏
     */
    public void setSourceid(String sourceid) {
        this.sourceid = sourceid == null ? null : sourceid.trim();
    }

    /**
     * 实际情况
     * @return ACTUALSITU 实际情况
     */
    public String getActualsitu() {
        return actualsitu;
    }

    /**
     * 实际情况
     * @param actualsitu 实际情况
     */
    public void setActualsitu(String actualsitu) {
        this.actualsitu = actualsitu == null ? null : actualsitu.trim();
    }

    /**
     * 处理情况
     * @return HANDLESITU 处理情况
     */
    public String getHandlesitu() {
        return handlesitu;
    }

    /**
     * 处理情况
     * @param handlesitu 处理情况
     */
    public void setHandlesitu(String handlesitu) {
        this.handlesitu = handlesitu == null ? null : handlesitu.trim();
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
     * 处理人员
     * @return HANDLEUSER 处理人员
     */
    public Long getHandleuser() {
        return handleuser;
    }

    /**
     * 处理人员
     * @param handleuser 处理人员
     */
    public void setHandleuser(Long handleuser) {
        this.handleuser = handleuser;
    }

    /**
     * 处理时间
     * @return HANDLEDATE 处理时间
     */
    public Date getHandledate() {
        return handledate;
    }

    /**
     * 处理时间
     * @param handledate 处理时间
     */
    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    /**
     * 处理标志：0：未处理 10：处理
     * @return HANDLEFLAG 处理标志：0：未处理 10：处理
     */
    public String getHandleflag() {
        return handleflag;
    }

    /**
     * 处理标志：0：未处理 10：处理
     * @param handleflag 处理标志：0：未处理 10：处理
     */
    public void setHandleflag(String handleflag) {
        this.handleflag = handleflag == null ? null : handleflag.trim();
    }
    private String reasonname;

	public String getReasonname() {
		return reasonname;
	}

	public void setReasonname(String reasonname) {
		this.reasonname = reasonname;
	}
private String handlename;
	public String getHandlename() {
	return handlename;
}

public void setHandlename(String handlename) {
	this.handlename = handlename;
}
private String createname;
	public String getCreatename() {
	return createname;
}

public void setCreatename(String createname) {
	this.createname = createname;
}

	public void setParam(String condname) {
		// TODO Auto-generated method stub
		
	}
}