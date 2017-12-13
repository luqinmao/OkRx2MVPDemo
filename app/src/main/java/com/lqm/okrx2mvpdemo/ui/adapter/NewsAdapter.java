package com.lqm.okrx2mvpdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.model.pojo.NewslistBean;
import com.lqm.okrx2mvpdemo.ui.activity.WebViewActivity;

import java.util.List;

/**
 * user：lqm
 * desc：新闻适配器
 */

public class NewsAdapter extends BaseQuickAdapter<NewslistBean> {

    private Context mContext;

    public NewsAdapter(Context context, @Nullable List<NewslistBean> data) {
        super(R.layout.item_news, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewslistBean item) {

        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_time,item.getCtime())
                .setText(R.id.tv_source,item.getDescription());

        ImageView ivPic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(item.getPicUrl()).into(ivPic);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(WebViewActivity.newIntent(mContext,item.getUrl()));
            }
        });
    }
}
