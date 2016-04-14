package com.living;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by work on 2016/4/14.
 */
public class LivingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiStoreSDK.init(this, "7f95bf87342d58243e5a5ce0bfda6b1b");
    }
}
