package com.app.gaolonglong.gankgirl.ui.activity.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.gaolonglong.gankgirl.R;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class Repos_detailsActivity extends AppCompatActivity {

    private BootstrapEditText bootstrapEditText;
    private BootstrapButton bootstrapButton;
    private String author_name = "yamaidie";

    private WebView webView;
    private FrameLayout avLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_details);

        bootstrapEditText = (BootstrapEditText)findViewById(R.id.github_researchEdit);
        bootstrapButton = (BootstrapButton)findViewById(R.id.github_researchButton);

        bootstrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author_name = String.valueOf(bootstrapEditText.getText());

                if (null == author_name){
                    Toast.makeText(getApplicationContext(),"填入的信息为空",Toast.LENGTH_SHORT).show();
                    avLoadingView.setVisibility(View.VISIBLE);
                    //webView.loadUrl("https://github.com/"+"yamaidie"+"?tab=repositories");

                }else {
                    webView.loadUrl("https://github.com/"+author_name+"?tab=repositories");
                }
            }
        });

        avLoadingView = (FrameLayout) findViewById(R.id.av_loading_search);
        //WebView
        webView = (WebView) findViewById(R.id.webview_search);
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



    }
}
