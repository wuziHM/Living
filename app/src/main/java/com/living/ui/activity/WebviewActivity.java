package com.living.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.living.R;
import com.living.ui.WebViewWithProgress;

/**
 * WebView 显示新闻网页
 * @author Tobin
 */
public class WebViewActivity extends BaseAppCompatActivity implements View.OnClickListener{

	//这实际上是RelativeLayout
    private WebViewWithProgress mWebViewWithProgress;
	private WebView mWebView;
	private ImageView ivBack;
	private TextView tvTitle;
	private String link;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		Intent intent = getIntent();
		link = intent.getStringExtra("link");

		initView();

		mWebView = mWebViewWithProgress.getWebView();
		mWebView.getSettings().setJavaScriptEnabled(false);  
		//设置WebView为单列显示，是一些大图片适应屏幕宽度
		mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.loadUrl(link);
	}

	private void initView(){
		ivBack = (ImageView) this.findViewById(R.id.iv_back);
		ivBack.setOnClickListener(this);
		tvTitle = (TextView)findViewById(R.id.tv_title);
		tvTitle.setText("新闻详情");
		mWebViewWithProgress = (WebViewWithProgress) findViewById(R.id.detail_webview);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.iv_back:
				this.finish();
				break;
			default:
				break;
		}
	}

}
