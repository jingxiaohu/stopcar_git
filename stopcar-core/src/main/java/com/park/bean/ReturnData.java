package com.park.bean;


public class ReturnData implements ReturnDataBase{

	private String status="";

	private String error="";

	private Object data="";

	private String errorcode="";
	
	
	



	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setReturnData(String status, String error, Object data,String errorcode) {
		this.status = status;
		this.error = error;
		if(data == null){
			data = "";
		}
		this.data = data;
		if(errorcode == null){
			errorcode = "";
		}
		this.errorcode=errorcode;
	}
	public void setReturnData(String status, String error, Object data) {
		this.status = status;
		this.error = error;
		this.data = data;
	}


}
