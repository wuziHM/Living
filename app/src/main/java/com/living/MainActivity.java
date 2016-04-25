package com.living;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.ui.activity.NewsActivity;
import com.living.bean.CountryWeatherBean;
import com.living.bean.NewsChannelBean;
import com.living.util.LivingNetUtils;

import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        ll_news = (LinearLayout) findViewById(R.id.ll_news);
        ll_news.setOnClickListener(this);
        findViewById(R.id.ll_weather).setOnClickListener(this);
        NewsChannelBean.ShowapiResBodyBean.ChannelListBean jj = new NewsChannelBean.ShowapiResBodyBean.ChannelListBean();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_news:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;

            case R.id.ll_weather:

                TreeMap<String, String> map = new TreeMap();
                map.put("city", "beijing");

                LivingNetUtils.getWeather(new Response.Listener<CountryWeatherBean>() {
                    @Override
                    public void onResponse(CountryWeatherBean response) {

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
