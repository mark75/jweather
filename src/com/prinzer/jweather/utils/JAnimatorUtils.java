package com.prinzer.jweather.utils;

import android.R.anim;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

/**
 * 
 * @author prin
 * 
 *         2015-2-10 下午4:45:05
 * 
 *         TODO 项目中使用到的属性动画
 */
public class JAnimatorUtils {

	private static AccelerateInterpolator accelerate_Interpolator = new AccelerateInterpolator();
	private static AnticipateInterpolator anticipate_Interpolator = new AnticipateInterpolator();
	private static LinearInterpolator linear_Interpolator = new LinearInterpolator();
	private static BounceInterpolator bounce_Interpolator = new BounceInterpolator();
	private static OvershootInterpolator overshoot_Interpolator = new OvershootInterpolator();

	// 菜单按钮切换
	public static void menu_open(View view){
			ObjectAnimator animator=ObjectAnimator.ofFloat(view, "rotation", 0f,90f);
			animator.setDuration(500);
			animator.setInterpolator(accelerate_Interpolator);
			animator.start();
	}
	
	public static void menu_close(View view){
		ObjectAnimator animator=ObjectAnimator.ofFloat(view, "rotation", 90f,180f);
		animator.setDuration(400);
		animator.setInterpolator(accelerate_Interpolator);
		animator.start();
	}
	

}
