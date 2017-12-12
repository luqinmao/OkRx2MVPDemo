package com.lqm.okrx2mvpdemo.ui.view;

import android.support.v7.widget.RecyclerView;

import com.lqm.okrx2mvpdemo.ui.adapter.NewsAdapter;

/**
 * user：lqm
 * desc：新闻View
 */

public interface NewsView {

    RecyclerView getRecyclerView();
    NewsAdapter getNewsAdapter();
    void setDataRefresh(Boolean refresh);

}
