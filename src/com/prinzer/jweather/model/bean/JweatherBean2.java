package com.prinzer.jweather.model.bean;

import java.util.List;

public class JweatherBean2 {

	private JTodayBean today;

	private JSkBean sk;

	private List<JFutureBean> future;

	public JTodayBean getToday() {
		return today;
	}

	public void setToday(JTodayBean today) {
		this.today = today;
	}

	public JSkBean getSk() {
		return sk;
	}

	public void setSk(JSkBean sk) {
		this.sk = sk;
	}

	public List<JFutureBean> getFuture() {
		return future;
	}

	public void setFuture(List<JFutureBean> future) {
		this.future = future;
	}

}
