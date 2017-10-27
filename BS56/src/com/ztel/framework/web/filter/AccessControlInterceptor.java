/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.framework.web.filter;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;

import com.ztel.framework.vo.ErrorCode;
import com.ztel.framework.web.annotation.AuthorityPage;
import com.ztel.framework.web.annotation.PublicPage;
import com.ztel.framework.web.annotation.UserPage;
import com.ztel.framework.web.util.SessionUtils;
import com.ztel.framework.web.vo.UserDetail;

/**
 * Default access control interceptor for authorization.preHttpRequestHandler
 * 1. If ctrl or method contain @PublicPage, all users can access
 * 2. If ctrl or method contain @UserPage logined users with role or member type can access
 * 3. If ctrl or method contain @AuthorityPage only the users with specified authorities can access
 * @author Zeal
 * @since 2016年4月27日
 */
public class AccessControlInterceptor extends HandlerInterceptorSupport {
	
//	/** Logger */
//    private static final Logger logger = LogManager.getLogger(AccessControlInterceptor.class);
	
	/** Save sth into session to mark user is logined */
	private String userDetailSessionKey = "user_detail_session_key";
	
	/**
	 * Check whether controller or method is public 
	 * @param handlerMethod
	 * @return
	 */
	private boolean isPublicPage(HandlerMethod handlerMethod) {
		if (handlerMethod == null) {
			return false;
		}
		//TODO Reserve the method/class as cache? which is faster?
		//We had better to load all the @PublicPage when system is started
		PublicPage pp = handlerMethod.getMethodAnnotation(PublicPage.class);
		if (pp != null) {
			return true;
		}
		Class<?> clazz = handlerMethod.getMethod().getDeclaringClass();
		pp = AnnotationUtils.findAnnotation(clazz, PublicPage.class);
		return pp != null;
	}
	
	/**
	 * Check whether controller or method contain @UserPage
	 * @param handlerMethod
	 * @return
	 */
	private UserPage getUserPageAnnotation(HandlerMethod handlerMethod) {
		return getMethodPageAnnotation(handlerMethod, UserPage.class);
	}
	
