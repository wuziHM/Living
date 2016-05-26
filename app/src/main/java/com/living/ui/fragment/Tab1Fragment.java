package com.living.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.living.R;
import com.living.ui.activity.ChatListActivity;
import com.living.ui.activity.NewsActivity;
import com.living.ui.activity.WeatherActivity;

public class Tab1Fragment extends BaseFragment implements View.OnClickListener {
    View rootView;

    private ImageView iv_crop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView=inflater.inflate(R.layout.fragment_tab1, null);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        initView();
        return rootView ;
    }

    private void initView() {

        rootView.findViewById(R.id.iv_main_activity_back).setOnClickListener(this);

        iv_crop = (ImageView)rootView.findViewById(R.id.iv_crop);

        rootView.findViewById(R.id.ll_news).setOnClickListener(this);
        rootView.findViewById(R.id.ll_weather).setOnClickListener(this);
        rootView.findViewById(R.id.ll_robot).setOnClickListener(this);
        rootView.findViewById(R.id.ll_xxxx).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_news:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.ll_weather:
                startActivity(new Intent(getActivity(), WeatherActivity.class));
                break;
            case R.id.iv_main_activity_back:

                break;
            case R.id.ll_robot:
                startActivity(new Intent(getActivity(), ChatListActivity.class));
                break;
            case R.id.ll_xxxx:
//                startActivity(new Intent(getActivity(), IntroActivity.class));
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

}
