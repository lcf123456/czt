package com.ztel.app.vo.sys;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;


public class UserVo {
	
	public static String USER_DETAIL_SESSION_KEY = "user_detail_session_key";
	
	private Collection<String> roles;
	private Collection<String> authorities;
	private String opList;//功能点列表
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户code
     */
    private String usercode;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * ic卡号码
     */
    private String iccardno;

    /**
     * 部门id
     */
    private Integer deptid;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 婚姻状况（0：未婚，1：已婚 3：离异）
     */
    private Integer ismarried;

    /**
     * 出生日期
     */
    private String birthdate;

    /**
     * 国籍
     */
    private Integer nationality;

    /**
     * 政治面貌(0:群众 1：共青团员 2：中共党员 3：民主党派 4：其他)
     */
    private Integer politicalstatus;

    /**
     * 身份证号
     */
    private String idnum;

    /**
     * C_HOUSEHOLDREGISTER户口所在地
     */
    private String idregister;

    /**
     * C_NATIVEPLACE籍贯
     */
    private String nativeplace;

    /**
     * C_SOCIALSECURITYID社保号
     */
    private String socialsecurityid;

    /**
     * 家庭住址
     */
    private String addr;

    /**
     * 家庭电话(做手机短码用)
     */
    private String telnum;

    /**
     * 手机号码
     */
    private String phonenum;

    /**
     * 办公电话
     */
    private String officenum;

    /**
     * qq号码
     */
    private String qqnum;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 专业
     */
    private String major;

    /**
     * 教育背景C_EDUCATIONALBACKGROUND
     */
    private Integer edubackground;

    /**
     * 所在银行C_BANK
     */
    private String bankid;

    /**
     * 所在银行卡号C_BANKCARD
     */
    private String bankcard;

    /**
     * POSTID邮政编码
     */
    private Integer postid;

    /**
     * C_POSTSTATE
     */
    private Integer pose;

    /**
     * C_TECHNICALTITLE
     */
    private String technicaltitle;

    /**
     * 外语种类C_FOREIGNLANGUAGE
     */
    private String foreignlanguage;

    /**
     * 外语等级C_FORIEGNLANGUAGELEVEL
     */
    private String foreignlanguagelevel;

    /**
     * 计算机等级C_COMPUTERLEVEL
     */
    private String computerlevel;

    /**
     * C_GOODQUALITIES特长
     */
    private String specialty;

    /**
     * 参加工作时间C_JOINWORKTIME
     */
    private String joinworkdate;

    /**
     * C_JOINUNITTIME单位入职时间
     */
    private String joinunitdate;

    /**
     * C_BARGAINBEGINTIME签订合同时间
     */
    private String bgbegdate;

    /**
     * C_BARGAINENDTIME合同终止时间
     */
    private String bgenddate;

    /**
     * C_DIMISIONTIME离职日期
     */
    private String dimissiondate;

    /**
     * 相片名称
     */
    private String photoname;

    /**
     * 记录人
     */
    private Long createid;

    /**
     * 记录时间
     */
    private Date createdate;

    /**
     * SYS备注信息
     */
    private String sysremarks;

    /**
     * GROUP备注信息
     */
    private Short groupremarks;

    /**
     * 10内部用户  20外部用户  30虚拟用户  
     */
    private Short usertype;

    /**
     * 公司邮箱
     */
    private String companyemail;

    /**
     * 用户密码
     */
    private String userpsw;

    /**
     * 用户岗位
     */
    private Integer userpostid;

    /**
     * 专业名称
     */
    private String majorname;

    /**
     * 在职状态（10在职、0离职、20退休）
     */
    private String workstatus;

    /**
     * 是否有资格认证(1：有 0：无） 手机程序启用后修改为：手机短码字段 20140328修改
     */
    private BigDecimal shorttelnum;

    /**
     * 民族
     */
    private String nation;

    /**
     * 学历（博士、硕士、大学本科、大学专科、高中、其他）
     */
    private String education;

    /**
     * 职称
     */
    private String title;

    /**
     * 本单位工龄
     */
    private Short seniority;

    /**
     * 离职时间
     */
    private String leavedate;

    /**
     * 工作工龄
     */
    private Short selfseniority;

    /**
     * 签名图片(格式为:signatureimg/图片名)
     */
    private String signatureimg;

    /**
     * 删除标识0 删除 10 正常
     */
    private Short delstatus;
    
    private String worksource;//外派来源
    private Integer roleid;//角色id
    
    private String deptname;//部门名称
    private String rolename;//角色名称
    private String postname;//岗位名称

    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getWorksource() {
		return worksource;
	}