	/**
	 * Check whether ctrl or method contain @AuthorityPage
	 * @param handlerMethod
	 * @return
	 */
	private AuthorityPage getAuthorityPageAnnocation(HandlerMethod handlerMethod) {
		return getMethodPageAnnotation(handlerMethod, AuthorityPage.class);
	}
	

	
	/**
	 * Check whether user contains role defined in @UserPage
	 * @param userDetail
	 * @param userPage
	 * @return
	 */
	private boolean checkUserRole(UserDetail userDetail, UserPage userPage) {
		Assert.notNull(userDetail);
		Assert.notNull(userPage);
		String[] requiredRoles = userPage.roles();
		//No role is required, logined user can access
		if (requiredRoles == null || requiredRoles.length <= 0) {
			return true;
		}
		Collection<String> userRoles = userDetail.getRoles();
		//User contains no role
		if (userRoles == null || userRoles.size() <= 0) {
			return false;
		}
		//Check whether user contains one of the required role
		for (String requiredRole : requiredRoles) {
			if (userRoles.contains(requiredRole)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check whether user owns authority to access
	 * @param userDetail
	 * @param authPage
	 * @return
	 */
	private boolean checkAuthPage(UserDetail userDetail, AuthorityPage authPage) {
		Assert.notNull(userDetail);
		Assert.notNull(authPage);
		if (authPage.value().length <= 0) {
			throw new IllegalStateException("@AuthorityPage value is required");
		}
		Collection<String> userAuthories = userDetail.getAuthorities();
		if (userAuthories == null || userAuthories.size() <= 0) {
			return false;
		}
		for (String requiredAuth : authPage.value()) {
			if (!userAuthories.contains(requiredAuth)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get user detail from session
	 * @param request
	 * @return
	 */
	protected UserDetail getUserDetailFromSession(HttpServletRequest request) {
		return SessionUtils.getAttribute(request, this.userDetailSessionKey, UserDetail.class);
	}
	
	/**
	 * Acess control interceptor
	 * @param request
	 * @param response
	 * @param handler
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = null;
		//Controller link
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
			return this.handleMethod(request, response, handlerMethod);
		}
		//HttpRequestHandler like DefaultHttpRequestHandler for static resources
		else if (handler instanceof HttpRequestHandler) {
			HttpRequestHandler requestHandler = (HttpRequestHandler) handler;
			return this.preHttpRequestHandler(request, response, requestHandler);
		}
		//Handle other unknown handler object
		else {
			return this.preObjectHandler(request, response, handler);
		}
	}
	
	/**
	 * Handle method, override it if @PublicPage,@UserPage,@AuthorityPage are not suitable for you
	 * @param request
	 * @param response
	 * @param handlerMethod
	 * @return
	 * @throws Exception
	 */
	protected boolean handleMethod(HttpServletRequest request, 
		HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		//Special process before @PublicPage
		if (preHandleMethod(request, response, handlerMethod)) {
			return true;
		}

		//@PublicPage all users can access
		if (this.isPublicPage(handlerMethod)) {
			return true;
		}
		//Exlclude @PublicPage, others require user to sign-in
		UserDetail userDetail = this.getUserDetailFromSession(request);
		if (userDetail == null) {
			return this.userNotLogin(request, response, handlerMethod);
		}
		//@UserPage with role
		UserPage userPage = this.getUserPageAnnotation(handlerMethod);
		if (userPage != null) {
			if (!this.checkUserRole(userDetail, userPage)) {
				return this.userPageAccessDenied(request, response, handlerMethod, userPage);
			}
		}
		//@UserPage and @AuthorityPage can use together
		//@AuthorityPage
		AuthorityPage authPage = this.getAuthorityPageAnnocation(handlerMethod);
		if (authPage != null) {
			if (!this.checkAuthPage(userDetail, authPage)) {
				return this.authPageAccessDenied(request, response, handlerMethod, authPage);
			}
		}
		//Override if there's additional validations
		return this.postHandleMethod(request, response, handlerMethod, userDetail);
	}
	
	/**
	 * User not login
	 * @param request
	 * @param response
	 * @param method
	 * @return
	 * @throws Exception
	 */
	protected boolean userNotLogin(HttpServletRequest request, 
		HttpServletResponse response,  HandlerMethod method) throws Exception {
		return this.error(request, response, method, ErrorCode.INVALID_SESSION, null, false);
	}
	
	/**
	 * Accessing user page is denied
	 * @param request
	 * @param response
	 * @param method
	 * @param userPage
	 * @return
	 * @throws Exception
	 */
	protected boolean userPageAccessDenied(HttpServletRequest request, 
		HttpServletResponse response,  HandlerMethod method, UserPage userPage) throws Exception {
		return this.error(request, response, method, ErrorCode.ACCESS_DENIED, userPage.errorPageUri(), userPage.redirect());
	}
	
	/**
	 * Accessing authrity page is denied
	 * @param request
	 * @param response
	 * @param method
	 * @param userPage
	 * @return
	 * @throws Exception
	 */
	protected boolean authPageAccessDenied(HttpServletRequest request, 
		HttpServletResponse response,  HandlerMethod method, AuthorityPage authPage) throws Exception {
		return this.error(request, response, method, ErrorCode.ACCESS_DENIED, authPage.errorPageUri(), authPage.redirect());
	}
	
	
	
	/**
	 * Override this method to handle spring mvc HandlerMethod
	 * @param request
	 * @param response
	 * @param handleMethod
	 * @param userDetail
	 * @return
	 */
	protected boolean preHandleMethod(HttpServletRequest request, 
		HttpServletResponse response, HandlerMethod handlerMethod) {
		return false;
	}
	
	/**
	 * Override this method to handle spring mvc HandlerMethod
	 * @param request
	 * @param response
	 * @param handleMethod
	 * @param userDetail
	 * @return
	 */
	protected boolean postHandleMethod(HttpServletRequest request, 
		HttpServletResponse response, HandlerMethod handlerMethod, UserDetail userDetail) {
		return true;
	}
	
	/**
	 * Override this method to handle HttpRequestHandler like static resources
	 * @param request
	 * @param response
	 * @param handleMethod
	 * @param userDetail
	 * @return
	 */
	protected boolean preHttpRequestHandler(HttpServletRequest request, 
		HttpServletResponse response, HttpRequestHandler requestHandler) {
		return true;
	}
	
	/**
	 * Override this method to handle other unknow object handler
	 * @param request
	 * @param response
	 * @param handleMethod
	 * @param userDetail
	 * @return
	 */
	protected boolean preObjectHandler(HttpServletRequest request, 
		HttpServletResponse response, Object handler) {
		return true;
	}

	/**
	 * @return the userDetailSessionKey
	 */
	public String getUserDetailSessionKey() {
		return userDetailSessionKey;
	}

	/**
	 * @param userDetailSessionKey the userDetailSessionKey to set
	 */
	public void setUserDetailSessionKey(String userDetailSessionKey) {
		this.userDetailSessionKey = userDetailSessionKey;
	}



	

}
