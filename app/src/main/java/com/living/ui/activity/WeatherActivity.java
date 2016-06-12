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
import com.living.util.Cn2SpellUtil;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.List;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class WeatherActivity extends BaseAppCompatActivity {

    public static final String LOCATION = "location";
    private TextView tvLocation;
    private TextView tvRefreshTime;
    private TextView tvTemperature;
    private TextView tvNowWea;
    private TextView tvWind, tvWindDes;
    private TextView tvWeaToday, tvTemToday;
    private TextView tvWeaTomorrow, tvTemTomorrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
//        getWeather();
        initViewPager();
    }


    private void initViewPager() {
        VerticalViewPager viewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        //viewPager.setPageTransformer(false, new ZoomOutTransformer());
        //viewPager.setPageTransformer(true, new StackTransformer());
        String title = "ContentFragment";
        ContentFragmentAdapter.Holder holder = new ContentFragmentAdapter.Holder(getSupportFragmentManager());


        viewPager.setAdapter(new ContentFragmentAdapter.Holder(getSupportFragmentManager())
                .add(ContentFragment.newInstance(title, 1))
                .add(ContentFragment.newInstance(title, 2))
                .add(ContentFragment.newInstance(title, 3))
                .add(ContentFragment.newInstance(title, 4))
                .add(ContentFragment.newInstance(title, 5))
                .set());
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void init() {
//        tvLocation = (TextView) findViewById(R.id.tv_location);
//        tvRefreshTime = (TextView) findViewById(R.id.tv_fresh_time);
//        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
//        tvWind = (TextView) findViewById(R.id.tv_wind);
//        tvWindDes = (TextView) findViewById(R.id.tv_wind_des);
//
//        tvWeaToday = (TextView) findViewById(R.id.tv_wea_today);
//        tvTemToday = (TextView) findViewById(R.id.tv_tem_today);
//        tvWeaTomorrow = (TextView) findViewById(R.id.tv_wea_tomorrow);
//        tvTemTomorrow = (TextView) findViewById(R.id.tv_tem_tomorrow);
//        tvNowWea = (TextView) findViewById(R.id.tv_now_wea);

    }

    public void getWeather() {
        String city = getIntent().getStringExtra(LOCATION);
        city = Cn2SpellUtil.converterToSpell(city);
        LogUtil.e("city:" + city);
        loading(true);
        LivingNetUtils.getWeather(city, new Response.Listener<CountryWeatherBean>() {
            @Override
            public void onResponse(CountryWeatherBean response) {
                loading(false);

                CountryWeatherBean.HeWeatherEntity weatherBean = response.getHeWeather().get(0);
                if (weatherBean.getStatus().equals("ok")) {
                    tvLocation.setText(weatherBean.getBasic().getCity());
                    tvRefreshTime.setText("更新时间" + weatherBean.getBasic().getUpdate().getLoc());
                    tvTemperature.setText(weatherBean.getNow().getTmp() + "°");
                    tvNowWea.setText(weatherBean.getNow().getCond().getTxt());
                    tvWind.setText(weatherBean.getNow().getWind().getDir() + " " + weatherBean.getNow().getWind().getSc() + "级");

                    List<CountryWeatherBean.HeWeatherEntity.DailyForecastEntity> days = weatherBean.getDaily_forecast();
                    CountryWeatherBean.HeWeatherEntity.DailyForecastEntity firstDay = days.get(0);
                    tvTemToday.setText(firstDay.getTmp().getMin() + "~" + firstDay.getTmp().getMax() + "°");
                    tvWeaToday.setText(firstDay.getCond().getTxt_d());

                    CountryWeatherBean.HeWeatherEntity.DailyForecastEntity secDay = days.get(1);
                    tvTemTomorrow.setText(secDay.getTmp().getMin() + "~" + secDay.getTmp().getMax() + "°");
                    tvWeaTomorrow.setText(secDay.getCond().getTxt_d());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading(false);
                Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}