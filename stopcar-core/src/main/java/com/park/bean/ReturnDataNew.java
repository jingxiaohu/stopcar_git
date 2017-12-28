package com.park.bean;


/**
 * {"data":"正确后的数据","errorcode":"当errorno=0的时候  区分错误类别  例如：0: 用户不存在  1:用户余额不足 ","errormsg":"错误信息","errorno":"错误代码 0：正确  1：有错误"}

 * @author jingxiaohu
 *
 */
public class ReturnDataNew implements ReturnDataBase{

	private String errorno="";

	private String errormsg="";

	private Object data="";
	
	private String errorcode="";
	
	
	






	public String getErrorno() {
		return errorno;
	}


	public void setErrorno(String errorno) {
		this.errorno = errorno;
	}


	public String getErrormsg() {
		return errormsg;
	}


	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public void setReturnData(String errorno, String errormsg, Object data,String errorcode) {
		this.errorno = errorno;
		this.errormsg = errormsg;
		if(errorcode == null){
			errorcode = "";
		}
		this.errorcode = errorcode;
		if(data == null){
			data = "";
		}
		this.data = data;
	}


	public void setReturnData(String errorno, String errormsg, Object data) {
		this.errorno = errorno;
		this.errormsg = errormsg;
		if(data == null){
			data = "";
		}
		this.data = data;
	}

    public String getErrorcode() {
		return errorcode;
	}


	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}


	/*public static void main(String[] args) {
    	ReturnDataNew ss = new ReturnDataNew();
    	ss.setReturnData("错误代码 0：正确  1：有错误", "错误信息", "正确后的数据", "当errorno=0的时候  区分错误类别  例如：0: 用户不存在  1:用户余额不足 ");
		System.out.println(JSON.toJSONString(ss));
	}*/
}
