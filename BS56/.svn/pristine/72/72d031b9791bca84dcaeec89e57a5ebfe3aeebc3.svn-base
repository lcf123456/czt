package com.ztel.framework.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User with all the authorities defined in value() can access the page
 * @author Zed
 * @since 2015-12-28
 */
@Target( {ElementType.METHOD, ElementType.TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityPage {
	
	String[] value() default {};
	
	/**
	 * If uri.length > 0, forward or redirect to it
	 * @return
	 */
	String errorPageUri() default "";
	
	boolean redirect() default true;
}