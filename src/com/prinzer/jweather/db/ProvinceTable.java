package com.prinzer.jweather.db;

import java.util.List;

import org.litepal.crud.DataSupport;

public class ProvinceTable extends DataSupport{

//	private int id;

	private String province;

	private List<CityTable> cityTables;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<CityTable> getCityTables() {
		return cityTables;
	}

	public void setCityTables(List<CityTable> cityTables) {
		this.cityTables = cityTables;
	}

}
