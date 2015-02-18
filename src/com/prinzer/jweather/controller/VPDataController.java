package com.prinzer.jweather.controller;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.prinzer.jweather.R;
import com.prinzer.jweather.model.bean.JForeCastBean;
import com.prinzer.jweather.model.bean.JForecast3HBean;
import com.prinzer.jweather.model.bean.JFutureBean;
import com.prinzer.jweather.model.bean.JWarningItemBean;
import com.prinzer.jweather.model.bean.JweatherBean2;
import com.prinzer.jweather.utils.JDataStatic;

/**
 * 
 * @author vancy ViewPager数据填充的Controller类
 */
public class VPDataController implements OnClickListener{

	private static VPDataController vpController;
	private static Activity context;
	private static View view;
	private static String j_cityname;

	private TextView headerCityTV;
	private TextView publishTV;
	private ImageView wiconIV;
	private ImageView temp1IV;
	private ImageView temp2IV;
	private TextView weatherTV;
	private TextView windTV;
	private TextView airTV;
	private TextView warningTV;

	private TextView t1TV;
	private ImageView t1IV;
	private TextView t1_tempTV;

	private TextView t2TV;
	private ImageView t2IV;
	private TextView t2_tempTV;

	private TextView t3TV;
	private ImageView t3IV;
	private TextView t3_tempTV;

	private TextView t4TV;
	private ImageView t4IV;
	private TextView t4_tempTV;

	private TextView t5TV;
	private ImageView t5IV;
	private TextView t5_tempTV;

	private TextView d1TV;
	private ImageView d1IV;
	private TextView d1_tempTV;

	private TextView d2TV;
	private ImageView d2IV;
	private TextView d2_tempTV;

	private TextView d3TV;
	private ImageView d3IV;
	private TextView d3_tempTV;

	private TextView d4TV;
	private ImageView d4IV;
	private TextView d4_tempTV;

	private TextView d5TV;
	private ImageView d5IV;
	private TextView d5_tempTV;
	
	private static List<Object> lists;
	private JweatherBean2 jweatherBean2;
	private JForecast3HBean jForecast3HBean;
	
	
	private SlidingMenu sm;
	private ImageView menuIV, shareIV;

	private VPDataController() {

	}

	public static VPDataController getInstance(Activity activity, View mView,
			List<Object> mlists) {
		vpController = new VPDataController();
		context = activity;
		view = mView;
		lists=mlists;
		return vpController;
	}


	public View getNewDataView() {
		getView();
		jweatherBean2 = (JweatherBean2) lists.get(0);
		jForecast3HBean = (JForecast3HBean) lists.get(1);
		setTodayFutureView(jweatherBean2);
		setForecastView(jForecast3HBean);
		initSM();
		return view;
	}


