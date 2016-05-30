package com.living.util;

import com.android.volley.Response;
import com.living.bean.CountryWeatherBean;
import com.living.bean.NewsChannelBean;
import com.living.bean.NewsSearchBean;
import com.living.config.Constant;
import com.living.net.LivingAPI;

import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class LivingNetUtils extends LivingAPI {

    /**
     * 新闻频道
     */
    public static void getChannelNew(Response.Listener<NewsChannelBean> listener, Response.ErrorListener errorListener, TreeMap<String, String> map) {
//        LivingRequest<NewsChannelBean> request = new LivingRequest<>(Request.Method.POST,
//                URL_NEWS_CHANNEL, NewsChannelBean.class, listener, errorListener, map);
//        Map<String, String> header = new HashMap<>();
//        header.put("apikey", ApiStoreSDK.getAppKey());
//        request.setHeader(header);
//        volleySingleton.addToRequestQueue(request);
//        VolleySingleton.getInstance().addToRequestQueue(request);

        get(Constant.URL_NEWS_CHANNEL, NewsChannelBean.class, listener, errorListener, map);
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
        post(Constant.URL_NEWS_SEARCH, NewsSearchBean.class, listener, errorListener, map);
    }

    /**
     * 获取天气的接口
     */
    public static void getWeather(String cityName, Response.Listener<CountryWeatherBean> listener, Response.ErrorListener errorListener) {
        TreeMap map = new TreeMap();
        map.put("city", cityName);
        get(Constant.URL_WEATHER, CountryWeatherBean.class, listener, errorListener, map);
    }
}
