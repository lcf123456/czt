package com.ztel.app.vo.safe;

import java.util.Date;

public class FireSystemVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * 代号
     */
    private String checkcode;

    /**
     * 名称
     */
    private String checkname;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 删除标识 10:正常  0:删除
     */
    private String delstatus;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 代号
     * @return CHECKCODE 代号
     */
    public String getCheckcode() {
        return checkcode;
    }

    /**
     * 代号
     * @param checkcode 代号
     */
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode == null ? null : checkcode.trim();
    }

    /**
     * 名称
     * @return CHECKNAME 名称
     */
    public String getCheckname() {
        return checkname;
    }

    /**
     * 名称
     * @param checkname 名称
     */
    public void setCheckname(String checkname) {
        this.checkname = checkname == null ? null : checkname.trim();
    }

    /**
     * 创建时间
     * @return CREATETIME 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 删除标识 10:正常  0:删除
     * @return DELSTATUS 删除标识 10:正常  0:删除
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标识 10:正常  0:删除
     * @param delstatus 删除标识 10:正常  0:删除
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
}