	/**
	 * 设置今天和未来几天的天气UI
	 * 
	 * @param jweatherBean2
	 */
	private void setTodayFutureView(JweatherBean2 jweatherBean2) {
		// TODO Auto-generated method stub
		if (jweatherBean2.getToday() != null) {
			// 城市
			Log.i("tag", "---->data"+jweatherBean2.getToday().getCity());
			Log.i("tag", "---->data"+view.toString());
			Log.i("tag", "---->data"+headerCityTV.toString());
			headerCityTV.setText(jweatherBean2.getToday().getCity());
			Log.i("tag", "---->data");
			Log.i("tag", "---->data" + jweatherBean2.getSk().getTime());
			Log.i("tag", "---->data" + jweatherBean2.getSk().getWind_direction());
			Log.i("tag", "---->data" +publishTV.toString());
			// 发布时间
			publishTV.setText(jweatherBean2.getSk().getTime());
			// 天气的图标
			wiconIV.setImageResource(context.getResources().getIdentifier(
					"w" + jweatherBean2.getToday().getWeather_id().getFa(),
					"drawable", JDataStatic.PACKAGE_NAME));
			// 温度的图片显示
			String temp = jweatherBean2.getSk().getTemp();
			Log.i("tag", "---"+temp.length()+"");
			if (temp.length()>1) {
				temp1IV.setImageResource(context.getResources().getIdentifier(
						"j" + temp.substring(0, 1), "drawable",
						JDataStatic.PACKAGE_NAME));
				temp2IV.setImageResource(context.getResources().getIdentifier(
						"j" + temp.substring(1, 2), "drawable",
						JDataStatic.PACKAGE_NAME));
			}else {
				temp2IV.setImageResource(context.getResources().getIdentifier(
						"j" + temp, "drawable",
						JDataStatic.PACKAGE_NAME));
			}
			
			// 天气情况
			weatherTV.setText(jweatherBean2.getToday().getWeather());
			// 风向
			windTV.setText(jweatherBean2.getSk().getWind_direction());
			// 将空气质量 改为穿衣指数
			airTV.setText(jweatherBean2.getToday().getDressing_index());

			// 设置未来几天的天气情况
			List<JFutureBean> jFutureBeans = jweatherBean2.getFuture();

			//第一天
			JFutureBean jFutureBean = jFutureBeans.get(0);
			d1TV.setText("今天");
			d1IV.setImageResource(context.getResources().getIdentifier(
					"w" + jFutureBean.getWeather_id().getFa(), "drawable",
					JDataStatic.PACKAGE_NAME));

			String[] tempArr = jFutureBean.getTemperature().split("~");
			String temp_str_a = tempArr[1]
					.substring(0, tempArr[1].indexOf("℃"));
			String temp_str_b = tempArr[0]
					.substring(0, tempArr[0].indexOf("℃"));

			d1_tempTV.setText(temp_str_b + "°~" + temp_str_a + "°");

			//第二天
			JFutureBean jFutureBean1 = jFutureBeans.get(1);
			d2TV.setText("明天");
			d2IV.setImageResource(context.getResources().getIdentifier(
					"w" + jFutureBean1.getWeather_id().getFa(), "drawable",
					JDataStatic.PACKAGE_NAME));
			String[] tempArr2 = jFutureBean1.getTemperature().split("~");
			String temp_str_a2 = tempArr2[1]
					.substring(0, tempArr2[1].indexOf("℃"));
			String temp_str_b2 = tempArr2[0]
					.substring(0, tempArr2[0].indexOf("℃"));

			d2_tempTV.setText(temp_str_b2 + "°~" + temp_str_a2 + "°");

			//第三天
			JFutureBean jFutureBean2 = jFutureBeans.get(2);
			String month2 = jFutureBean2.getDate().substring(4, 6);
			String day2 = jFutureBean2.getDate().substring(6);
			d3TV.setText(month2 + "/" + day2);
			d3IV.setImageResource(context.getResources().getIdentifier(
					"w" + jFutureBean2.getWeather_id().getFa(), "drawable",
					JDataStatic.PACKAGE_NAME));
			String[] tempArr3 = jFutureBean2.getTemperature().split("~");
			String temp_str_a3 = tempArr3[1]
					.substring(0, tempArr3[1].indexOf("℃"));
			String temp_str_b3 = tempArr3[0]
					.substring(0, tempArr3[0].indexOf("℃"));

			d3_tempTV.setText(temp_str_b3+ "°~" + temp_str_a3 + "°");

			//第四天
			JFutureBean jFutureBean3 = jFutureBeans.get(3);
			String month3 = jFutureBean3.getDate().substring(4, 6);
			String day3 = jFutureBean3.getDate().substring(6);
			d4TV.setText(month3 + "/" + day3);
			d4IV.setImageResource(context.getResources().getIdentifier(
					"w" + jFutureBean3.getWeather_id().getFa(), "drawable",
					JDataStatic.PACKAGE_NAME));
			String[] tempArr4 = jFutureBean3.getTemperature().split("~");
			String temp_str_a4 = tempArr4[1]
					.substring(0, tempArr4[1].indexOf("℃"));
			String temp_str_b4 = tempArr4[0]
					.substring(0, tempArr4[0].indexOf("℃"));

			d4_tempTV.setText(temp_str_b4 + "°~" + temp_str_a4 + "°");
			
			//第五天

			JFutureBean jFutureBean5 = jFutureBeans.get(4);
			String month5 = jFutureBean5.getDate().substring(4, 6);
			String day5 = jFutureBean5.getDate().substring(6);
			d5TV.setText(month5 + "/" + day5);
			d5IV.setImageResource(context.getResources().getIdentifier(
					"w" + jFutureBean5.getWeather_id().getFa(), "drawable",
					JDataStatic.PACKAGE_NAME));
			String[] tempArr5 = jFutureBean5.getTemperature().split("~");
			String temp_str_a5 = tempArr5[1]
					.substring(0, tempArr[1].indexOf("℃"));
			String temp_str_b5 = tempArr5[0]
					.substring(0, tempArr5[0].indexOf("℃"));

			d5_tempTV.setText(temp_str_b5 + "°~" + temp_str_a5+ "°");

		}
	};

