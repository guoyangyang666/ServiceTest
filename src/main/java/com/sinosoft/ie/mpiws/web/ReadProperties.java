package com.sinosoft.ie.mpiws.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;
public class ReadProperties {
	
	private static List<String> list = new Vector<String>();
	
	private static Map<String,String> map = new  HashMap<String,String>();
	
/**
 * 读取配置文件信息
 * @param filePath 配置文件路径
 * @return
 */
	public static Map<String,String> readProperties(String filePath) {
		ReadProperties readProperties = new ReadProperties();
		Properties props = new Properties();
		try {
			if(isExist(filePath)){
				InputStream in = ReadProperties.class.getResourceAsStream(filePath);
				props.load(in);
				Enumeration en = props.propertyNames();
				while (en.hasMoreElements()) {
					String key = (String) en.nextElement();
					String Property = props.getProperty(key);
					map.put(key, Property);
				}
				list.add(filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
		/**
	 	* 功能：Java读取txt文件的内容
Correct
	     * 步骤：1：先获得文件句柄
	     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	     * 3：读取到输入流后，需要读取生成字节流
	     * 4：一行一行的输出。readline()。
	     * 备注：需要考虑的是异常情况
	     * @param filePath
	     */
	public static String readTxtFile(String filePath){
		 String str = "";
		 try {
		         String encoding="UTF-8";
		         File file=new File(filePath);
		         if(file.isFile() && file.exists()){ //判断文件是否存在
		                 InputStreamReader read = new InputStreamReader(
		                 new FileInputStream(file),encoding);//考虑到编码格式
		                 BufferedReader bufferedReader = new BufferedReader(read);
		                 String lineTxt=null;
		                 while((lineTxt = bufferedReader.readLine()) != null){
		                 	str+=lineTxt;
		                 }
		                 System.out.println(str);
		                 read.close();
				     }else{
				         System.out.println("找不到指定的文件");
				     }
		 } catch (Exception e) {
		     System.out.println("读取文件内容出错");
		         e.printStackTrace();
		     }
	     return str;
	 }

	/**
	 * 判断文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean  isExist(String filePath){
		boolean b = true;
		for (int i = 0; i < list.size(); i++) {
			if(filePath.equals(list.get(i))){
				b = false;
			}
		}
		return b;
	}
	/**
	 * 产生UUID
	 * @return
	 */
	public static String  uuidToString(){
		String s = new String(UUID.randomUUID().toString());   
	    String a[] = s.split("-");
	    StringBuilder uuid = new StringBuilder("");
	    for (String string : a) {
			uuid.append(string);
		}
		return uuid.toString();
	}
}
