package com.living.okhttp;

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

    private OkHttpHelper(){
        okHttpClient = new OkHttpClient();
        gson = new Gson();
    }

    private static OkHttpHelper getInstance(){
        return new OkHttpHelper();
    }

    public void get(String url,HttpCallback httpCallback){
        Request request = buildRequest(url,null,HttpMethodType.GET);
        doRequest(request,httpCallback);
    }
    public void post(String url, Map<String , String> params,HttpCallback httpCallback){
        Request request = buildRequest(url,params,HttpMethodType.POST);
        doRequest(request,httpCallback);
    }

    public void doRequest(final Request request, final HttpCallback httpCallback){
        httpCallback.onBefore(request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallback.onFailure(request,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    String resultStr = response.body().string();
                    if (httpCallback.type == String.class){
                        httpCallback.onSuccess(response,resultStr);
                    }else{
                        try {
                            Object object = gson.fromJson(resultStr,httpCallback.type);
                            httpCallback.onSuccess(response,object);
                        }catch (JsonIOException e){
                            httpCallback.onError(response,response.code(),e);
                        }
                    }

                }else{
                    httpCallback.onError(response,response.code(),null);
                }
            }
        });

    }

    private Request buildRequest(String url,Map<String,String> params,HttpMethodType methodType){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (methodType == HttpMethodType.GET){
            builder.get();
        }else if (methodType == HttpMethodType.POST){
            RequestBody body = buildFormData(params);
            builder.post(body);
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

    enum HttpMethodType{
        GET,
        POST
    }
}
