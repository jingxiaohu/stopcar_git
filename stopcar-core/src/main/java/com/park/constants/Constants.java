package com.park.constants;


import java.io.File;

public class Constants {

	
	
	
	/**
	 * 系统跟路径
	 */
	public static String SYSTEM_ROOT_PATH = "";
	/**
	 * 系统通用加密代码串
	 */
	public final static String SYSTEM_KEY = "!*#@()@dfgdfgFasdafaaJ*asddas499NHIJ)";

	/**
	 * 系统默认字符
	 */
	public final static String SYSTEM_CHARACTER = "utf-8";


	
	public static String tableTemplate ="WEB-INF/config/properties/TableTemplate.properties";
	

	/**
	 * 用户头像存放路径
	 */
	public static String USER_AVATAR = "img/avatar";
	/**
	 * 用户人脸存放路径
	 */
	public static String USER_FACE = "img/face";
	/**
	 * 用户身份证存放路径
	 */
	public static String USER_CARD = "img/card";
	/**
	 * 用户行驶证存放路径
	 */
	public static String USER_LIENCE = "img/lience";
	/**
	 * 用户消息图片路径
	 */
	public static String USER_MSG = "img/msg";

	/**
	 * 用户指纹静脉图片路径
	 */
	public static String FINGER_IMGPATH = "img"+ File.separator+"finger";
	
	/**
	 * 系统默认字符
	 */
	public final static String JPUSH_LOGO = "https://t12.baidu.com/it/u=330026144,767285751&fm=76";
    /**
     * 用户消息图片高
     */
	public final static int MSG_HIGHT = 800;
    /**
     * 用户消息图片宽
     */
	public final static int MSG_WIDTH = 480;
	/**
	 * 广告图片存放路径
	 */
	public static String IMG_ADVER = "img/adver";
    /**
     * 头像高
     */
	public final static int AVATAR_HIGHT = 120;
    /**
     * 头像宽
     */
	public final static int AVATAR_WIDTH = 120;
	
    /**
     * 行驶证高
     */
	public final static int LIENCE_HIGHT = 800;
    /**
     * 行驶证宽
     */
	public final static int LIENCE_WIDTH = 800;
	
    /**
     * 身份证高
     */
	public final static int CARD_HIGHT = 800;
    /**
     * 身份证宽
     */
	public final static int CARD_WIDTH = 800;
	/*****************************下面定义跟客户端交互的全局字段***************************************/
	public final static String ClientDeviceParamKey = "c_device";
		
	

	
	
	
	/**
	 * 项目真实地址
	 */
	public static String APP_PATH = null;
	/**
	 * 二维码图片存放路径
	 */
	public static String SYSTEM_QrCode_path ="QzQrCode";
	/**
	 * 热门软件图片保存文件夹
	 */
	public static String SYSTEM_HOTSOFTWARE_PICTURE = "hotsoftware_pic";
	/**
	 * 热门软件下载APP文件夹
	 */
	public static String SYSTEM_HOTSOFTWARE_APP = "hotsoftware_app";
	
}
