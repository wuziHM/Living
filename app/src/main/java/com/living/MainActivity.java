package com.living;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.emokit.sdk.util.SDKAppInit;
import com.living.impl.LocationCallBack;
import com.living.listener.LivingLoListener;
import com.living.ui.fragment.Tab1Fragment;
import com.living.ui.fragment.Tab2Fragment;
import com.living.ui.fragment.Tab3Fragment;
import com.living.util.LogUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements LocationCallBack {

    String[] tabsTxt = {"首页", "发现", " 我"};
    int[] tabImg = {R.mipmap.tab1_n, R.mipmap.tab2_n, R.mipmap.tab3_n};
    int[] tabsImgLight = {R.mipmap.tab1_p, R.mipmap.tab2_p, R.mipmap.tab3p};
    Class[] clz = {Tab1Fragment.class, Tab2Fragment.class, Tab3Fragment.class};

    private LocationClient mLocationClient;//定位SDK的核心类
    private LivingLoListener loListener;
    private FragmentTabHost tabHost;
    private static  final int UPDATE_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
        initLocation();
        SDKAppInit.createInstance(this);
    }

    private void initLocation() {

        mLocationClient = new LocationClient(this);
        loListener = new LivingLoListener();
        loListener.setCallBack(this);
        mLocationClient.registerLocationListener(loListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);            //是否打开GPS
        option.setCoorType("bd09ll");       //设置返回值的坐标类型。
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置定位优先级
        option.setProdName("Living");       //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔。单位毫秒
        mLocationClient.setLocOption(option);
        mLocationClient.start();

    }

    private void initTab() {

        tabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(new FragmentTabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab();
            }
        });
        for (int i = 0; i < tabsTxt.length; i++) {
            FragmentTabHost.TabSpec tabSpec = tabHost.newTabSpec(tabsTxt[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, clz[i], null);
            tabHost.setTag(i);
        }
        //设置初始显示Tab页 默认位0显示第一个Tab
        tabHost.setCurrentTab(1);
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs, null);
        ((TextView) view.findViewById(R.id.tvTab)).setText(tabsTxt[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(this, R.color.foot_txt_light));
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(tabsImgLight[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(tabImg[idx]);
        }
        return view;
    }

    private void updateTab() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.ivImg);
            if (i == tabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(this, R.color.foot_txt_light));
                iv.setImageResource(tabsImgLight[i]);
            } else {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(this, R.color.foot_txt_gray));
                iv.setImageResource(tabImg[i]);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 在fragment的管理类中，我们要实现这步操作，而他的主要作用是，当MainActivity回传数据到fragnment中时,
         * 往往会经过这个管理器中的onActivityResult的方法。
         * 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment
         * 然后在碎片中调用重写的onActivityResult方法
         */
        Fragment f = getSupportFragmentManager().findFragmentByTag(tabsTxt[tabHost.getCurrentTab()]);
        f.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Are you sure you want to quit!")
                    .setConfirmText(" Yes ")
                    .setCancelText("Cancel")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            MainActivity.this.finish();
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void broadcastLocation(BDLocation bdLocation) {
        LogUtil.e("经度:" + bdLocation.getLongitude());
        Toast.makeText(this, "嘻嘻嘻", Toast.LENGTH_SHORT).show();
    }


}
