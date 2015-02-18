package com.prinzer.jweather.db;

import org.litepal.crud.DataSupport;

public class DistrictTable extends DataSupport{

//	private int id;

	private String district;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
