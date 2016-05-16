package com.living.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.living.util.ProgressUtil;

public class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * @param isLoading 是否加载
     */
    public void loading(boolean isLoading) {
        ProgressUtil.loading(this, isLoading);
    }

}
