package com.ztel.app.vo.sys;

import java.math.BigDecimal;

public class OperationinfoVo {
    /**
     * id序号
     */
    private BigDecimal id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单code
     */
    private String menucode;

    /**
     * 备注信息
     */
    private String remarks;
    
    private String checked;
    

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
     * 名称
     * @return NAME 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 菜单code
     * @return MENUCODE 菜单code
     */
    public String getMenucode() {
        return menucode;
    }

    /**
     * 菜单code
     * @param menucode 菜单code
     */
    public void setMenucode(String menucode) {
        this.menucode = menucode == null ? null : menucode.trim();
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

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}


	
	
    
}