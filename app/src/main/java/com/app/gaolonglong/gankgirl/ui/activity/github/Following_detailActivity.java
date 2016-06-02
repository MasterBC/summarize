package com.app.gaolonglong.gankgirl.ui.activity.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.app.gaolonglong.gankgirl.R;
import com.wang.avi.AVLoadingIndicatorView;

public class Following_detailActivity extends AppCompatActivity {
    private String url;
    private FrameLayout loadingView;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_detail);

        init();

    }

    private void init() {

        getData();

        initView();
    }

    private void getData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("following_url");
    }

    private void initView(){
        //AVLoadingIndicatorView
        loadingView = (FrameLayout) findViewById(R.id.loading_following);
        //WebView
        webView = (WebView) findViewById(R.id.webview_following);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){//进度1-100
                    loadingView.setVisibility(View.GONE);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);

    }
}
