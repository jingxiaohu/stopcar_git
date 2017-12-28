package com.park.bean;

import java.io.*;
import java.util.*;

//pda_punch_card
@SuppressWarnings({"serial"})
public class Pda_punch_card implements Cloneable , Serializable{

    //public static String[] carrays ={"ppc_id","nd","pi_id","area_code","pu_id","pu_nd","mac","type","ontime","offtime","loginname","is_clearing","ctime","utime","worker_name","worker_no","pda_user_id","note"};

    public long ppc_id;//bigint(20)    主键ID
    public String nd="";//varchar(80)    唯一标识符ND
    public long pi_id;//bigint(20)    PDA停车场主键ID
    public String area_code="";//varchar(30)    地区地址编码
    public long pu_id;//bigint(20)    商户主键ID
    public String pu_nd="";//varchar(80)    商户ND
    public String mac="";//varchar(100)    PDA的MAC地址
    public int type;//int(11)    打卡类型0：上班打卡1：下班打卡
    public String ontime="";//varchar(30)    上班时间（9：00）
    public String offtime="";//varchar(30)    下班时间（20：00）
    public String loginname="";//varchar(100)    当前PDA登录帐号
    public int is_clearing;//int(11)    是否进行了清算（如果是上班则是上班清算如果是下班则是下班清算）0:未清算1：已经清算
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String worker_name="";//varchar(60)    PDA管理人姓名
    public String worker_no="";//varchar(60)    PDA管理人编号
    public long pda_user_id;//bigint(20)    PDA商户旗下员工管理表主键ID
    public String note="";//varchar(100)    备注



    public long getPpc_id(){
        return ppc_id;
    }

    public void setPpc_id(long value){
        this.ppc_id= value;
    }

    public String getNd(){
        return nd;
    }

    public void setNd(String value){
    	if(value == null){
           value = "";
        }
        this.nd= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
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

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
    }

    public String getPu_nd(){
        return pu_nd;
    }

