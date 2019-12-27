package com.playkuapp.kumobi.webview;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.playkuapp.kumobi.R;
import com.playkuapp.kumobi.base.BaseActivity;
import com.playkuapp.kumobi.catloadingview.CatLoadingView;


public class WebViewActivity extends BaseActivity {
    String status;
    int type = 0;
    WebView webview;
    SwipeRefreshLayout swipe;
    CatLoadingView catLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = findViewById(R.id.webview);
        swipe = findViewById(R.id.swipe);
        setupWindowAnimations();
        catLoadingView=new CatLoadingView();
        catLoadingView.setCancelable(false);
        catLoadingView.setBackgroundColor(Color.parseColor("#A6A6A6"));
        catLoadingView.show(getSupportFragmentManager(),"");
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("home")) {
                status = intent.getStringExtra("home");
                getWebview(status);
            }

        }

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWebview(status);
            }
        });
    }


    @Override
    protected void haveConnect() {
        getWebview(status);
    }

    @Override
    protected void dontHaveConnect() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    void getWebview(String link) {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                catLoadingView.show(getSupportFragmentManager(),"");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                catLoadingView.dismiss();
                swipe.setRefreshing(false);
            }
        });
        webview.loadUrl(link);
        WebSettings webSettings = webview.getSettings();

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.getSettings().setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
    }

    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(500);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }
}
