package com.prinzer.jweather.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @author prin
 * 
 *         2015-2-9 下午7:02:30
 * 
 *         TODO 共享参考
 */
public class JSPUtils {

	// 保存数据
	public static void save(Context context, String sp_name, String key,
			Object value) {
		SharedPreferences sp = context.getSharedPreferences(sp_name, 0);
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();
		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		}
	}

	public static void get(Context context, String sp_name, String key) {
		SharedPreferences sp = context.getSharedPreferences(sp_name, 0);
		
	}
}
