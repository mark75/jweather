package com.prinzer.jweather.model.bean;

import java.util.List;


/**
 * 
 * @author prin
 * 
 *         2015-2-6 上午8:43:30
 * 
 *         TODO 今天的天气情况和未来几天的天气情况
 */
public class JWeatherBean {

	// sk 实况天气
	private String wind_strength; // 当前风力
	private String temp; // 当前温度
	private String time; // 更新时间
	private String humidity; // 当前湿度
	private String wind_direction; // 风向

	// today
	private String wind; // 风向和风力
	private String uv_index; // 紫外线强度
	private String travel_index; // 旅游指数
	private String city; // 城市
	private String temperature; // 今日温度
	private String comfort_index; // 舒适度指数
	private String date_y; // 日期
	private String wash_index; // 洗车指数
	private String exercise_index; // 锻炼指数
	private String weather; // 今日天气
	private String drying_index; // 干燥指数
	private String weather_id; // 天气唯一标识
	private String dressing_advice; // 穿衣建议
	private String dressing_index; // 穿衣指数
	private String week; // 星期

	// 两个返回标识
	private int error_code;
	private String resultcode;
	
	
	//未来天气
	private List<JFutureBean> futureBeans;
	
	

	public List<JFutureBean> getFutureBeans() {
		return futureBeans;
	}

	public void setFutureBeans(List<JFutureBean> futureBeans) {
		this.futureBeans = futureBeans;
	}

	public String getWind_strength() {
		return wind_strength;
	}

	public void setWind_strength(String wind_strength) {
		this.wind_strength = wind_strength;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getUv_index() {
		return uv_index;
	}

	public void setUv_index(String uv_index) {
		this.uv_index = uv_index;
	}

	public String getTravel_index() {
		return travel_index;
	}

	public void setTravel_index(String travel_index) {
		this.travel_index = travel_index;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getComfort_index() {
		return comfort_index;
	}

	public void setComfort_index(String comfort_index) {
		this.comfort_index = comfort_index;
	}

	public String getDate_y() {
		return date_y;
	}

	public void setDate_y(String date_y) {
		this.date_y = date_y;
	}

	public String getWash_index() {
		return wash_index;
	}

	public void setWash_index(String wash_index) {
		this.wash_index = wash_index;
	}

	public String getExercise_index() {
		return exercise_index;
	}

	public void setExercise_index(String exercise_index) {
		this.exercise_index = exercise_index;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getDrying_index() {
		return drying_index;
	}

	public void setDrying_index(String drying_index) {
		this.drying_index = drying_index;
	}

	public String getWeather_id() {
		return weather_id;
	}

	public void setWeather_id(String weather_id) {
		this.weather_id = weather_id;
	}

	public String getDressing_advice() {
		return dressing_advice;
	}

	public void setDressing_advice(String dressing_advice) {
		this.dressing_advice = dressing_advice;
	}

	public String getDressing_index() {
		return dressing_index;
	}

	public void setDressing_index(String dressing_index) {
		this.dressing_index = dressing_index;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

}
