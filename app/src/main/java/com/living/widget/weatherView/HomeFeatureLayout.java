package com.living.widget.weatherView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.living.impl.ScrollViewListener;
import com.living.ui.fragment.BaseFragment;
import com.living.util.LogUtil;

/**
 * Author：燕青 $ on 16/6/30 11:10
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HomeFeatureLayout extends ScrollView {

    private GestureDetector mGestureDetector;
    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;
    private float t = 5;
    private ScrollViewListener listener;
    private boolean canScroll;

    public HomeFeatureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    public ScrollViewListener getListener() {
        return listener;
    }

    public void setListener(ScrollViewListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        this.t = t;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean flag = super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);

        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0;
                xLast = ev.getX();
                yLast = ev.getY();
                canScroll = true;
                break;

            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance = Math.abs(curX - xLast);
                yDistance = Math.abs(curY - yLast);
                if (canScroll && t == 0 && listener != null & yDistance > 200) {
                    canScroll = false;
                    listener.onScrollChanged(xDistance, yDistance);
                }

                break;
        }


        return super.onTouchEvent(ev);

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