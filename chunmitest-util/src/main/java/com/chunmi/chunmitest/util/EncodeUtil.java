/***********************************************************************
 * 版权所有(C) 上海科拉迪电子科技有限公司 2014-2023
 * Copyright 2014-2023 KELADI TECHNOLOGY CO..
 * <p/>
 * This software is the confidential and proprietary information of
 * KELADI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with KELADI.
 ***********************************************************************/

package com.chunmi.chunmitest.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunmi.chunmitest.util.ArraysUtils;;

/*******************************************
 * @COMPANY:上海科拉迪电子科技有限公司
 * @CLASS:EncodeUtil
 * @DESCRIPTION:
 * <p class="detail">加密解密接口</p>
 * @AUTHOR:<a href="mailto:tuojin@coo-k.com ">T</a>
 * @VERSION:v1.0
 * @DATE:2014年12月25日 -下午4:31:00
 *******************************************/

public class EncodeUtil {
    public static final String MODEL = "keladi.ricecooker.indenpendent";
    public static final String APP_ID = "10006";
    public static final String APP_KEY = "mJxhaXrFSZzNCUnP";
    private static Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

    public static String decryptdataAES(byte[] sessionsecurity, String basedata) {
	String data = "";
	try {
	    data = CryptUtil.decryptAES(basedata, sessionsecurity);
	} catch (Exception e) {
	    logger.error("decryptdataAES occur a exception : " + e.getMessage());
	    throw new RuntimeException(e);
	}
	return data;
    }

    public static byte[] getSessionSecurity(String baseNoce) {
	byte[] sessionsecurity = null;
	try {
	    byte[] nonce = CryptUtil.decryptBASE64(baseNoce);
	    sessionsecurity = CryptUtil.SHA256(ArraysUtils.byteArrayMerge(APP_KEY.getBytes(), nonce));
	} catch (UnsupportedEncodingException e) {
	    logger.error("getSessionSecurity occur a exception : " + e.getMessage());
	    return null;
	}
	return sessionsecurity;
    }

    public static byte[] getSessionSecurity2(String baseNoce) {
	byte[] sessionsecurity = null;
	try {
	    byte[] nonce = CryptUtil.decryptBASE642(baseNoce);
	    sessionsecurity = CryptUtil.SHA256(ArraysUtils.byteArrayMerge(APP_KEY.getBytes(), nonce));
	} catch (UnsupportedEncodingException e) {
	    logger.error("getSessionSecurity occur a exception : " + e.getMessage());
	    return null;
	}
	return sessionsecurity;
    }

    public static String encryptdataAES(String resultString, byte[] nonce) {
	String result = "";
	try {
	    result = CryptUtil.encryptAES(resultString, nonce);
	} catch (Exception e) {
	    logger.error("encryptdataAES occur a exception : " + e.getMessage());
	    result = "data exception";
	    throw new RuntimeException(e);
	}
	return result;
    }

}
