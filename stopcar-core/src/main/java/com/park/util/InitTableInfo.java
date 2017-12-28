package com.park.util;
/*package com.qz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.qz.core.Constants;

public class InitTableInfo {
	*//**
	 * 初始化数据库所有表信息
	 *//*
	public static  void initTables() {
		PathUtil  p = new InitTableInfo().new PathUtil();
		//获取路径
		String path = p.getWebInfPath()+Constants.tableTemplate;
		File file = new File(path);
		try {
			//新建文件
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			SqlSession sqlSession = Constants.sqlSessionFactory.openSession();
			Connection conn = sqlSession.getConnection();
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet tbSet = dmd.getTables(null, null, null,new String[] { "TABLE" });
			while (tbSet.next()) {
				//获取表名
				String name = tbSet.getString("TABLE_NAME");
				//获取列集合
				ResultSet colSet = dmd.getColumns(null, null, name, null);
				String arr = "";
				while (colSet.next()) {
					//获取列名
					String colName = colSet.getString("COLUMN_NAME");
					//获取列类型
					String typeName = colSet.getString("TYPE_NAME");
					if (!"".equals(arr)) {
						arr += ",";
					}
					arr += "{\"type\":" + getType(typeName) + ",\"name\":\""+ colName + "\"}";
				}
				System.out.println(name + "=[" + arr + "]");
				arr = "["+arr+"]";
				writeProperties(path, name, arr);
			}
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 * 获取类型
	 * 
	 * @param typeName
	 * @return
	 *//*
	private static int getType(String typeName) {
		if ("BIGINT".equals(typeName)) {
			return 1;
		} else if ("INT".equals(typeName)) {
			return 2;
		} else {
			return 0;
		}
	}
	public static void writeProperties(String filePath, String parameterName,String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos,null);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Visit " + filePath + " for updating "+ parameterName + " value error");
		}
	}
	//获取路径工具类
	public class PathUtil {
		public   String getWebInfPath(){
	        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();  
	        String path = url.toString();  
	        int index = path.indexOf("WEB-INF");  
	        if(index == -1){  
	            index = path.indexOf("classes");  
	        }  
	        if(index == -1){  
	            index = path.indexOf("bin");  
	        }  
	        path = path.substring(0, index);  
	        if(path.startsWith("zip")){//当class文件在war中时，此时返回zip:D:/...这样的路径  
	            path = path.substring(4);  
	        }else if(path.startsWith("file")){//当class文件在class文件中时，此时返回file:/D:/...这样的路径  
	            path = path.substring(6);  
	        }else if(path.startsWith("jar")){//当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径  
	            path = path.substring(10);   
	        }  
	        try {  
	            path =  URLDecoder.decode(path, "UTF-8");  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }
	        return path;  
	    }  
	}
}
*/