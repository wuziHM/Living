package com.living.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.util.ProgressUtil;

public abstract class BaseFragment extends Fragment {

    protected View view; // 当前界面的根

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * @param isLoading 是否加载
     */
    public void loading(boolean isLoading) {
        ProgressUtil.loading(getActivity(), isLoading);
    }


}
