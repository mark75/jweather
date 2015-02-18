package com.prinzer.jweather.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.prinzer.jweather.R;

public class JListViewAdapter extends BaseAdapter implements View.OnClickListener {
    // 数据源，用于存放颜色值的。
    private List<String> colors;
    private Context mContext;
    // 屏幕宽度,由于我们用的是HorizontalScrollView,所以按钮选项应该在屏幕外
    private int mScreentWidth;
    private View view;

    /**
     * 构造方法
     * 
     * @param context
     * @param screenWidth
     */
    public JListViewAdapter(Context context, int screenWidth) {

        // 初始化
        mContext = context;
        mScreentWidth = screenWidth;

        // 填充list的内容模拟数据，否则应该异步执行
        colors = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            colors.add("item"+i);
        }
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return colors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // 如果没有设置过,初始化convertView
        if (convertView == null) {
            // 获得设置的view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_jlistview_item, parent, false);

            // 初始化holder
            holder = new ViewHolder();
            holder.hSView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);

            holder.action = convertView.findViewById(R.id.ll_action);
            holder.btOne = (Button) convertView.findViewById(R.id.button1);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv);

            // 设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
            holder.content = convertView.findViewById(R.id.ll_content);
            LayoutParams lp = holder.content.getLayoutParams();
            lp.width = mScreentWidth;

            convertView.setTag(holder);
        } else {
            // 有直接获得ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }
        // 把位置放到view中,这样点击事件就可以知道点击的是哪一条item
        holder.btOne.setTag(position);

        // 设置监听事件
        convertView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        if (view != null) {
                            ViewHolder viewHolder1 = (ViewHolder) view.getTag();
                            viewHolder1.hSView.smoothScrollTo(0, 0);
                        }
                    case MotionEvent.ACTION_UP:
                        // 获得ViewHolder
                        ViewHolder viewHolder = (ViewHolder) v.getTag();
                        view = v;
                        // 获得HorizontalScrollView滑动的水平方向值.
                        int scrollX = viewHolder.hSView.getScrollX();

                        // 获得操作区域的长度
                        int actionW = viewHolder.action.getWidth();

                        // 注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                        // 如果水平方向的移动值<操作区域的长度的一半,就复原
                        if (scrollX < actionW / 2)
                        {
                            viewHolder.hSView.smoothScrollTo(0, 0);
                        }
                        else// 否则的话显示操作区域
                        {
                            viewHolder.hSView.smoothScrollTo(actionW, 0);
                        }
                        return true;
                }
                return false;
            }
        });

        // 这里防止删除一条item后,ListView处于操作状态,直接还原
        if (holder.hSView.getScrollX() != 0) {
            holder.hSView.scrollTo(0, 0);
        }

        // 设置背景颜色,设置填充内容.
        holder.tvContent.setText("" + position);

        // 设置监听事件
        holder.btOne.setOnClickListener(this);

        return convertView;
    }

    /**
     * ViewHolder
     * 
     * @Title:
     * @Description:主要是避免了不断的view获取初始化.
     * @Author:yzy
     * @Since:2013-10-22
     */
    class ViewHolder {
        public HorizontalScrollView hSView;

        public View content;
        public TextView tvContent;

        public View action;
        public Button btOne;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.button1:
            	colors.remove(position);
                break;

            default:
                break;
        }
        // 刷新ListView内容
        notifyDataSetChanged();
    }
}