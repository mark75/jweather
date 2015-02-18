package com.prinzer.jweather.model.bean;

import java.util.List;

public class JForecast3HBean {

	private String resultcode;
	private List<JForeCastBean> result;
	private String reason;
	private int error_code;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public List<JForeCastBean> getResult() {
		return result;
	}

	public void setResult(List<JForeCastBean> result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}
