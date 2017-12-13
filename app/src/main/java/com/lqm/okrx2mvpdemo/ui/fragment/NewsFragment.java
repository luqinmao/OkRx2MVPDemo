package com.lqm.okrx2mvpdemo.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.adapter.NewsAdapter;
import com.lqm.okrx2mvpdemo.ui.base.BaseFragment;
import com.lqm.okrx2mvpdemo.ui.presenter.NewsPresenter;
import com.lqm.okrx2mvpdemo.ui.view.NewsView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：新闻页
 */

public class NewsFragment extends BaseFragment<NewsView, NewsPresenter>
                    implements NewsView,SwipeRefreshLayout.OnRefreshListener,
                                BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private NewsAdapter mNewsAdapter;

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_news;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rvContent;
    }

    @Override
    public NewsAdapter getNewsAdapter() {
        return mNewsAdapter;
    }

    @Override
    public void initView(View rootView) {

        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsAdapter = new NewsAdapter(getContext(),null);
        mNewsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mNewsAdapter.isFirstOnly(false);
        rvContent.setAdapter(mNewsAdapter);

        swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefresh.setOnRefreshListener(this);
        mNewsAdapter.setOnLoadMoreListener(this);

        onRefresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreData();
    }

    @Override
    public void onRefresh() {
        setDataRefresh(true);
        mPresenter.refreshData();
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

}
