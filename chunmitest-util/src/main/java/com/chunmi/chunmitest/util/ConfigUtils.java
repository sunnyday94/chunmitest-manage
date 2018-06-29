/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-util Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2017年6月19日, 上午10:35:31 (CST)]
 */
package com.chunmi.chunmitest.util;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigUtils {

	
		/**
		 * 日志
		 */
		private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);
	
		private static Properties properties;
		
		static{
			try {
				properties = new Properties();
				properties.load(ConfigUtils.class.getClassLoader().getResourceAsStream("com/chunmi/chunmitest/config/config.properties"));
			} catch (Exception e) {
				LOGGER.error("配置文件读取失败:{}",e.getMessage());
			}
		}
		
		
		/**
		 * 
		 * @description: <p class="detail"></p>
		 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
		 * @date: 2017年6月19日-上午11:20:51
		 * @param @param key
		 * @param @return
		 * @return String
		 */
		public static String getPropertiesValue(String key){
			return properties.getProperty(key);
		}
		
}
