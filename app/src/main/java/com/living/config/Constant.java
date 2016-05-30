package com.living.config;

import android.os.Environment;

public class Constant {
    public static final String BAIDU_API_STORE_API_KEY = "66c809c8b137a8f9968fd5fb9a27ca9e";

    //新闻频道查询
    public static final String URL_NEWS_CHANNEL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
    // 根据频道或关键词查询新闻
    public static final String URL_NEWS_SEARCH = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";

    public static final String URL_WEATHER = "http://apis.baidu.com/heweather/weather/free";

    public static final String FILE_IMG_CACHE = Environment.getExternalStorageDirectory() + "/living/images/cache/";
    public static final String FILE_VOICE_CACHE = Environment.getExternalStorageDirectory() + "/living/voice/";
    public static final String FILE_DOWNLOAD = Environment.getExternalStorageDirectory() + "/living/download/";

    //静态地图API
    public static  final String LOCATION_URL_S = "http://api.map.baidu.com/staticimage?width=320&height=240&zoom=17&center=";
    public static  final String LOCATION_URL_L = "http://api.map.baidu.com/staticimage?width=480&height=800&zoom=17&center=";


    //机器人api， 图灵机器人官网注册http://www.tuling123.com
    public static final String ROBOT_URL="http://www.tuling123.com/openapi/api?key=cceba4433c5e0b24c146eda940a7350d&info=";



}
