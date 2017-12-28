package com.park.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.park.constants.Constants;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dom4jUtil {

  private static Logger loger = LoggerFactory.getLogger(Dom4jUtil.class);
  private static JSONArray jsonroot = null;
  private static Map<String, JSONObject> jsonUserLevel = null;//用户等级

  public static JSONArray getJsonroot() {
    if (jsonroot == null) {
      jsonroot = new JSONArray();
      READXMLFile();
    }
    return jsonroot;
  }

  public static Map<String, JSONObject> getJsonUserLevel() {
    if (jsonUserLevel == null) {
      jsonUserLevel = new Hashtable<String, JSONObject>();
      READUserLevelXMLFile();
    }
    return jsonUserLevel;
  }


  /**
   * 读取用户等级奖励
   */
  public static void READUserLevelXMLFile() {
    try {
      String filepath = Constants.SYSTEM_ROOT_PATH + "WEB-INF/config/properties/userLevel.xml";
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(new File(filepath));
      Element root = document.getRootElement();
      for (Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
        Element el = (Element) i.next();
        String id = el.attribute("id").getValue();
        String level = el.attribute("最低领取等级").getValue();
        String gold = el.attribute("奖励金币").getValue();
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("level", level);
        json.put("gold", gold);
        getJsonUserLevel().put(id, json);
      }

    } catch (Exception ex) {
      loger.error("加载用户等级奖励模板文件失败", ex);
    }
  }


  /**
   * 读取活动礼包配置XML文件
   */
  public static void READXMLFile() {
    try {
      String filepath =
          Constants.SYSTEM_ROOT_PATH + "WEB-INF/config/properties/ActivityGiftBag.xml";
      // filepath = System.getProperty("user.dir")+"/webapp/WEB-INF/config/properties/ActivityGiftBag.xml";
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(new File(filepath));
      Element root = document.getRootElement();
      for (Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
        JSONObject json = new JSONObject();
        Element el = (Element) i.next();
        String id = el.attribute("id").getValue();
        json.put("id", id);
        Element el1 = el.element("获取条件");
        String up = el1.attribute("充值金额上限").getValue();
        String floor = el1.attribute("充值金额下限").getValue();
        json.put("floor", floor);
        json.put("up", up);

        Element el2 = el.element("奖励");
        String gold = el2.attribute("金币").getValue();
        String exper = el2.attribute("经验").getValue();
        json.put("gold", gold);
        json.put("exper", exper);

        getJsonroot().add(json);
      }

    } catch (Exception ex) {
      loger.error("加载活动奖励模板文件失败", ex);
    }
  }

  /**
   * 遍历进行处理
   */
  public static JSONObject returnResult(long money) {
    JSONObject obj = null;
    for (int i = 0; i < getJsonroot().size(); i++) {
      obj = getJsonroot().getJSONObject(i);
      long floor = obj.getLong("floor");
      long up = obj.getLong("up");//上限
      if (money > 0 && money >= floor && money < up) {
        break;
      }
    }
    return obj;

  }

  /**
   * 通过模板ID获取对应的数据
   */
  public static JSONObject returnJsonByTtemplateID(int gbid) {
    JSONObject obj = null;
    for (int i = 0; i < getJsonroot().size(); i++) {
      obj = getJsonroot().getJSONObject(i);
      if (gbid == obj.getIntValue("id")) {
        break;
      }
    }
    return obj;

  }


}
