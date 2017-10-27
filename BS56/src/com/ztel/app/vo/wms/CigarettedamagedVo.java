package com.ztel.app.vo.wms;

import java.math.BigDecimal;
import java.util.Date;

public class CigarettedamagedVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 入库单id
     */
    private BigDecimal inboundid;

    /**
     * 录入用户
     */
    private Long createuser;

    /**
     * 破损描述
     */
    private Object damageddescribe;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 审核用户
     */
    private Long checkuser;

    /**
     * 审核描述
     */
    private Object checkdescribe;

    /**
     * 审核日期
     */
    private Date checktime;

    /**
     * 备注
     */
    private Object remarks;

    /**
     * 删除标识0 删除 10 正常
     */
    private Short delstatus;

    /**
     * 破损总数量
     */
    private BigDecimal qty;
    
    /**
     * 创建人用户姓名
     */
    private String createusername;
    /**
     * 审核人用户姓名
     */
    private String checkusername;
    
    /**
     * 搜索关键字
     */
    private String keyword;
    
    /**
     * 准运证
     */
    private String navicert;
    
    /**
     * 合同号
     */
    private String contractno;
    
    /**
     * 审核标志  10：新增 20：审核通过 30：审核驳回
     */
    private String checkflag;
    
    /**
     *  审核标志名称  10：新增 20：审核通过 30：审核驳回
     */
    private String checkflagname;
    
    /**
     * 实际破损数量(条)
     */
    private BigDecimal actualqty;
    
    /**
     * 破损类别(10：来烟破损，20：称重异常)
     */
    private BigDecimal damagedtype;
    
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
     * 入库单id
     * @return INBOUNDID 入库单id
     */
    public BigDecimal getInboundid() {
        return inboundid;
    }

    /**
     * 入库单id
     * @param inboundid 入库单id
     */
    public void setInboundid(BigDecimal inboundid) {
        this.inboundid = inboundid;
    }

    /**
     * 录入用户
     * @return CREATEUSER 录入用户
     */
    public Long getCreateuser() {
        return createuser;
    }

    /**
     * 录入用户
     * @param createuser 录入用户
     */
    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    /**
     * 破损描述
     * @return DAMAGEDDESCRIBE 破损描述
     */
    public Object getDamageddescribe() {
        return damageddescribe;
    }

    /**
     * 破损描述
     * @param damageddescribe 破损描述
     */
    public void setDamageddescribe(Object damageddescribe) {
        this.damageddescribe = damageddescribe;
    }

    /**
     * 创建日期
     * @return CREATETIME 创建日期
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建日期
     * @param createtime 创建日期
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 审核用户
     * @return CHECKUSER 审核用户
     */
    public Long getCheckuser() {
        return checkuser;
    }

    /**
     * 审核用户
     * @param checkuser 审核用户
     */
    public void setCheckuser(Long checkuser) {
        this.checkuser = checkuser;
    }

    /**
     * 审核描述
     * @return CHECKDESCRIBE 审核描述
     */
    public Object getCheckdescribe() {
        return checkdescribe;
    }

    /**
     * 审核描述
     * @param checkdescribe 审核描述
     */
    public void setCheckdescribe(Object checkdescribe) {
        this.checkdescribe = checkdescribe;
    }

    /**
     * 审核日期
     * @return CHECKTIME 审核日期
     */
    public Date getChecktime() {
        return checktime;
    }

    /**
     * 审核日期
     * @param checktime 审核日期
     */
    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    /**
     * 备注
     * @return REMARKS 备注
     */
    public Object getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    /**
     * 删除标识0 删除 10 正常
     * @return DELSTATUS 删除标识0 删除 10 正常
     */
    public Short getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标识0 删除 10 正常
     * @param delstatus 删除标识0 删除 10 正常
     */
    public void setDelstatus(Short delstatus) {
        this.delstatus = delstatus;
    }

    /**
     * 破损总数量
     * @return QTY 破损总数量
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 破损总数量
     * @param qty 破损总数量
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getCheckusername() {
		return checkusername;
	}

	public void setCheckusername(String checkusername) {
		this.checkusername = checkusername;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNavicert() {
		return navicert;
	}

	public void setNavicert(String navicert) {
		this.navicert = navicert;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getCheckflag() {
		return checkflag;
	}

	public void setCheckflag(String checkflag) {
		this.checkflag = checkflag;
	}

	public String getCheckflagname() {
		return checkflagname;
	}

	public void setCheckflagname(String checkflagname) {
		this.checkflagname = checkflagname;
	}

	/**
	 * 实际破损数量(条)
	 * @return 实际破损数量(条)
	 */
	public BigDecimal getActualqty() {
		return actualqty;
	}

	/**
	 * 实际破损数量(条)
	 * @param actualqty 实际破损数量(条)
	 */
	public void setActualqty(BigDecimal actualqty) {
		this.actualqty = actualqty;
	}

	/**
	 * 破损类别(10：来烟破损，20：称重异常)
	 * @return 破损类别(10：来烟破损，20：称重异常)
	 */
	public BigDecimal getDamagedtype() {
		return damagedtype;
	}

	/**
	 * 破损类别(10：来烟破损，20：称重异常)
	 * @param damagedtype 破损类别(10：来烟破损，20：称重异常)
	 */
	public void setDamagedtype(BigDecimal damagedtype) {
		this.damagedtype = damagedtype;
	}
	
	
}