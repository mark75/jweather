package com.prinzer.jweather.utils;

import com.prinzer.jweather.R.string;

/**
 * 
 * @author prin
 * 
 *         2015-2-7 下午2:10:37
 * 
 *         TODO 在项目中使用到的一些静态数据
 */
public class JDataStatic {

	// 获得全国天气预警信息的URL地址
	public static final String WARNING_URL = "http://search.weather.com.cn/static/xxfb/rss/alert.xml";

	// 天气获得成功
	public static final int WEATHERER_OK = 1;
	// 城市获得成功
	public static final int CITY_OK = 2;
	// 预警信息获得成功
	public static final int WARNING_OK = 3;
	// 城市未来三小时获得成功
	public static final int FORECAST3H_OK = 4;

	// 天气获得失败
	public static final int WEATHERER_ERROR = 101;
	// 城市获得失败
	public static final int CITY_ERROR = 102;
	// 预警信息获得失败
	public static final int WARNING_ERROR = 103;
	// 城市未来三小时获得失败
	public static final int FORECAST3H_ERROR = 104;

	// SharedPrefrences

	// 共享参考的文件名
	public static final String SP_NAME = "jweather_sp";

	// 全程
	public static final String SP_NEW_CITYNAME = "new_cityname";
	// 省
	public static final String SP_NEW_PROVINCE = "new_province";
	// 市
	public static final String SP_NEW_CITY = "new_city";
	// 区
	public static final String SP_NEW_DISTRICT = "new_district";

	// 已选城市个数
	public static final String SP_SELECTED_DISTRICT_COUNT = "selected_district";
	
	// 已选城市编号
	public static final String SP_SELECTED_D1 = "selected_d1";
	public static final String SP_SELECTED_D2 = "selected_d2";
	public static final String SP_SELECTED_D3 = "selected_d3";
	public static final String SP_SELECTED_D4 = "selected_d4";
	public static final String SP_SELECTED_D5 = "selected_d5";
	public static final String SP_SELECTED_D6 = "selected_d6";
	public static final String SP_SELECTED_D7 = "selected_d7";
	public static final String SP_SELECTED_D8 = "selected_d8";
	public static final String SP_SELECTED_D9 = "selected_d9";
	public static final String SP_SELECTED_D10 = "selected_d10";
	
	//缓存文件
	public static final String SP_CACHE_TODAYFUTURE="todayfuture";
	public static final String SP_CACHE_FORECAST3H="forecast3h";
	public static final String SP_CACHE_WARNING="warning";
	
	
	//系统的包名
	public static final String PACKAGE_NAME="com.prinzer.jweather";

}
