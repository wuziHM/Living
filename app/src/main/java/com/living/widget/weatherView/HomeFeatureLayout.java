package com.living.widget.weatherView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Author：燕青 $ on 16/6/30 11:10
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HomeFeatureLayout extends ScrollView {

    private GestureDetector mGestureDetector;

    public HomeFeatureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            /**
             * if we're scrolling more closer to x direction, return false, let subview to process it
             */
            return (Math.abs(distanceY) > Math.abs(distanceX));
        }
    }
}