/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.framework.vo;

/**
 * @author Zeal
 * @since 2016年5月6日
 */
public class ErrorCode {
	
	/** Error code key in request,session or application */
	public static final String ERROR_CODE = "errorCode";
	
	/** Key prefix in message source */
	public static final String KEY_PREFIX = "err";
	
	/** Success */
	public static final int NO_ERROR = 0;
	
	/** Unknown error */
	public static final int UNKNOWN_ERROR = 1;
	
	/** Wrong token */
	public static final int INVALID_SESSION_TOKEN = 397;
	
	/** Session is invalid */
	public static final int INVALID_SESSION = 398;
	
	/** Not allowed to access */
	public static final int ACCESS_DENIED = 399;

}
