package com.living.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.living.R;
import com.living.adapter.NewsAdapter;
import com.living.bean.NewsSearchBean;
import com.living.ui.activity.WebViewActivity;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.List;
import java.util.TreeMap;

public class NewsFragment extends BaseFragment {
    View view;
    private List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentListBean;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecycleView;
    private LinearLayoutManager mManager;
    private NewsAdapter mAdapter;

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
        view =  super.onCreateView(inflater, container, savedInstanceState);
        initView();
        initEvent();
        return view;
    }

    private void initView(){
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_news);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary);
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycle_view_news);
        mManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getActivity());
        mRecycleView.setAdapter(mAdapter);
    }

    //当fragment可见时加载新闻数据
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            getNewsData(false);
        }
    }

    // 初始化事件监听
    private void initEvent() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsData(false);
            }
        });
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int last = mManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && last + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() > 1) {
                    if (contentListBean.size() >= 20){
                        mAdapter.setIsLoading(true);
                        getNewsData(true);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("link",contentListBean.get(position).getLink() == null ? "http://blog.csdn.net/aprilqq":contentListBean.get(position).getLink());
                getActivity().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     *  获取新闻数据
     * @param isLoadMore 是否加载更多数据
     */
    private void getNewsData(final boolean isLoadMore){
        if (isLoadMore)
            page ++;
        else
            page = 1;
        TreeMap<String,String> map = new TreeMap<>();
        map.put("channelId",channelId);// 新闻频道id，必须精确匹配
//        map.put("channelName",channelName);// 新闻频道名称，可模糊匹配
//        map.put("title",title); // 新闻标题，模糊匹配
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
                contentListBean = response.getShowapi_res_body().getPagebean().getContentlist();
                if (isLoadMore)
                    mAdapter.addDatas(contentListBean);
                else
                    mAdapter.setDatas(contentListBean);

                mSwipeRefresh.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefresh.setRefreshing(false);
                mAdapter.setmError(error.getMessage());
                LogUtil.e("tobin", "tobin getNewsSearch onErrorResponse: " + error.getMessage());
            }
        }, map);
    }
}