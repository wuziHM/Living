package com.living;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.activity.NewsActivity;
import com.living.bean.CountryWeatherBean;
import com.living.bean.NewsChannelBean;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll_news;
//    private LinearLayout ll_weather;

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
        switch (v.getId()){
            case R.id.ll_news:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;

            case R.id.ll_weather:

                TreeMap<String,String> map = new TreeMap();
                map.put("city","beijing");

                LivingNetUtils.getWeather(new Response.Listener<CountryWeatherBean>() {
                    @Override
                    public void onResponse(CountryWeatherBean response) {
                        LogUtil.e("onResponse  success");
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LogUtil.e("onResponse  VolleyError");

                    }
                },map);

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        LogUtil.e("xxxxxxxxxx");
//
//                        String result = request(httpUrl, httpArg);
//                    }
//                }).start();


                break;
            default:
                break;
        }
    }



    String httpUrl = "http://apis.baidu.com/heweather/weather/free";
    String httpArg = "city=beijing";
//    String jsonResult = request(httpUrl, httpArg);

    /**
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  LivingNetUtils.APIKEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("访问失败");
        }
//        LogUtil.e("访问成功:"+result);
        return result;
    }
}
