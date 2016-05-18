package com.living.util.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Glide图片设置工具类
 *
 * @author lijunbin on 2016-04-27.
 *
 */
public class GlideImageUtil {

	/**
	 * 快速设置图片
	 * @param activity 上下文
	 * @param transformations 设置圆形或者圆角或者不设置
	 * @param uri 图片地址
	 * @param imageView 显示控件
     * @param defaultPhoto 加载失败时显示的占位图
     */
	public static void setPhotoFast(Activity activity, BitmapTransformation transformations, String uri, ImageView imageView, int defaultPhoto) {
		if (activity.isFinishing()) {
			return;
		}
		if (transformations != null) {
			Glide.with(activity).load(uri).centerCrop().transform(transformations).error(defaultPhoto).into(imageView);
		} else {
			Glide.with(activity).load(uri).centerCrop().error(defaultPhoto).crossFade(0).into(imageView);
		}
	}

	/**
	 * 设置资源图片
	 * 
	 * @param activity
	 * @param transformations
	 *            设置圆形或者圆角或者不设置
	 * @param resourceId
	 * @param imageView
	 */
	public static void setPhotoResourceId(Activity activity, BitmapTransformation transformations, int resourceId, ImageView imageView) {
		if (activity.isFinishing()) {
			return;
		}
		if(transformations == null){
            //crossFade()默认动画，fitCenter()，placeholder()占位图，error()错误图
//			Glide.with(activity).load(resourceId).placeholder(R.drawable.loading_ios).error(R.drawable.ic_launcher).crossFade().into(imageView);
			Glide.with(activity).load(resourceId).centerCrop().into(imageView);
		}else{
			Glide.with(activity).load(resourceId).transform(transformations).into(imageView);
		}
	}

}
