package com.ztel.app.vo.sys;

import java.util.Date;

public class OperationlogVo {
    /**
     * 操作日志id
     */
    private Integer id;

    /**
     * 操作用户id
     */
    private Long userid;

    /**
     * 操作时间
     */
    private Date operationdate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 操作链接
     */
    private String url;

    /**
     * 操作菜单
     */
    private String menu;

    /**
     * 操作点 (新增、修改、删除等)
     */
    private String point;

    /**
     * 操作用户姓名
     */
    private String username;
    
    private String keyword;

    /**
     * 操作日志id
     * @return ID 操作日志id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 操作日志id
     * @param id 操作日志id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 操作用户id
     * @return USERID 操作用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 操作用户id
     * @param userid 操作用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 操作时间
     * @return OPERATIONDATE 操作时间
     */
    public Date getOperationdate() {
        return operationdate;
    }

    /**
     * 操作时间
     * @param operationdate 操作时间
     */
    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    /**
     * 备注信息
     * @return REMARKS 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注信息
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 操作链接
     * @return URL 操作链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 操作链接
     * @param url 操作链接
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 操作菜单
     * @return MENU 操作菜单
     */
    public String getMenu() {
        return menu;
    }

    /**
     * 操作菜单
     * @param menu 操作菜单
     */
    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    /**
     * 操作点 (新增、修改、删除等)
     * @return POINT 操作点 (新增、修改、删除等)
     */
    public String getPoint() {
        return point;
    }

    /**
     * 操作点 (新增、修改、删除等)
     * @param point 操作点 (新增、修改、删除等)
     */
    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    /**
     * 操作用户姓名
     * @return USERNAME 操作用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 操作用户姓名
     * @param username 操作用户姓名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}