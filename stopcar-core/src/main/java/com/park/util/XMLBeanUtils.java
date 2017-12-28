package com.park.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * XML与JavaBean相互转换工具类
 * File: XMLBeanUtils.java
 * User: leizhimin
 * Date: 2008-3-5 14:28:29
 */
public final class XMLBeanUtils {
  static  Logger log = LoggerFactory.getLogger(XMLBeanUtils.class);
  /**
   * 将Bean转换为XML
   *
   * @param clazzMap 别名-类名映射Map
   * @param bean 要转换为xml的bean对象
   * @return XML字符串
   */
  public static String bean2xml(Map<String, Class<?>> clazzMap, Object bean) {
    XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
    for (Iterator<Entry<String, Class<?>>> it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry<String, Class<?>> m = (Map.Entry<String, Class<?>>) it.next();
      xstream.alias(m.getKey(), m.getValue());
    }
    String xml = xstream.toXML(bean);
    log.info(xml);
    return xml;
  }

  /**
   * 将XML转换为Bean
   *
   * @param clazzMap 别名-类名映射Map
   * @param xml 要转换为bean对象的xml字符串
   * @return Java Bean对象
   */
  public static Object xml2Bean(Map<String, Class<?>> clazzMap, String xml) {
    XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
    for (Iterator<Entry<String, Class<?>>> it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry<String, Class<?>> m = (Map.Entry<String, Class<?>>) it.next();
      xstream.alias(m.getKey(), m.getValue());
    }
    Object bean = xstream.fromXML(xml);
    return bean;
  }


  public static Map<String, String> xml2Map(String xml) {
    Map<String, String> map = new HashMap<String, String>();
    SAXReader reader = new SAXReader();
    try {
      Document doc = reader.read(new StringReader(xml));
      Element root = doc.getRootElement();

      @SuppressWarnings("unchecked")
      List<Element> list = root.elements();
      for (Element e : list) {
        map.put(e.getName(), e.getText());
      }
    } catch (Exception e) {
    	log.error("xml2Map is error", e);
    }
    return map;
  }


  /**
   * 获取XStream对象
   *
   * @param clazzMap 别名-类名映射Map
   * @return XStream对象
   */
  @SuppressWarnings("unchecked")
  public static XStream getXStreamObject(Map<String, Class> clazzMap) {
    XStream xstream = new XStream();
    for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
      xstream.alias(m.getKey(), m.getValue());
    }
    return xstream;
  }

  public static String map2xml(Map<String, String> map) {
    StringBuilder sb = new StringBuilder();
    sb.append("<xml>");
    for (String key : map.keySet()) {
      String value = "<![CDATA[" + map.get(key) + "]]>";
      sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
    }
    sb.append("</xml>");
    return sb.toString();
  }

}
