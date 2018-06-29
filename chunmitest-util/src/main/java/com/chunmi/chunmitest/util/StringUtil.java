package com.chunmi.chunmitest.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class StringUtil {
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * @param obj
     * @return
     * @description: 判断对象是否为空
     * @author:tuojin
     * @return:boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object obj) {
	if (obj == null)
	    return true;

	if (obj instanceof CharSequence)
	    return ((CharSequence) obj).length() == 0;

	if (obj instanceof Collection)
	    return ((Collection) obj).isEmpty();

	if (obj instanceof Map)
	    return ((Map) obj).isEmpty();

	if (obj instanceof Object[]) {
	    Object[] object = (Object[]) obj;
	    if (object.length == 0) {
		return true;
	    }
	    boolean empty = true;
	    for (int i = 0; i < object.length; i++) {
		if (!isNullOrEmpty(object[i])) {
		    empty = false;
		    break;
		}
	    }
	    return empty;
	}
	return false;
    }

    public static String getutfString(String source) throws UnsupportedEncodingException {
	return new String(source.getBytes("iso-8859-1"), "utf-8");
    }

    public static String replaceBlank(String str) {
	String dest = "";
	if (str != null) {
	    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	    Matcher m = p.matcher(str);
	    dest = m.replaceAll("");
	}
	return dest;
    }
    
    public static boolean isNumeric(String str){
    	
    	if(StringUtil.isNullOrEmpty(str))
    		return false;
    	
    	for (int i = 0; i < str.length(); i++){
    		if (!Character.isDigit(str.charAt(i))){
    			return false;
    		}
		}
		return true;
	}

	public static String getConvertString(String str) {
    	if(StringUtil.isNullOrEmpty(str))
		{
			return "";
		}
		String s = "";
		if (str.length() == 0) return "";
		s = str.replace("'"," ");
		return s;
	}
	
	/**
	 * 
	 * @description: <p class="detail">生成sign</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月19日-上午11:27:46
	 * @param @param requestParams
	 * @param @return
	 * @return String
	 */ 
	@Deprecated
	public static String createSign(String requestParams){
		if(requestParams==null || requestParams.isEmpty())
		return null;
		String md5key = ConfigUtils.getPropertiesValue("MD5key");
		StringBuilder sb = new StringBuilder(Constants.MD5KEY+md5key);
		Map<String,Object> map = new HashMap<String,Object>();
		if(requestParams.contains("&")){
			String[] strArray = requestParams.split("&");
			for(int i =0;i<strArray.length;i++){
				String str = strArray[i];
				String[] strArray_new = str.split("=");
				map.put(strArray_new[0], strArray_new[1]);
			}
			List<String> keyList = new ArrayList<String>(map.keySet());
			Collections.sort(keyList);
			for(int i =0;i<keyList.size();i++){
				sb.append("&"+keyList.get(i)+"="+map.get(keyList.get(i)));
			}
		}else{
			sb.append("&"+requestParams);
		}
		System.out.println("拼接后的字符串:"+sb.toString());
		return MD5Util.MD5Encryption(sb.toString());
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">sign的生成规则(默认)</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月17日-下午3:17:17
	 * @param @param requestParams
	 * @param @return
	 * @return String
	 */
	public static String createSignDefault(String requestParams){
		if(requestParams==null || requestParams.equals("")){
			return "";
		}
		String md5key = ConfigUtils.getPropertiesValue("MD5key");
		StringBuilder sb = new StringBuilder(Constants.MD5KEY+md5key);
		try {
			JSONObject jsonObject = JSONObject.parseObject(requestParams);
			List<String> keyList = new ArrayList<String>(jsonObject.keySet());
			Collections.sort(keyList);
			keyList.forEach(key -> {
				if(!key.equals("sign")){
					sb.append("&");
					sb.append(key);
					sb.append("=");
					sb.append(jsonObject.get(key));
				}
			});
		} catch (Exception e) {
			logger.error("sign生成失败:{}", e.getMessage());
		}
		return MD5Util.MD5Encryption(sb.toString());
	}
    
}
