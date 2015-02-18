package com.prinzer.jweather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.prinzer.jweather.controller.IndexVPAdapter;
import com.prinzer.jweather.model.JWeatherModel2;
import com.prinzer.jweather.model.bean.JForecast3HBean;
import com.prinzer.jweather.model.bean.JweatherBean2;
import com.prinzer.jweather.model.bean.VPWeatherBean;
import com.prinzer.jweather.utils.JDataStatic;
import com.prinzer.jweather.utils.JSPUtils;

/**
 * 
 * @author prin
 * 
 *         2015-2-9 下午7:42:45
 * 
 *         TODO 主界面
 * 
 *         结构使用的是一个viewpager加上slidingmenu的布局
 * 
 *         1，获得传递来的定位信息 2，判断网络，如果有网络 就更新第一屏的气候数据 ，并将数据插入到数据库中 如果没有网络就使用数据库中的离线数据
 *         如果是第一次，则使用系统初始界面 3,获得sp中城市的信息，来进行viewpager的初始化
 */

public class IndexActivity extends Activity{

	private String district;
	private String city;
	

	private ViewPager weatherVP;
	

	private VPWeatherBean vpWeatherBean;

	private Handler mainHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {

			case JDataStatic.FORECAST3H_OK:
				// 此时获得所有的天气情况
				List<HashMap<String, List<Object>>> msglists = (List<HashMap<String, List<Object>>>) msg.obj;
				IndexVPAdapter adapter = new IndexVPAdapter(IndexActivity.this,
						msglists);
				weatherVP.setAdapter(adapter);
				break;
			default:
				break;
			}
		}

	};
	private List<String> district_lists;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);

		
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		
		weatherVP = (ViewPager) this.findViewById(R.id.vp_index_content);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		city = bundle.getString("city");
		district = bundle.getString("district");

		district_lists = new ArrayList<String>();
		district_lists.add("北京");
		district_lists.add("南京");
		district_lists.add("明光");

		// 获得数据
		JWeatherModel2 jWeatherModel2 = JWeatherModel2.getInstance(
				district_lists, mainHandler);
		jWeatherModel2.getWeatherBean();

	

	}

	

}
