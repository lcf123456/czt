package com.ztel.app.vo.sys;

public class InterfaceGenmanagerVo {
    /**
     * 序号id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 工作界面id
     */
    private Integer workid;

    /**
     * 序号id
     * @return ID 序号id
     */
    public Long getId() {
        return id;
    }

    /**
     * 序号id
     * @param id 序号id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return USERID 用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 用户id
     * @param userid 用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 工作界面id
     * @return WORKID 工作界面id
     */
    public Integer getWorkid() {
        return workid;
    }

    /**
     * 工作界面id
     * @param workid 工作界面id
     */
    public void setWorkid(Integer workid) {
        this.workid = workid;
    }
}