	/**
	 * 设置未来三小时的UI
	 * 
	 * @param jForecast3HBean
	 */
	private void setForecastView(JForecast3HBean jForecast3HBean) {
		List<JForeCastBean> jForeCastBeans = jForecast3HBean.getResult();

		try {

			// 第一个
			JForeCastBean jForeCastBean = jForeCastBeans.get(0);
			// 如果是现在的时间的后面才会设置
			t1TV.setText(jForeCastBean.getSh() + ":00");
			t1IV.setImageResource(context.getResources().getIdentifier(
					"w" + jForeCastBean.getWeatherid(), "drawable",
					JDataStatic.PACKAGE_NAME));
			t1_tempTV.setText(jForeCastBean.getTemp1() + "°~"
					+ jForeCastBean.getTemp2() + "°");

			// 第二个
			JForeCastBean jForeCastBean2 = jForeCastBeans.get(1);
			// 如果是现在的时间的后面才会设置
			t2TV.setText(jForeCastBean2.getSh() + ":00");
			t2IV.setImageResource(context.getResources().getIdentifier(
					"w" + jForeCastBean2.getWeatherid(), "drawable",
					JDataStatic.PACKAGE_NAME));
			t2_tempTV.setText(jForeCastBean2.getTemp1() + "°~"
					+ jForeCastBean2.getTemp2() + "°");

			// 第三个
			JForeCastBean jForeCastBean3 = jForeCastBeans.get(2);
			// 如果是现在的时间的后面才会设置
			t3TV.setText(jForeCastBean3.getSh() + ":00");
			t3IV.setImageResource(context.getResources().getIdentifier(
					"w" + jForeCastBean3.getWeatherid(), "drawable",
					JDataStatic.PACKAGE_NAME));
			t3_tempTV.setText(jForeCastBean3.getTemp1() + "°~"
					+ jForeCastBean3.getTemp2() + "°");

			// 第四个
			JForeCastBean jForeCastBean4 = jForeCastBeans.get(3);
			// 如果是现在的时间的后面才会设置
			t4TV.setText(jForeCastBean4.getSh() + ":00");
			t4IV.setImageResource(context.getResources().getIdentifier(
					"w" + jForeCastBean4.getWeatherid(), "drawable",
					JDataStatic.PACKAGE_NAME));
			t4_tempTV.setText(jForeCastBean4.getTemp1() + "°~"
					+ jForeCastBean4.getTemp2() + "°");

			// 第五个
			JForeCastBean jForeCastBean5 = jForeCastBeans.get(4);
			// 如果是现在的时间的后面才会设置
			t5TV.setText(jForeCastBean5.getSh() + ":00");
			t5IV.setImageResource(context.getResources().getIdentifier(
					"w" + jForeCastBean5.getWeatherid(), "drawable",
					JDataStatic.PACKAGE_NAME));
			t5_tempTV.setText(jForeCastBean5.getTemp1() + "°~"
					+ jForeCastBean5.getTemp2() + "°");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 设置预警信息的UI
	 * 
	 * @param jWarningItemBeans
	 */
	private void setWarningView(List<JWarningItemBean> jWarningItemBeans) {

	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_index_header_menu:
			sm.toggle();
			break;

		case R.id.iv_index_header_share:

			break;

		default:
			break;
		}
	}

	// *//**
	// * 初始化Sm 并将
	// *//*
	private void initSM() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		sm = new SlidingMenu(context);

		sm.attachToActivity(context, SlidingMenu.SLIDING_CONTENT);
		sm.setMenu(R.layout.sliding_menu);

		// 设置SM的滑动模式 左右都支持
		sm.setMode(SlidingMenu.LEFT);
		// 设置SM与主界面的边缘效果
		sm.setShadowDrawable(R.drawable.shadow);
		// 设置SM边缘效果的宽度
		sm.setShadowWidth(10);
		// 设置SM附加的主界面的宽度
		sm.setBehindOffset(269);
		// sm.setBehindWidth(500);
		// 设置划出SM的屏幕范围
		// 参数说明：SlidingMenu.TOUCHMODE_FULLSCREEN全屏都可以划出
		// SlidingMenu.TOUCHMODE_MARGIN边缘才能划出
		// SlidingMenu.TOUCHMODE_NONE任何地方都不能划出
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	}
	
	

