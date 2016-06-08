package com.living.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.living.R;

public class MapActivity extends BaseAppCompatActivity {

    private MapView mMapView = null;
    private BaiduMap map;
    public LocationClient mLocationClient;//定位SDK的核心类
//    private LivingLoListener loListener;
    private static final int UPDATE_TIME = 5000;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MapStatusUpdate mMapStatusUpdate;
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.map_view);
        map = mMapView.getMap();
        map.setMyLocationEnabled(true);


//        loListener = new LivingLoListener();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);            //是否打开GPS
        option.setCoorType("bd09ll");       //设置返回值的坐标类型。
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置定位优先级
        option.setProdName("Living");       //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔。单位毫秒
        mLocationClient.setLocOption(option);
        mLocationClient.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                //定位出错
                //访问缓存数据
                Toast.makeText(MapActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                return;
            }
            int[] locations = new int[2];
            mMapView.getLocationOnScreen(locations);

            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            map.setMyLocationData(locData);
            LatLng latlng = new LatLng(location.getLongitude(), location.getLatitude());
            if (mMapStatusUpdate == null)
                mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(latlng);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                map.animateMapStatus(u);

            }
        }
    }
}
