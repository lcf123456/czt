package com.ztel.app.vo.sys;

import java.math.BigDecimal;

public class OperationrolerelativeVo {
    /**
     * id序号
     */
    private BigDecimal id;

    /**
     * 功能点id
     */
    private BigDecimal operationid;

    /**
     * 角色id
     */
    private Integer roleid;
    
    /**
     * 所属系统
     */
    private String belongsys;

    /**
     * id序号
     * @return ID id序号
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * id序号
     * @param id id序号
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * 功能点id
     * @return OPERATIONID 功能点id
     */
    public BigDecimal getOperationid() {
        return operationid;
    }

    /**
     * 功能点id
     * @param operationid 功能点id
     */
    public void setOperationid(BigDecimal operationid) {
        this.operationid = operationid;
    }

    /**
     * 角色id
     * @return ROLEID 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 角色id
     * @param roleid 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

	public String getBelongsys() {
		return belongsys;
	}

	public void setBelongsys(String belongsys) {
		this.belongsys = belongsys;
	}
    
}