	private void getView() {
		// TODO Auto-generated method stub
		
		menuIV = (ImageView) view.findViewById(R.id.iv_index_header_menu);
		shareIV = (ImageView) view.findViewById(R.id.iv_index_header_share);
		
		menuIV.setOnClickListener(this);
		shareIV.setOnClickListener(this);
		
		
		Log.i("tag", "------>getview");
		// 当前的城市
		headerCityTV = (TextView) view
				.findViewById(R.id.tv_index_header_city);
		
		publishTV = (TextView) view.findViewById(R.id.tv_index_item_publish);
		Log.i("tag", "------>getview"+publishTV.toString());
		wiconIV = (ImageView) view.findViewById(R.id.iv_index_item_wicon);
		temp1IV = (ImageView) view.findViewById(R.id.iv_index_item_t1);
		temp2IV = (ImageView) view.findViewById(R.id.iv_index_item_t2);
		weatherTV = (TextView) view.findViewById(R.id.tv_index_item_weather);
		windTV = (TextView) view.findViewById(R.id.tv_index_item_wind);
		airTV = (TextView) view.findViewById(R.id.tv_index_item_air);
		warningTV = (TextView) view.findViewById(R.id.tv_index_item_warnig);

		t1TV = (TextView) view.findViewById(R.id.tv_index_t1);
		t1IV = (ImageView) view.findViewById(R.id.iv_index_t1);
		t1_tempTV = (TextView) view.findViewById(R.id.tv_index_t1_temp);

		t2TV = (TextView) view.findViewById(R.id.tv_index_t2);
		t2IV = (ImageView) view.findViewById(R.id.iv_index_t2);
		t2_tempTV = (TextView) view.findViewById(R.id.tv_index_t2_temp);

		t3TV = (TextView) view.findViewById(R.id.tv_index_t3);
		t3IV = (ImageView) view.findViewById(R.id.iv_index_t3);
		t3_tempTV = (TextView) view.findViewById(R.id.tv_index_t3_temp);

		t4TV = (TextView) view.findViewById(R.id.tv_index_t4);
		t4IV = (ImageView) view.findViewById(R.id.iv_index_t4);
		t4_tempTV = (TextView) view.findViewById(R.id.tv_index_t4_temp);

		t5TV = (TextView) view.findViewById(R.id.tv_index_t5);
		t5IV = (ImageView) view.findViewById(R.id.iv_index_t5);
		t5_tempTV = (TextView) view.findViewById(R.id.tv_index_t5_temp);

		d1TV = (TextView) view.findViewById(R.id.tv_index_d1);
		d1IV = (ImageView) view.findViewById(R.id.iv_index_d1);
		d1_tempTV = (TextView) view.findViewById(R.id.tv_index_d1_temp);

		d2TV = (TextView) view.findViewById(R.id.tv_index_d2);
		d2IV = (ImageView) view.findViewById(R.id.iv_index_d2);
		d2_tempTV = (TextView) view.findViewById(R.id.tv_index_d2_temp);

		d3TV = (TextView) view.findViewById(R.id.tv_index_d3);
		d3IV = (ImageView) view.findViewById(R.id.iv_index_d3);
		d3_tempTV = (TextView) view.findViewById(R.id.tv_index_d3_temp);

		d4TV = (TextView) view.findViewById(R.id.tv_index_d4);
		d4IV = (ImageView) view.findViewById(R.id.iv_index_d4);
		d4_tempTV = (TextView) view.findViewById(R.id.tv_index_d4_temp);

		d5TV = (TextView) view.findViewById(R.id.tv_index_d5);
		d5IV = (ImageView) view.findViewById(R.id.iv_index_d5);
		d5_tempTV = (TextView) view.findViewById(R.id.tv_index_d5_temp);

	}
	
	
	

//	private void setView() {
//		// TODO Auto-generated method stub
//		// 如果有网络的话
//		int expired = JCacheUtils.CONFIG_CACHE_EXPIRED_MEDIUM;
//		File cacheFile = JCacheUtils
//				.getCacheFile(JDataStatic.SP_CACHE_TODAYFUTURE);
//
//		if (JNetUtils.checkNet(context)) {
//			// 如果缓存过期 则重新加载网络数据进行缓存cache更新
//			if (System.currentTimeMillis() - cacheFile.lastModified() > expired) {
//				setDataFromNet();
//			} else {
//				setDataFromCache();
//			}
//		} else {
//			// 只能从缓存中读取
//			if (JCacheUtils.getCache(JDataStatic.SP_CACHE_TODAYFUTURE) != null
//					&& JCacheUtils.getCache(JDataStatic.SP_CACHE_FORECAST3H) != null
//					&& JCacheUtils.getCache(JDataStatic.SP_CACHE_WARNING) != null) {
//				// 如果缓存中的数据不为空 加载缓存中的数据进行数据填充
//				setDataFromCache();
//
//			} else {
//				// 只能加载提示开启网络的界面进行重新加载
//				view = LayoutInflater.from(context).inflate(
//						R.layout.index_error, null);
//			}
//		}
//
//	}
//
//
//	private void setDataFromCache() {
//		// TODO Auto-generated method stub
//		String todayfuture_cache = JCacheUtils
//				.getCache(JDataStatic.SP_CACHE_TODAYFUTURE);
//		String forecast3h_cache = JCacheUtils
//				.getCache(JDataStatic.SP_CACHE_FORECAST3H);
//		String warning_cache = JCacheUtils
//				.getCache(JDataStatic.SP_CACHE_WARNING);
//
//		// 获得相应解析后的bean文件
//	}

}
