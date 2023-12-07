package com.aia.exception;
/** 
 * api参数异常
* @author yangxy
* @version 创建时间：2023年9月7日 上午10:02:14 
*/
public class ParamException extends RuntimeException {
	private String msg;
	private String errCode;
	
	public ParamException(String msg,String errCode) {
		super(msg);
		this.msg = msg;
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	
}
