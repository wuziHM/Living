package com.living.util;


import com.android.volley.Request;
import com.android.volley.Response;
import com.living.bean.CountryWeatherBean;
import com.living.bean.NewsChannelBean;
import com.living.net.LivingRequest;
import com.living.net.VolleySingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class LivingNetUtils {
    private static final String URL_BASE = "http://58.252.5.166:10008";
    private static final String URL_NEWS_CHANNEL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
//    private static final String URL_WEAHTER = "http://apis.baidu.com/heweather/weather/free";
    private static final String URL_WEAHTER = "http://apis.baidu.com/heweather/weather/free?city=beijing";
    public static final String APIKEY = "66c809c8b137a8f9968fd5fb9a27ca9e";
/*

    //首页接口
   public static void getHomeForecastData(Response.Listener<HomeForecastBaseBean> listener,Response.ErrorListener errorListener,TreeMap<String,String> map){
       LivingRequest<HomeForecastBaseBean> request=new LivingRequest<HomeForecastBaseBean>(URL_HOME_FORECAST,HomeForecastBaseBean.class,listener,map,errorListener);
       VolleySingleton.getInstance().addToRequestQueue(request);
   }
*/


    //首页接口
    public static void getChannelNew(Response.Listener<NewsChannelBean> listener, Response.ErrorListener errorListener, TreeMap<String, String> map) {
        LivingRequest<NewsChannelBean> request = new LivingRequest<NewsChannelBean>(URL_NEWS_CHANNEL, NewsChannelBean.class, listener, map, errorListener);

        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    /**
     * 获取天气的接口
     * @param listener
     * @param errorListener
     * @param map
     */
    public static void getWeather(Response.Listener<CountryWeatherBean> listener, Response.ErrorListener errorListener, TreeMap<String, String> map) {
        LivingRequest<CountryWeatherBean> request = new LivingRequest<CountryWeatherBean>(Request.Method.GET,
                URL_WEAHTER,CountryWeatherBean.class,listener,errorListener,map);
        Map<String, String> header = new HashMap<>();
        header.put("apikey",APIKEY);
        request.setHeader(header);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }


}
