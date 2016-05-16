package com.living.config;

import android.os.Environment;

public class Constant {
    public static final String API_KEY = "66c809c8b137a8f9968fd5fb9a27ca9e";

    //新闻频道查询
    public static final String URL_NEWS_CHANNEL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
    // 根据频道或关键词查询新闻
    public static final String URL_NEWS_SEARCH = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";

    public static final String URL_WEATHER = "http://apis.baidu.com/heweather/weather/free";

    public static final String FILE_IMG_CACHE = Environment.getExternalStorageDirectory() + "/living/images/cache/";
    public static final String FILE_VOICE_CACHE = Environment.getExternalStorageDirectory() + "/living/voice/";
    public static final String FILE_DOWNLOAD = Environment.getExternalStorageDirectory() + "/living/download/";

    /**
     * 默认横坐标
     */
    public final static double LOC_LONGITUDE = 116.403119;
    /**
     * 默认纵坐标
     */
    public final static double LOC_LATITUDE = 39.915378;
    /**
     * 实时定位地址
     */
    public final static String ADDRESS = "ADDRESS";
    /**
     * 实时定位城市
     */
    public final static String CITY = "CITY";
    /**
     * 实时定位坐标
     */
    public final static String LOCTION = "LOCTION";

    /**
     * 版本号,如果版本更新时更新了引导图，则将"VERSION_CODE"名改变即可,没有更新则不改变；
     * 为了统一，更改策略为：
     * VERSION_CODE="VERSION_CODE_1"
     * VERSION_CODE="VERSION_CODE_2"
     * VERSION_CODE="VERSION_CODE_3"
     * 。。。。。。
     */
    public final static String VERSION_CODE = "VERSION_CODE";

    //静态地图API
    public static  final String LOCATION_URL_S = "http://api.map.baidu.com/staticimage?width=320&height=240&zoom=17&center=";
    public static  final String LOCATION_URL_L = "http://api.map.baidu.com/staticimage?width=480&height=800&zoom=17&center=";

    public static final String MSG_TYPE_TEXT="msg_type_text";//文本消息
    public static final String MSG_TYPE_IMG="msg_type_img";//图片
    public static final String MSG_TYPE_VOICE="msg_type_voice";//语音
    public static final String MSG_TYPE_LOCATION="msg_type_location";//位置
    public static final String MSG_TYPE_MUSIC="msg_type_music";//音乐

    //机器人api， 图灵机器人官网注册http://www.tuling123.com
    public static final String ROBOT_URL="http://www.tuling123.com/openapi/api?key=8c9e460d261588a152d99853df80fcfd&info=";



}
