package com.living.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.CountryWeatherBean;
import com.living.util.LivingNetUtils;

import java.util.List;

public class WeatherActivity extends BaseAppCompatActivity implements LocationListener {

    private TextView tvLocation;
    private TextView tvRefreshTime;
    private TextView tvTemperature;
    private TextView tvNowWea;
    private TextView tvWind, tvWindDes;
    private TextView tvWeaToday, tvTemToday;
    private TextView tvWeaTomorrow, tvTemTomorrow;
    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
        getLocation();
        getWeather();
    }

    private void init() {
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvRefreshTime = (TextView) findViewById(R.id.tv_fresh_time);
        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
        tvWind = (TextView) findViewById(R.id.tv_wind);
        tvWindDes = (TextView) findViewById(R.id.tv_wind_des);

        tvWeaToday = (TextView) findViewById(R.id.tv_wea_today);
        tvTemToday = (TextView) findViewById(R.id.tv_tem_today);
        tvWeaTomorrow = (TextView) findViewById(R.id.tv_wea_tomorrow);
        tvTemTomorrow = (TextView) findViewById(R.id.tv_tem_tomorrow);
        tvNowWea = (TextView) findViewById(R.id.tv_now_wea);
    }

    public void getLocation() {
       
    }

    public void getWeather() {
        loading(true);
        LivingNetUtils.getWeather("guangzhou", new Response.Listener<CountryWeatherBean>() {
            @Override
            public void onResponse(CountryWeatherBean response) {
                loading(false);

                CountryWeatherBean.HeWeatherEntity weatherBean = response.getHeWeather().get(0);
                tvLocation.setText(weatherBean.getBasic().getCity());
                tvRefreshTime.setText("更新时间" + weatherBean.getBasic().getUpdate().getLoc());
                tvTemperature.setText(weatherBean.getNow().getTmp() + "°");
                tvNowWea.setText(weatherBean.getNow().getCond().getTxt());
                tvWind.setText(weatherBean.getNow().getWind().getDir() + " " + weatherBean.getNow().getWind().getSc() + "级");
//                tvWindDes.setText(weatherBean.getSuggestion().getComf().getTxt());

                List<CountryWeatherBean.HeWeatherEntity.DailyForecastEntity> days = weatherBean.getDaily_forecast();
                CountryWeatherBean.HeWeatherEntity.DailyForecastEntity firstDay = days.get(0);
                tvTemToday.setText(firstDay.getTmp().getMin() + "~" + firstDay.getTmp().getMax() + "°");
                tvWeaToday.setText(firstDay.getCond().getTxt_d());

                CountryWeatherBean.HeWeatherEntity.DailyForecastEntity secDay = days.get(1);
                tvTemTomorrow.setText(secDay.getTmp().getMin() + "~" + secDay.getTmp().getMax() + "°");
                tvWeaTomorrow.setText(secDay.getCond().getTxt_d());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading(false);
                Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkLocationPermission() {
        PackageManager pkm = getPackageManager();
        boolean has_permission = (PackageManager.PERMISSION_GRANTED ==
                pkm.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, "com.living"));
        if (!has_permission) {
            return false;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}