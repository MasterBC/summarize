package com.app.gaolonglong.gankgirl.ui.activity.home;

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

public class AuthorActivity extends AppCompatActivity {
    private String author_url;
    private WebView webView;
    private FrameLayout avLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        Intent intent = getIntent();
        author_url = intent.getStringExtra("author_url");

        avLoadingView = (FrameLayout) findViewById(R.id.av_loading_author);
        //WebView
        webView = (WebView) findViewById(R.id.webview_author);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){//进度1-100
                    avLoadingView.setVisibility(View.GONE);
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
        webView.loadUrl(author_url);
        //webView.destroy();

    }
}
