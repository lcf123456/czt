package com.ztel.app.vo.sq;

public class VoiceparaVo {
    /**
     * null
     */
    private Long id;

    /**
     * 参数名
     */
    private String paraname;

    /**
     * 参数内容
     */
    private String paracontent;

    /**
     * 参数说明
     */
    private String pararemarks;

    /**
     * null
     * @return ID null
     */
    public Long getId() {
        return id;
    }

    /**
     * null
     * @param id null
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 参数名
     * @return PARANAME 参数名
     */
    public String getParaname() {
        return paraname;
    }

    /**
     * 参数名
     * @param paraname 参数名
     */
    public void setParaname(String paraname) {
        this.paraname = paraname == null ? null : paraname.trim();
    }

    /**
     * 参数内容
     * @return PARACONTENT 参数内容
     */
    public String getParacontent() {
        return paracontent;
    }

    /**
     * 参数内容
     * @param paracontent 参数内容
     */
    public void setParacontent(String paracontent) {
        this.paracontent = paracontent == null ? null : paracontent.trim();
    }

    /**
     * 参数说明
     * @return PARAREMARKS 参数说明
     */
    public String getPararemarks() {
        return pararemarks;
    }

    /**
     * 参数说明
     * @param pararemarks 参数说明
     */
    public void setPararemarks(String pararemarks) {
        this.pararemarks = pararemarks == null ? null : pararemarks.trim();
    }
}