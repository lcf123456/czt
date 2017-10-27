package com.ztel.app.vo.mobile;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersignVo {
    /**
     * 序号ID
     */
    private BigDecimal id;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 创建人
     */
    private BigDecimal createid;

    /**
     * 签收时间
     */
    private Date createtime;

    /**
     * 图片名称
     */
    private String imgname;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 签到信息地址
     */
    private String detailaddr;

    /**
     * 类型  10：车组退货 20：暂存  30：组间转货  40暂存送货  50.签收
     */
    private String ctype;

    /**
     * 订单日期
     */
    private Date orderdate;

    private Date begintime;
    private Date endtime;
    private String custname;//零售户名
    private String custid;//专卖证号
    private String orderdatestr;//订单日期
    private String keywdnumber;
    private String keywd;
    private String createtimestr;
    private BigDecimal deleverycount;//签收数量
    private BigDecimal deliverytime;//送货总时长(小时)
    private BigDecimal avgcount;//均值(户/小时)

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
     * 订单号
     * @return ORDERID 订单号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 订单号
     * @param orderid 订单号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     * 纬度
     * @return LAT 纬度
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * 纬度
     * @param lat 纬度
     */
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     * 经度
     * @return LNG 经度
     */
    public BigDecimal getLng() {
        return lng;
    }

    /**
     * 经度
     * @param lng 经度
     */
    public void setLng(BigDecimal lng) {
        this.lng = lng;
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
     * 创建人
     * @return CREATEID 创建人
     */
    public BigDecimal getCreateid() {
        return createid;
    }

    /**
     * 创建人
     * @param createid 创建人
     */
    public void setCreateid(BigDecimal createid) {
        this.createid = createid;
    }

    /**
     * 签收时间
     * @return CREATETIME 签收时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 签收时间
     * @param createtime 签收时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 图片名称
     * @return IMGNAME 图片名称
     */
    public String getImgname() {
        return imgname;
    }

    /**
     * 图片名称
     * @param imgname 图片名称
     */
    public void setImgname(String imgname) {
        this.imgname = imgname == null ? null : imgname.trim();
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
     * 签到信息地址
     * @return DETAILADDR 签到信息地址
     */
    public String getDetailaddr() {
        return detailaddr;
    }

    /**
     * 签到信息地址
     * @param detailaddr 签到信息地址
     */
    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr == null ? null : detailaddr.trim();
    }

    /**
     * 类型  10：车组退货 20：暂存  30：组间转货  40暂存送货  50.签收
     * @return CTYPE 类型  10：车组退货 20：暂存  30：组间转货  40暂存送货  50.签收
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * 类型  10：车组退货 20：暂存  30：组间转货  40暂存送货  50.签收
     * @param ctype 类型  10：车组退货 20：暂存  30：组间转货  40暂存送货  50.签收
     */
    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    /**
     * 订单日期
     * @return ORDERDATE 订单日期
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * 订单日期
     * @param orderdate 订单日期
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getOrderdatestr() {
		return orderdatestr;
	}

	public void setOrderdatestr(String orderdatestr) {
		this.orderdatestr = orderdatestr;
	}

	public String getKeywdnumber() {
		return keywdnumber;
	}

	public void setKeywdnumber(String keywdnumber) {
		this.keywdnumber = keywdnumber;
	}

	public String getKeywd() {
		return keywd;
	}

	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}

	public String getCreatetimestr() {
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public BigDecimal getDeleverycount() {
		return deleverycount;
	}

	public void setDeleverycount(BigDecimal deleverycount) {
		this.deleverycount = deleverycount;
	}

	public BigDecimal getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(BigDecimal deliverytime) {
		this.deliverytime = deliverytime;
	}

	public BigDecimal getAvgcount() {
		return avgcount;
	}

	public void setAvgcount(BigDecimal avgcount) {
		this.avgcount = avgcount;
	}
    
    
}