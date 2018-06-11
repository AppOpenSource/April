package com.abt.price.ui.viewmodel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.abt.price.ui.IGankWebView;

import java.lang.ref.WeakReference;

/**
 * @描述： @GankWebVM
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class GankWebVM {

    private WeakReference<Activity> mActivity;
    private IGankWebView gankWebView = null;

    public GankWebVM(Activity activity, IGankWebView view) {
        mActivity = new WeakReference<>(activity);
        gankWebView = view;
    }

    public void loadUrl(String url) {
        ProgressBar progressBar = gankWebView.getProgressBar();
        WebView webView = gankWebView.getWebView();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持JS
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击放大缩小

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("网页开始加载");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
                progressBar.setVisibility(View.GONE);
            }

            /**
             * 所有跳转的链接都在此方法中回调
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("网页title：" + title);
                mActivity.get().setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });
        webView.loadUrl(url);
    }
}