	public void setWorksource(String worksource) {
		this.worksource = worksource;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	/**
     * 用户ID
     * @return ID 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户ID
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户code
     * @return USERCODE 用户code
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 用户code
     * @param usercode 用户code
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    /**
     * 用户姓名
     * @return USERNAME 用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户姓名
     * @param username 用户姓名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * ic卡号码
     * @return ICCARDNO ic卡号码
     */
    public String getIccardno() {
        return iccardno;
    }

    /**
     * ic卡号码
     * @param iccardno ic卡号码
     */
    public void setIccardno(String iccardno) {
        this.iccardno = iccardno == null ? null : iccardno.trim();
    }

    /**
     * 部门id
     * @return DEPTID 部门id
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 部门id
     * @param deptid 部门id
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 用户性别
     * @return SEX 用户性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 用户性别
     * @param sex 用户性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 婚姻状况（0：未婚，1：已婚 3：离异）
     * @return ISMARRIED 婚姻状况（0：未婚，1：已婚 3：离异）
     */
    public Integer getIsmarried() {
        return ismarried;
    }

    /**
     * 婚姻状况（0：未婚，1：已婚 3：离异）
     * @param ismarried 婚姻状况（0：未婚，1：已婚 3：离异）
     */
    public void setIsmarried(Integer ismarried) {
        this.ismarried = ismarried;
    }

    /**
     * 出生日期
     * @return BIRTHDATE 出生日期
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * 出生日期
     * @param birthdate 出生日期
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate == null ? null : birthdate.trim();
    }

    /**
     * 国籍
     * @return NATIONALITY 国籍
     */
    public Integer getNationality() {
        return nationality;
    }

    /**
     * 国籍
     * @param nationality 国籍
     */
    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    /**
     * 政治面貌(0:群众 1：共青团员 2：中共党员 3：民主党派 4：其他)
     * @return POLITICALSTATUS 政治面貌(0:群众 1：共青团员 2：中共党员 3：民主党派 4：其他)
     */
    public Integer getPoliticalstatus() {
        return politicalstatus;
    }

    /**
     * 政治面貌(0:群众 1：共青团员 2：中共党员 3：民主党派 4：其他)
     * @param politicalstatus 政治面貌(0:群众 1：共青团员 2：中共党员 3：民主党派 4：其他)
     */
    public void setPoliticalstatus(Integer politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    /**
     * 身份证号
     * @return IDNUM 身份证号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 身份证号
     * @param idnum 身份证号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum == null ? null : idnum.trim();
    }

    /**
     * C_HOUSEHOLDREGISTER户口所在地
     * @return IDREGISTER C_HOUSEHOLDREGISTER户口所在地
     */
    public String getIdregister() {
        return idregister;
    }

    /**
     * C_HOUSEHOLDREGISTER户口所在地
     * @param idregister C_HOUSEHOLDREGISTER户口所在地
     */
    public void setIdregister(String idregister) {
        this.idregister = idregister == null ? null : idregister.trim();
    }

    /**
     * C_NATIVEPLACE籍贯
     * @return NATIVEPLACE C_NATIVEPLACE籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    /**
     * C_NATIVEPLACE籍贯
     * @param nativeplace C_NATIVEPLACE籍贯
     */
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    /**
     * C_SOCIALSECURITYID社保号
     * @return SOCIALSECURITYID C_SOCIALSECURITYID社保号
     */
    public String getSocialsecurityid() {
        return socialsecurityid;
    }

    /**
     * C_SOCIALSECURITYID社保号
     * @param socialsecurityid C_SOCIALSECURITYID社保号
     */
    public void setSocialsecurityid(String socialsecurityid) {
        this.socialsecurityid = socialsecurityid == null ? null : socialsecurityid.trim();
    }

    /**
     * 家庭住址
     * @return ADDR 家庭住址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 家庭住址
     * @param addr 家庭住址
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * 家庭电话(做手机短码用)
     * @return TELNUM 家庭电话(做手机短码用)
     */
    public String getTelnum() {
        return telnum;
    }

    /**
     * 家庭电话(做手机短码用)
     * @param telnum 家庭电话(做手机短码用)
     */
    public void setTelnum(String telnum) {
        this.telnum = telnum == null ? null : telnum.trim();
    }

    /**
     * 手机号码
     * @return PHONENUM 手机号码
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * 手机号码
     * @param phonenum 手机号码
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    /**
     * 办公电话
     * @return OFFICENUM 办公电话
     */
    public String getOfficenum() {
        return officenum;
    }

    /**
     * 办公电话
     * @param officenum 办公电话
     */
    public void setOfficenum(String officenum) {
        this.officenum = officenum == null ? null : officenum.trim();
    }

    /**
     * qq号码
     * @return QQNUM qq号码
     */
    public String getQqnum() {
        return qqnum;
    }

    /**
     * qq号码
     * @param qqnum qq号码
     */
    public void setQqnum(String qqnum) {
        this.qqnum = qqnum == null ? null : qqnum.trim();
    }

    /**
     * 电子邮箱
     * @return EMAIL 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 毕业学校
     * @return SCHOOL 毕业学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 毕业学校
     * @param school 毕业学校
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * 专业
     * @return MAJOR 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 专业
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * 教育背景C_EDUCATIONALBACKGROUND
     * @return EDUBACKGROUND 教育背景C_EDUCATIONALBACKGROUND
     */
    public Integer getEdubackground() {
        return edubackground;
    }

    /**
     * 教育背景C_EDUCATIONALBACKGROUND
     * @param edubackground 教育背景C_EDUCATIONALBACKGROUND
     */
    public void setEdubackground(Integer edubackground) {
        this.edubackground = edubackground;
    }

    /**
     * 所在银行C_BANK
     * @return BANKID 所在银行C_BANK
     */
    public String getBankid() {
        return bankid;
    }

    /**
     * 所在银行C_BANK
     * @param bankid 所在银行C_BANK
     */
    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    /**
     * 所在银行卡号C_BANKCARD
     * @return BANKCARD 所在银行卡号C_BANKCARD
     */
    public String getBankcard() {
        return bankcard;
    }

    /**
     * 所在银行卡号C_BANKCARD
     * @param bankcard 所在银行卡号C_BANKCARD
     */
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard == null ? null : bankcard.trim();
    }

    /**
     * POSTID邮政编码
     * @return POSTID POSTID邮政编码
     */
    public Integer getPostid() {
        return postid;
    }

    /**
     * POSTID邮政编码
     * @param postid POSTID邮政编码
     */
    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    /**
     * C_POSTSTATE
     * @return POSE C_POSTSTATE
     */
    public Integer getPose() {
        return pose;
    }

    /**
     * C_POSTSTATE
     * @param pose C_POSTSTATE
     */
    public void setPose(Integer pose) {
        this.pose = pose;
    }

    /**
     * C_TECHNICALTITLE
     * @return TECHNICALTITLE C_TECHNICALTITLE
     */
    public String getTechnicaltitle() {
        return technicaltitle;
    }

    /**
     * C_TECHNICALTITLE
     * @param technicaltitle C_TECHNICALTITLE
     */
    public void setTechnicaltitle(String technicaltitle) {
        this.technicaltitle = technicaltitle == null ? null : technicaltitle.trim();
    }

    /**
     * 外语种类C_FOREIGNLANGUAGE
     * @return FOREIGNLANGUAGE 外语种类C_FOREIGNLANGUAGE
     */
    public String getForeignlanguage() {
        return foreignlanguage;
    }

    /**
     * 外语种类C_FOREIGNLANGUAGE
     * @param foreignlanguage 外语种类C_FOREIGNLANGUAGE
     */
    public void setForeignlanguage(String foreignlanguage) {
        this.foreignlanguage = foreignlanguage == null ? null : foreignlanguage.trim();
    }

    /**
     * 外语等级C_FORIEGNLANGUAGELEVEL
     * @return FOREIGNLANGUAGELEVEL 外语等级C_FORIEGNLANGUAGELEVEL
     */
    public String getForeignlanguagelevel() {
        return foreignlanguagelevel;
    }

    /**
     * 外语等级C_FORIEGNLANGUAGELEVEL
     * @param foreignlanguagelevel 外语等级C_FORIEGNLANGUAGELEVEL
     */
    public void setForeignlanguagelevel(String foreignlanguagelevel) {
        this.foreignlanguagelevel = foreignlanguagelevel == null ? null : foreignlanguagelevel.trim();
    }

    /**
     * 计算机等级C_COMPUTERLEVEL
     * @return COMPUTERLEVEL 计算机等级C_COMPUTERLEVEL
     */
    public String getComputerlevel() {
        return computerlevel;
    }

    /**
     * 计算机等级C_COMPUTERLEVEL
     * @param computerlevel 计算机等级C_COMPUTERLEVEL
     */
    public void setComputerlevel(String computerlevel) {
        this.computerlevel = computerlevel == null ? null : computerlevel.trim();
    }

    /**
     * C_GOODQUALITIES特长
     * @return SPECIALTY C_GOODQUALITIES特长
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * C_GOODQUALITIES特长
     * @param specialty C_GOODQUALITIES特长
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    /**
     * 参加工作时间C_JOINWORKTIME
     * @return JOINWORKDATE 参加工作时间C_JOINWORKTIME
     */
    public String getJoinworkdate() {
        return joinworkdate;
    }

    /**
     * 参加工作时间C_JOINWORKTIME
     * @param joinworkdate 参加工作时间C_JOINWORKTIME
     */
    public void setJoinworkdate(String joinworkdate) {
        this.joinworkdate = joinworkdate == null ? null : joinworkdate.trim();
    }

    /**
     * C_JOINUNITTIME单位入职时间
     * @return JOINUNITDATE C_JOINUNITTIME单位入职时间
     */
    public String getJoinunitdate() {
        return joinunitdate;
    }

    /**
     * C_JOINUNITTIME单位入职时间
     * @param joinunitdate C_JOINUNITTIME单位入职时间
     */
    public void setJoinunitdate(String joinunitdate) {
        this.joinunitdate = joinunitdate == null ? null : joinunitdate.trim();
    }

    /**
     * C_BARGAINBEGINTIME签订合同时间
     * @return BGBEGDATE C_BARGAINBEGINTIME签订合同时间
     */
    public String getBgbegdate() {
        return bgbegdate;
    }

    /**
     * C_BARGAINBEGINTIME签订合同时间
     * @param bgbegdate C_BARGAINBEGINTIME签订合同时间
     */
    public void setBgbegdate(String bgbegdate) {
        this.bgbegdate = bgbegdate == null ? null : bgbegdate.trim();
    }

    /**
     * C_BARGAINENDTIME合同终止时间
     * @return BGENDDATE C_BARGAINENDTIME合同终止时间
     */
    public String getBgenddate() {
        return bgenddate;
    }

    /**
     * C_BARGAINENDTIME合同终止时间
     * @param bgenddate C_BARGAINENDTIME合同终止时间
     */
    public void setBgenddate(String bgenddate) {
        this.bgenddate = bgenddate == null ? null : bgenddate.trim();
    }

    /**
     * C_DIMISIONTIME离职日期
     * @return DIMISSIONDATE C_DIMISIONTIME离职日期
     */
    public String getDimissiondate() {
        return dimissiondate;
    }

    /**
     * C_DIMISIONTIME离职日期
     * @param dimissiondate C_DIMISIONTIME离职日期
     */
    public void setDimissiondate(String dimissiondate) {
        this.dimissiondate = dimissiondate == null ? null : dimissiondate.trim();
    }

    /**
     * 相片名称
     * @return PHOTONAME 相片名称
     */
    public String getPhotoname() {
        return photoname;
    }

    /**
     * 相片名称
     * @param photoname 相片名称
     */
    public void setPhotoname(String photoname) {
        this.photoname = photoname == null ? null : photoname.trim();
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
     * 记录时间
     * @return CREATEDATE 记录时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 记录时间
     * @param createdate 记录时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * SYS备注信息
     * @return SYSREMARKS SYS备注信息
     */
    public String getSysremarks() {
        return sysremarks;
    }

    /**
     * SYS备注信息
     * @param sysremarks SYS备注信息
     */
    public void setSysremarks(String sysremarks) {
        this.sysremarks = sysremarks == null ? null : sysremarks.trim();
    }

    /**
     * GROUP备注信息
     * @return GROUPREMARKS GROUP备注信息
     */
    public Short getGroupremarks() {
        return groupremarks;
    }

    /**
     * GROUP备注信息
     * @param groupremarks GROUP备注信息
     */
    public void setGroupremarks(Short groupremarks) {
        this.groupremarks = groupremarks;
    }

    /**
     * 10内部用户  20外部用户  30虚拟用户  
     * @return USERTYPE 10内部用户  20外部用户  30虚拟用户  
     */
    public Short getUsertype() {
        return usertype;
    }

    /**
     * 10内部用户  20外部用户  30虚拟用户  
     * @param usertype 10内部用户  20外部用户  30虚拟用户  
     */
    public void setUsertype(Short usertype) {
        this.usertype = usertype;
    }

    /**
     * 公司邮箱
     * @return COMPANYEMAIL 公司邮箱
     */
    public String getCompanyemail() {
        return companyemail;
    }

    /**
     * 公司邮箱
     * @param companyemail 公司邮箱
     */
    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail == null ? null : companyemail.trim();
    }

    /**
     * 用户密码
     * @return USERPSW 用户密码
     */
    public String getUserpsw() {
        return userpsw;
    }

    /**
     * 用户密码
     * @param userpsw 用户密码
     */
    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw == null ? null : userpsw.trim();
    }

