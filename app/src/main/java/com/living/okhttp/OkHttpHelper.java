package com.living.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Tobin
 * @since 2016/7/1 14:56
 */

public class OkHttpHelper {
    private static OkHttpClient okHttpClient;
    private Gson gson;

    private Handler handler;

    private OkHttpHelper(){
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpHelper getInstance(){
        return new OkHttpHelper();
    }

    public void get(String url,HttpCallback httpCallback){
        Request request = buildRequest(url,null,null,HttpMethodType.GET);
        doRequest(request,httpCallback);
    }
    public void post(String url,Map<String , String> header, Map<String , String> params,HttpCallback httpCallback){
        Request request = buildRequest(url,header,params,HttpMethodType.POST);
        doRequest(request,httpCallback);
    }

    public void doRequest(final Request request, final HttpCallback httpCallback){
        callbackBefore(httpCallback,request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(httpCallback,request,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    String resultStr = response.body().string();
                    if (httpCallback.type == String.class){
                        callbackSuccess(httpCallback,response,resultStr);
                    }else{
                        try {
                            Object object = gson.fromJson(resultStr,httpCallback.type);
                            callbackSuccess(httpCallback,response,object);
                        }catch (JsonIOException e){
                            callbackError(httpCallback,response,e);
                        }
                    }

                }else{
                    callbackError(httpCallback,response,null);
                }
            }
        });

    }

    private Request buildRequest(String url, Map<String,String> headerParams,Map<String,String> bodyParams, HttpMethodType methodType){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (methodType == HttpMethodType.GET){
            builder.get();
        }else if (methodType == HttpMethodType.POST){
            RequestBody body = buildFormData(bodyParams);
            builder.post(body);
            if(headerParams != null){
                for (Map.Entry<String,String> entry: headerParams.entrySet()){
                    builder.addHeader(entry.getKey(),entry.getValue());
                }
            }
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String,String> params){
        FormBody.Builder body = new FormBody.Builder();
        if (params != null){
            for (Map.Entry<String,String> entry: params.entrySet()){
                body.add(entry.getKey(),entry.getValue());
            }
        }
        return body.build();
    }

    private void callbackSuccess(final HttpCallback httpCallback, final Response response, final Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallback.onSuccess(response,object);
            }
        });
    }

    private void callbackError(final HttpCallback httpCallback, final Response response,final Exception e){
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallback.onError(response,response.code(),e);
            }
        });
    }

    private void callbackFailure(final HttpCallback httpCallback, final Request request,final IOException e){
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallback.onFailure(request,e);
            }
        });
    }

    private void callbackBefore(final HttpCallback httpCallback, final Request request){
        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallback.onBefore(request);
            }
        });
    }

    enum HttpMethodType{
        GET,
        POST
    }
}
