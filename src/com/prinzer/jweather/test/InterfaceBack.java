package com.prinzer.jweather.test;

import java.util.ArrayList;
import java.util.List;

public class InterfaceBack {

	
	public interface OnCallBack{
		public abstract void getData(List<String> datas);
 	}
	
	//设置本类中接口的属性
	public OnCallBack onCallBack;
	
	//设置外部调用该接口的方法
	public void setOnCallBack(OnCallBack onCallBack){
		this.onCallBack=onCallBack;
	}
	
	
	//将数据传入到接口的方法中
	public void test(){
		List<String> dataList=new ArrayList<String>();
		this.onCallBack.getData(dataList);
	}
	
	
	
}
