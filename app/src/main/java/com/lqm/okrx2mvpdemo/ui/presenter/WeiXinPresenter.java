package com.lqm.okrx2mvpdemo.ui.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.api.WeiXinService;
import com.lqm.okrx2mvpdemo.model.pojoVO.WeiXinArticleVO;
import com.lqm.okrx2mvpdemo.ui.base.BasePresenter;
import com.lqm.okrx2mvpdemo.ui.view.WeiXinView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * user：lqm
 * desc：微信文章
 */

public class WeiXinPresenter extends BasePresenter<WeiXinView> {

    private static final int PAGENUM = 10;
    private FragmentActivity mActivity;
    private WeiXinView mWxView;
    private int mCurrentPage;

    public WeiXinPresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    //刷新
    public void getRefreshData() {
        mCurrentPage = 1;
        mWxView = getView();
        WeiXinService.getArticleData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeiXinArticleVO>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mWxView.setDataRefresh(true);
                    }

                    @Override
                    public void onNext(WeiXinArticleVO newsModel) {
                        if (newsModel.getNewslist().size() != 0) {   //防止崩溃
                            mWxView.getAdapter().addData(newsModel.getNewslist());
                            //显示没有更多数据
                            if (newsModel.getNewslist().size() == 0) {
                                mWxView.getAdapter().loadComplete();         //加载完成
                                View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
                                mWxView.getAdapter().addFooterView(noDataView);
                            }
                        } else {
                            mWxView.getAdapter().loadComplete();         //加载完成
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {
                        //可能需要移除之前添加的布局
                        mWxView.getAdapter().removeAllFooterView();
                        //最后调用结束刷新的方法
                        mWxView.setDataRefresh(false);
                    }
                });

    }

    //加载更多
    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        mWxView = getView();
        WeiXinService.getArticleData(mCurrentPage, PAGENUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleData -> setMoreDataView(articleData),this::showError);
    }

    private void setMoreDataView(WeiXinArticleVO articleData){
        if (articleData.getNewslist().size() != 0) {
            mWxView.getAdapter().addData(articleData.getNewslist());
        } else {
            mWxView.getAdapter().loadComplete();
            View noDataView = View.inflate(mActivity, R.layout.item_no_data, null);
            mWxView.getAdapter().addFooterView(noDataView);
        }
    }

    private void showError(Throwable e){
        mWxView.setDataRefresh(false);
        Snackbar.make(mWxView.getRecyclerView(), e.getMessage() + "", Snackbar.LENGTH_SHORT).show();
    }

}