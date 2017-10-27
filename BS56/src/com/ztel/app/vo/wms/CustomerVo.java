package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerVo {
    /**
     * 43专卖证号
     */
    private String id;

    /**
     * null
     */
    private String dcno;

    /**
     * 所属烟草公司
     */
    private BigDecimal shipperId;

    /**
     * 零售户代码
     */
    private Object code;

    /**
     * 简称
     */
    private Object shortname;

    /**
     * 零售户点名
     */
    private Object name;

    /**
     * 等级
     */
    private Object customerlevel;

    /**
     * 业态，国家局统一枚举值 Z：食杂店，B：便利店，S：超市，N：商场，Y：烟酒商店，F：娱乐服务，Q：其他 
     */
    private String industry;

    /**
     * 联系地址
     */
    private Object contactaddress;

    /**
     * 邮编
     */
    private Object zipcode;

    /**
     * 联系电话
     */
    private Object contactphone;

    /**
     * 备用电话
     */
    private Object bakphone;

    /**
     * 联系人
     */
    private Object contact;

    /**
     * 传真
     */
    private Object faxno;

    /**
     * 网址
     */
    private Object website;

    /**
     * 邮箱
     */
    private Object email;

    /**
     * 账户
     */
    private Object account;

    /**
     * 备注(43)
     */
    private Object remarks;

    /**
     * 记录时间
     */
    private Date createtime;

    /**
     * 记录人
     */
    private Object createuser;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 修改人
     */
    private Object modifyuser;

    /**
     * 10为正常,0为删除
     */
    private BigDecimal delstatus;

    /**
     * 10普通发票  20增值税票，和invoiceflag配合使用
     */
    private String billtype;

    /**
     * 车组CODE
     */
    private String routecode;

    /**
     * 送货顺序
     */
    private BigDecimal deliveryseq;

    /**
     * 备用顺序
     */
    private BigDecimal bakseq;

    /**
     * 客户经理名称
     */
    private String managername;

    /**
     * 客户经理电话
     */
    private String managertel;

    /**
     * 送货顺序
     */
    private BigDecimal addressseq;

    /**
     * 维度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 店名图片
     */
    private String img;

    /**
     * 订货批次： 51，52，。。
     */
    private String orderbatch;

    /**
     * 发票客户 0:无需发票 1：发票客户
     */
    private String invoiceflag;

    /**
     * 预付款客户 0：非 1：预付款客户
     */
    private String prepayflag;

    /**
     * 预付款客户上级ID
     */
    private String prepayparentid;

    /**
     * 客户类型 1：普通客户 2：预付款一级客户
     */
    private String customertype;

    /**
     * 描点状态:已描点 未描点
     */
    private String drawstatus;
    
    /**
     * 模糊查询参数值，用于投诉受理零售户查询，将模糊查询的传入值放入此参数中
     */
    private String param;

    private String parentcustomer;
    
    private String billtypename;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    public String getBilltypename() {
		return billtypename;
	}

	public void setBilltypename(String billtypename) {
		this.billtypename = billtypename;
	}

	public String getParentcustomer() {
		return parentcustomer;
	}

	public void setParentcustomer(String parentcustomer) {
		this.parentcustomer = parentcustomer;
	}

	/**
     * 43专卖证号
     * @return ID 43专卖证号
     */
	public String getId() {
		return id;
	}

    /**
     * 43专卖证号
     * @param id 43专卖证号
     */
	public void setId(String id) {
		this.id = id;
	}
	
    /**
     * null
     * @return DCNO null
     */
    public String getDcno() {
        return dcno;
    }

	/**
     * null
     * @param dcno null
     */
    public void setDcno(String dcno) {
        this.dcno = dcno == null ? null : dcno.trim();
    }

    /**
     * 所属烟草公司
     * @return SHIPPER_ID 所属烟草公司
     */
    public BigDecimal getShipperId() {
        return shipperId;
    }

    /**
     * 所属烟草公司
     * @param shipperId 所属烟草公司
     */
    public void setShipperId(BigDecimal shipperId) {
        this.shipperId = shipperId;
    }

    /**
     * 零售户代码
     * @return CODE 零售户代码
     */
    public Object getCode() {
        return code;
    }

    /**
     * 零售户代码
     * @param code 零售户代码
     */
    public void setCode(Object code) {
        this.code = code;
    }

    /**
     * 简称
     * @return SHORTNAME 简称
     */
    public Object getShortname() {
        return shortname;
    }

    /**
     * 简称
     * @param shortname 简称
     */
    public void setShortname(Object shortname) {
        this.shortname = shortname;
    }

    /**
     * 零售户点名
     * @return NAME 零售户点名
     */
    public Object getName() {
        return name;
    }

    /**
     * 零售户点名
     * @param name 零售户点名
     */
    public void setName(Object name) {
        this.name = name;
    }

    /**
     * 等级
     * @return CUSTOMERLEVEL 等级
     */
    public Object getCustomerlevel() {
        return customerlevel;
    }

    /**
     * 等级
     * @param customerlevel 等级
     */
    public void setCustomerlevel(Object customerlevel) {
        this.customerlevel = customerlevel;
    }

    /**
     * 业态，国家局统一枚举值 Z：食杂店，B：便利店，S：超市，N：商场，Y：烟酒商店，F：娱乐服务，Q：其他 
     * @return INDUSTRY 业态，国家局统一枚举值 Z：食杂店，B：便利店，S：超市，N：商场，Y：烟酒商店，F：娱乐服务，Q：其他 
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 业态，国家局统一枚举值 Z：食杂店，B：便利店，S：超市，N：商场，Y：烟酒商店，F：娱乐服务，Q：其他 
     * @param industry 业态，国家局统一枚举值 Z：食杂店，B：便利店，S：超市，N：商场，Y：烟酒商店，F：娱乐服务，Q：其他 
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 联系地址
     * @return CONTACTADDRESS 联系地址
     */
    public Object getContactaddress() {
        return contactaddress;
    }

    /**
     * 联系地址
     * @param contactaddress 联系地址
     */
    public void setContactaddress(Object contactaddress) {
        this.contactaddress = contactaddress;
    }

    /**
     * 邮编
     * @return ZIPCODE 邮编
     */
    public Object getZipcode() {
        return zipcode;
    }

    /**
     * 邮编
     * @param zipcode 邮编
     */
    public void setZipcode(Object zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 联系电话
     * @return CONTACTPHONE 联系电话
     */
    public Object getContactphone() {
        return contactphone;
    }

    /**
     * 联系电话
     * @param contactphone 联系电话
     */
    public void setContactphone(Object contactphone) {
        this.contactphone = contactphone;
    }

    /**
     * 备用电话
     * @return BAKPHONE 备用电话
     */
    public Object getBakphone() {
        return bakphone;
    }

    /**
     * 备用电话
     * @param bakphone 备用电话
     */
    public void setBakphone(Object bakphone) {
        this.bakphone = bakphone;
    }

    /**
     * 联系人
     * @return CONTACT 联系人
     */
    public Object getContact() {
        return contact;
    }

    /**
     * 联系人
     * @param contact 联系人
     */
    public void setContact(Object contact) {
        this.contact = contact;
    }

    /**
     * 传真
     * @return FAXNO 传真
     */
    public Object getFaxno() {
        return faxno;
    }

    /**
     * 传真
     * @param faxno 传真
     */
    public void setFaxno(Object faxno) {
        this.faxno = faxno;
    }

    /**
     * 网址
     * @return WEBSITE 网址
     */
    public Object getWebsite() {
        return website;
    }

    /**
     * 网址
     * @param website 网址
     */
    public void setWebsite(Object website) {
        this.website = website;
    }

    /**
     * 邮箱
     * @return EMAIL 邮箱
     */
    public Object getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(Object email) {
        this.email = email;
    }

    /**
     * 账户
     * @return ACCOUNT 账户
     */
    public Object getAccount() {
        return account;
    }

    /**
     * 账户
     * @param account 账户
     */
    public void setAccount(Object account) {
        this.account = account;
    }

    /**
     * 备注(43)
     * @return REMARKS 备注(43)
     */
    public Object getRemarks() {
        return remarks;
    }

    /**
     * 备注(43)
     * @param remarks 备注(43)
     */
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    /**
     * 记录时间
     * @return CREATETIME 记录时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 记录时间
     * @param createtime 记录时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 记录人
     * @return CREATEUSER 记录人
     */
    public Object getCreateuser() {
        return createuser;
    }

    /**
     * 记录人
     * @param createuser 记录人
     */
    public void setCreateuser(Object createuser) {
        this.createuser = createuser;
    }

    /**
     * 修改时间
     * @return MODIFYTIME 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 修改人
     * @return MODIFYUSER 修改人
     */
    public Object getModifyuser() {
        return modifyuser;
    }

    /**
     * 修改人
     * @param modifyuser 修改人
     */
    public void setModifyuser(Object modifyuser) {
        this.modifyuser = modifyuser;
    }

    /**
     * 10为正常,0为删除
     * @return DELSTATUS 10为正常,0为删除
     */
    public BigDecimal getDelstatus() {
        return delstatus;
    }

    /**
     * 10为正常,0为删除
     * @param delstatus 10为正常,0为删除
     */
    public void setDelstatus(BigDecimal delstatus) {
        this.delstatus = delstatus;
    }

    /**
     * 10普通发票  20增值税票，和invoiceflag配合使用
     * @return BILLTYPE 10普通发票  20增值税票，和invoiceflag配合使用
     */
    public String getBilltype() {
        return billtype;
    }

    /**
     * 10普通发票  20增值税票，和invoiceflag配合使用
     * @param billtype 10普通发票  20增值税票，和invoiceflag配合使用
     */
    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
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
     * 送货顺序
     * @return DELIVERYSEQ 送货顺序
     */
    public BigDecimal getDeliveryseq() {
        return deliveryseq;
    }

    /**
     * 送货顺序
     * @param deliveryseq 送货顺序
     */
    public void setDeliveryseq(BigDecimal deliveryseq) {
        this.deliveryseq = deliveryseq;
    }

    /**
     * 备用顺序
     * @return BAKSEQ 备用顺序
     */
    public BigDecimal getBakseq() {
        return bakseq;
    }

    /**
     * 备用顺序
     * @param bakseq 备用顺序
     */
    public void setBakseq(BigDecimal bakseq) {
        this.bakseq = bakseq;
    }

    /**
     * 客户经理名称
     * @return MANAGERNAME 客户经理名称
     */
    public String getManagername() {
        return managername;
    }

    /**
     * 客户经理名称
     * @param managername 客户经理名称
     */
    public void setManagername(String managername) {
        this.managername = managername == null ? null : managername.trim();
    }

    /**
     * 客户经理电话
     * @return MANAGERTEL 客户经理电话
     */
    public String getManagertel() {
        return managertel;
    }

    /**
     * 客户经理电话
     * @param managertel 客户经理电话
     */
    public void setManagertel(String managertel) {
        this.managertel = managertel == null ? null : managertel.trim();
    }

    /**
     * 送货顺序
     * @return ADDRESSSEQ 送货顺序
     */
    public BigDecimal getAddressseq() {
        return addressseq;
    }

    /**
     * 送货顺序
     * @param addressseq 送货顺序
     */
    public void setAddressseq(BigDecimal addressseq) {
        this.addressseq = addressseq;
    }

    /**
     * 维度
     * @return LAT 维度
     */
    public String getLat() {
        return lat;
    }

    /**
     * 维度
     * @param lat 维度
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * 经度
     * @return LNG 经度
     */
    public String getLng() {
        return lng;
    }

    /**
     * 经度
     * @param lng 经度
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * 店名图片
     * @return IMG 店名图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 店名图片
     * @param img 店名图片
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 订货批次： 51，52，。。
     * @return ORDERBATCH 订货批次： 51，52，。。
     */
    public String getOrderbatch() {
        return orderbatch;
    }

    /**
     * 订货批次： 51，52，。。
     * @param orderbatch 订货批次： 51，52，。。
     */
    public void setOrderbatch(String orderbatch) {
        this.orderbatch = orderbatch == null ? null : orderbatch.trim();
    }

    /**
     * 发票客户 0:无需发票 1：发票客户
     * @return INVOICEFLAG 发票客户 0:无需发票 1：发票客户
     */
    public String getInvoiceflag() {
        return invoiceflag;
    }

    /**
     * 发票客户 0:无需发票 1：发票客户
     * @param invoiceflag 发票客户 0:无需发票 1：发票客户
     */
    public void setInvoiceflag(String invoiceflag) {
        this.invoiceflag = invoiceflag == null ? null : invoiceflag.trim();
    }

    /**
     * 预付款客户 0：非 1：预付款客户
     * @return PREPAYFLAG 预付款客户 0：非 1：预付款客户
     */
    public String getPrepayflag() {
        return prepayflag;
    }

    /**
     * 预付款客户 0：非 1：预付款客户
     * @param prepayflag 预付款客户 0：非 1：预付款客户
     */
    public void setPrepayflag(String prepayflag) {
        this.prepayflag = prepayflag == null ? null : prepayflag.trim();
    }

    /**
     * 预付款客户上级ID
     * @return PREPAYPARENTID 预付款客户上级ID
     */
    public String getPrepayparentid() {
        return prepayparentid;
    }

    /**
     * 预付款客户上级ID
     * @param prepayparentid 预付款客户上级ID
     */
    public void setPrepayparentid(String prepayparentid) {
        this.prepayparentid = prepayparentid == null ? null : prepayparentid.trim();
    }

    /**
     * 客户类型 1：普通客户 2：预付款一级客户
     * @return CUSTOMERTYPE 客户类型 1：普通客户 2：预付款一级客户
     */
    public String getCustomertype() {
        return customertype;
    }

    /**
     * 客户类型 1：普通客户 2：预付款一级客户
     * @param customertype 客户类型 1：普通客户 2：预付款一级客户
     */
    public void setCustomertype(String customertype) {
        this.customertype = customertype == null ? null : customertype.trim();
    }

    /**
     * 描点状态:已描点 未描点
     * @return DRAWSTATUS 描点状态:已描点 未描点
     */
    public String getDrawstatus() {
        return drawstatus;
    }

    /**
     * 描点状态:已描点 未描点
     * @param drawstatus 描点状态:已描点 未描点
     */
    public void setDrawstatus(String drawstatus) {
        this.drawstatus = drawstatus == null ? null : drawstatus.trim();
    }

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public List<CustomerVo> getListByCond(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return null;
	}

}