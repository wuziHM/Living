package com.living.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.NewsSearchBean;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.TreeMap;

public class NewsFragment extends BaseFragment {

    String channelId;
    int page = 1;

    public NewsFragment() {
        super(R.layout.fragment_news);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        channelId = args != null ? args.getString("channelId") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            TreeMap<String,String> map = new TreeMap<>();
            map.put("channelId",channelId);// 新闻频道id，必须精确匹配
//            map.put("channelName",channelName);// 新闻频道名称，可模糊匹配
//            map.put("title",title); // 新闻标题，模糊匹配
            map.put("page",page + ""); // 页数，默认1。每页最多20条记录。
            LivingNetUtils.getNewsSearch(new Response.Listener<NewsSearchBean>() {
                @Override
                public void onResponse(NewsSearchBean response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogUtil.e("tobin", "tobin getNewsSearch onErrorResponse: " + error.getMessage());
                }
            }, map);
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
