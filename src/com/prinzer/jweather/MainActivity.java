package com.prinzer.jweather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.prinzer.jweather.utils.JDataStatic;
import com.prinzer.jweather.utils.JSPUtils;

/**
 * 
 * @author prin
 * 
 *         2015-2-9 下午4:24:30
 * 
 *         TODO 入口类
 * 
 *         主要实现 （1）引导页的效果 （2）初始化定位信息， --定位成功则获得该城市 --定位失败则如果是第一次则使用北京
 *         ---使用sp缓存下来的原来定位城市 （3）提示定位是否获得成功，但是成功与否 都跳转到IndexActivity中
 * 
 */
public class MainActivity extends Activity {

	private LocationClient client;
	private BDLocationListener listener;

	private TextView locationTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initViews();
		initLocation();

	}

	// 开启定位
	@Override
	protected void onResume() {
		initLocation();
		client.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		client.stop();
		super.onPause();
	}

	/**
	 * 实现定位城市
	 */
	private void initLocation() {
		client = new LocationClient(this);
		listener = new MyBDLocationListener();
		// client数据的设置
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		option.setOpenGps(true);
		option.setAddrType("all");

		client.setLocOption(option);
		client.registerLocationListener(listener);
	}

	private class MyBDLocationListener implements BDLocationListener {

		private Bundle index_Bundle;

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (location == null) {
				return;
			} else {
				// 完整的地址信息
				String cityname = location.getAddrStr();
				String province = location.getProvince();
				String city = location.getCity();
				String cityCode = location.getCityCode();
				String district = location.getDistrict();

				if (cityname != null && district != null && province != null
						&& city != null) {
					// 更新Sp中的定位城市数据
					JSPUtils.save(MainActivity.this, JDataStatic.SP_NAME,
							JDataStatic.SP_NEW_CITYNAME, cityname);
					JSPUtils.save(MainActivity.this, JDataStatic.SP_NAME,
							JDataStatic.SP_NEW_PROVINCE, province);
					JSPUtils.save(MainActivity.this, JDataStatic.SP_NAME,
							JDataStatic.SP_NEW_CITY, city);
					JSPUtils.save(MainActivity.this, JDataStatic.SP_NAME,
							JDataStatic.SP_NEW_DISTRICT, district);

					Toast.makeText(MainActivity.this, cityname,
							Toast.LENGTH_LONG).show();

					index_Bundle = new Bundle();
					index_Bundle.putString("district", district);
					index_Bundle.putString("city", city);

				} else {
					Toast.makeText(MainActivity.this, "定位失败喽~",
							Toast.LENGTH_LONG).show();

					// 如果定位失败

					SharedPreferences sp = getSharedPreferences(
							JDataStatic.SP_NAME, 0);
					String sp_district = sp.getString(
							JDataStatic.SP_NEW_DISTRICT, "no");
					String sp_city = sp.getString(
							JDataStatic.SP_NEW_CITY, "no");
					if (sp_district.equals("no")) {
						// 首次进入 则默认传递的是北京
						index_Bundle = new Bundle();
						index_Bundle.putString("district", "北京");
						index_Bundle.putString("city", "北京");
					} else {
						// 不是首次进入 使用sp中的数据
						index_Bundle = new Bundle();
						index_Bundle.putString("district", sp_district);
						index_Bundle.putString("city",sp_city);
					}

				}
				
				
				//开启获得获得数据的服务
			
				

				Intent index_Intent = new Intent(MainActivity.this,
						IndexActivity.class);
				index_Intent.putExtras(index_Bundle);
				
				//开启服务
				
				
				
				startActivity(index_Intent);
				// 设置界面切换的动画
				overridePendingTransition(R.anim.tran_main_enter,
						R.anim.tran_main_outer);

			}
		}
	}

	/**
	 * 初始化界面的布局
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		locationTV = (TextView) this.findViewById(R.id.tv_main_location);
	}

}
