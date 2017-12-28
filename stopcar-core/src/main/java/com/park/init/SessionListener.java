package com.park.init;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {
   public static Logger log  = LoggerFactory.getLogger(SessionListener.class);
	public void sessionCreated(HttpSessionEvent arg0) {
		log.info("开始创建SessionListener");
	}
	
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		/**by jxh 2013-6-8 去除原离线操作*/
		/*String temp = RequestUtil.valifyStr(session.getAttribute("wrado3_pack"), "");
		if(temp!=""){
			try{
				long uiid = RequestUtil.valifyInt(temp, 0);
				if(uiid>0){//	用户退出包间
					String sql = "delete from wradio3_pack_user where ui_id="+uiid;
					try{
						Constants.bean.getCoreService().executeNoRsSQL(sql);
					}catch (Exception ee) {}
				}
			}catch (Exception e) {
				try{//	游客退出
					String sql = "delete from wradio3_pack_user where ui_nickname='"+temp+"'";
					Constants.bean.getCoreService().executeNoRsSQL(sql);
				}catch (Exception ee) {}
			}
		}
		*/
		//by jxh 2013-06-08 处理游客离线的删除操作
		//处理游客离线的删除操作
/*		try {
			if(session.getAttribute("ui_nickname")==null){
				return;
			}
			String ui_nickname = session.getAttribute("ui_nickname").toString();
			if(ui_nickname != null && !"".equalsIgnoreCase(ui_nickname)){
				String ui_id = "0";
				PostMethod post = new PostMethod("http://uw.iwreader.com/wradio3_joinpack.do");
				post.addParameter("ui_id", ui_id);
				post.addParameter("pack_id", "1");
				post.addParameter("ui_nickname", URLEncoder.encode(ui_nickname, Constants.SYSTEM_CHARACTER));
				post.addParameter("type", "1");// 退出房间
				HttpUtils.executeHttpClient(post);
			}
		} catch (Throwable e) {
			log.error("处理游客离线的删除操作失败", e);
		} */
		
	}

}
