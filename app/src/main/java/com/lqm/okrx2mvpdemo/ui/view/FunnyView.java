package com.lqm.okrx2mvpdemo.ui.view;

import android.support.v7.widget.RecyclerView;

import com.lqm.okrx2mvpdemo.ui.adapter.FunnyAdapter;

/**
 * user：lqm
 * desc：笑话
 */

public interface FunnyView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    FunnyAdapter getAdapter();

}
