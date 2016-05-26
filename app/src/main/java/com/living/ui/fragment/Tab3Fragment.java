package com.living.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.living.R;

/**
 * @author tobin
 */
public class Tab3Fragment extends BaseFragment {
    private View rootView;

    public Tab3Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        initView();

        return rootView;
    }

    private void initView(){
        ((TextView) rootView.findViewById(R.id.tv_main_title)).setText("我");
    }

}
