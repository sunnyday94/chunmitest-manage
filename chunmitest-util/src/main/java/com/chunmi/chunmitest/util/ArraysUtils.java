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
import java.util.ArrayList;
import java.util.List;

/*******************************************
 * @COMPANY:上海科拉迪电子科技有限公司
 * @CLASS:ArraysUtils
 * @DESCRIPTION:
 * <p class="detail">
 *                 </p>
 * @AUTHOR:<a href="mailto:tuojin@coo-k.com ">T</a>
 * @VERSION:v1.0
 * @DATE:2015年2月2日 -上午11:06:56
 *******************************************/

public class ArraysUtils {
	/**
	 * ************************************************************
	 *
	 * @description: <p class="detail"> 合并数组 </p> @author: <a
	 * href="mailto:tuojin@keladisco.com ">T</a> @date:
	 * 2015年2月2日-上午11:07:19 @param @param byte1 @param @param
	 * byte2 @param @return @return String @throws
	 ***************************************************************
	 */
	public static byte[] byteArrayMerge(byte[] byte1, byte[] byte2) {
		int len = byte1.length + byte2.length;
		byte[] target = new byte[len];
		System.arraycopy(byte1, 0, target, 0, byte1.length);
		System.arraycopy(byte2, 0, target, byte1.length, byte2.length);
		return target;
	}
	//int to byte[]
	public static byte[] intToByteArray(int i) {   
        byte[] result = new byte[4];   
        //由高位到低位
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF); 
        result[3] = (byte)(i & 0xFF);
        return result;
      }
	 
	/**
	 * *****************************************
	 * @COMPANY:上海科拉迪电子科技有限公司
	 * <p class="detail"> 分割list
	 *                 </p>
	 * @AUTHOR:<a href="luqiantu@chunmi.com">路前途</a>
	 * @DATA: 2016年4月19日 下午12:09:01
	 ******************************************
	 */
     public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        
        int listSize = list.size();                                  
        int page = (listSize + (pageSize-1))/ pageSize;                    
        List<List<T>> listArray = new ArrayList<List<T>>();
        for(int i=0;i<page;i++) {
            List<T> subList = new ArrayList<T>();
            for(int j=0;j<listSize;j++) {
                int pageIndex = ( (j + 1) + (pageSize-1) ) / pageSize;
                if(pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if( (j + 1) == ((j + 1) * pageSize) ) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }
}
