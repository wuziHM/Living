package com.living.widget.weatherView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.living.util.LogUtil;

/**
 * Created by wujiajun
 *
 * @author 928320442@qq.com
 */
public class SceneSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private RenderThread renderThread;
    private SurfaceHolder surfaceHolder;

    public SceneSurfaceView(Context context) {
        super(context);
        LogUtil.e("一个参数的实例化");
        init();
    }

    public SceneSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.e("2个参数的实例化");
        init();
    }


    public SceneSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.e("3个参数的实例化");
        init();
    }


    public void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        LogUtil.e("surfaceCreated");
        if (renderThread == null) {
            LogUtil.e("renderThread is null");
            renderThread = new RenderThread(surfaceHolder, getContext());
            renderThread.start();
        }
    }

    int width;
    int height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("onMeasure width=" + width + ",height=" + height);
        if (renderThread != null) {
            renderThread.setWidth(width);
            renderThread.setHeight(height);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        LogUtil.e("surfaceChanged");
//        renderThread.prepare();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        LogUtil.e("surfaceDestroyed");
        renderThread.getRenderHandler().sendEmptyMessage(1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.e("onFinishInflate");
    }

    public void destory() {
        renderThread = null;
    }

    public void setSize(int width, int height) {
        LogUtil.e("setSize-->width:" + width + "   height:" + height);
        if (renderThread != null) {
            LogUtil.e("renderThread is not null");
            renderThread.setWidth(width);
            renderThread.setHeight(height);
        }
    }
}
