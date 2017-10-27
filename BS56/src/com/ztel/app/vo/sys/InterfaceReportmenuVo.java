package com.ztel.app.vo.sys;

public class InterfaceReportmenuVo {
    /**
     * id序号
     */
    private Short id;

    /**
     * 报表名称
     */
    private String reportname;

    /**
     * url
     */
    private String url;

    /**
     * 报表级次
     */
    private String menulevel;

    /**
     * 上级id
     */
    private Short fid;

    /**
     * 排序
     */
    private Short seq;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 标志(10:可自主选择;20:不可自主选择)
     */
    private String flag;

    /**
     * id序号
     * @return ID id序号
     */
    public Short getId() {
        return id;
    }

    /**
     * id序号
     * @param id id序号
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * 报表名称
     * @return REPORTNAME 报表名称
     */
    public String getReportname() {
        return reportname;
    }

    /**
     * 报表名称
     * @param reportname 报表名称
     */
    public void setReportname(String reportname) {
        this.reportname = reportname == null ? null : reportname.trim();
    }

    /**
     * url
     * @return URL url
     */
    public String getUrl() {
        return url;
    }

    /**
     * url
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 报表级次
     * @return MENULEVEL 报表级次
     */
    public String getMenulevel() {
        return menulevel;
    }

    /**
     * 报表级次
     * @param menulevel 报表级次
     */
    public void setMenulevel(String menulevel) {
        this.menulevel = menulevel == null ? null : menulevel.trim();
    }

    /**
     * 上级id
     * @return FID 上级id
     */
    public Short getFid() {
        return fid;
    }

    /**
     * 上级id
     * @param fid 上级id
     */
    public void setFid(Short fid) {
        this.fid = fid;
    }

    /**
     * 排序
     * @return SEQ 排序
     */
    public Short getSeq() {
        return seq;
    }

    /**
     * 排序
     * @param seq 排序
     */
    public void setSeq(Short seq) {
        this.seq = seq;
    }

    /**
     * 备注
     * @return REMARKS 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 标志(10:可自主选择;20:不可自主选择)
     * @return FLAG 标志(10:可自主选择;20:不可自主选择)
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 标志(10:可自主选择;20:不可自主选择)
     * @param flag 标志(10:可自主选择;20:不可自主选择)
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}