package com.seekho.live.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.R;

public class WebViewActivity extends AppBaseActivity {

    WebView web_view;
    ProgressBar progress_bar;

    String url = "",slug = "";

    @Override
    public int layoutResourceID() {
        return R.layout.activity_web_view;
    }

    public String getSlug(){
        if (!getIntent().getExtras().getString(WEB_SLUG).isEmpty()){
            slug = getIntent().getExtras().getString(WEB_SLUG);
        } else {
            slug = "";
        }
        return slug;
    }

    public String getWebUrl(){
        if (!getIntent().getExtras().getString(WEB_URL).isEmpty() &&
                getIntent().getExtras().getString(WEB_URL) != null){
            url = getIntent().getExtras().getString(WEB_URL);
        } else {
            url = "";
        }
        return url;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        web_view = findViewById(R.id.web_view);
        progress_bar = findViewById(R.id.progress_bar);

        if (getWebUrl() != null && !getWebUrl().isEmpty()){
            getToolbarFragment().setTitle(getWebUrl());
        } else {
            getToolbarFragment().setTitle("");
        }
        setWebView();
    }

    private void setWebView(){
        if (!getWebUrl().isEmpty() && getWebUrl() != null){
            web_view.loadUrl(getWebUrl());
        }
//        else {
//            Spanned body = Html.fromHtml(HTML_DATA);
//            web_view.loadData(String.valueOf(body),"text/html","utf-8");
//        }
        web_view.setBackgroundColor(0x00000000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            web_view.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        } else {
            web_view.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web_view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        web_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.indexOf(web_view.getOriginalUrl()) > -1)return false;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        web_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                updateProgressBar(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }

    private void updateProgressBar(int progress){
        progress_bar.setProgress(progress);
        if (progress < 100){
            progress_bar.setVisibility(View.VISIBLE);
        } else {
            progress_bar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.web_view.canGoBack()){
            this.web_view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onToolbarClick(View view) {
        super.onToolbarClick(view);
        switch (view.getId()){
            case R.id.refresh_iv:
                web_view.reload();
            break;
        }
    }
}
