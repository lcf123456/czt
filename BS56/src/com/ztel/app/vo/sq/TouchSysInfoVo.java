package com.ztel.app.vo.sq;

import java.util.Date;

public class TouchSysInfoVo {
    /**
     * 序号ID
     */
    private Long id;

    /**
     * 发布人
     */
    private Long publishid;

    /**
     * 发布日期
     */
    private Date publishdate;

    /**
     * "开始日期 "
     */
    private Date begdata;

    /**
     * 结束日期
     */
    private Date enddata;

    /**
     * 状态（10，显示；20，不显示）
     */
    private String status;

    /**
     * 显示方式
     */
    private String display;

    /**
     * 发布位置
     */
    private String location;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 发布人
     * @return PUBLISHID 发布人
     */
    public Long getPublishid() {
        return publishid;
    }

    /**
     * 发布人
     * @param publishid 发布人
     */
    public void setPublishid(Long publishid) {
        this.publishid = publishid;
    }

    /**
     * 发布日期
     * @return PUBLISHDATE 发布日期
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * 发布日期
     * @param publishdate 发布日期
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * "开始日期 "
     * @return BEGDATA "开始日期 "
     */
    public Date getBegdata() {
        return begdata;
    }

    /**
     * "开始日期 "
     * @param begdata "开始日期 "
     */
    public void setBegdata(Date begdata) {
        this.begdata = begdata;
    }

    /**
     * 结束日期
     * @return ENDDATA 结束日期
     */
    public Date getEnddata() {
        return enddata;
    }

    /**
     * 结束日期
     * @param enddata 结束日期
     */
    public void setEnddata(Date enddata) {
        this.enddata = enddata;
    }

    /**
     * 状态（10，显示；20，不显示）
     * @return STATUS 状态（10，显示；20，不显示）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态（10，显示；20，不显示）
     * @param status 状态（10，显示；20，不显示）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 显示方式
     * @return DISPLAY 显示方式
     */
    public String getDisplay() {
        return display;
    }

    /**
     * 显示方式
     * @param display 显示方式
     */
    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    /**
     * 发布位置
     * @return LOCATION 发布位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 发布位置
     * @param location 发布位置
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 标题
     * @return TITLE 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 内容
     * @return CONTENT 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}