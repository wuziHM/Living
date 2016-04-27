package com.living.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected View view; // 当前界面的根
    private int layoutId; // 当前界面对应的布局

    public BaseFragment(int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(layoutId, container, false);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
