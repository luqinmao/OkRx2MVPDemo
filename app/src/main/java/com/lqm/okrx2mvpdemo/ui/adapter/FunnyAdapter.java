package com.lqm.okrx2mvpdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.model.pojo.FunnyBean;

import java.util.List;

/**
 * user：lqm
 * desc：新闻适配器
 */

public class FunnyAdapter extends BaseQuickAdapter<FunnyBean> {

    private Context mContext;

    public FunnyAdapter(Context context, @Nullable List<FunnyBean> data) {
        super(R.layout.item_funny, data);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final FunnyBean bean) {
        holder.setText(R.id.tv_funny_title, bean.getTitle());
        String contentString = bean.getContent();
        if (contentString.contains("<br/>")){
            contentString =  contentString.replaceAll("<br/>"," ");
        }
        holder.setText(R.id.tv_funny_content,contentString);
    }
}
