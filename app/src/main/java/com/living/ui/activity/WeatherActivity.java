package com.living.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.living.R;
import com.living.adapter.ContentFragmentAdapter;
import com.living.ui.fragment.ForecastFragment;
import com.living.ui.fragment.MoreDayFragment;
import com.living.widget.weatherView.SceneSurfaceView;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class WeatherActivity extends BaseAppCompatActivity {

    private SceneSurfaceView sceneView;
    public static final String LOCATION = "location";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        sceneView = (SceneSurfaceView) findViewById(R.id.sceneView);
        initViewPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        sceneView.setSize(width, height);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sceneView.destory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initViewPager() {
        VerticalViewPager viewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        //viewPager.setPageTransformer(false, new ZoomOutTransformer());
//        viewPager.setPageTransformer(true, new StackTransformer());
        String title = "MoreDayFragment";
        ContentFragmentAdapter.Holder holder = new ContentFragmentAdapter.Holder(getSupportFragmentManager());
        String city = getIntent().getStringExtra(LOCATION);
        holder.add(ForecastFragment.newInstance(city, 1));
        holder.add(MoreDayFragment.newInstance(title, 2));
        viewPager.setAdapter(holder.set());
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


}