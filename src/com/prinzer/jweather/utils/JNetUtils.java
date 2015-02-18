package com.prinzer.jweather.utils;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class JNetUtils {

	public static InputStream sendStringData(String url, String data,
			String encode) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		InputStream is = null;
		// 设置需要传递的数据
		StringEntity entity;

		try {
			entity = new StringEntity(data, encode);
			httpPost.setEntity(entity);

			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得数据成功 返回输入流
				is = response.getEntity().getContent();
				return is;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 获得指定url的响应数据
	 * 
	 * @param url
	 * @return
	 */
	public static HttpResponse getNetData(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 获得响应数据成功
				return response;
			} else {
				// 获得响应数据失败
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getNetPostStirng(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		String data = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				data = EntityUtils.toString(response.getEntity(), "utf-8");
//				EntityUtils.toByteArray(response.getEntity());
//				Log.i("ll", "--->"+data);
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	public static String getNetGetStirng(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		String data = null;
		HttpGet httpGet=new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				data = EntityUtils.toString(response.getEntity(), "utf-8");
//				EntityUtils.toByteArray(response.getEntity());
//				Log.i("ll", "--->"+data);
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 获得指定网络请求的字符串数据 String
	 * 
	 * @param url
	 * @return
	 */
	public static String getNetDataString(String url) {
		// 获得网络的响应数据

		HttpResponse response = response = getNetData(url);
		try {
			String dataString = EntityUtils.toString(response.getEntity(),
					"UTF-8");
			// String dataString=EntityUtils.toString(response.getEntity());
			return dataString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得指定网络请求的字节数组数据 Byte[]
	 * 
	 * @param url
	 * @return
	 */
	public static byte[] getNetDataByte(String url) {
		// 获得网络的响应数据
		HttpResponse response = response = getNetData(url);
		try {
			byte[] dataByte = EntityUtils.toByteArray(response.getEntity());
			return dataByte;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得指定网络请求的输入流数据 InputStream
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getNetDataInputStream(String url) {
		// 获得网络的响应数据
		HttpResponse response = response = getNetData(url);
		try {
			InputStream dataInputStream = response.getEntity().getContent();
			return dataInputStream;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 检查用户的网络:是否有网络
	 */
	public static boolean checkNet(Context context) {
		// 判断：WIFI链接
		boolean isWIFI = isWIFIConnection(context);
		// 判断：Mobile链接
		boolean isMOBILE = isMOBILEConnection(context);
		if (!isWIFI && !isMOBILE) {
			return false;
		}

		return true;
	}


	/**
	 * 判断：Mobile链接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isMOBILEConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}

	/**
	 * 判断：WIFI链接
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isWIFIConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}

}
