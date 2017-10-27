/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.vo;


/**
 * @author lcf
 * @since 2017年2月27日
 */
public class DeviceType {
	
	private int tId = 0;
	private String tName = null;
	/**
	 * @return the tId
	 */
	public int gettId() {
		return tId;
	}
	/**
	 * @param tId the tId to set
	 */
	public void settId(int tId) {
		this.tId = tId;
	}
	/**
	 * @return the tName
	 */
	public String gettName() {
		return tName;
	}
	/**
	 * @param tName the tName to set
	 */
	public void settName(String tName) {
		this.tName = tName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceType [tId=" + tId + ", tName=" + tName + "]";
	}
	
	
}
