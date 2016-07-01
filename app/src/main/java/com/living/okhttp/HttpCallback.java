package com.living.okhttp;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Tobin
 * @since 2016/7/1 15:01
 */

public abstract class HttpCallback<T> {
    public Type type;
    /**
     * 把 T 转化成 Type
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class){
            throw new RuntimeException("HttpCallback: Missing type parameter");
        }
        ParameterizedType parameteterized = (ParameterizedType)superclass;
        return $Gson$Types.canonicalize(parameteterized.getActualTypeArguments()[0]);
    }

    public HttpCallback(){
        type = getSuperclassTypeParameter(getClass());
    }

    public abstract void onBefore(Request request);

    public abstract void onFailure(Request request, IOException e);

    public abstract void onSuccess(Response response,T t);

    public abstract void onError(Response response,int code, Exception e);

}
