package com.living.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.living.R;
import com.living.adapter.ContentFragmentAdapter;
import com.living.ui.fragment.MoreDayFragment;
import com.living.ui.fragment.ForecastFragment;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class WeatherActivity extends BaseAppCompatActivity {

    public static final String LOCATION = "location";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initViewPager();
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