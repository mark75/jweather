package com.prinzer.jweather;

import org.json.JSONObject;
import org.litepal.LitePalApplication;


import android.app.Application;
import android.util.Log;

import com.thinkland.juheapi.common.CommonFun;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.weather.WeatherData;

/**
 * 
 * @author prin
 * 
 *         2015-2-6 上午8:26:57
 * 
 *         TODO 入口类，对一些第三方的工程进行初始化
 */
public class JApplication extends LitePalApplication {

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 进行聚合数据的初始化
		CommonFun.initialize(getApplicationContext());
		
	}
}
