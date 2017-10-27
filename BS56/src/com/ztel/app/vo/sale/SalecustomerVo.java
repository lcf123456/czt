package com.ztel.app.vo.sale;

import java.math.BigDecimal;
import java.util.Date;

public class SalecustomerVo {
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
     * 等级（３０档）
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
     * 发证日期（yyyy-mm-dd）
     */
    private Date createtime;

    /**
     * 许可证有效日期（yyyy-mm-dd）
     */
    private Date validtime;

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
     * 订货批次： 51，52，。。
     */
    private String orderbatch;

    /**
     * 43专卖证号
     */
    private String licensecode;

    /**
     * 市场类型：　城镇、乡村
     */
    private String markettype;

    /**
     * 订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
     */
    private String orderway;

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
        this.id = id == null ? null : id.trim();
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
     * 等级（３０档）
     * @return CUSTOMERLEVEL 等级（３０档）
     */
    public Object getCustomerlevel() {
        return customerlevel;
    }

    /**
     * 等级（３０档）
     * @param customerlevel 等级（３０档）
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
     * 发证日期（yyyy-mm-dd）
     * @return CREATETIME 发证日期（yyyy-mm-dd）
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 发证日期（yyyy-mm-dd）
     * @param createtime 发证日期（yyyy-mm-dd）
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 许可证有效日期（yyyy-mm-dd）
     * @return VALIDTIME 许可证有效日期（yyyy-mm-dd）
     */
    public Date getValidtime() {
        return validtime;
    }

    /**
     * 许可证有效日期（yyyy-mm-dd）
     * @param validtime 许可证有效日期（yyyy-mm-dd）
     */
    public void setValidtime(Date validtime) {
        this.validtime = validtime;
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
     * 43专卖证号
     * @return LICENSECODE 43专卖证号
     */
    public String getLicensecode() {
        return licensecode;
    }

    /**
     * 43专卖证号
     * @param licensecode 43专卖证号
     */
    public void setLicensecode(String licensecode) {
        this.licensecode = licensecode == null ? null : licensecode.trim();
    }

    /**
     * 市场类型：　城镇、乡村
     * @return MARKETTYPE 市场类型：　城镇、乡村
     */
    public String getMarkettype() {
        return markettype;
    }

    /**
     * 市场类型：　城镇、乡村
     * @param markettype 市场类型：　城镇、乡村
     */
    public void setMarkettype(String markettype) {
        this.markettype = markettype == null ? null : markettype.trim();
    }

    /**
     * 订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
     * @return ORDERWAY 订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
     */
    public String getOrderway() {
        return orderway;
    }

    /**
     * 订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
     * @param orderway 订货渠道，10:电访 20:网上配货 31:网上订货 32:电视 33：手机 34：终端 35：微信 61：业务 62：应急 63：特供 64：罚没 65：优惠 66：积压 67：残损
     */
    public void setOrderway(String orderway) {
        this.orderway = orderway == null ? null : orderway.trim();
    }
}