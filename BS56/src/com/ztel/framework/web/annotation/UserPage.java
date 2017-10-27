package com.ztel.framework.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User who has one of the role can access the page.
 * 1.Usually, role definition is dynamic, role has many authorities, 
 * in this scenario, using @AuthorizePage will be a better choice.
 * 2.Sometime user/account types are countable, and each user type can access 
 * range of pages, @UserPage with roles will be suitable  
 * @author Zed
 * @since 2015-12-28
 */
@Target( {ElementType.METHOD, ElementType.TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserPage {
	
	String[] roles() default {};
	
	/**
	 * If uri.length > 0, forward or redirect to it
	 * @return
	 */
	String errorPageUri() default "";
	
	boolean redirect() default true;
	
}
