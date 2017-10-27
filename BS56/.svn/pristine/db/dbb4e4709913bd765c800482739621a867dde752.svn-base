package com.ztel.framework.web.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.ErrorCode;
import com.ztel.framework.web.util.WebUtils;



/**
 * Extract some common methods 
 * @author Zeal
 * @since 2016年5月6日
 */
public abstract class HandlerInterceptorSupport extends HandlerInterceptorAdapter {
	
	/**
	 * Find annocation in method header first, and then find in class header
	 * @param handlerMethod
	 * @param clazz
	 * @return
	 */
	protected <A extends Annotation>A getMethodPageAnnotation(HandlerMethod handlerMethod, Class<A> clazz) {
		if (handlerMethod == null) {
			return null;
		}
		//TODO Reserve the method/class as cache? which is faster?
		//We had better to load all the @UserPage when system is started
		A up = handlerMethod.getMethodAnnotation(clazz);
		if (up != null) {
			return up;
		}
		Class<?> ctrlClass = handlerMethod.getMethod().getDeclaringClass();
		return AnnotationUtils.findAnnotation(ctrlClass, clazz);
	}
	
	protected boolean error(HttpServletRequest request, HttpServletResponse response, 
		HandlerMethod method, int errorCode, String errorPageUri, boolean redirect) throws Exception {
		
		request.setAttribute(ErrorCode.ERROR_CODE, errorCode);
		if (WebUtils.isAjaxRequest(request)) {
			response.setStatus(errorCode);
		}
		else if (StringUtils.isNotBlank(errorPageUri)) {
			if (redirect) {
				response.sendRedirect(WebUtils.getRequestPath(request, errorPageUri, false, false));
			}
			else {
				request.getRequestDispatcher(errorPageUri).forward(request, response);
			}
		}
		else {
			errorPage(request, response, method, errorCode);
		}
		return false;
	}
	
	/**
	 * Handle error
	 * @param request
	 * @param response
	 * @param errorCode
	 * @deprecated
	 * @return
	 * @throws IOException 
	 */
	protected boolean error(HttpServletRequest request, HttpServletResponse response, int errorCode) throws Exception {
		return error(request, response, null, errorCode, null, false);
	}
	
	/**
	 * Handle error page(Not ajax request)
	 * @param request
	 * @param response
	 * @param errorCode
	 * @see ErrorCode
	 */
	protected void errorPage(HttpServletRequest request, HttpServletResponse response, HandlerMethod method, int errorCode) throws Exception {
		request.setAttribute(ErrorCode.ERROR_CODE, errorCode);
		throw new IllegalStateException("Error code is " + errorCode);
	}
	

	
	/**
	 * Handle error page(Not ajax request)
	 * @param request
	 * @param response
	 * @param errorCode
	 * @see ErrorCode
	 * @deprecated
	 */
	protected void errorPage(HttpServletRequest request, HttpServletResponse response, int errorCode) throws Exception {
		errorPage(request, response, null, errorCode);
	}

}
