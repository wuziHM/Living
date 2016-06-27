package com.living.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.CountryWeatherBean;
import com.living.util.Cn2SpellUtil;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.List;

/**
 * Author：燕青 $ on 2016/6/13  15:16
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ForecastFragment extends BaseFragment {

    private TextView tvLocation;
    private TextView tvRefreshTime;
    private TextView tvTemperature;
    private TextView tvNowWea;
    private TextView tvWind, tvWindDes;
    private TextView tvWeaToday, tvTemToday;
    private TextView tvWeaTomorrow, tvTemTomorrow;
    public static final String CITY = "city";

    public ForecastFragment() {
    }

    public static Fragment newInstance(String title, int position) {
        Bundle args = new Bundle();
        args.putString(CITY, title);
        args.putInt("position", position);
        ForecastFragment fragment = new ForecastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        getWeather();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forecast;
    }


    private void init() {
        tvLocation = findViewById(R.id.tv_location);
        tvRefreshTime = findViewById(R.id.tv_fresh_time);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvWind = findViewById(R.id.tv_wind);
        tvWindDes = findViewById(R.id.tv_wind_des);

        tvWeaToday = findViewById(R.id.tv_wea_today);
        tvTemToday = findViewById(R.id.tv_tem_today);
        tvWeaTomorrow = findViewById(R.id.tv_wea_tomorrow);
        tvTemTomorrow = findViewById(R.id.tv_tem_tomorrow);
        tvNowWea = findViewById(R.id.tv_now_wea);

    }


    public String getTitle() {
        return getArguments().getString(CITY);
    }

    public int getPosition() {
        return getArguments().getInt("position");
    }


    public void getWeather() {
        String city = getTitle();
        city = Cn2SpellUtil.converterToSpell(city);
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
                Toast.makeText(getActivity(), "获取天气失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
