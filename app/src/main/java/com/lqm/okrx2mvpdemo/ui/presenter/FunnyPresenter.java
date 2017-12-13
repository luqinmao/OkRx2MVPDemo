package com.lqm.okrx2mvpdemo.ui.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.api.FunnyService;
import com.lqm.okrx2mvpdemo.model.Funny;
import com.lqm.okrx2mvpdemo.ui.base.BasePresenter;
import com.lqm.okrx2mvpdemo.ui.view.FunnyView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：笑话
 */

public class FunnyPresenter extends BasePresenter<FunnyView> {
    private static final int PAGENUM = 10;
    private FragmentActivity mActivity;
    private FunnyView mFunnyView;
    private int mCurrentPage;

    public FunnyPresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    //刷新
    public void getRefreshData() {
        mCurrentPage = 1;
        mFunnyView = getView();
        FunnyService.getFunnyData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Funny>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        mFunnyView.setDataRefresh(true);
                    }

                    @Override
                    public void onNext(Funny newsModel) {
                        if (newsModel.getNewslist().size() != 0) {   //防止崩溃
                            mFunnyView.getAdapter().addData(newsModel.getNewslist());
                            //显示没有更多数据
                            if (newsModel.getNewslist().size() == 0) {
                                mFunnyView.getAdapter().loadComplete();         //加载完成
                                View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
                                mFunnyView.getAdapter().addFooterView(noDataView);
                            }
                        } else {
                            mFunnyView.getAdapter().loadComplete();         //加载完成
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFunnyView.getAdapter().showLoadMoreFailedView();
                        mFunnyView.setDataRefresh(false);
                        Snackbar.make(mFunnyView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //可能需要移除之前添加的布局
                        mFunnyView.getAdapter().removeAllFooterView();
                        //最后调用结束刷新的方法
                        mFunnyView.setDataRefresh(false);
                    }
                });

    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        mFunnyView = getView();
        FunnyService.getFunnyData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleData -> setMoreDataView(articleData),this::showError);
    }

    private void setMoreDataView(Funny articleData){
        if (articleData.getNewslist().size() != 0) {
            mFunnyView.getAdapter().addData(articleData.getNewslist());
        } else {
            mFunnyView.getAdapter().loadComplete();
            View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
            mFunnyView.getAdapter().addFooterView(noDataView);
        }
    }

    private void showError(Throwable e){
        mFunnyView.getAdapter().showLoadMoreFailedView();
        Snackbar.make(mFunnyView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
    }

}