package com.living.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.living.R;

/**
 * Author：燕青 $ on 16/5/16 14:23
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ProgressUtil {
    
    private static RelativeLayout rlLoading;
    private static CircularProgressView progressView;

    public static void loading(Activity activity, boolean flag) {
        if (rlLoading == null) {
            rlLoading = (RelativeLayout) LayoutInflater.from(activity).inflate(
                    R.layout.layout_loading, null);
            rlLoading.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        if (progressView == null)
            progressView = (CircularProgressView) rlLoading.findViewById(R.id.progress_view);
        if (flag && rlLoading.getParent() == null) {
            getContentView(activity).addView(rlLoading);
            progressView.startAnimation();
        } else {
            progressView.stopAnimation();
            getContentView(activity).removeView(rlLoading);
        }
    }

    /**
     * @return 返回ContentView的父布局FrameLayout
     */
    private static ViewGroup getContentView(Activity activity) {
        return (ViewGroup) activity.getWindow().getDecorView().findViewById(
                android.R.id.content);
    }

}
