package com.living.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author：燕青 $ on 16/5/16 16:01
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MovingPictureView extends View implements Runnable {

    private float downXValue;
    private long downTime;
    private float lastTouchX, lastTouchY;
    private boolean hasShowed = false;
    private Bitmap bitmap;

    int left = 100;
    int top = 20;
    private Handler handler;
    private MoveHandler moveHandler;
    public static boolean isRunning = true;
    private int sleepSeconds;
    private int goBackType;
    private int dx = 1;
    private int dy = 1;
    public int index;
    public Thread moving;
    public boolean isstarted = false;

    public MovingPictureView(Context context, int resource, int left, int top, int sleepSeconds) {
        super(context);
        this.left = left;
        this.top = top;
        this.sleepSeconds = sleepSeconds;
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bitmap = BitmapFactory.decodeResource(getResources(), resource);
        handler = new Handler();
        moveHandler = new MoveHandler();
    }

    public void move() {
        moving = new Thread(this);
        moving.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap!=null){
            canvas.drawBitmap(bitmap,left,top,null);
        }
    }

    @Override
    public void run() {

    }

    private class MoveHandler {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean cunsumed = super.onTouchEvent(event);
        if(isClickable()){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX();
                    lastTouchY = event.getY();
                    downXValue = event.getX();
                    downTime = event.getEventTime();
                    hasShowed = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    hasShowed = moved(event);
                    break;
                case MotionEvent.ACTION_UP:
                    float currentX = event.getX();
                    long currentTime = event.getEventTime();

                    break;
            }
        }
        return cunsumed||isClickable();
    }

    private boolean moved(MotionEvent event) {

        return hasShowed||Math.abs(event.getX()-lastTouchX)>10.0||Math.abs(event.getY()-lastTouchY)>10.0;
    }
}
