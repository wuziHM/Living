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

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.living.R;
import com.living.adapter.NewsAdapter;
import com.living.bean.NewsSearchBean;
import com.living.config.Constant;
import com.living.ui.activity.WebViewActivity;
import com.living.util.JsonUtil;
import com.living.util.LogUtil;

import java.util.List;

public class NewsFragment extends BaseFragment {
    View view;

    private NewsSearchBean newsSearchBean;
    private List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentListBean;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecycleView;
    private LinearLayoutManager mManager;
    private NewsAdapter mAdapter;

    private String channelId;
    private int page = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        channelId = args != null ? args.getString("channelId") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_news);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary, R
                .color.colorPrimary);
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && last + 1 == mAdapter.getItemCount() && mAdapter
                        .getItemCount() > 1) {
                    if (contentListBean.size() >= 20) {
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
                intent.putExtra("link", contentListBean.get(position).getLink() == null ? "http://blog.csdn" +
                        ".net/aprilqq" : contentListBean.get(position).getLink());
                getActivity().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * 获取新闻数据
     *
     * @param isLoadMore 是否加载更多数据
     */
    private void getNewsData(final boolean isLoadMore) {
        if (isLoadMore)
            page++;
        else
            page = 1;

        Parameters para = new Parameters();
        para.put("apikey", ApiStoreSDK.getAppKey());
        para.put("channelId", channelId);// 新闻频道id，必须精确匹配
        para.put("page", page + ""); // 页数，默认1。每页最多20条记录。
        ApiStoreSDK.execute(Constant.URL_NEWS_SEARCH, ApiStoreSDK.POST, para, new ApiCallBack() {
            @Override
            public void onSuccess(int status, String responseString) {
                LogUtil.e("tobin getNewsData status: " + status + " //response: " + responseString);
                newsSearchBean = JsonUtil.Json2T(responseString, NewsSearchBean.class);
                if (newsSearchBean == null) {
    //                            getNewsData();
                } else {
                    if (newsSearchBean.getShowapi_res_code() == 300301) {
                        Toast.makeText(NewsFragment.this.getActivity(), "内部错误 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (newsSearchBean.getShowapi_res_code() == 300302) {
                        Toast.makeText(NewsFragment.this.getActivity(), "系统繁忙稍候再试 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (newsSearchBean.getShowapi_res_code() == 300101) {
                        Toast.makeText(NewsFragment.this.getActivity(), "用户请求过期 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (newsSearchBean.getShowapi_res_code() == 300102) {
                        Toast.makeText(NewsFragment.this.getActivity(), "用户日调用量超限 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (newsSearchBean.getShowapi_res_code() == 300103) {
                        Toast.makeText(NewsFragment.this.getActivity(), "服务每秒调用量超限 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (newsSearchBean.getShowapi_res_code() == 300104) {
                        Toast.makeText(NewsFragment.this.getActivity(), "服务日调用量超限 : " + newsSearchBean
                                .getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    contentListBean = newsSearchBean.getShowapi_res_body().getPagebean().getContentlist();
                    if (isLoadMore) {
                        mAdapter.addDatas(contentListBean);
                        mAdapter.setmError(null);
                    } else {
                        mAdapter.setDatas(contentListBean);
                    }
                    mSwipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onComplete() {
                LogUtil.e("tobin getNewsData" + "onComplete");
            }

            @Override
            public void onError(int status, String responseString, Exception e) {
                LogUtil.e("tobin getNewsData errMsg: " + (e == null ? "" : e.getMessage()));
                mSwipeRefresh.setRefreshing(false);
                mAdapter.setmError(responseString);
                LogUtil.e("tobin", "tobin getNewsSearch onErrorResponse: " + responseString);
            }
        });

    }
}