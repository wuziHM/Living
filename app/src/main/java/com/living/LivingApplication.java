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

        ApiStoreSDK.init(this, "10cf56b74c39366d6b202a57428dbb6b");
    }
}
