/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.framework.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ztel.framework.util.NumberUtils;
import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.ErrorCode;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.vo.Result;
import com.ztel.framework.web.util.DataGridModel;
import com.ztel.framework.web.util.WebUtils;

/**
 * Extract some common functions of message resource,pagination,json
 * @author Zeal
 * @since 2016年4月27日
 */
public abstract class BaseCtrl {
	
	@Autowired
	protected MessageSource messageSource;
	//private Map<String,String> sortKeyColumnMapping = new HashMap<>();
	/**
	 * Get message by key from resource
	 * @param key
	 * @return
	 */
	protected String getMessage(String key) {
		try {
		    return this.messageSource.getMessage(key, null, null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}
	
	/**
	 * The error message's key is started with 'err'
	 * @param errorCode
	 * @return
	 */
	protected String getErrorMessage(int errorCode) {
		String key = ErrorCode.KEY_PREFIX + errorCode;
		try {
		    return this.messageSource.getMessage(key, null, null);
		}
		catch (Exception e) {
			return getDefaultErrorMessage(errorCode);
		}
	}
	
	/**
	 * Create default error message if errors do not exist in message source
	 * @param errorCode
	 * @return
	 */
	private String getDefaultErrorMessage(int errorCode) {
		//Avoid web application missing these public error code defined in ErrorCode
		if (errorCode == ErrorCode.NO_ERROR) {
			return "Operation is succesful";
		}
		else if (errorCode == ErrorCode.UNKNOWN_ERROR) {
			return "Unknown error";
		}
		else if (errorCode == ErrorCode.INVALID_SESSION) {
			return "User session is expired or invalid";
		}
		else if (errorCode == ErrorCode.INVALID_SESSION_TOKEN) {
			return "Request token is invalid";
		}
		else {
			return "error code is " + errorCode;
		}
	}
	
	/**
	 * The new version of pagination object
	 * @param request
	 * @return
	 */
	protected Pagination<?> getPagination(HttpServletRequest request) {
		return getPagination(request, true);
	}

	
	/**
	 * The new version of pagination object
	 * @param request
	 * @return
	 */
	protected Pagination<?> getPagination(HttpServletRequest request, boolean generateDefault) {
		if (!this.isPaginationRequest(request)) {
			return generateDefault ? new Pagination<Object>() : null;
		}
		Pagination<?> page = new Pagination<Object>();
		String pageNoStr = request.getParameter(Pagination.PAGE_NO);
		int pageNo = 1;
		if(StringUtils.isNotBlank(pageNoStr)){
			pageNo = NumberUtils.toInt(pageNoStr, 1);
			if (pageNo <= 0) {
				pageNo = 1;
			}
		}
		String pageSizeStr = request.getParameter(Pagination.PAGE_SIZE);
		int pageSize = Pagination.DEFALUT_PAGE_SIZE;
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = NumberUtils.toInt(pageSizeStr, Pagination.DEFALUT_PAGE_SIZE);
			if (pageSize > Pagination.MAX_PAGE_SIZE) {
				pageSize = Pagination.MAX_PAGE_SIZE;
			}
			if (pageSize <= 0) {
				pageSize = Pagination.DEFALUT_PAGE_SIZE;
			}
		}
		//Notice, never use it directly in your sql since it's not filtered
		String sortKey = request.getParameter(Pagination.SORT_KEY);
		String sortAscStr = request.getParameter(Pagination.SORT);
		boolean sortAsc = false;
		if(StringUtils.isNotBlank(sortAscStr)){
			sortAsc = Boolean.valueOf(sortAscStr.equals("asc"));
		}
		page.setPageNum(pageNo);
		page.setNumPerPage(pageSize);
		page.setSortKey(sortKey);
		page.setSortAsc(sortAsc);		
		page.setParamMap(WebUtils.getRequestParameterSingleMap(request, true));
		page.setUri(WebUtils.getRequestURI(request));
		return page;
	}
	
	/**
	 * Check whether it's pagination request
	 * @param request
	 * @return
	 */
	private boolean isPaginationRequest(HttpServletRequest request) {
		String pageNoStr = request.getParameter(Pagination.PAGE_NO);
		if (StringUtils.isBlank(pageNoStr)) {
			return false;
		}
		int pageNo = NumberUtils.toInt(pageNoStr, 0);
		return pageNo > 0;
	}
	
	/**
	 * Get result 
	 * @param errorCode
	 * @return
	 */
	protected <T>Result<T> getResult(int errorCode) {
		return getResult(errorCode, this.getErrorMessage(errorCode));
	}
	
	/**
	 * Get result 
	 * @param errorCode
	 * @return
	 */
	protected <T>Result<T> getResult(int errorCode, String resultMessage) {
		Result<T> result = new Result<>(errorCode);
		result.setResultMessage(resultMessage);
		return result;
	}
	
	/**
	 * Get result 
	 * @param errorCode
	 * @return
	 */
	protected <T>Result<T> getResult(int errorCode, String resultMessage, T resultEntity) {
		Result<T> result = new Result<>(errorCode);
		result.setResultMessage(resultMessage);
		result.setResultEntity(resultEntity);
		return result;
	}
	
	/**
	 * Get result 
	 * @param errorCode
	 * @return
	 */
	protected <T>Result<T> getResult(int errorCode, T resultEntity) {
		Result<T> result = new Result<>(errorCode);
		result.setResultMessage(this.getErrorMessage(errorCode));
		result.setResultEntity(resultEntity);
		return result;
	}
	
	
	/**
	 * Extract standard result from bean validator binding result
	 * @param result
	 * @return
	 */
	protected <T>Result<T> getBindingResult(BindingResult result) {
		List<ObjectError> errors = result.getAllErrors();
		//Assume operation is successful if no error
		if (errors == null || errors.size() <= 0) {
			return this.getResult(ErrorCode.NO_ERROR);
		}
		ObjectError error = errors.get(0);
		String message = error.getDefaultMessage();
		if (StringUtils.isEmpty(message)) {
			return this.getResult(ErrorCode.UNKNOWN_ERROR);
		}
		if (message.startsWith(ErrorCode.KEY_PREFIX)) {
			String errorCodeStr = message.substring(ErrorCode.KEY_PREFIX.length());
			int errorCode = 0;
			try {
			    errorCode = Integer.parseInt(errorCodeStr);
			    return this.getResult(errorCode, this.getMessage(message));
			}
			catch (Exception e) {
				return this.getResult(ErrorCode.UNKNOWN_ERROR, this.getMessage(message));
			}
		}
		else {
			return this.getResult(ErrorCode.UNKNOWN_ERROR, this.getMessage(message));
		}
	}
	
	/**
	 * Extract standard result object into request attribute
	 * @param request
	 * @param bindingResult
	 */
	protected <T> void setBindingResultAttribute(HttpServletRequest request, BindingResult bindingResult) {
		Result<T> result = this.getBindingResult(bindingResult);
		request.setAttribute(Result.RESULT_KEY, result);
	}
	
//	/**
//	 * Default binder
//	 * @param binder
//	 */
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		if (enableXssFilter()) {
//	        binder.registerCustomEditor(String.class, new StringEscapeEditor());
//		}
//	}
//	
//	/**
//	 * Enable xss filter or not
//	 * @return
//	 */
//	protected boolean enableXssFilter() {
//		return true;
//	}
	
	/**
	 * Set pagination into request attribute
	 * @param request
	 * @param page
	 */
	protected void setPagination(HttpServletRequest request, Pagination<?> page) {
		request.setAttribute(Pagination.ATTR_KEY, page);
	}
//
//	public  Map<String, Object> listToMap(List<Object>  obj,String total)
//	{
//		 Map<String, Object> result=new HashMap<String, Object>();  
//		 result.put("rows",obj);  
//		 result.put("total",total);  
//		 
//		 return result;
//	}

}
