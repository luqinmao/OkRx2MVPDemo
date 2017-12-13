package com.lqm.okrx2mvpdemo.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.base.BaseFragment;
import com.lqm.okrx2mvpdemo.ui.presenter.ZhihuFgPresenter;
import com.lqm.okrx2mvpdemo.ui.view.IZhihuFgView;

import butterknife.Bind;

/**
 * @user  lqm
 * @desc  知乎
 */
public class ZhihuFragment extends BaseFragment<IZhihuFgView,ZhihuFgPresenter>
        implements IZhihuFgView ,SwipeRefreshLayout.OnRefreshListener{

    private LinearLayoutManager mLayoutManager;
    @Bind(R.id.content_list)
    RecyclerView content_list;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected ZhihuFgPresenter createPresenter() {
        return new ZhihuFgPresenter(getContext());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.frag_zhihu;
    }

    @Override
    public void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getContext());
        content_list.setLayoutManager(mLayoutManager);

        swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefresh.setOnRefreshListener(this);

        onRefresh();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return content_list;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void onRefresh() {
        setDataRefresh(true);
        mPresenter.getLatestNews();
        mPresenter.scrollRecycleView();

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
