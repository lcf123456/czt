/*
 * Copyright (c) 2015, All rights reserved.
 */
package com.ztel.framework.web.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UrlPathHelper;

import com.ztel.framework.util.StringEscapeUtils;
import com.ztel.framework.util.StringUtils;

/**
 * Web utility 
 * @author Zeal
 * @since 2015年12月24日
 */
public class WebUtils extends org.springframework.web.util.WebUtils {
	
	/** URL helper */
	private static UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	/**
	 * Check whether it's ajax request, actually it only supports jquery
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")); 
	}
	
	/**
	 * Get request path, concat the request path with server ip,port,jsessionid..
	 * @param request
	 * @param requestPath
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request, String requestPath) {
		return getRequestPath(request, requestPath, false, false);
	}
	
	/**
	 * Get request path, concat the request path with server ip,port,jsessionid..
	 * @param request
	 * @param requestPath
	 * @param completedURL
	 * @param withSessionId
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request,
		String requestPath, boolean completedURL, boolean withSessionId) {
		
		String contextPath = request.getContextPath();
		String serverPrefix = "";
		if (completedURL) {
			serverPrefix = request.getScheme() + "://" + request.getServerName();
			if ("http".equalsIgnoreCase(request.getScheme()) && request.getServerPort() == 80) {
			} 
			else if ("https".equalsIgnoreCase(request.getScheme())
					&& request.getServerPort() == 843) {
			} 
			else {
				serverPrefix += (":" + request.getServerPort());
			}
		}
		String url = null;
		if (requestPath == null || requestPath.length() <= 0) {
			url = serverPrefix + contextPath;
		} 
		else if (requestPath.charAt(0) == '/') {
			url = serverPrefix + contextPath + requestPath;
		} 
		else {
			url = serverPrefix + contextPath + '/' + requestPath;
		}

		if (withSessionId) {
			url += (";jsessionid=" + request.getSession(true).getId());
		}
		return url;
	}
	
	/**
	 * 获取远程用户IP
	 * @param request
	 * @return
	 */
	public static String getRemoteClientIp(HttpServletRequest request) {
		String [] ipHeaders = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
		String ip = null;
		for (int i = 0; i < ipHeaders.length;) {
			ip = request.getHeader(ipHeaders[i]);
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				++i;
				continue;
			}
			else {
				break;
			}
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			return request.getRemoteAddr();
		}
		else {
			int index = ip.indexOf(',');
			if (index > 0) {
				String[] values = StringUtils.split(ip, ',');
				return values[0].trim();
			}
			else {
			    return ip;
			}
		}
	}
	
	/**
	 * Get request parameter and escape to defense xss
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getRequestParameter(HttpServletRequest request, String name) {
		return getRequestParameter(request, name, true);
	}
	
	/**
	 * Get request parameter and escape to defense xss
	 * @param request
	 * @param name
	 * @param escapeHtml
	 * @return
	 */
	public static String getRequestParameter(HttpServletRequest request, String name, boolean escapeHtml) {
		String value = request.getParameter(name);
		if (!escapeHtml || StringUtils.isEmpty(value)) {
			return value;
		}
		else {
			return StringEscapeUtils.escapeHtml4(value);
		}
	}
	
	/**
	 * Get request parameters and escape html
	 * @param request
	 * @param name
	 * @param escapeHtml
	 * @return
	 */
	public static String[] getRequestParameters(HttpServletRequest request, String name) {
		return getRequestParameters(request, name, true);
	}
	
	/**
	 * Get request parameters and escape html
	 * @param request
	 * @param name
	 * @param escapeHtml
	 * @return
	 */
	public static String[] getRequestParameters(HttpServletRequest request, String name, boolean escapeHtml) {
		String[] values = request.getParameterValues(name);
		if (values == null || values.length <= 0 || !escapeHtml) {
			return values;
		}
		for (int i = 0; i < values.length; ++i) {
			if (StringUtils.isNotEmpty(values[i])) {
				values[i] = StringEscapeUtils.escapeHtml4(values[i]);
			}
		}
		return values;
	}
	
	/**
	 * Get request parameter map and escape html
	 * @param request
	 * @param name
	 * @param escapeHtml
	 * @return
	 */
	public static Map<String,String[]> getRequestParameterMap(HttpServletRequest request, String name) {
		return getRequestParameterMap(request, name, true);
	}
	
	/**
	 * Get request parameter map and escape html
	 * @param request
	 * @param name
	 * @param escapeHtml
	 * @return
	 */
	public static Map<String,String[]> getRequestParameterMap(HttpServletRequest request, String name, boolean escapeHtml) {
		Map<String,String[]> map = request.getParameterMap();
		if (map == null || map.size() <= 0 || !escapeHtml) {
			return map;
		}
		Iterator<Map.Entry<String, String[]>> entryIter = map.entrySet().iterator();
		while (entryIter.hasNext()) {
			Map.Entry<String, String[]> entry = entryIter.next();
			String[] values = entry.getValue();
			if (values == null || values.length <= 0) {
				continue;
			}
			for (int i = 0; i < values.length; ++i) {
				if (StringUtils.isNotEmpty(values[i])) {
					values[i] = StringEscapeUtils.escapeHtml4(values[i]);
				}
			}
		}
		return map;
	}
	
	/**
	 * Get request map that each parameter has one value
	 * @param request
	 * @param escapeHtml
	 * @return
	 */
	public static Map<String,String> getRequestParameterSingleMap(HttpServletRequest request, boolean escapeHtml) {
		Map<String,String[]> map = request.getParameterMap();
		if (map == null || map.size() <= 0 || !escapeHtml) {
			return new HashMap<>(0);
		}
		Map<String, String> resultMap = new LinkedHashMap<>(map.size());
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = en.nextElement();
			String value = request.getParameter(paramName);
			if (value != null && escapeHtml) {
				value = StringEscapeUtils.escapeHtml4(value);
			}
			resultMap.put(paramName, value);
		}
		return resultMap;
	}
	
	/**
	 * Get request uri and trim ;
	 * @param request
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest request) {
		return urlPathHelper.getRequestUri(request);
	}
	
	/**
	 * Set request attribute
	 * @param request
	 * @param attrMap
	 */
	public static void setRequestAttribute(HttpServletRequest request, Map<String, Object> attrMap) {
		if (attrMap == null || attrMap.size() <= 0) {
			return;
		}
		Iterator<Map.Entry<String, Object>> iter = attrMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			request.setAttribute(entry.getKey(), entry.getValue());
		}
	}

}
