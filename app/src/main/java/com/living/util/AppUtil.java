package com.living.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Author：燕青 $ on 16/5/31 17:33
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class AppUtil {

    public static final String LAUNCHCOUNT = "launch_count";

    /**
     * 判断是不是第一次启动
     *
     * @param context
     * @return
     */
    public static boolean isFirstLaunch(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int count = preferences.getInt(LAUNCHCOUNT, 0);
        if (count == 0) {
            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt(LAUNCHCOUNT, 1);
            edit.commit();
            return true;
        }
        return false;
    }

    /**
     * 反射设置图片
     * @param imageView
     * @param imageName
     */
    public static void getImageByReflect(ImageView imageView, String imageName) {
        try {
            Field field = Class.forName("com.living.R$mipmap").getField("ic_" + imageName);
            imageView.setImageResource(field.getInt(field));
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

}
