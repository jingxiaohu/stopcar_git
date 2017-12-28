package com.park.util;
//package com.qz.util;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.LoggerFactory;
//
//import com.qz.core.Constants;
//
///**
// * 
// * @Title: 处理自己必须的SQL
// * @Description: TODO	
// * @author 敬小虎
// * @date 2014年3月16日 下午3:42:34
// * @version V1.0
// */
//public class CoreSqlUtil {
//	static Logger logger = LoggerFactory.getLogger(CoreSqlUtil.class);
//	/**
//	 * 获取select count()  by sql
//	 */
//	public static int returnCountByYourSelfSql(String sql){
//	try {
//		List<Map<String,Object>> list = 	Constants.bean.getCoreService().executeSQLForList(sql);
//		if(list != null && list.size() == 1){
//			return  Integer.parseInt(list.get(0).get("count").toString());
//		}
//	} catch (Exception e) {
//		logger.error("调用CoreSqlUtil.returnCountByYourSelfSql 获取count 错误  ", e); 
//	}
//	return -1;
//	}
//}
