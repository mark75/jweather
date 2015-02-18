package com.prinzer.jweather.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prinzer.jweather.model.bean.JWarningItemBean;
import com.prinzer.jweather.model.bean.VPWeatherBean;
import com.prinzer.jweather.utils.JCacheUtils;
import com.prinzer.jweather.utils.JDataStatic;
import com.prinzer.jweather.utils.JNetUtils;

/**
 * 
 * @author prin
 * 
 *         2015-2-7 下午4:09:46
 * 
 *         TODO 获得天气预警信息
 */
public class JWarningModel {

	private static Handler handler;
	private static Context context;

	private static VPWeatherBean vpWeatherBean;

	private JWarningModel() {

	}

	public static JWarningModel getInstance(Handler mainhandler,
			Context maincontext) {
		handler = mainhandler;
		context = maincontext;
		return new JWarningModel();
	}
	// 获得预警信息的bean
	public void getWarning() {
		// 使用volley获得网络上的xml数据

		// 1.首先需要获得一个RequestQueue的对象
		RequestQueue mQueue = Volley.newRequestQueue(context);
		// 创建一个StringRequest对象
		StringRequest stringRequest = new StringRequest(Method.GET,
				JDataStatic.WARNING_URL, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.i("warning", "-->" + response);
						parseWarningXml(response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.i("warning", "-->  NO");
					}
				});

		// 将StringRequest添加到RequestQueue中
		mQueue.add(stringRequest);

	}

	/**
	 * 自己的网络获得数据
	 */
	public void getWarning2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String xml = JNetUtils.getNetGetStirng(JDataStatic.WARNING_URL);
				JCacheUtils.setUrlCache(xml, JDataStatic.SP_CACHE_WARNING);
				parseWarningXml(xml);
			}
		}).start();
	}

	/**
	 * 解析xml
	 */
	private void parseWarningXml(String xml) {
		List<JWarningItemBean> jWarningItemBeans = null;
		boolean title_flag = false; // 解决重复的title结点带来的错误
		try {
			XmlPullParser parser = Xml.newPullParser();
			JWarningItemBean jWarningItemBean = null;
			parser.setInput(new StringReader(xml));
			int type = parser.getEventType();
			while (type != XmlPullParser.END_DOCUMENT) {

				switch (type) {
				case XmlPullParser.START_DOCUMENT:
					jWarningItemBeans = new ArrayList<JWarningItemBean>();
					break;

				case XmlPullParser.START_TAG:
					if (parser.getName().equals("item")) {
						title_flag = true;
						jWarningItemBean = new JWarningItemBean();
					} else if (parser.getName().equals("title")
							&& title_flag == true) {
						parser.next();
						jWarningItemBean.setTitle(parser.getText());
					} else if (parser.getName().equals("link")
							&& title_flag == true) {
						parser.next();
						jWarningItemBean.setLink(parser.getText());
					} else if (parser.getName().equals("atype")) {
						parser.next();
						jWarningItemBean.setAtype(parser.getText());
					} else if (parser.getName().equals("alevel")) {
						parser.next();
						jWarningItemBean.setAlevel(parser.getText());
					} else if (parser.getName().equals("astatus")) {
						parser.next();
						jWarningItemBean.setAstatus(parser.getText());
					} else if (parser.getName().equals("img")) {
						parser.next();
						jWarningItemBean.setImg(parser.getText());
					} else if (parser.getName().equals("description")) {
						parser.next();
						jWarningItemBean.setDescription(parser.getText());
					} else if (parser.getName().equals("pubDate")) {
						parser.next();
						jWarningItemBean.setPubDate(parser.getText());
					}
					break;

				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						jWarningItemBeans.add(jWarningItemBean);
					}
					break;

				default:
					break;
				}
				type = parser.next();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jWarningItemBeans.size() > 0) {
			Message message = Message.obtain();
			message.what = JDataStatic.WARNING_OK;
			message.obj = jWarningItemBeans;
			handler.sendMessage(message);
		}

	}

}
