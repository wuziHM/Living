package com.living;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.living.greendao.util.DbCore;
import com.living.net.VolleySingleton;

/**
 * Created by Tobin on 2016/4/14.
 */
public class LivingApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        DbCore.init(this);
        VolleySingleton.init(this);
        ApiStoreSDK.init(this, "10cf56b74c39366d6b202a57428dbb6b");
    }
}
