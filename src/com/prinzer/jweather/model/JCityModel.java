package com.prinzer.jweather.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.prinzer.jweather.model.bean.JCityBean;
import com.prinzer.jweather.model.bean.JCityResultBean;
import com.prinzer.jweather.utils.JDataStatic;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.weather.WeatherData;

public class JCityModel {

	private List<JCityResultBean> jCityBeans;
	private static Handler handler;

	private JCityModel() {

	}

	public static JCityModel getInstance(Handler mainHandler) {
		handler = mainHandler;
		return new JCityModel();
	}

	/**
	 * 获得支持天气查询的城市
	 * 
	 * @return
	 */
	public void getCityBean() {

		WeatherData data = WeatherData.getInstance();
		jCityBeans = new ArrayList<JCityResultBean>();
		data.getCities(new JsonCallBack() {

			@Override
			public void jsonLoaded(JSONObject jsonObject) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				JCityBean jCityBean = gson.fromJson(jsonObject.toString(),
						JCityBean.class);
				if (jCityBean.getResult().size() > 0) {
					// 数据获得成功 通知activity开启一个service将获得的数据插入到数据库中
					Message message = Message.obtain();
					message.what = JDataStatic.CITY_OK;
					message.obj = jCityBean;
					handler.sendMessage(message);
				}

			}
		});

	}

}
