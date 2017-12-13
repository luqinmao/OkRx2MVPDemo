package com.lqm.okrx2mvpdemo.ui.view;

import android.support.v7.widget.RecyclerView;

import com.lqm.okrx2mvpdemo.ui.adapter.WeiXinAdapter;

/**
 * user：lqm
 * desc：微信文章
 */

public interface WeiXinView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    WeiXinAdapter getAdapter();

}
