package com.ztel.app.vo.sys;

public class BusinessRoleRelativeVo {
    /**
     * 员工ID
     */
    private Long userid;

    /**
     * 业务角色ID
     */
    private Integer roleid;
    
    private String username;

    /**
     * 部门id
     */
    private Integer deptid;
    
    private String usercode;
    /**
     * 员工ID
     * @return USERID 员工ID
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 员工ID
     * @param userid 员工ID
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 业务角色ID
     * @return ROLEID 业务角色ID
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 业务角色ID
     * @param roleid 业务角色ID
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
    
}