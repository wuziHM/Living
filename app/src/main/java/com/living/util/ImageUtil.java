package com.living.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/***
 * 图片操作
 */
@SuppressLint("ResourceAsColor")
public class ImageUtil {
	public static final int IMAGE_PIXELS = 5; // 图片切圆角 幅度

	private static final long MAX_PIC_SIZE = 1024 * 1024L;

	public static enum PhotoType {
		// BIG("(/small/)|(/special/)", "/big/"), // 原图
		// SMALL("(/big/)|(/special/)", "/big/"), //
		// 比BIG小，比SPECIAL大。长宽比在5以内则最小边为128px
		// SPECIAL("(/small/)|(/big/)", "/big/");
		BIG("(/small/)|(/special/)", "/big/"), // 原图
		SMALL("(/big/)|(/special/)", "/small/"); // 比BIG小，比SPECIAL大。长宽比在5以内则最小边为200px
		// SPECIAL("(/small/)|(/big/)",
		// "/special/");//最小。长宽比在5以内则最小边为50px。该选项为默认值

		public String fromRegex;
		public String toRegex;

		private PhotoType(String fromRegex, String toRegex) {
			this.fromRegex = fromRegex;
			this.toRegex = toRegex;
		}
	}

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
		Bitmap creBitmap = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		Canvas canvas = new Canvas(creBitmap);
		Paint paint = new Paint();
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.setSaturation(0);
		ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
				colorMatrix);
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
			drawable.setBounds(0, 0, (int) (imgWidth * k),
					(int) (drawable.getIntrinsicHeight() * k));
		} else {
			drawable.setBounds(0, 0, imgWidth > width ? width : imgWidth,
					imgWidth > width ? drawable.getIntrinsicHeight() * width / imgWidth : drawable.getIntrinsicHeight());
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
		Bitmap creBitmap = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
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
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		if (StringUtil.isBlank(path)) {
			return 0;
		}
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
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
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getThumbnailBitMap(Bitmap bitmap) {
		Log.d("iu", "图片剪切");
		if (bitmap == null) {
			return bitmap;
		}
		bitmap = cropBitmapPortrait(bitmap);
		return bitmap;
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
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565;
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

	/***
	 * 保存图片
	 */
	private static void insertWallpaper(Context paramContext,
			ContentValues values) {
		Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		ContentResolver localContentResolver = paramContext
				.getContentResolver();
		localContentResolver.insert(localUri, values);
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

	static ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
	static ReadLock cacheReadLock = cacheLock.readLock();
	static WriteLock cacheWriteLock = cacheLock.writeLock();

	public static void addBitmapToCache(String url, Bitmap bitmap) {
		try {
			cacheWriteLock.lock();
			if (bitmap != null) {
				// Log.d("ImageUtil", "save cache " + bitmapCache.size() + ":" +
				// url);
				bitmapCache.put(url, new SoftReference<Bitmap>(bitmap));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cacheWriteLock.unlock();
		}
	}

	public static Bitmap getBitmapCache(String url) {
		try {
			cacheReadLock.lock();
			// Log.d("ImageUtil", "get cache " + bitmapCache.size() + " :" +
			// url);
			SoftReference<Bitmap> bitmapReference = bitmapCache.get(url);
			if (bitmapReference != null) {
				Bitmap bitmap = bitmapReference.get();
				bitmapCache.remove(url);
				if (bitmap != null) {
					bitmapCache.put(url, bitmapReference);
					return bitmap;
				} else {
					bitmapCache.remove(url);
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			cacheReadLock.unlock();
		}
	}

	public synchronized static Bitmap process(Bitmap bitmap, int pixels,
			boolean square, boolean huidu, boolean yuanjiao, int px) {
		if (bitmap == null) {
			return null;
		}
		if (square) {
			bitmap = cropBitmapPortrait(bitmap);
		}
		if (huidu) {
			bitmap = removeHuiDu(bitmap);
		}
		if (yuanjiao) {
			bitmap = removeYuanjiao(bitmap, px);
		}
		return bitmap;
	}

	/***
	 * 根据资源文件获取Bitmap
	 * 
	 * @param context
	 * @param drawableId
	 * @param screenWidth
	 * @param screenHight
	 * @return
	 */
	public static Bitmap ReadBitmapById(Context context, int drawableId,
			int screenWidth, int screenHight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.ARGB_8888;
		options.inInputShareable = true;
		options.inPurgeable = true;
		InputStream stream = context.getResources().openRawResource(drawableId);
		Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
		return getBitmap(bitmap, screenWidth, screenHight);
	}

	/***
	 * 等比例压缩图片
	 * 
	 * @param bitmap
	 * @param screenWidth
	 * @param screenHight
	 * @return
	 */
	public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
			int screenHight) {
		return bitmap;
		// int w = bitmap.getWidth();
		// int h = bitmap.getHeight();
		// Matrix matrix = new Matrix();
		// float scale = (float) screenWidth / w;
		// float scale2 = (float) screenHight / h;
		// // scale = scale < scale2 ? scale : scale2;
		// // 保证图片不变形.
		// matrix.postScale(scale, scale);
		// // w,h是原图的属性.
		// return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

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
	public static Bitmap compressImageByWidth(int mWidth, Activity context,
			Bitmap bitmap) {
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