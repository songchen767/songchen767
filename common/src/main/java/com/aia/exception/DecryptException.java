package com.aia.exception;
/** 
 * 解密异常
* @author yangxy
* @version 创建时间：2023年9月6日 下午6:56:58 
*/
public class DecryptException extends RuntimeException {
	private String errCode;
	
	public DecryptException(String errCode) {
		super(errCode);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
