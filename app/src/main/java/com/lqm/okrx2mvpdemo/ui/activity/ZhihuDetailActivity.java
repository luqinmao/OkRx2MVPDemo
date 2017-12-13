package com.lqm.okrx2mvpdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.base.BaseActivity;
import com.lqm.okrx2mvpdemo.ui.presenter.ZhihuWebPresenter;
import com.lqm.okrx2mvpdemo.ui.view.IZhihuWebView;

import butterknife.Bind;

/**
 * @user  lqm
 * @desc  知乎详情页面
 */

public class ZhihuDetailActivity extends BaseActivity<IZhihuWebView,ZhihuWebPresenter> implements IZhihuWebView {

    private static final String ID = "id";

    private String id;

    @Bind(R.id.web_view)
    WebView web_view;
    @Bind(R.id.iv_web_img)
    ImageView iv_web_img;
    @Bind(R.id.tv_img_title)
    TextView tv_img_title;
    @Bind(R.id.tv_img_source)
    TextView tv_img_source;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected ZhihuWebPresenter createPresenter() {
        return new ZhihuWebPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public void init() {
        id = getIntent().getStringExtra(ID);
    }

    @Override
    public void initView() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回箭头

        mPresenter.getDetailNews(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroyImg();
    }

    public static Intent newIntent(Context context, String id){
        Intent intent = new Intent(context,ZhihuDetailActivity.class);
        intent.putExtra(ZhihuDetailActivity.ID,id);
        return intent;
    }

    @Override
    public WebView getWebView() {
        return web_view;
    }

    @Override
    public ImageView getWebImg() {
        return iv_web_img;
    }

    @Override
    public TextView getImgTitle() {
        return tv_img_title;
    }

    @Override
    public TextView getImgSource() {
        return tv_img_source;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 此时android.R.id.home即为返回箭头
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
