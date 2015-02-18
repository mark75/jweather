package com.prinzer.jweather.model.bean;

public class JForeCastBean {

	private String eh; // 结束小时
	private String temp1; // 低温
	private String sfdate; // 完整开始时间
	private String temp2; // 高温
	private String weatherid; // 天气标识ID
	private String weather; // 天气
	private String date; // 日期
	private String sh; // 开始小时
	private String efdate; // 完整结束时间

	public String getEh() {
		return eh;
	}

	public void setEh(String eh) {
		this.eh = eh;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getSfdate() {
		return sfdate;
	}

	public void setSfdate(String sfdate) {
		this.sfdate = sfdate;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getWeatherid() {
		return weatherid;
	}

	public void setWeatherid(String weatherid) {
		this.weatherid = weatherid;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
	}

	public String getEfdate() {
		return efdate;
	}

	public void setEfdate(String efdate) {
		this.efdate = efdate;
	}

}
