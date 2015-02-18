package com.prinzer.jweather.db;

import java.util.List;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import com.prinzer.jweather.model.bean.JCityBean;
import com.prinzer.jweather.model.bean.JCityResultBean;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author prin
 * 
 *         2015-2-7 下午10:03:42
 * 
 *         TODO 控制数据的操作
 */
public class LitepalDBController {

	private static JCityBean jCityBean;
	private static List<JCityResultBean> jCityResultBeans;

	private LitepalDBController() {

	}

	public static LitepalDBController getInstance(JCityBean main_jCityBean) {
		jCityBean=main_jCityBean;
		jCityResultBeans = jCityBean.getResult();
		return new LitepalDBController();
	}

	public void dbController() {
		SQLiteDatabase db = Connector.getDatabase();
		addData();
//		queryData();
	}

	/**
	 * 增加数据
	 */
	private void addData() {

//		Log.i("0---","--->>"+jCityResultBeans.get(1).getDistrict());
		for (JCityResultBean jCityResultBean : jCityResultBeans) {
			DistrictTable districtTable = new DistrictTable();
			districtTable.setDistrict(jCityResultBean.getDistrict());
//			Log.i("data","---->>"+jCityResultBean.getDistrict());
			districtTable.save();

			CityTable cityTable = new CityTable();
			cityTable.setCity(jCityResultBean.getCity());
			cityTable.getDistrictTables().add(districtTable);
			cityTable.save();
			Log.i("data","---->>"+cityTable.getCity());

//
//			ProvinceTable provinceTable = new ProvinceTable();
//			provinceTable.setProvince(jCityResultBean.getProvince());
//			provinceTable.getCityTables().add(cityTable);
//			provinceTable.save();
//			
			
		}

	}

	/**
	 * 查询数据
	 */

	private void queryData() {
		// 查询出来Province表中的数据
//		List<ProvinceTable> all_provinces = DataSupport
//				.findAll(ProvinceTable.class);
		List<DistrictTable> districtTables = DataSupport.findAll(DistrictTable.class);
		List<CityTable> cityTables = DataSupport.findAll(CityTable.class);
		Log.i("data","---->>"+districtTables.size());
		Log.i("data","---->"+cityTables.size());
		
	}

}
