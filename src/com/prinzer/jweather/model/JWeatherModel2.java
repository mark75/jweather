package com.prinzer.jweather.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.R.integer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
public class JWeatherModel2 {

	private static String cityname;
	private static Handler handler;

	private List<Object> lists = null;
	private List<HashMap<String, List<Object>>> msglists = new ArrayList<HashMap<String, List<Object>>>();
	private static List<String> district_lists;
	private int i=0;
	private WeatherData data;

	private JWeatherModel2() {

	}

	public static JWeatherModel2 getInstance(List<String> mdistrict_lists,
			Handler mainHandler) {
		district_lists = mdistrict_lists;
		handler = mainHandler;
		return new JWeatherModel2();
	}

	/**
	 * 获得天气的bean
	 * 
	 * @return
	 */
	public void getWeatherBean() {
		data = WeatherData.getInstance();

		// 获得今天的天气情况
		getTodayWeather();

	}


	/**
	 * 获得城市三小时的天气情况
	 */
	private void getForeCast3H() {
		// TODO Auto-generated method stub
		data.getForecast3h(district_lists.get(i), new JsonCallBack() {

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
						lists.add(jForecast3HBean);
						HashMap<String, List<Object>> one_dis_map = new HashMap<String, List<Object>>();
						one_dis_map.put("district",lists);
						i++;
						
						msglists.add(one_dis_map);
						if (i<district_lists.size()) {
							getTodayWeather();
						}else {
							Log.i("tag", "---i+");
							Message message = Message.obtain();
							message.what = JDataStatic.FORECAST3H_OK;
							message.obj = msglists;
							handler.sendMessage(message);
						}
						
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
	public void getTodayWeather() {
		// TODO Auto-generated method stub
		
		data.getByCitys(district_lists.get(i), 2, new JsonCallBack() {

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
					if (jweatherBean2.getFuture().size() > 0) {
						lists=new ArrayList<Object>();
						lists.add(jweatherBean2);
						getForeCast3H();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

	}
}
