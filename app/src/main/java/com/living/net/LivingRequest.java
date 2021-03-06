package com.living.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.living.util.LogUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by senghor on 2015/12/23.
 */
public class LivingRequest<NetModel> extends Request<NetModel> {
    private final Listener<NetModel> mListener;

    private Gson mGson;

    private Class<NetModel> mClass;

    public LivingRequest(int method, String url, Class<NetModel> clazz, Listener<NetModel> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }


    public LivingRequest(String url, Class<NetModel> clazz, Listener<NetModel> listener,
                         Map<String, String> mParam,
                         Response.ErrorListener errorListener) {
        this(Method.POST, url, clazz, listener, errorListener);
        this.mParam = mParam;
    }

    public LivingRequest(int method, String url, Class<NetModel> clazz, Listener<NetModel> listener,
                         Response.ErrorListener errorListener, Map<String, String> mParam) {
        this(method, url, clazz, listener, errorListener);
        this.mParam = mParam;
    }


    @Override
    protected Response<NetModel> parseNetworkResponse(NetworkResponse response) {
        try {
            //得到返回数据
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//            String jsonString = new String(response.data, "UTF-8");
            LogUtil.e("返回结果:" + jsonString);
            // 转化成对象返回
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(NetModel response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHeader == null) {
            mHeader = new HashMap<>();
        }
        mHeader.put("Cache-Control", "no-store,no-cache,must-revalidate");
        mHeader.put("Pragma", "no-cache");
        return mHeader;
//        return mHeader.isEmpty()?super.getHeaders():mHeader;
    }

    /**
     * 额外设置的header,出去登录相关的
     */
    public void setHeader(Map<String, String> header) {
        mHeader = header;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mParam == null) {
            mParam = new TreeMap<>();
        }
        return mParam;
    }

    private Map<String, String> mHeader = new HashMap<>();
    private Map<String, String> mParam = new HashMap<String, String>();
}