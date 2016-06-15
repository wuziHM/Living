package com.living.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.living.R;
import com.living.ui.activity.EmokitActivity;
import com.living.ui.activity.TuLingChatActivity;

public class Tab2Fragment extends BaseFragment implements View.OnClickListener {

    LinearLayout ll_chat, ll_mood;
    TextView tv_content, tv_time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_main_title)).setText("发现");
        ll_chat =  findViewById(R.id.ll_chat);
        tv_content = findViewById(R.id.tv_content);
        tv_time = findViewById(R.id.tv_time);
        ll_chat.setOnClickListener(this);

        ll_mood = findViewById(R.id.ll_mood);
        ll_mood.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.ll_chat:
                startActivity(new Intent(getActivity(), TuLingChatActivity.class));
                break;
            case R.id.ll_mood:
                startActivity(new Intent(getActivity(), EmokitActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab2;
    }

}
