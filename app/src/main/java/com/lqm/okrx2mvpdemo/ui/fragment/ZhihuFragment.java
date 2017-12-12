package com.lqm.okrx2mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class ZhihuFragment extends BaseFragment<IZhihuFgView,ZhihuFgPresenter> implements IZhihuFgView {

    private LinearLayoutManager mLayoutManager;
    @Bind(R.id.content_list)
    RecyclerView content_list;

    @Override
    protected ZhihuFgPresenter createPresenter() {
        return new ZhihuFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.frag_zhihu;
    }

    @Override
    protected void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getContext());
        content_list.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getLatestNews();
        mPresenter.scrollRecycleView();
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getLatestNews();
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return content_list;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}
