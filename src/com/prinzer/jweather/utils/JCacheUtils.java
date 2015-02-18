package com.prinzer.jweather.utils;

import java.io.File;

import com.android.volley.Cache;
import com.prinzer.jweather.FileUtils;

import android.os.Environment;
import android.provider.SyncStateContract.Constants;

/**
 * 
 * @author vancy 实现文件的缓存工具类
 */
public class JCacheUtils {

	private static final String TAG = JCacheUtils.class.getName();

	// 设置缓存的时间
	// 5分钟过期
	public static final int CONFIG_CACHE_EXPIERED_SHORT = 1000 * 60 * 5;
	// 2小时
	public static final int CONFIG_CACHE_EXPIRED_MEDIUM = 1000 * 3600 * 2;
	// 1天
	public static final int CONFIG_CACHE_EXPIRED_ML = 1000 * 3600 * 24;
	// 最大缓存 七天
	public static final int CONFIG_CACHE_EXPIRED_MAX = 1000 * 3600 * 24 * 7;

	/**
	 * 获得缓存中的数据
	 */

	public static String getCache(String path) {
		File cacheFile = getCacheFile(path);
		String result = FileUtils.readTextFile(cacheFile);
		return result;
	}

	/**
	 * 获得缓存
	 */
	public static String getUrlCache(String url,String path) {
		if (url == null) {
			return null;
		}

		String result = null;
		File cacheFile = getCacheFile(path);
		if (cacheFile.exists() && cacheFile.isFile()) {
			long expiredTime = System.currentTimeMillis()
					- cacheFile.lastModified();
			if (expiredTime < CONFIG_CACHE_EXPIRED_MEDIUM) {
				return null;
			}
			result = FileUtils.readTextFile(cacheFile);
		}
		return result;
	}

	/**
	 * 设置缓存
	 */
	public static void setUrlCache(String data,String path) {
		File cacheFile = getCacheFile(path);
		if (!cacheFile.exists() && FileUtils.isSdCardMounted()) {
			cacheFile.mkdir();
		}

		try {
			FileUtils.writeTextFile(cacheFile, data);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 获得缓存路径
	 * 
	 * @return
	 */
	public static File getCacheFile(String path) {
		String cache = null;
		if (FileUtils.isSdCardMounted()) {
			cache = Environment.getExternalStorageDirectory() + "/"+path+".txt";
		} else {
			cache = path+".txt";
		}
		return new File(cache);
	}

	/**
	 * 删除历史缓存
	 */
	public static void clearCache(File cacheFile) {
		if (cacheFile != null) {
			cacheFile.delete();
		}
	}

}
