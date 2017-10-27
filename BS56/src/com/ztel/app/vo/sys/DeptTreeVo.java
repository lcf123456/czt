package com.ztel.app.vo.sys;

import java.io.Serializable;
import java.util.List;

/**
 * 用户选择时需要部门树
 * @author lcf
 *
 */
public class DeptTreeVo implements Serializable{
	private static final long serialVersionUID = -8032736954376548826L;
    private Integer id;//节点的id值  
    private String text;//节点显示的名称
    
    private String state;//节点的状态
    private List<DeptTreeVo> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<DeptTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<DeptTreeVo> children) {
		this.children = children;
	}
    
    
}
