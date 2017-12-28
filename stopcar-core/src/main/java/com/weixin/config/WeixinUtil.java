///**
// * 
// */
//package com.weixin.config;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Formatter;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.SortedMap;
//
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//
///**
// * @author Administrator
// *
// */
//public class WeixinUtil {
//
//	/**
//	 * 创建微信支付时需要的签名
//	 * @param characterEncoding 签名的编码格式
//	 * @param parameters 必要的参数
//	 * @param weixinPayKey 微信支付的秘钥
//	 * @return
//	 */
//	public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters, String weixinPayKey){
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            Object v = entry.getValue();
//            if(null != v && !"".equals(v) 
//                    && !"sign".equals(k) && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=" + weixinPayKey);
////        String sign = MD5Tool.MD5Encode(sb.toString(), "utf-8").toUpperCase();
////        return sign;
//        return null;
//    }
//	/**
//	 * 根据参数生成普通的签名
//	 * @param characterEncoding
//	 * @param parameters
//	 * @return
//	 * @throws NoSuchAlgorithmException 
//	 * @throws UnsupportedEncodingException 
//	 */
//	public static String createCommonSign(String characterEncoding,SortedMap<Object,Object> parameters) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            Object v = entry.getValue();
//            if(null != v && !"".equals(v) 
//                    && !"sign".equals(k) && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        
//        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//        crypt.reset();
//        crypt.update(new String(sb).substring(0, sb.length()-1).getBytes(characterEncoding));
//        return byteToHex(crypt.digest());
//    }
//	public static String getRequestXml(SortedMap<Object,Object> parameters){
//        StringBuffer sb = new StringBuffer();
//        sb.append("<xml>");
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            String v = (String)entry.getValue();
//            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
//                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
//            }else {
//                sb.append("<"+k+">"+v+"</"+k+">");
//            }
//        }
//        sb.append("</xml>");
//        return sb.toString();
//    }
//	public static Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
//        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
//
//        if(null == strxml || "".equals(strxml)) {
//            return null;
//        }
//        
//        Map<String, String> m = new HashMap<String, String>();
//        
//        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
//        SAXBuilder builder = new SAXBuilder();
//        Document doc = builder.build(in);
//        Element root = doc.getRootElement();
//        List list = root.getChildren();
//        Iterator it = list.iterator();
//        while(it.hasNext()) {
//            Element e = (Element) it.next();
//            String k = e.getName();
//            String v = "";
//            List children = e.getChildren();
//            if(children.isEmpty()) {
//                v = e.getTextNormalize();
//            } else {
//                v = WeixinUtil.getChildrenText(children);
//            }
//            
//            m.put(k, v);
//        }
//        
//        //关闭流
//        in.close();
//        
//        return m;
//    }
//    
//    /**
//     * 获取子结点的xml
//     * @param children
//     * @return String
//     */
//    public static String getChildrenText(List children) {
//        StringBuffer sb = new StringBuffer();
//        if(!children.isEmpty()) {
//            Iterator it = children.iterator();
//            while(it.hasNext()) {
//                Element e = (Element) it.next();
//                String name = e.getName();
//                String value = e.getTextNormalize();
//                List list = e.getChildren();
//                sb.append("<" + name + ">");
//                if(!list.isEmpty()) {
//                    sb.append(WeixinUtil.getChildrenText(list));
//                }
//                sb.append(value);
//                sb.append("</" + name + ">");
//            }
//        }
//        
//        return sb.toString();
//    }
//    public static String setXML(String return_code, String return_msg) {
//        return "<xml><return_code><![CDATA[" + return_code
//                + "]]></return_code><return_msg><![CDATA[" + return_msg
//                + "]]></return_msg></xml>";
//        }
//    private static String byteToHex(final byte[] hash) {
//        Formatter formatter = new Formatter();
//        for (byte b : hash)
//        {
//            formatter.format("%02x", b);
//        }
//        String result = formatter.toString();
//        formatter.close();
//        return result;
//    }
//}
