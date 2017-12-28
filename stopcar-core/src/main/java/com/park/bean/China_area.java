package com.park.bean;

import java.io.*;
import java.util.*;

//china_area
@SuppressWarnings({"serial"})
public class China_area implements Cloneable , Serializable{

    //public static String[] carrays ={"id","area_code","area_name","province_code","province_name","city_code","city_name","father"};

    public int id;//int(11)    
    public String area_code="";//varchar(50)    区/县编号
    public String area_name="";//varchar(60)    区县名称
    public String province_code="";//varchar(50)    省编号
    public String province_name="";//varchar(60)    省或者直辖市名称
    public String city_code="";//varchar(50)    市代码
    public String city_name="";//varchar(60)    市名称
    public String father="";//varchar(6)    市编号



    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id= value;
    }

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
    }

    public String getArea_name(){
        return area_name;
    }

    public void setArea_name(String value){
    	if(value == null){
           value = "";
        }
        this.area_name= value;
    }

    public String getProvince_code(){
        return province_code;
    }

    public void setProvince_code(String value){
    	if(value == null){
           value = "";
        }
        this.province_code= value;
    }

    public String getProvince_name(){
        return province_name;
    }

    public void setProvince_name(String value){
    	if(value == null){
           value = "";
        }
        this.province_name= value;
    }

    public String getCity_code(){
        return city_code;
    }

    public void setCity_code(String value){
    	if(value == null){
           value = "";
        }
        this.city_code= value;
    }

    public String getCity_name(){
        return city_name;
    }

    public void setCity_name(String value){
    	if(value == null){
           value = "";
        }
        this.city_name= value;
    }

    public String getFather(){
        return father;
    }

    public void setFather(String value){
    	if(value == null){
           value = "";
        }
        this.father= value;
    }



    public static China_area newChina_area(int id, String area_code, String area_name, String province_code, String province_name, String city_code, String city_name, String father) {
        China_area ret = new China_area();
        ret.setId(id);
        ret.setArea_code(area_code);
        ret.setArea_name(area_name);
        ret.setProvince_code(province_code);
        ret.setProvince_name(province_name);
        ret.setCity_code(city_code);
        ret.setCity_name(city_name);
        ret.setFather(father);
        return ret;    
    }

    public void assignment(China_area china_area) {
        int id = china_area.getId();
        String area_code = china_area.getArea_code();
        String area_name = china_area.getArea_name();
        String province_code = china_area.getProvince_code();
        String province_name = china_area.getProvince_name();
        String city_code = china_area.getCity_code();
        String city_name = china_area.getCity_name();
        String father = china_area.getFather();

        this.setId(id);
        this.setArea_code(area_code);
        this.setArea_name(area_name);
        this.setProvince_code(province_code);
        this.setProvince_name(province_name);
        this.setCity_code(city_code);
        this.setCity_name(city_name);
        this.setFather(father);

    }

    @SuppressWarnings("unused")
    public static void getChina_area(China_area china_area ){
        int id = china_area.getId();
        String area_code = china_area.getArea_code();
        String area_name = china_area.getArea_name();
        String province_code = china_area.getProvince_code();
        String province_name = china_area.getProvince_name();
        String city_code = china_area.getCity_code();
        String city_name = china_area.getCity_name();
        String father = china_area.getFather();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(China_area china_area){
        int id = china_area.getId();
        String area_code = china_area.getArea_code();
        String area_name = china_area.getArea_name();
        String province_code = china_area.getProvince_code();
        String province_name = china_area.getProvince_name();
        String city_code = china_area.getCity_code();
        String city_name = china_area.getCity_name();
        String father = china_area.getFather();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("area_code",area_code);
        _ret.put("area_name",area_name);
        _ret.put("province_code",province_code);
        _ret.put("province_name",province_name);
        _ret.put("city_code",city_code);
        _ret.put("city_name",city_name);
        _ret.put("father",father);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public China_area clone2(){
        try{
            return (China_area) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
