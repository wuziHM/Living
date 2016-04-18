package com.living;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.living.activity.NewsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        ll_news = (LinearLayout) findViewById(R.id.ll_news);
        ll_news.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_news:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;
            default:
                break;
        }
    }
}
