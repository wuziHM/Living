package com.living;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_news;
    //    private LinearLayout ll_weather;
    private static final String s = "{\n" +
            "    \"HeWeather data service 3.0\": [\n" +
            "        {\n" +
            "            \"aqi\": {\n" +
            "                \"city\": {\n" +
            "                    \"aqi\": \"33\",\n" +
            "                    \"co\": \"0\",\n" +
            "                    \"no2\": \"7\",\n" +
            "                    \"o3\": \"103\",\n" +
            "                    \"pm10\": \"19\",\n" +
            "                    \"pm25\": \"22\",\n" +
            "                    \"qlty\": \"优\",\n" +
            "                    \"so2\": \"2\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"basic\": {\n" +
            "                \"city\": \"北京\",\n" +
            "                \"cnty\": \"中国\",\n" +
            "                \"id\": \"CN101010100\",\n" +
            "                \"lat\": \"39.904000\",\n" +
            "                \"lon\": \"116.391000\",\n" +
            "                \"update\": {\n" +
            "                    \"loc\": \"2016-04-18 15:49\",\n" +
            "                    \"utc\": \"2016-04-18 07:49\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"daily_forecast\": [\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:31\",\n" +
            "                        \"ss\": \"18:55\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"100\",\n" +
            "                        \"code_n\": \"104\",\n" +
            "                        \"txt_d\": \"晴\",\n" +
            "                        \"txt_n\": \"阴\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-18\",\n" +
            "                    \"hum\": \"8\",\n" +
            "                    \"pcpn\": \"0.0\",\n" +
            "                    \"pop\": \"1\",\n" +
            "                    \"pres\": \"1013\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"21\",\n" +
            "                        \"min\": \"8\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"316\",\n" +
            "                        \"dir\": \"北风\",\n" +
            "                        \"sc\": \"3-4\",\n" +
            "                        \"spd\": \"12\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:30\",\n" +
            "                        \"ss\": \"18:56\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"305\",\n" +
            "                        \"code_n\": \"104\",\n" +
            "                        \"txt_d\": \"小雨\",\n" +
            "                        \"txt_n\": \"阴\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-19\",\n" +
            "                    \"hum\": \"20\",\n" +
            "                    \"pcpn\": \"1.3\",\n" +
            "                    \"pop\": \"84\",\n" +
            "                    \"pres\": \"1015\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"16\",\n" +
            "                        \"min\": \"9\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"168\",\n" +
            "                        \"dir\": \"无持续风向\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"3\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:28\",\n" +
            "                        \"ss\": \"18:57\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"101\",\n" +
            "                        \"code_n\": \"100\",\n" +
            "                        \"txt_d\": \"多云\",\n" +
            "                        \"txt_n\": \"晴\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-20\",\n" +
            "                    \"hum\": \"12\",\n" +
            "                    \"pcpn\": \"0.0\",\n" +
            "                    \"pop\": \"16\",\n" +
            "                    \"pres\": \"1011\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"26\",\n" +
            "                        \"min\": \"12\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"26\",\n" +
            "                        \"dir\": \"无持续风向\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"7\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:27\",\n" +
            "                        \"ss\": \"18:58\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"100\",\n" +
            "                        \"code_n\": \"101\",\n" +
            "                        \"txt_d\": \"晴\",\n" +
            "                        \"txt_n\": \"多云\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-21\",\n" +
            "                    \"hum\": \"11\",\n" +
            "                    \"pcpn\": \"0.0\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1003\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"29\",\n" +
            "                        \"min\": \"15\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"308\",\n" +
            "                        \"dir\": \"北风\",\n" +
            "                        \"sc\": \"3-4\",\n" +
            "                        \"spd\": \"10\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:25\",\n" +
            "                        \"ss\": \"18:59\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"100\",\n" +
            "                        \"code_n\": \"100\",\n" +
            "                        \"txt_d\": \"晴\",\n" +
            "                        \"txt_n\": \"晴\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-22\",\n" +
            "                    \"hum\": \"11\",\n" +
            "                    \"pcpn\": \"0.0\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1009\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"24\",\n" +
            "                        \"min\": \"11\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"307\",\n" +
            "                        \"dir\": \"北风\",\n" +
            "                        \"sc\": \"3-4\",\n" +
            "                        \"spd\": \"16\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:24\",\n" +
            "                        \"ss\": \"19:00\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"100\",\n" +
            "                        \"code_n\": \"100\",\n" +
            "                        \"txt_d\": \"晴\",\n" +
            "                        \"txt_n\": \"晴\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-23\",\n" +
            "                    \"hum\": \"7\",\n" +
            "                    \"pcpn\": \"0.0\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1012\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"23\",\n" +
            "                        \"min\": \"11\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"291\",\n" +
            "                        \"dir\": \"无持续风向\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"3\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"astro\": {\n" +
            "                        \"sr\": \"05:23\",\n" +
            "                        \"ss\": \"19:01\"\n" +
            "                    },\n" +
            "                    \"cond\": {\n" +
            "                        \"code_d\": \"100\",\n" +
            "                        \"code_n\": \"101\",\n" +
            "                        \"txt_d\": \"晴\",\n" +
            "                        \"txt_n\": \"多云\"\n" +
            "                    },\n" +
            "                    \"date\": \"2016-04-24\",\n" +
            "                    \"hum\": \"15\",\n" +
            "                    \"pcpn\": \"0.2\",\n" +
            "                    \"pop\": \"42\",\n" +
            "                    \"pres\": \"1010\",\n" +
            "                    \"tmp\": {\n" +
            "                        \"max\": \"26\",\n" +
            "                        \"min\": \"13\"\n" +
            "                    },\n" +
            "                    \"vis\": \"10\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"201\",\n" +
            "                        \"dir\": \"无持续风向\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"0\"\n" +
            "                    }\n" +
            "                }\n" +
            "            ],\n" +
            "            \"hourly_forecast\": [\n" +
            "                {\n" +
            "                    \"date\": \"2016-04-18 16:00\",\n" +
            "                    \"hum\": \"8\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1012\",\n" +
            "                    \"tmp\": \"21\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"304\",\n" +
            "                        \"dir\": \"西北风\",\n" +
            "                        \"sc\": \"3-4\",\n" +
            "                        \"spd\": \"21\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"date\": \"2016-04-18 19:00\",\n" +
            "                    \"hum\": \"12\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1013\",\n" +
            "                    \"tmp\": \"20\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"247\",\n" +
            "                        \"dir\": \"西南风\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"10\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"date\": \"2016-04-18 22:00\",\n" +
            "                    \"hum\": \"14\",\n" +
            "                    \"pop\": \"0\",\n" +
            "                    \"pres\": \"1015\",\n" +
            "                    \"tmp\": \"17\",\n" +
            "                    \"wind\": {\n" +
            "                        \"deg\": \"211\",\n" +
            "                        \"dir\": \"西南风\",\n" +
            "                        \"sc\": \"微风\",\n" +
            "                        \"spd\": \"8\"\n" +
            "                    }\n" +
            "                }\n" +
            "            ],\n" +
            "            \"now\": {\n" +
            "                \"cond\": {\n" +
            "                    \"code\": \"100\",\n" +
            "                    \"txt\": \"晴\"\n" +
            "                },\n" +
            "                \"fl\": \"21\",\n" +
            "                \"hum\": \"11\",\n" +
            "                \"pcpn\": \"0\",\n" +
            "                \"pres\": \"1012\",\n" +
            "                \"tmp\": \"19\",\n" +
            "                \"vis\": \"10\",\n" +
            "                \"wind\": {\n" +
            "                    \"deg\": \"340\",\n" +
            "                    \"dir\": \"北风\",\n" +
            "                    \"sc\": \"5-6\",\n" +
            "                    \"spd\": \"32\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"status\": \"ok\",\n" +
            "            \"suggestion\": {\n" +
            "                \"comf\": {\n" +
            "                    \"brf\": \"舒适\",\n" +
            "                    \"txt\": \"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。\"\n" +
            "                },\n" +
            "                \"cw\": {\n" +
            "                    \"brf\": \"较适宜\",\n" +
            "                    \"txt\": \"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"\n" +
            "                },\n" +
            "                \"drsg\": {\n" +
            "                    \"brf\": \"较舒适\",\n" +
            "                    \"txt\": \"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。\"\n" +
            "                },\n" +
            "                \"flu\": {\n" +
            "                    \"brf\": \"少发\",\n" +
            "                    \"txt\": \"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\"\n" +
            "                },\n" +
            "                \"sport\": {\n" +
            "                    \"brf\": \"较适宜\",\n" +
            "                    \"txt\": \"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意避风保暖。\"\n" +
            "                },\n" +
            "                \"trav\": {\n" +
            "                    \"brf\": \"适宜\",\n" +
            "                    \"txt\": \"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。\"\n" +
            "                },\n" +
            "                \"uv\": {\n" +
            "                    \"brf\": \"中等\",\n" +
            "                    \"txt\": \"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


    String httpUrl = "http://apis.baidu.com/heweather/weather/free";
    String httpArg = "city=beijing";
//    String jsonResult = request(httpUrl, httpArg);

    /**
     * @param httpUrl :请求接口
     * @param httpArg :参数
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
            connection.setRequestProperty("apikey", LivingNetUtils.APIKEY);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.living/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.living/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
