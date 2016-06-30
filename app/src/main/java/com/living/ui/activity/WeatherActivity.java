package com.living.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.living.R;
import com.living.adapter.ContentFragmentAdapter;
import com.living.bean.CountryWeatherBean;
import com.living.ui.fragment.ForecastFragment;
import com.living.ui.fragment.MoreDayFragment;
import com.living.widget.weatherView.SceneSurfaceView;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class WeatherActivity extends BaseAppCompatActivity implements ForecastFragment.OnHandlerData {

    private SceneSurfaceView sceneView;
    public static final String LOCATION = "location";
    private MoreDayFragment moreDayFragment;

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
        moreDayFragment = (MoreDayFragment) MoreDayFragment.newInstance(title, 2);
        holder.add(moreDayFragment);
        viewPager.setAdapter(holder.set());
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    moreDayFragment.setAlpha(0.5f * positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                moreDayFragment.setAlpha(position * 0.5f);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onPostWeather(CountryWeatherBean.HeWeatherEntity entity) {
        moreDayFragment.setWeather(entity);
    }
}