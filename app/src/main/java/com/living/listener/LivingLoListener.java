package com.living.listener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.living.impl.LocationCallBack;
import com.living.util.LogUtil;

/**
 * Author：燕青 $ on 2016/6/7  15:44
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class LivingLoListener implements BDLocationListener {

    private LocationCallBack callBack;

    public void setCallBack(LocationCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (callBack != null) {
            callBack.broadcastLocation(bdLocation);
        }
    }
}
