/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

import com.ztel.framework.util.ClassPathUtils;
import com.ztel.framework.util.IOUtils;
import com.ztel.framework.util.NumberUtils;
import com.ztel.framework.util.StringUtils;


/**
 * Suggest using external property file whose location is out of webapp.
 * Firstly, we will find the config file under system environment path,  
 * if it's not existed, we will search the file in class path/config.
 * Usage:
 *    #{yourEnvironmentName.properties['yourPropertyKey']} or #{yourEnvironmentName.getProperty('yourPropertyKey')}
 * Notice: spring has its envrionment bean name, we must set a different @Bean("yourEnvrionmentName")
 * @author Zeal
 * @since 2016年4月27日
 */
public class Environment implements InitializingBean {
	
	/** Configuration file content */
	private Properties properties = null;
	
	/** System environment name represents the config dir */
	private String sysConfigDirName = "";
	
	/** Configuration file name */
	private String configFileName = "gx_app.properties";
	
	/** Reserve the configuration dir */
	private File configDir = null;
	
	/**
	 * Load properties under system environment or class path config dir
	 * @throws IOException 
	 */
	public void afterPropertiesSet() throws Exception {
		
		if (StringUtils.isBlank(this.configFileName)) {
			throw new IllegalStateException("Property file name is required");
		}
		File configFile = null;
		
		//Search in system environment path
		if (StringUtils.isNotBlank(this.sysConfigDirName)) {
			String configDirStr = System.getenv(this.sysConfigDirName);
			if (StringUtils.isNotBlank(configDirStr)) {
				File _configDir = new File(configDirStr);
				if (_configDir.exists() && _configDir.isDirectory()) {
					configFile = new File(_configDir, this.configFileName);
					if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
						this.configDir = _configDir;
					}
				}
			}
		}
		//Search in class path
		if (this.configDir == null) {
			File _configDir = ClassPathUtils.getClassPath(Environment.class);
            //File _configDir = new File(classPath, "config");
			if (_configDir.exists() && _configDir.isDirectory()) {
				configFile = new File(_configDir, this.configFileName);
				if (!(configFile.exists() && configFile.isFile() && configFile.canRead())) {
					throw new IllegalStateException("Property file " + this.configFileName + " cannot be found in environment or class path");
				}
				this.configDir = _configDir;
			}
			else {
				throw new IllegalStateException("Property file " + this.configFileName + " cannot be found in environment or class path");
			}
		}
		//Load property
		this.properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configFile);
			this.properties.load(fis);
		}
		finally {
			IOUtils.closeQuietly(fis);
		}
	}

	/**
	 * @return the propertyFileName
	 */
	public String getConfigFileName() {
		return configFileName;
	}

	/**
	 * @param propertyFileName the propertyFileName to set
	 */
	public void setConfigFileName(String propertyFileName) {
		this.configFileName = propertyFileName;
	}
	
	public String getProperty(String key, String defaultValue) {
		return this.properties.getProperty(key, defaultValue);
	}
	
	public String getProperty(String key) {
		return getProperty(key, "");
	}
	
	public int getIntProperty(String key, int defaultValue) {
		String value = this.properties.getProperty(key);
		return NumberUtils.toInt(value, defaultValue);
	}
	
	public int getIntProperty(String key) {
		return this.getIntProperty(key, 0);
	}
	
	public double getDoubleProperty(String key, double defaultValue) {
		String value = this.properties.getProperty(key);
		return NumberUtils.toDouble(value, defaultValue);
	}
	
	public double getDoubleProperty(String key) {
		return this.getDoubleProperty(key, 0d);
	}
	
	public Properties getProperties() {
		return this.properties;
	}

	/**
	 * @return the sysConfigDirName
	 */
	public String getSysConfigDirName() {
		return sysConfigDirName;
	}

	/**
	 * @param sysConfigDirName the sysConfigDirName to set
	 */
	public void setSysConfigDirName(String sysConfigDirName) {
		this.sysConfigDirName = sysConfigDirName;
	}

}
