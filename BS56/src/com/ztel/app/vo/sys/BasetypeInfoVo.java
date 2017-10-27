package com.ztel.app.vo.sys;

import java.math.BigDecimal;

public class BasetypeInfoVo {
    /**
     * null
     */
    private BigDecimal id;

    /**
     * 类别代码
     */
    private String typecode;

    /**
     * 类别名称
     */
    private String typename;

    /**
     * 内容
     */
    private String contentlist;

    /**
     * 预留字段1
     */
    private String bak1;

    /**
     * 预留字段1
     */
    private String bak2;

    /**
     * null
     */
    private String remarks;

    /**
     * 类型标识  10:对外可维护  20:不开放维护
     */
    private String typeflag;

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
     * 类别代码
     * @return TYPECODE 类别代码
     */
    public String getTypecode() {
        return typecode;
    }

    /**
     * 类别代码
     * @param typecode 类别代码
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /**
     * 类别名称
     * @return TYPENAME 类别名称
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 类别名称
     * @param typename 类别名称
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * 内容
     * @return CONTENTLIST 内容
     */
    public String getContentlist() {
        return contentlist;
    }

    /**
     * 内容
     * @param contentlist 内容
     */
    public void setContentlist(String contentlist) {
        this.contentlist = contentlist == null ? null : contentlist.trim();
    }

    /**
     * 预留字段1
     * @return BAK1 预留字段1
     */
    public String getBak1() {
        return bak1;
    }

    /**
     * 预留字段1
     * @param bak1 预留字段1
     */
    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    /**
     * 预留字段1
     * @return BAK2 预留字段1
     */
    public String getBak2() {
        return bak2;
    }

    /**
     * 预留字段1
     * @param bak2 预留字段1
     */
    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }

    /**
     * null
     * @return REMARKS null
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * null
     * @param remarks null
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 类型标识  10:对外可维护  20:不开放维护
     * @return TYPEFLAG 类型标识  10:对外可维护  20:不开放维护
     */
    public String getTypeflag() {
        return typeflag;
    }

    /**
     * 类型标识  10:对外可维护  20:不开放维护
     * @param typeflag 类型标识  10:对外可维护  20:不开放维护
     */
    public void setTypeflag(String typeflag) {
        this.typeflag = typeflag == null ? null : typeflag.trim();
    }
}