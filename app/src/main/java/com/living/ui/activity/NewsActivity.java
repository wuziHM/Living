package com.living.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.adapter.NewsClassifyAdapter;
import com.living.bean.NewsChannelBean;
import com.living.ui.fragment.NewsFragment;
import com.living.util.LivingNetUtils;
import com.living.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseAppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    NewsChannelBean newsChannelBean;
    List<NewsChannelBean.ShowapiResBodyBean.ChannelListBean> channelListBean;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    /**
     * tabLayout 分类列表
     */
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        getChannelNews();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText(getString(R.string.title_activity_news));
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void initTabView() {
        if (channelListBean == null || channelListBean.size() == 0) {
            return;
        }
        for (int i = 0; i < channelListBean.size(); i++) {
            mTitleList.add(channelListBean.get(i).getName());
        }
        initTabLayout();
        initFragment();
        NewsClassifyAdapter mAdapter = new NewsClassifyAdapter(getSupportFragmentManager(), fragments, mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器 // 新的版本不需要设置，会自动根据ViewPage找到mAdapter
        mViewPager.addOnPageChangeListener(pageListener);
        mViewPager.setOffscreenPageLimit(4);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
        }
    };

    // 初始化 Fragment
    private void initFragment() {
        for (int i = 0; i < mTitleList.size(); i++) {
            Bundle data = new Bundle();
            data.putString("channelId", channelListBean.get(i).getChannelId());
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setArguments(data);
            fragments.add(newsFragment);
        }
    }

    //初始化导航栏
    private void initTabLayout() {
        if (mTitleList == null) {
            return;
        }
        for (int tab = 0; mTitleList.size() > tab; tab++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(tab)));//添加tab选项卡，默认选中第一个
        }
        //设置TabLayout模式可以用默认是不可滑动的，大于5个时设置为可滑动
        if (mTitleList.size() > 5) {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    //获取全部新闻分类
    private void getChannelNews() {
//        Parameters para = new Parameters();
//        para.put("apikey", ApiStoreSDK.getAppKey());
//        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/channel_news",
//                ApiStoreSDK.GET,
//                para,
//                new ApiCallBack() {
//                    @Override
//                    public void onSuccess(int status, String responseString) {
//                        LogUtil.e("tobin : " + status + " //response: " + responseString);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogUtil.e("tobin channel_news" + "onComplete");
//
//                    }
//
//                    @Override
//                    public void onError(int status, String responseString, Exception e) {
//                        LogUtil.e("tobin errMsg: " + (e == null ? "" : e.getMessage()));
//                    }
//                }
//        );
        LivingNetUtils.getChannelNew(new Response.Listener<NewsChannelBean>() {
            @Override
            public void onResponse(NewsChannelBean response) {
                newsChannelBean = response;
                LogUtil.e("tobin response: " + response + "//error: " + response.getShowapi_res_error() + "//code:" + response.getShowapi_res_code());
                channelListBean = response.getShowapi_res_body().getChannelList();
                initTabView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("tobin getChannelNew onErrorResponse: " + error.getMessage());
            }
        }, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
