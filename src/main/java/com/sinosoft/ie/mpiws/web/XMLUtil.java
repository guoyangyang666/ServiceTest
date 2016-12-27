package com.sinosoft.ie.mpiws.web;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLUtil {
	
	 public static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
        	System.out.println(e.toString());
        }  
        return result;
	 }  
	/**
	 * xml转换成JavaBean
	 * @param xml
	 * @param class
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return t;
	}
	
	/**
	 * 将node转换成JavaBean
	 * @param node
	 * @param class
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBeanByNode(Node node, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> void beanToNode(Node node,T obj,Class<T> c) {
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(obj, node);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//将xml转换成document对象
	public static Document parseDocument(String xml){
		//创建装载xml对象
		DocumentBuilderFactory domfactory = DocumentBuilderFactory.newInstance();
		domfactory.setNamespaceAware(false);
		Document document = null;
		try {
			DocumentBuilder builder = domfactory.newDocumentBuilder();
			StringReader sr = new StringReader(xml);
			InputSource is = new InputSource(sr);
			document = builder.parse(is);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return document;
	}
	/**
	 * 读取子元素TextNode
	 * @param temp 父元素
	 * @param tagName 子元素名称
	 * @return 内容
	 */
	public static String readTextContent(Element temp,String tagName){
		if(temp==null) return "";
		NodeList list = temp.getElementsByTagName(tagName);
    	if(list!=null && list.getLength()>=1){
    		return list.item(0).getTextContent();
    	}
    	return "";
	}
	/**
	 * 读取子元素属性
	 * @param temp 父元素
	 * @param tagName 子元素名称
	 * @param attr 子元素属性名
	 * @return 内容
	 */
	public static String readElementAttr(Element temp,String tagName,String attr){
		if(temp==null) return "";
		NodeList list = temp.getElementsByTagName(tagName);
    	if(list!=null && list.getLength()>=1){
    		Element e = (Element)list.item(0);
    		return e.getAttribute(attr);
    	}
    	return "";
	}
	/**
	 * 读取元素对象
	 * @param temp 父元素
	 * @param tagName 子元素名称
	 * @return Element
	 */
	public static Element findFirstElement(Element temp,String tagName){
		List<Element> list = findElements(temp, tagName);
    	return list==null ? null : list.get(0);
	}
	/**
	 * 读取元素对象
	 * @param temp 父元素
	 * @param tagName 子元素名称
	 * @return Element
	 */
	public static List<Element> findElements(Element temp,String tagName){
		NodeList list = temp.getElementsByTagName(tagName);
    	if(list!=null && list.getLength()>=1){
    		List<Element> listElement = new ArrayList<Element>();
    		for (int i = 0; i < list.getLength(); i++) {
    			Element e = (Element)list.item(0);
    			listElement.add(e);
			}
    		return listElement;
    	}
    	return null;
	}
}
