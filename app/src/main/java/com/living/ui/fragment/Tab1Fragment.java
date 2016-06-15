package com.living.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.living.MainActivity;
import com.living.R;
import com.living.ui.activity.MapActivity;
import com.living.ui.activity.NewsActivity;
import com.living.ui.activity.WeatherActivity;

public class Tab1Fragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_main_activity_back).setOnClickListener(this);
        findViewById(R.id.ll_news).setOnClickListener(this);
        findViewById(R.id.ll_weather).setOnClickListener(this);
        findViewById(R.id.ll_robot).setOnClickListener(this);
        findViewById(R.id.ll_xxxx).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_news:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.ll_weather:

                String city = ((MainActivity) getActivity()).getCity();
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                if (!TextUtils.isEmpty(city)) {
                    intent.putExtra(WeatherActivity.LOCATION, city);
                } else {
                    intent.putExtra(WeatherActivity.LOCATION, "资兴");
                }
                startActivity(intent);
                break;
            case R.id.iv_main_activity_back:

                break;
            case R.id.ll_robot:

                break;
            case R.id.ll_xxxx:
                startActivity(new Intent(getActivity(), MapActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab1;
    }

}
