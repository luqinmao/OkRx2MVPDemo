package com.lqm.okrx2mvpdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.base.BaseActivity;
import com.lqm.okrx2mvpdemo.ui.presenter.WebViewPresenter;
import com.lqm.okrx2mvpdemo.ui.view.CommonWebView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：共用WebView界面
 */

public class WebViewActivity extends BaseActivity<CommonWebView,WebViewPresenter>
        implements CommonWebView {

    public static final String WEB_URL = "web_url";

    @Bind(R.id.pb_progress)
    ProgressBar pb_progress;
    @Bind(R.id.url_web)
    WebView webView;

    private String gank_url;

    @Override
    protected WebViewPresenter createPresenter() {
        return new WebViewPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parseIntent();
        mPresenter.setWebView(gank_url);
    }

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL, url);
        return intent;
    }

    /**
     * 得到Intent传递的数据
     */
    private void parseIntent() {
        gank_url = getIntent().getStringExtra(WEB_URL);
    }

    @Override
    public ProgressBar getProgressBar() {
        return pb_progress;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                finish();//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}