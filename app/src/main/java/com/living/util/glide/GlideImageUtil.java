package com.living.util.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.living.R;
import com.living.util.ImageUtil;

public class GlideImageUtil {

	/**
	 * 设置圆角图片
	 * 
	 * @param activity
	 * @param transformations
	 *            设置圆形或者圆角或者不设置
	 * @param uri
	 * @param imageView
	 * @param defaultPhoto
	 */
	public static void setPhotoFast(Activity activity,
			BitmapTransformation transformations, String uri,
			ImageView imageView, int defaultPhoto) {
		// TODO Auto-generated constructor stub
		if (!activity.isFinishing()) {
			if (transformations == null) {
				Glide.with(activity).load(uri).centerCrop().error(defaultPhoto).crossFade(0).into(imageView);
				// Glide.with(activity).load(uri).centerCrop().error(defaultPhoto).into(imageView);
			} else {
				Glide.with(activity).load(uri).centerCrop().error(defaultPhoto).transform(transformations).into(imageView);
				// Glide.with(activity).load(uri).error(defaultPhoto).centerCrop().transform(transformations).into(imageView);
			}
		} else {
			Glide.clear(imageView);
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
	public static void setPhotoResourceId(Activity activity,
			BitmapTransformation transformations, int resourceId,
			ImageView imageView) {
		// TODO Auto-generated constructor stub
		if (activity.isFinishing()) {
			return;
		}
		Glide.with(activity).load(resourceId).transform(transformations)
				.into(imageView);
	}

	/**
	 * 设置资源图片
	 * 
	 * @param activity
	 * @param resourceId
	 * @param imageView
	 */
	public static void setPhotoResourceId(Activity activity,
			int resourceId, ImageView imageView) {
		// TODO Auto-generated constructor stub
		if (activity.isFinishing()) {
			return;
		}
		Glide.with(activity).load(resourceId).centerCrop().into(imageView);
	}

	/**
	 * 设置大图
	 * 
	 * @param activity
	 * @param transformations
	 *            设置圆形或者圆角或者不设置
	 * @param uri
	 * @param imageView
	 * @param photoType
	 */
	public static void setBigPhotoFast(Activity activity,
			BitmapTransformation transformations, String uri,
			ImageView imageView, ImageUtil.PhotoType photoType) {
		if (activity.isFinishing()) {
			return;
		}
		if (uri == null)
			return;
		uri = uri.replaceAll(photoType.fromRegex, photoType.toRegex);
		if (transformations != null) {
			Glide.with(activity).load(uri).centerCrop().transform(transformations).error(R.drawable.ic_launcher).into(imageView);
		} else {
			Glide.with(activity).load(uri).error(R.drawable.ic_launcher).into(imageView);
		}
	}

}
