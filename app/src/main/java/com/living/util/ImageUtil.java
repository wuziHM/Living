package com.living.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

/***
 * 图片操作
 */
public class ImageUtil {

	/***
	 * 给图片增加灰色
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap removeHuiDu(Bitmap bitmap) {
		// return bitmap;
		if (bitmap == null) {
			return bitmap;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap creBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(creBitmap);
		Paint paint = new Paint();
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.setSaturation(0);
		ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
		paint.setColorFilter(colorMatrixFilter);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		bitmap.recycle();
		return creBitmap;
	}

	/**
	 * 根据最大尺寸，等比缩放图片大小
	 * 
	 * @param drawable
	 * @param width
	 * @param k
	 *            当图片不足width时，拉伸系数
	 */
	public static void setDrawableBound(Drawable drawable, int width, float k) {
		int imgWidth = drawable.getIntrinsicWidth();
		if (imgWidth * k < width) {
			drawable.setBounds(0, 0, (int) (imgWidth * k), (int) (drawable.getIntrinsicHeight() * k));
		} else {
			drawable.setBounds(0, 0, imgWidth > width ? width : imgWidth, imgWidth > width ? drawable.getIntrinsicHeight() * width / imgWidth : drawable.getIntrinsicHeight());
		}
	}

	/***
	 * 为图片 切圆角
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap removeYuanjiao(Bitmap bitmap, int pixels) {
		Log.d("iu", "图片切圆角");
		// return bitmap;
		if (bitmap == null) {
			return bitmap;
		}
		final int color = 0x00424242;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap creBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas();
		canvas.drawColor(color);
		canvas.setBitmap(creBitmap);
		Paint paint = new Paint();
		RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
		float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);

		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		canvas.drawBitmap(bitmap, 0, 0, paint);
		// canvas.restore();
		bitmap.recycle();
		return creBitmap;
	}

	/***
	 * 本地读图片
	 * 
	 * @param path
	 *            图片路径
	 * @param maxSize
	 *            是否对图片压缩已防止oom，该值为图片最大占用内存空间，单位byte
	 * @return
	 */
	public static Bitmap getThumbnailBitMap(String path, long maxSize) {
		File file = new File(path);
		if (file.exists()) {
			Bitmap bitmap = null;
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			if (maxSize > 0) {
				options.inSampleSize = ((int) (file.length() / maxSize)) > 0 ? (int) (file
						.length() / maxSize) : 1;
			}
			// 获取原图 此时返回的bitmap 为空
			bitmap = BitmapFactory.decodeFile(path, options);
			return bitmap;
		} else {
			return null;
		}
	}
	/**
	 * * 旋转图片
	 * 
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 * */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/***
	 * 图片剪切
	 * 
	 * @param bmp
	 * @return
	 */
	private static Bitmap cropBitmapPortrait(Bitmap bmp) {
		if (bmp == null) {
			return bmp;
		}
		final int width = bmp.getWidth();
		final int height = bmp.getHeight();
		int size = 0;
		if (width < height) {
			size = width;
		} else {
			size = height;
		}
		Bitmap bitmap = Bitmap.createBitmap(size, size, Config.RGB_565); // 创建新图片，
		Rect r = new Rect((width - size) / 2, (height - size) / 2, width,
				height);
		// 图片要切的范围
		Rect rect = new Rect(0, 0, size, size); // 新图片的大小
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(bmp, r, rect, null); // 切割图片
		return bitmap;
	}

	/***
	 * 图片 指定大小 放大或者缩小
	 * 
	 * @param bm
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bm, int w, int h) {
		Bitmap BitmapOrg = bm;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();

		float scaleWidth = ((float) w) / width;
		float scaleHeight = ((float) h) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap
				.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
	}

	/***
	 * drawable 资源文件转换为 bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable, int width,
			int height) {
		// 取 drawable 的长宽
		width = width == 0 ? drawable.getIntrinsicWidth() : width;
		height = height == 0 ? drawable.getIntrinsicHeight() : height;

		// 取 drawable 的颜色格式
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 将bitmap切成圆状
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		if (bitmap == null) {
			return bitmap;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	private static final int HARD_CACHE_CAPACITY = 100;

	public static volatile HashMap<String, SoftReference<Bitmap>> bitmapCache = new LinkedHashMap<String, SoftReference<Bitmap>>(
			HARD_CACHE_CAPACITY / 10, 0.75f, true) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(
				Entry<String, SoftReference<Bitmap>> eldest) {
			if (size() > HARD_CACHE_CAPACITY) {
				return true;
			} else
				return false;
		}
	};

	/***
	 * 判断图片是存在
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isExist(String url) {
		File file = new File(url);
		return file.exists();
	}


	/***
	 * 二进制文件 转换为 Bitmap
	 * 
	 * @param b
	 * @return
	 */
	public static Bitmap BytesToBimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	// 根据宽度等比例放大 压缩图片
	public static Bitmap compressImageByWidth(int mWidth, Activity context, Bitmap bitmap) {
		int rWidth;
		if (mWidth == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			context.getWindowManager().getDefaultDisplay().getMetrics(dm);
			rWidth = dm.widthPixels;
		} else {
			rWidth = mWidth;
		}
		int width = bitmap.getWidth();
		float zoomScale;
		zoomScale = ((float) rWidth) / width;// 根据屏幕的宽度来缩放 或者放大
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 缩放图片动作
		matrix.postScale(zoomScale, zoomScale);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	/**
	 * 以最省内存的方式读取本地资源的图片
	 * 
	 * @param context
	 * @param bitmapString
	 * @return
	 */
	public static Bitmap readBitMap(Activity context, String bitmapString) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = null;
		try {
			is = context.getBaseContext().getAssets().open(bitmapString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BitmapFactory.decodeStream(is, null, opt);
	}
}