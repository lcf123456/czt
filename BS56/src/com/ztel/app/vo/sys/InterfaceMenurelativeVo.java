package com.ztel.app.vo.sys;

public class InterfaceMenurelativeVo {
    /**
     * id序号
     */
    private Integer id;

    /**
     * 人员id
     */
    private Long userid;

    /**
     * 报表菜单id
     */
    private Short menuid;

    /**
     * id序号
     * @return ID id序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * id序号
     * @param id id序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 人员id
     * @return USERID 人员id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 人员id
     * @param userid 人员id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 报表菜单id
     * @return MENUID 报表菜单id
     */
    public Short getMenuid() {
        return menuid;
    }

    /**
     * 报表菜单id
     * @param menuid 报表菜单id
     */
    public void setMenuid(Short menuid) {
        this.menuid = menuid;
    }
}