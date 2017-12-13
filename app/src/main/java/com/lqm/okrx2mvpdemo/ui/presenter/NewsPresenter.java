package com.lqm.okrx2mvpdemo.ui.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.api.NewsService;
import com.lqm.okrx2mvpdemo.model.pojoVO.NewsModel;
import com.lqm.okrx2mvpdemo.ui.base.BasePresenter;
import com.lqm.okrx2mvpdemo.ui.view.NewsView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：新闻
 */

public class NewsPresenter extends BasePresenter<NewsView> {


    private static final int PAGENUM = 10;
    private FragmentActivity mActivity;
    private NewsView mNewsView;
    private int mCurrentPage;

    public NewsPresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    //刷新
    public void refreshData() {
        mCurrentPage = 1;
        mNewsView = getView();
        NewsService.getNewsData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        mNewsView.setDataRefresh(true);
                    }

                    @Override
                    public void onNext(NewsModel newsModel) {
                        if (newsModel.getNewslist().size() != 0) {   //防止崩溃
                            mNewsView.getNewsAdapter().addData(newsModel.getNewslist());
                            //显示没有更多数据
                            if (newsModel.getNewslist().size() == 0) {
                                mNewsView.getNewsAdapter().loadComplete();         //加载完成
                                View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
                                mNewsView.getNewsAdapter().addFooterView(noDataView);
                            }
                        } else {
                            mNewsView.getNewsAdapter().loadComplete();         //加载完成
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mNewsView.getNewsAdapter().showLoadMoreFailedView();
                        mNewsView.setDataRefresh(false);
                        Snackbar.make(mNewsView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //可能需要移除之前添加的布局
                        mNewsView.getNewsAdapter().removeAllFooterView();
                        //最后调用结束刷新的方法
                        mNewsView.setDataRefresh(false);
                    }
                });

    }

    //加载更多
    public void loadMoreData() {
        mCurrentPage = mCurrentPage + 1;
        mNewsView = getView();
        NewsService.getNewsData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsModel -> setMoreDataView(newsModel),this::showError);
    }

    private void setMoreDataView(NewsModel newsModel){
        if (newsModel.getNewslist().size() != 0) {
                mNewsView.getNewsAdapter().addData(newsModel.getNewslist());
        } else {
            mNewsView.getNewsAdapter().loadComplete();
            View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
            mNewsView.getNewsAdapter().addFooterView(noDataView);
        }
    }

    private void showError(Throwable e){
        mNewsView.getNewsAdapter().showLoadMoreFailedView();
        Snackbar.make(mNewsView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
    }

}
