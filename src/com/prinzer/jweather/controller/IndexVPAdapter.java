package com.prinzer.jweather.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.prinzer.jweather.R;

/**
 * 
 * @author vancy 现实天气界面的Viewpager
 */
public class IndexVPAdapter extends PagerAdapter{

	private List<View> views = null;

	private Activity context;
	


	private List<HashMap<String, List<Object>>> msglists = new ArrayList<HashMap<String, List<Object>>>();

	public IndexVPAdapter(Activity activity,
			List<HashMap<String, List<Object>>> mmsglists) {
		this.context = activity;
		msglists = mmsglists;
		views=new ArrayList<View>();
	
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		
		
		for (int i = 0; i < msglists.size(); i++) {
			View view = LayoutInflater.from(context).inflate(
					R.layout.index_vp_item, null);
			VPDataController controller = VPDataController.getInstance(context,view,
					msglists.get(i).get("district"));
			view=controller.getNewDataView();
			views.add(view);
		}

	}
	

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View view=views.get(position);
		container.addView(view);
		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(views.get(position));
	}

}
