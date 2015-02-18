package com.prinzer.jweather.model;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.prinzer.jweather.model.bean.JForecast3HBean;
import com.prinzer.jweather.model.bean.JweatherBean2;
import com.prinzer.jweather.utils.JCacheUtils;
import com.prinzer.jweather.utils.JDataStatic;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.air.AirData;
import com.thinkland.juheapi.data.weather.WeatherData;

/**
 * 
 * @author prin
 * 
 *         2015-2-6 上午8:33:55
 * 
 *         TODO 获得网络层的数据 抽象模型类 返回bean数据
 * 
 *         获得今天天气 和未来几天天气 和城市三小时天气情况
 */
public class JWeatherModel {

	private static String cityname;
	private static Handler handler;

	private JWeatherModel() {

	}

	public static JWeatherModel getInstance(String j_cityname,
			Handler mainHandler) {
		cityname = j_cityname;
		handler = mainHandler;
		return new JWeatherModel();
	}

	/**
	 * 获得天气的bean
	 * 
	 * @return
	 */
	public void getWeatherBean() {
		WeatherData data = WeatherData.getInstance();

		// 获得今天的天气情况
		getTodayWeather(data);

		// 获得未来三小时的天气情况
		getForeCast3H(data);

		// 获得城市的空气质量
		AirData airData = AirData.getInstance();
		getAirData(airData);
	}

	/**
	 * 获得城市的空气质量
	 * 
	 * 发现有些地区没有，那就在数据库中检索支持的上一级地区的空气质量
	 * 
	 * @param airData
	 */
	private void getAirData(AirData airData) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获得城市三小时的天气情况
	 */
	private void getForeCast3H(WeatherData data) {
		// TODO Auto-generated method stub
		data.getForecast3h(cityname, new JsonCallBack() {

			@Override
			public void jsonLoaded(JSONObject jsonObject) {
				// TODO Auto-generated method stub
				// 设置缓存 需要根据文件设置对应的缓存
				JCacheUtils.setUrlCache(jsonObject.toString(),
						JDataStatic.SP_CACHE_FORECAST3H);
				try {
					Gson gson = new Gson();
					JForecast3HBean jForecast3HBean = gson.fromJson(
							jsonObject.toString(), JForecast3HBean.class);
					
					if (jForecast3HBean.getResult().size() > 0) {
						Message message = Message.obtain();
						message.what = JDataStatic.FORECAST3H_OK;
						message.obj = jForecast3HBean;
						handler.sendMessage(message);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * 获得今天和未来几天的天气情况
	 */
	public void getTodayWeather(WeatherData data) {
		// TODO Auto-generated method stub

		data.getByCitys(cityname, 2, new JsonCallBack() {

			@Override
			public void jsonLoaded(JSONObject jsonObject) {
				// 获得数据
				JCacheUtils.setUrlCache(jsonObject.toString(),
						JDataStatic.SP_CACHE_TODAYFUTURE);
				try {
					String resultcode = jsonObject.getString("resultcode");
					int error_code = jsonObject.getInt("error_code");
					JSONObject resultObject = jsonObject
							.getJSONObject("result");
					// Log.i("wea", "--->"+resultObject.toString());
					Gson gson = new Gson();
					JweatherBean2 jweatherBean2 = gson.fromJson(
							resultObject.toString(), JweatherBean2.class);
					// 这就获得了天气信息
					Message message = Message.obtain();
					if (jweatherBean2.getFuture().size() > 0) {
						message.what = JDataStatic.WEATHERER_OK;
						message.obj = jweatherBean2;
						handler.sendMessage(message);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

	}
}
