package com.living.util.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class GlideCircleTransform extends BitmapTransformation {

	private static final int DEFAULT_BORDER_WIDTH = 1;
	private static final int DEFAULT_BORDER_COLOR = Color.parseColor("#e8ded8");

	private static int mBorderColor = DEFAULT_BORDER_COLOR;
	private static int mBorderWidth = DEFAULT_BORDER_WIDTH;

	private static GlideCircleTransform circleTransform;

	public static GlideCircleTransform getInstance(Activity activity) {
		if (circleTransform == null) {
			circleTransform = new GlideCircleTransform(activity);
		}
		mBorderColor = DEFAULT_BORDER_COLOR;
		mBorderWidth = DEFAULT_BORDER_WIDTH;
		return circleTransform;
	}

	public static GlideCircleTransform getInstance(Activity activity,
			int color, int width) {
		if (circleTransform == null) {
			circleTransform = new GlideCircleTransform(activity);
		}
		mBorderColor = color;
		mBorderWidth = width;
		return circleTransform;
	}

	public GlideCircleTransform(Context context) {
		super(context);
	}

	protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
			int outWidth, int outHeight) {
		return circleCrop(pool, toTransform);
	}

	private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
		if (source == null)
			return null;
		int size = Math.min(source.getWidth(), source.getHeight());
		int x = (source.getWidth() - size) / 2;
		int y = (source.getHeight() - size) / 2;
		// TODO this could be acquired from the pool too
		Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
		Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
		if (result == null) {
			result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		}
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP,
				BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);
		// Paint mBorderPaint = new Paint();
		// mBorderPaint.setStyle(Paint.Style.STROKE);
		// mBorderPaint.setAntiAlias(true);
		// mBorderPaint.setColor(mBorderColor);
		// mBorderPaint.setStrokeWidth(mBorderWidth);
		// canvas.drawCircle(r, r, r, mBorderPaint);
		return result;
	}

	@Override
	public String getId() {
		return getClass().getName();
	}
}