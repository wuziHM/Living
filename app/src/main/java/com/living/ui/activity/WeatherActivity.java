package com.living.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.adapter.ContentFragmentAdapter;
import com.living.bean.CountryWeatherBean;
import com.living.ui.fragment.ContentFragment;
import com.living.ui.fragment.ForecastFragment;
import com.living.util.Cn2SpellUtil;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.List;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.StackTransformer;

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
        String title = "ContentFragment";
        ContentFragmentAdapter.Holder holder = new ContentFragmentAdapter.Holder(getSupportFragmentManager());
        String city = getIntent().getStringExtra(LOCATION);
        holder.add(ForecastFragment.newInstance(city, 1));
        holder.add(ContentFragment.newInstance(title, 2));
        viewPager.setAdapter(holder.set());
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


}