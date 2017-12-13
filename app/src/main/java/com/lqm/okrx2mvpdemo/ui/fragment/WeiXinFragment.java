package com.lqm.okrx2mvpdemo.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.adapter.WeiXinAdapter;
import com.lqm.okrx2mvpdemo.ui.base.BaseFragment;
import com.lqm.okrx2mvpdemo.ui.presenter.WeiXinPresenter;
import com.lqm.okrx2mvpdemo.ui.view.WeiXinView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：微信文章
 */

public class WeiXinFragment extends BaseFragment<WeiXinView, WeiXinPresenter>
                            implements WeiXinView,SwipeRefreshLayout.OnRefreshListener,
                              BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private WeiXinAdapter mAdapter;

    @Override
    protected WeiXinPresenter createPresenter() {
        return new WeiXinPresenter(getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_weixin;
    }

    @Override
    public void initView(View rootView) {
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new WeiXinAdapter(getContext(),null);
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this);

        onRefresh();

    }


    @Override
    public void setDataRefresh(Boolean refresh) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(refresh);
            }
        });
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rvContent;
    }

    @Override
    public WeiXinAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onRefresh() {
        mPresenter.getRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();

    }
}
