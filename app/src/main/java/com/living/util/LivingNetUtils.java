package com.living.util;

import com.android.volley.Request;
import com.android.volley.Response;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.living.bean.CountryWeatherBean;
import com.living.bean.NewsChannelBean;
import com.living.bean.NewsSearchBean;
import com.living.net.LivingAPI;
import com.living.net.LivingRequest;
import com.living.net.VolleySingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class LivingNetUtils extends LivingAPI {

    //新闻频道查询
    private static final String URL_NEWS_CHANNEL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
    // 根据频道或关键词查询新闻
    private static final String URL_NEWS_SEARCH = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";

    private static final String URL_WEATHER = "http://apis.baidu.com/heweather/weather/free";

    public static final String API_KEY = "66c809c8b137a8f9968fd5fb9a27ca9e";

    /**
     * 新闻频道
     */
    public static void getChannelNew(Response.Listener<NewsChannelBean> listener, Response.ErrorListener errorListener, TreeMap<String, String> map) {
        LivingRequest<NewsChannelBean> request = new LivingRequest<>(Request.Method.POST,
                URL_NEWS_CHANNEL, NewsChannelBean.class, listener, errorListener, map);
        Map<String, String> header = new HashMap<>();
        header.put("apikey", ApiStoreSDK.getAppKey());
        request.setHeader(header);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    /**
     * 根据频道获取新闻
     */
    public static void getNewsSearch(Response.Listener<NewsSearchBean> listener, Response.ErrorListener errorListener, TreeMap<String, String> map) {
//        LivingRequest<NewsSearchBean> request = new LivingRequest<>(Request.Method.POST,
//                URL_NEWS_SEARCH, NewsSearchBean.class, listener, errorListener, map);
//        Map<String, String> header = new HashMap<>();
//        header.put("apikey", ApiStoreSDK.getAppKey());
//        request.setHeader(header);
//        VolleySingleton.getInstance().addToRequestQueue(request);
        post(URL_NEWS_SEARCH, NewsSearchBean.class, listener, errorListener, map);
    }

    /**
     * 获取天气的接口
     */
    public static void getWeather(String cityName, Response.Listener<CountryWeatherBean> listener, Response.ErrorListener errorListener) {
        TreeMap map = new TreeMap();
        map.put("city", cityName);
        get(URL_WEATHER, CountryWeatherBean.class, listener, errorListener, map);
    }
}
