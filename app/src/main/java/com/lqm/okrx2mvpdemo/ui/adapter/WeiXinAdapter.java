package com.lqm.okrx2mvpdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.model.WeiXinArticle;
import com.lqm.okrx2mvpdemo.ui.activity.WebViewActivity;

import java.util.List;

/**
 * user：lqm
 * desc：新闻适配器
 */

public class WeiXinAdapter extends BaseQuickAdapter<WeiXinArticle.NewslistBean> {

    private Context mContext;

    public WeiXinAdapter(Context context, @Nullable List<WeiXinArticle.NewslistBean> data) {
        super(R.layout.item_weixin_article, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WeiXinArticle.NewslistBean bean) {

        helper.setText(R.id.tv_article_title,bean.getTitle())
                .setText(R.id.tv_article_time,bean.getCtime())
                .setText(R.id.tv_article_form,bean.getDescription());
        ImageView imgView = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(bean.getPicUrl()).into(imgView);

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(WebViewActivity.newIntent(mContext, bean.getUrl()));

            }
        });
    }
}
