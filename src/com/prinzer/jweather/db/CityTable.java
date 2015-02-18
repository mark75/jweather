package com.prinzer.jweather.db;

import java.util.List;

import org.litepal.crud.DataSupport;

public class CityTable extends DataSupport{

//	private int id;

	private String city;

	private List<DistrictTable> districtTables;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<DistrictTable> getDistrictTables() {
		return districtTables;
	}

	public void setDistrictTables(List<DistrictTable> districtTables) {
		this.districtTables = districtTables;
	}

}
