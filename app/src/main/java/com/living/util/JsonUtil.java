package com.living.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Collection;
import java.util.List;

/**
 * Json序列化工具包
 *
 * @author Sun
 * @author tobin
 * @modify 2015-12-12
 * @since 2012-1-19
 */
public class JsonUtil {

    private static final String CHARSET = "UTF-8";

    /**
     * 将json转换成为java对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T Json2T(String json, Class<T> classOfT) {
        if (null == json || "".equals(json.trim()))
            return null;
        try {
            return JSON.parseObject(json, classOfT);
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e("JsonUtil.Json2T", "msg:" + json + "\nclazz:" + classOfT.getName());
            return null;
        }
    }

    /**
     * 将json转换成为java数组
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> List<T> Json2List(String json, Class<T> classOfT) {
        if (null == json || "".equals(json.trim())) {
            return null;
        }
        try {
            return JSON.parseArray(json, classOfT);
        } catch (Exception e) {
            Log.e("JsonUtil.Json2T", "msg:" + json + "\r\nclazz:" + classOfT.getName());
            return null;
        }
    }

    /**
     * 将非集合类java对象转换成为json object
     *
     * @param o
     * @return
     */
    public static JSONObject Object2JsonObject(Object o) {
        try {
            return (JSONObject) JSON.toJSON(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将集合类java对象装换成为json array
     *
     * @param collection
     * @return
     */
    public static JSONArray Collection2JsonArray(Collection collection) {
        try {
            return (JSONArray) JSON.toJSON(collection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json String 转 JSONObject
     *
     * @param json
     * @return
     */
    public static JSONObject json2JsonObject(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成为jsonArray对象
     *
     * @param str
     * @return
     */
    public static JSONArray Json2JsonArray(String str) {
        if (null == str || "".equals(str.trim())) {
            return null;
        }
        try {
            return JSON.parseArray(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象Object转换成json格式数据
     *
     * @param o
     * @return
     */
    public static String Object2Json(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * byte[] 转 JSONObject
     *
     * @param bytes
     * @return
     */
    public static JSONObject bytes2JsonObject(byte[] bytes) {
        try {
            return JSON.parseObject(new String(bytes, CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * byte[] 转 java对象
     *
     * @param bytes
     * @return
     */
    public static <T> T bytes2T(byte[] bytes, Class<T> classOfT) {
        try {
            return JSON.parseObject(new String(bytes, CHARSET), classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象转换成json格式数据，使用全序列化方案
     *
     * @param o
     * @return
     */
    public static String Object2JsonSerial(Object o) {
        try {
            return JSON.toJSONString(o, SerializerFeature.WriteClassName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将列表转换成为json，使用全序列化方案
     *
     * @param l
     * @return
     */
    public static String list2JsonSerial(List l) {
        if (null == l) {
            return null;
        }
        try {
            return JSON.toJSONString(l, SerializerFeature.WriteClassName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将全序列化json转换成为java对象
     *
     * @param json
     * @return
     */
    public static Object JsonSerial2Object(String json) {
        try {
            return JSON.parse(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将全序列化json转换成为list对象
     *
     * @param json
     * @return
     */
    public static List JsonSerial2List(String json) {
        try {
            return JSON.parseArray(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将全序列化json转换为jsonArray对象
     *
     * @param json
     * @return
     */
    public static JSONArray JsonSerial2JsonArray(String json) {
        try {
            return JSON.parseArray(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更加方便观察，但会增加存储空间
     *
     * @param o
     * @return
     */
    public static String Object2JsonPrettyFormat(Object o) {
        try {
            return JSON.toJSONString(o, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
