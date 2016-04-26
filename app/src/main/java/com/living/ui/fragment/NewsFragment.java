package com.living.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.NewsSearchBean;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;
import java.util.List;
import java.util.TreeMap;

public class NewsFragment extends BaseFragment {

    private List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBean;
    private String channelId;
    private int page = 1;

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
        return super.onCreateView(inflater, container, savedInstanceState);
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
                    if (response.getShowapi_res_code() == 300301){
                        Toast.makeText(NewsFragment.this.getActivity(), "内部错误 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getShowapi_res_code() == 300302){
                        Toast.makeText(NewsFragment.this.getActivity(), "系统繁忙稍候再试 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getShowapi_res_code() == 300101){
                        Toast.makeText(NewsFragment.this.getActivity(), "用户请求过期 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getShowapi_res_code() == 300102){
                        Toast.makeText(NewsFragment.this.getActivity(), "用户日调用量超限 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getShowapi_res_code() == 300103){
                        Toast.makeText(NewsFragment.this.getActivity(), "服务每秒调用量超限 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getShowapi_res_code() == 300104){
                        Toast.makeText(NewsFragment.this.getActivity(), "服务日调用量超限 : " + response.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    contentlistBean = response.getShowapi_res_body().getPagebean().getContentlist();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogUtil.e("tobin", "tobin getNewsSearch onErrorResponse: " + error.getMessage());
                }
            }, map);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}