package com.prinzer.jweather.model.bean;

/**
 * 
 * @author prin
 * 
 *         2015-2-6 上午8:47:02
 * 
 *         TODO 未来天气的bean
 */
public class JFutureBean {
	private String wind; //
	private String weather;
	private JweatherIDBean weather_id;
	private String date;
	private String week;
	private String temperature;

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public JweatherIDBean getWeather_id() {
		return weather_id;
	}

	public void setWeather_id(JweatherIDBean weather_id) {
		this.weather_id = weather_id;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
