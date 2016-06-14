package com.living.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.living.util.ProgressUtil;

public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
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

    public <T extends View> T findViewById(int resId) {
        return (T) rootView.findViewById(resId);
    }

    public abstract int getLayoutId();

}
