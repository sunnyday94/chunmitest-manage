/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-web Mod.
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
 * File Created @ [2017年8月23日, 上午10:25:08 (CST)]
 */
package com.chunmi.chunmitest.test;

import java.io.FileOutputStream;  
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.io.XMLWriter;

public class ConvertToXml {
	
	public static void main(String[] args) throws Exception    
    {    
//        // 创建文档并设置文档的根元素节点   
//        Element root = DocumentHelper.createElement("books");  
//        Document doucment = DocumentHelper.createDocument(root);  
//        //根节点  
//        root.addAttribute("name","bookvalue");  
//        //子节点  
//        Element element1 = root.addElement("author1 ");  
//        element1.addAttribute( "name", "James1" );  
//        element1.addAttribute( "location1", "UK1" );  
//        element1.addText( "James Strachan1" );  
//          
//        Element element = root.addElement("author2 ");  
//        element.addAttribute( "name", "chen" );  
//        element.addAttribute( "kenken", "ZK" );  
//        element.addText( "chen kenken" );  
//        //添加  
//        XMLWriter xmlwriter2 = new XMLWriter(new FileOutputStream("books.xml")); 
//        xmlwriter2.write(doucment);  
//        xmlwriter2.close();  
		
		Map<String,String> student = new HashMap<String, String>();
		student.put("name","sunnyday");
		student.put("age","23");
		student.put("hobby", "sing");
		System.out.println(mapToXml(student));
    }  
	
	/**
	 * 
	 * @description: <p class="detail">map转xml</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年8月23日-上午11:07:37
	 * @param @param map
	 * @param @return
	 * @return String
	 */
	public static String mapToXml(Map<String,String> map){
		if(map==null || map.size()==0){
			throw new RuntimeException("map集合为空,转换xml失败!");
		}
		Element root = DocumentHelper.createElement("student"); //创建根节点
		Document doc = DocumentHelper.createDocument(root);   //设置文档对象

		map.forEach((k, v) -> {
		if(v != null && !"".equals(v)){
		Element e = root.addElement(k);  //添加子节点
		e.setText(v);
		}
		});
		// doc.asXML()，此方法含抬头;
		// root.asXML(),此方法不含抬头;
		return doc.asXML();
	}
}
