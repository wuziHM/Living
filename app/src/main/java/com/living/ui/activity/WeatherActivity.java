package com.living.ui.activity;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.CountryWeatherBean;
import com.living.util.LivingNetUtils;

public class WeatherActivity extends BaseAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
        getLocation();
    }

    private void init() {
    }

    public void getLocation() {
        loading(true);
        LivingNetUtils.getWeather("guangzhou", new Response.Listener<CountryWeatherBean>() {
            @Override
            public void onResponse(CountryWeatherBean response) {
                loading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading(false);
            }
        });
    }
}