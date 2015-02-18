package com.prinzer.jweather.model.bean;

import java.util.List;

public class JCityBean {

	private String resultcode;
	private String reason;
	private List<JCityResultBean> result;
	private int error_code;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<JCityResultBean> getResult() {
		return result;
	}

	public void setResult(List<JCityResultBean> result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
