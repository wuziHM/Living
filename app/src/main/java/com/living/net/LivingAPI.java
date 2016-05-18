package com.living.net;

import com.android.volley.Request;
import com.android.volley.Response;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.living.bean.NetModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author：燕青 $ on 16/5/12 16:43
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class LivingAPI {

    static VolleySingleton volleySingleton;

    static {
        volleySingleton = VolleySingleton.getInstance();
    }

    protected static void post(String url, Class<? extends NetModel> clazz, Response.Listener<? extends NetModel> listener, Response.ErrorListener errorListener, Map map) {
        LivingRequest request = new LivingRequest(Request.Method.POST, url, clazz, listener, errorListener, map);
        Map<String, String> header = new HashMap<>();
        header.put("apikey", ApiStoreSDK.getAppKey());
        request.setHeader(header);
        volleySingleton.addToRequestQueue(request);
    }

    protected static void get(String url, Class<? extends NetModel> clazz, Response.Listener<?> listener, Response.ErrorListener errorListener, Map map) {
        url = url + "?";
        if (map != null) {
            Iterator<Map.Entry> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = it.next();
                url = url + en.getKey().toString().trim() + "=" + en.getValue().toString().trim();
            }
        }
        LivingRequest request = new LivingRequest(Request.Method.GET, url, clazz, listener, errorListener);
        Map<String, String> header = new HashMap<>();
        header.put("apikey", ApiStoreSDK.getAppKey());
        request.setHeader(header);
        volleySingleton.addToRequestQueue(request);

    }
}
