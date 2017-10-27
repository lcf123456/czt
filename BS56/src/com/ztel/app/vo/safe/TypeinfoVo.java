package com.ztel.app.vo.safe;

public class TypeinfoVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String ctype;

    /**
     * 类别标志(10：事件隐患类别  20：安全事故时间段  30：安全事故地点  40：安全事故气候 50:危险源类别    60:风险控制措施     70:安全值日值班岗位  80:安全检查地点    90:安全检查内容 )
     */
    private String flag;

    /**
     * 删除标识 10:正常 0:删除
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
     * 类别名称
     * @return CTYPE 类别名称
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * 类别名称
     * @param ctype 类别名称
     */
    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    /**
     * 类别标志(10：事件隐患类别  20：安全事故时间段  30：安全事故地点  40：安全事故气候 50:危险源类别    60:风险控制措施     70:安全值日值班岗位  80:安全检查地点    90:安全检查内容 )
     * @return FLAG 类别标志(10：事件隐患类别  20：安全事故时间段  30：安全事故地点  40：安全事故气候 50:危险源类别    60:风险控制措施     70:安全值日值班岗位  80:安全检查地点    90:安全检查内容 )
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 类别标志(10：事件隐患类别  20：安全事故时间段  30：安全事故地点  40：安全事故气候 50:危险源类别    60:风险控制措施     70:安全值日值班岗位  80:安全检查地点    90:安全检查内容 )
     * @param flag 类别标志(10：事件隐患类别  20：安全事故时间段  30：安全事故地点  40：安全事故气候 50:危险源类别    60:风险控制措施     70:安全值日值班岗位  80:安全检查地点    90:安全检查内容 )
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * 删除标识 10:正常 0:删除
     * @return DELSTATUS 删除标识 10:正常 0:删除
     */
    public String getDelstatus() {
        return delstatus;
    }

    /**
     * 删除标识 10:正常 0:删除
     * @param delstatus 删除标识 10:正常 0:删除
     */
    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus == null ? null : delstatus.trim();
    }
}