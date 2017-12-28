package com.park.mvc.action.v1.param;
/**
 * 定义公共参数
 * @author jingxiaohu
 *
 */
public class BaseParam {
	/**
	 * 系统请求接口公有参数
	 */
	public long ui_id;//用户ID
	public String ui_nd;//用户UUID
	public String sign;//md5散列后的值
	public int dtype;//从什么设备发出的请求 0:android 1:ios  2:PDA 3:web 
	public int page=1;//页码
	public int size=20;//条数
	public int vnum;//当前内部版本号
	public String token;//用户登陆后的令牌
	

	/**
	 * true：合法请求  false ：非法请求
	 * @return
	 */
	public boolean checkRequest(){
		if(null == sign || "".equals(sign)){ 
			return false;
		}
		return true; 
	}
	
	 /*****************get set **********************/
	
		public long getUi_id() {
			return ui_id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public int getVnum() {
		return vnum;
	    }

		public void setVnum(int vnum) {
			this.vnum = vnum;
		}

		public void setUi_id(long ui_id) {
			this.ui_id = ui_id;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public int getDtype() {
			return dtype;
		}
		public void setDtype(int dtype) {
			this.dtype = dtype;
		}

		public String getUi_nd() {
			return ui_nd;
		}

		public void setUi_nd(String ui_nd) {
			this.ui_nd = ui_nd;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		
}
