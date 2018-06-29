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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunmi.chunmitest.util.ArraysUtils;
import com.chunmi.chunmitest.util.Base64;
import com.chunmi.chunmitest.util.StringUtil;

/*******************************************
 * @COMPANY:上海科拉迪电子科技有限公司
 * @CLASS:CryptUtil
 * @DESCRIPTION:
 *               <p class="detail">
 *               </p>
 * @AUTHOR:<a href="mailto:tuojin@coo-k.com ">T</a>
 * @VERSION:v1.0
 * @DATE:2014年12月4日 -下午4:15:28
 *******************************************/

@SuppressWarnings("all")
public class CryptUtil {

	public static final Random random = new Random();
	private static Logger logger = LoggerFactory.getLogger(CryptUtil.class);

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               SHA1
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:36:46 @param @param
	 *          decript @param @return @return String @throws
	 ***************************************************************
	 */
	public static byte[] SHA1(byte[] decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript);
			byte messageDigest[] = digest.digest();
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               SHA
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:37:02 @param @param
	 *          decript @param @return @return String @throws
	 ***************************************************************
	 */
	public static String SHA(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @throws UnsupportedEncodingException
	 *             ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               SHA256
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:37:14 @param @param
	 *          decript @param @return @return String @throws
	 ***************************************************************
	 */
	public static byte[] SHA256(byte[] bytes) throws UnsupportedEncodingException {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = digest.digest(bytes);
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               MD5
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:37:29 @param @param input @param @return @return
	 *          String @throws
	 ***************************************************************
	 */
	public static String MD5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               AES解密
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2015年2月5日-下午1:58:01 @param @param data @param @param
	 *          key @param @return @return String @throws
	 ***************************************************************
	 */
	public static String decryptAES(String data, byte[] key) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			return new String(cipher.doFinal(decryptBASE64(data)), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               AES加密
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2015年2月5日-下午1:58:40 @param @param data @param @param
	 *          key @param @return @return String @throws
	 ***************************************************************
	 */
	public static String encryptAES(String data, byte[] key) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			return new String(encryptBASE64(cipher.doFinal(data.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * @throws UnsupportedEncodingException
	 *             ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               base64解密-mi
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:38:54 @param @param key @param @return @return
	 *          String @throws
	 ***************************************************************
	 */
	public static byte[] decryptBASE64(String bytes) throws UnsupportedEncodingException {
		return org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               base64加密-mi
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:39:13 @param @param key @param @return @return
	 *          String @throws
	 * @throws UnsupportedEncodingException
	 ***************************************************************
	 */
	public static String encryptBASE64(byte[] bytes) throws UnsupportedEncodingException {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
	}

	/**
	 * @throws UnsupportedEncodingException
	 *             ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               base64解密-app
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:38:54 @param @param key @param @return @return
	 *          String @throws
	 ***************************************************************
	 */
	public static byte[] decryptBASE642(String bytes) throws UnsupportedEncodingException {
		return Base64.decode(bytes, Base64.URL_SAFE);
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               base64加密-app
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午4:39:13 @param @param key @param @return @return
	 *          String @throws
	 * @throws UnsupportedEncodingException
	 ***************************************************************
	 */
	public static String encryptBASE642(byte[] bytes) throws UnsupportedEncodingException {
		return new String(Base64.encode(bytes, Base64.URL_SAFE)).trim().replace("\n", "").replace("\r", "");
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               二进制转换为16进制
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午5:00:21 @param @param buf @param @return @return
	 *          String @throws
	 ***************************************************************
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toLowerCase());
		}
		return sb.toString();
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               16进制转换为2进制
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2014年12月4日-下午5:00:35 @param @param hexStr @param @return @return
	 *          byte[] @throws
	 ***************************************************************
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               获取时间戳-mi
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2015年2月5日-下午1:58:59 @param @return @return String @throws
	 * @throws UnsupportedEncodingException
	 ***************************************************************
	 */
	public static String generateNonce() throws UnsupportedEncodingException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try {
			dos.writeLong(random.nextLong());
			dos.writeInt((int) (System.currentTimeMillis() / 60000L));
			dos.flush();
		} catch (IOException var3) {
			logger.error("never happend", var3);
		}
		return encryptBASE64(bos.toByteArray());
	}
	

	/**
	 * ************************************************************
	 *
	 * @description:
	 *               <p class="detail">
	 *               获取时间戳-app
	 *               </p>
	 * @author: <a href="mailto:tuojin@keladisco.com " >T</a> @date:
	 *          2015年2月5日-下午1:58:59 @param @return @return String @throws
	 * @throws UnsupportedEncodingException
	 ***************************************************************
	 */
	public static String generateNonce2() throws UnsupportedEncodingException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try {
			dos.writeLong(random.nextLong());
			dos.writeInt((int) (System.currentTimeMillis() / 60000L));
			dos.flush();
		} catch (IOException var3) {
			logger.error("never happend", var3);
		}
		return encryptBASE642(bos.toByteArray());
	}

	public static boolean isWithinOneMinute(byte[] time, byte[] systime) {
		for (int i = 0; i < time.length - 1; i++) {
			if (time[i] != systime[i])
				return false;
		}

		return Math.abs(time[time.length - 1] - systime[time.length - 1]) <= 1;

	}

	/**
	 * 验证nonce 先对特殊字符进行处理 + , /
	 * 
	 * @param nonce
	 * @return
	 * @throws IOException
	 */
	public static boolean validateNonce(String nonce) throws IOException {
		if (StringUtil.isNullOrEmpty(nonce)) {
			return false;
		}
		byte[] de64nonce = decryptBASE642(nonce);
		byte[] time = Arrays.copyOfRange(de64nonce, 8, 12);
		byte[] systemtime = ArraysUtils.intToByteArray((int) (System.currentTimeMillis() / 60000L));
		if (isWithinOneMinute(time, systemtime)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 服务器端验证数据签名 先对特殊字符进行处理 + , /
	 * 
	 * @param nonce
	 * @param httpmethod
	 * @param httpurl
	 * @param signature
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validateSignature(String nonce, String httpmethod, String httpurl, String signature)
			throws UnsupportedEncodingException {
		if(StringUtil.isNullOrEmpty(nonce) || StringUtil.isNullOrEmpty(signature))
			return false;
		String s1 = encryptBASE642(SHA256(ArraysUtils.byteArrayMerge(EncodeUtil.APP_KEY.getBytes(), nonce.getBytes())));
		String s2 = httpmethod.concat("&").concat(httpurl).concat("&").concat("app_id=" + EncodeUtil.APP_ID).concat("&")
				.concat(s1);
		byte[] signature2 = encryptBASE642(SHA1(s2.getBytes())).getBytes();
		if (Arrays.equals(signature2, signature.getBytes())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成数据签名
	 * 
	 * @param nonce
	 * @param httpmethod(post|get)
	 * @param httpurl
	 *            (一直到QueryString结束 ?)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String generateSignature(String nonce, String httpmethod, String httpurl)
			throws UnsupportedEncodingException {
		String s1 = encryptBASE64(SHA256(ArraysUtils.byteArrayMerge(EncodeUtil.APP_KEY.getBytes(), nonce.getBytes())));
		String s2 = httpmethod.concat("&").concat(httpurl).concat("&").concat("app_id=" + EncodeUtil.APP_ID).concat("&")
				.concat(s1);
		return encryptBASE64(SHA1(s2.getBytes()));
	}
	
	/**
	 * 生成数据签名
	 * 
	 * @param nonce
	 * @param httpmethod(post|get)
	 * @param httpurl
	 *            (一直到QueryString结束 ?)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String generateSignature2(String nonce, String httpmethod, String httpurl)
			throws UnsupportedEncodingException {
		String s1 = encryptBASE642(SHA256(ArraysUtils.byteArrayMerge(EncodeUtil.APP_KEY.getBytes(), nonce.getBytes())));
		String s2 = httpmethod.concat("&").concat(httpurl).concat("&").concat("app_id=" + EncodeUtil.APP_ID).concat("&")
				.concat(s1);
		return encryptBASE642(SHA1(s2.getBytes()));
	}
	
}