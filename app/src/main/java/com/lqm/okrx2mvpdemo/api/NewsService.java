package com.lqm.okrx2mvpdemo.api;

import com.lqm.okrx2mvpdemo.helper.JsonConvert;
import com.lqm.okrx2mvpdemo.model.NewsModel;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;

/**
 * user：lqm
 * desc：新闻
 */

public class NewsService {

    public static String NEWS_BASE_URL = "https://api.tianapi.com/";

    public static Observable<NewsModel> getNewsData(int page,int num){
        String itUrl  = NEWS_BASE_URL+"it";
        return OkGo.<NewsModel>get(itUrl)
                .params("key","d54d8c95b85b8c4fe651976a731538e0")
                .params("page",page)
                .params("num",num)
                .converter(new JsonConvert<NewsModel>() {})
                .adapt(new ObservableBody<NewsModel>());
    }




}