package com.living.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.living.R;

public class ChatListActivity extends BaseAppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        initView();
    }

    private void initView(){
        ((TextView)findViewById(R.id.tv_title)).setText("聊天");
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            default:
                break;
        }
    }


}