    /**
     * 用户岗位
     * @return USERPOSTID 用户岗位
     */
    public Integer getUserpostid() {
        return userpostid;
    }

    /**
     * 用户岗位
     * @param userpostid 用户岗位
     */
    public void setUserpostid(Integer userpostid) {
        this.userpostid = userpostid;
    }

    /**
     * 专业名称
     * @return MAJORNAME 专业名称
     */
    public String getMajorname() {
        return majorname;
    }

    /**
     * 专业名称
     * @param majorname 专业名称
     */
    public void setMajorname(String majorname) {
        this.majorname = majorname == null ? null : majorname.trim();
    }

    /**
     * 在职状态（10在职、0离职、20退休）
     * @return WORKSTATUS 在职状态（10在职、0离职、20退休）
     */
    public String getWorkstatus() {
        return workstatus;
    }

    /**
     * 在职状态（10在职、0离职、20退休）
     * @param workstatus 在职状态（10在职、0离职、20退休）
     */
    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus == null ? null : workstatus.trim();
    }

    /**
     * 是否有资格认证(1：有 0：无） 手机程序启用后修改为：手机短码字段 20140328修改
     * @return SHORTTELNUM 是否有资格认证(1：有 0：无） 手机程序启用后修改为：手机短码字段 20140328修改
     */
    public BigDecimal getShorttelnum() {
        return shorttelnum;
    }

    /**
     * 是否有资格认证(1：有 0：无） 手机程序启用后修改为：手机短码字段 20140328修改
     * @param shorttelnum 是否有资格认证(1：有 0：无） 手机程序启用后修改为：手机短码字段 20140328修改
     */
    public void setShorttelnum(BigDecimal shorttelnum) {
        this.shorttelnum = shorttelnum;
    }

    /**
     * 民族
     * @return NATION 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 民族
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 学历（博士、硕士、大学本科、大学专科、高中、其他）
     * @return EDUCATION 学历（博士、硕士、大学本科、大学专科、高中、其他）
     */
    public String getEducation() {
        return education;
    }

    /**
     * 学历（博士、硕士、大学本科、大学专科、高中、其他）
     * @param education 学历（博士、硕士、大学本科、大学专科、高中、其他）
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 职称
     * @return TITLE 职称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 职称
     * @param title 职称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 本单位工龄
     * @return SENIORITY 本单位工龄
     */
    public Short getSeniority() {
        return seniority;
    }

    /**
     * 本单位工龄
     * @param seniority 本单位工龄
     */
    public void setSeniority(Short seniority) {
        this.seniority = seniority;
    }

    /**
     * 离职时间
     * @return LEAVEDATE 离职时间
     */
    public String getLeavedate() {
        return leavedate;
    }

    /**
     * 离职时间
     * @param leavedate 离职时间
     */
    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate == null ? null : leavedate.trim();
    }

    /**
     * 工作工龄
     * @return SELFSENIORITY 工作工龄
     */
    public Short getSelfseniority() {
        return selfseniority;
    }

    /**
     * 工作工龄
     * @param selfseniority 工作工龄
     */
    public void setSelfseniority(Short selfseniority) {
        this.selfseniority = selfseniority;
    }

    /**
     * 签名图片(格式为:signatureimg/图片名)
     * @return SIGNATUREIMG 签名图片(格式为:signatureimg/图片名)
     */
    public String getSignatureimg() {
        return signatureimg;
    }

    /**
     * 签名图片(格式为:signatureimg/图片名)
     * @param signatureimg 签名图片(格式为:signatureimg/图片名)
     */
    public void setSignatureimg(String signatureimg) {
        this.signatureimg = signatureimg == null ? null : signatureimg.trim();
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

	public static String getUSER_DETAIL_SESSION_KEY() {
		return USER_DETAIL_SESSION_KEY;
	}

	public static void setUSER_DETAIL_SESSION_KEY(String uSER_DETAIL_SESSION_KEY) {
		USER_DETAIL_SESSION_KEY = uSER_DETAIL_SESSION_KEY;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		
		this.roles = roles;
	}

	public Collection<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<String> authorities) {
		this.authorities = authorities;
	}

	public String getOpList() {
		return opList;
	}

	public void setOpList(String opList) {
		this.opList = opList;
	}

	
}