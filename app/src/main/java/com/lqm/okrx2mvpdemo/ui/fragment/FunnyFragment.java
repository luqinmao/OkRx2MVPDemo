package com.lqm.okrx2mvpdemo.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.adapter.FunnyAdapter;
import com.lqm.okrx2mvpdemo.ui.base.BaseFragment;
import com.lqm.okrx2mvpdemo.ui.presenter.FunnyPresenter;
import com.lqm.okrx2mvpdemo.ui.view.FunnyView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：笑话
 */

public class FunnyFragment extends BaseFragment<FunnyView,FunnyPresenter>
                            implements FunnyView,SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener{
        @Bind(R.id.rv_content)
        RecyclerView rvContent;
        @Bind(R.id.swipe_refresh)
        SwipeRefreshLayout swipeRefresh;
        private FunnyAdapter mAdapter;

        @Override
        protected FunnyPresenter createPresenter() {
            return new FunnyPresenter(getActivity());
        }

        @Override
        protected int provideContentViewId() {
            return R.layout.frag_weixin;
        }

        @Override
        public void initView(View rootView) {
            rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapter = new FunnyAdapter(getContext(),null);
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
        public FunnyAdapter getAdapter() {
            return mAdapter;
        }

        @Override
        public void onRefresh() {
            setDataRefresh(true);
            mPresenter.getRefreshData();
        }

        @Override
        public void onLoadMoreRequested() {
            mPresenter.getMoreData();
        }
    }