    public void setPu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pu_nd= value;
    }

    public String getMac(){
        return mac;
    }

    public void setMac(String value){
    	if(value == null){
           value = "";
        }
        this.mac= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public String getOntime(){
        return ontime;
    }

    public void setOntime(String value){
    	if(value == null){
           value = "";
        }
        this.ontime= value;
    }

    public String getOfftime(){
        return offtime;
    }

    public void setOfftime(String value){
    	if(value == null){
           value = "";
        }
        this.offtime= value;
    }

    public String getLoginname(){
        return loginname;
    }

    public void setLoginname(String value){
    	if(value == null){
           value = "";
        }
        this.loginname= value;
    }

    public int getIs_clearing(){
        return is_clearing;
    }

    public void setIs_clearing(int value){
        this.is_clearing= value;
    }

    public java.util.Date getCtime(){
        return ctime;
    }

    public void setCtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.ctime= value;
    }

    public java.util.Date getUtime(){
        return utime;
    }

    public void setUtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.utime= value;
    }

    public String getWorker_name(){
        return worker_name;
    }

    public void setWorker_name(String value){
    	if(value == null){
           value = "";
        }
        this.worker_name= value;
    }

    public String getWorker_no(){
        return worker_no;
    }

    public void setWorker_no(String value){
    	if(value == null){
           value = "";
        }
        this.worker_no= value;
    }

    public long getPda_user_id(){
        return pda_user_id;
    }

    public void setPda_user_id(long value){
        this.pda_user_id= value;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }



    public static Pda_punch_card newPda_punch_card(long ppc_id, String nd, long pi_id, String area_code, long pu_id, String pu_nd, String mac, int type, String ontime, String offtime, String loginname, int is_clearing, java.util.Date ctime, java.util.Date utime, String worker_name, String worker_no, long pda_user_id, String note) {
        Pda_punch_card ret = new Pda_punch_card();
        ret.setPpc_id(ppc_id);
        ret.setNd(nd);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setMac(mac);
        ret.setType(type);
        ret.setOntime(ontime);
        ret.setOfftime(offtime);
        ret.setLoginname(loginname);
        ret.setIs_clearing(is_clearing);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setWorker_name(worker_name);
        ret.setWorker_no(worker_no);
        ret.setPda_user_id(pda_user_id);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Pda_punch_card pda_punch_card) {
        long ppc_id = pda_punch_card.getPpc_id();
        String nd = pda_punch_card.getNd();
        long pi_id = pda_punch_card.getPi_id();
        String area_code = pda_punch_card.getArea_code();
        long pu_id = pda_punch_card.getPu_id();
        String pu_nd = pda_punch_card.getPu_nd();
        String mac = pda_punch_card.getMac();
        int type = pda_punch_card.getType();
        String ontime = pda_punch_card.getOntime();
        String offtime = pda_punch_card.getOfftime();
        String loginname = pda_punch_card.getLoginname();
        int is_clearing = pda_punch_card.getIs_clearing();
        java.util.Date ctime = pda_punch_card.getCtime();
        java.util.Date utime = pda_punch_card.getUtime();
        String worker_name = pda_punch_card.getWorker_name();
        String worker_no = pda_punch_card.getWorker_no();
        long pda_user_id = pda_punch_card.getPda_user_id();
        String note = pda_punch_card.getNote();

        this.setPpc_id(ppc_id);
        this.setNd(nd);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setMac(mac);
        this.setType(type);
        this.setOntime(ontime);
        this.setOfftime(offtime);
        this.setLoginname(loginname);
        this.setIs_clearing(is_clearing);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setWorker_name(worker_name);
        this.setWorker_no(worker_no);
        this.setPda_user_id(pda_user_id);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getPda_punch_card(Pda_punch_card pda_punch_card ){
        long ppc_id = pda_punch_card.getPpc_id();
        String nd = pda_punch_card.getNd();
        long pi_id = pda_punch_card.getPi_id();
        String area_code = pda_punch_card.getArea_code();
        long pu_id = pda_punch_card.getPu_id();
        String pu_nd = pda_punch_card.getPu_nd();
        String mac = pda_punch_card.getMac();
        int type = pda_punch_card.getType();
        String ontime = pda_punch_card.getOntime();
        String offtime = pda_punch_card.getOfftime();
        String loginname = pda_punch_card.getLoginname();
        int is_clearing = pda_punch_card.getIs_clearing();
        java.util.Date ctime = pda_punch_card.getCtime();
        java.util.Date utime = pda_punch_card.getUtime();
        String worker_name = pda_punch_card.getWorker_name();
        String worker_no = pda_punch_card.getWorker_no();
        long pda_user_id = pda_punch_card.getPda_user_id();
        String note = pda_punch_card.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pda_punch_card pda_punch_card){
        long ppc_id = pda_punch_card.getPpc_id();
        String nd = pda_punch_card.getNd();
        long pi_id = pda_punch_card.getPi_id();
        String area_code = pda_punch_card.getArea_code();
        long pu_id = pda_punch_card.getPu_id();
        String pu_nd = pda_punch_card.getPu_nd();
        String mac = pda_punch_card.getMac();
        int type = pda_punch_card.getType();
        String ontime = pda_punch_card.getOntime();
        String offtime = pda_punch_card.getOfftime();
        String loginname = pda_punch_card.getLoginname();
        int is_clearing = pda_punch_card.getIs_clearing();
        java.util.Date ctime = pda_punch_card.getCtime();
        java.util.Date utime = pda_punch_card.getUtime();
        String worker_name = pda_punch_card.getWorker_name();
        String worker_no = pda_punch_card.getWorker_no();
        long pda_user_id = pda_punch_card.getPda_user_id();
        String note = pda_punch_card.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("ppc_id",ppc_id);
        _ret.put("nd",nd);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("mac",mac);
        _ret.put("type",type);
        _ret.put("ontime",ontime);
        _ret.put("offtime",offtime);
        _ret.put("loginname",loginname);
        _ret.put("is_clearing",is_clearing);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("worker_name",worker_name);
        _ret.put("worker_no",worker_no);
        _ret.put("pda_user_id",pda_user_id);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pda_punch_card clone2(){
        try{
            return (Pda_punch_card) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
