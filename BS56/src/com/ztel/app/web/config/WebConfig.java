/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.app.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ztel.framework.web.config.WebConfigSupport;

/**
 * @author Zeal
 * @since 2016年4月27日
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.ztel.app.web,com.ztel.app.inspur.webctrl")
public class WebConfig extends WebConfigSupport {
	
//	/**
//	 * {@inheritDoc}
//	 * <p>This implementation is empty.
//	 */
//	public void addInterceptors(InterceptorRegistry registry) {
//		AccessControlInterceptor acl = accessControlInterceptor();
//		//acl.setUserDetailSessionKey(User.USER_DETAIL_SESSION_KEY);
//		registry.addInterceptor(acl).addPathPatterns("/**");
//		TokenInterceptor token = new TokenInterceptor();
//		registry.addInterceptor(token).addPathPatterns("/**");
//	}
//	
//	@Bean
//	public String testStringBean() {
//		AccessControlInterceptor acl = accessControlInterceptor();
//		return acl.toString();
//	}
//	
//	//Construct your own AccessControlInterceptor if it's necessary, @Bean is not necessary
//	
//	protected AccessControlInterceptor accessControlInterceptor() {
//		AccessControlInterceptor acl = new MyAccessControlInterceptor();
//		acl.setUserDetailSessionKey(User.USER_DETAIL_SESSION_KEY);
//		return acl;
//	}
	
    @Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
    	CommonsMultipartResolver multipart = new CommonsMultipartResolver();
    	multipart.setMaxUploadSize(50000000);
    
		return multipart;
	}
	

}
