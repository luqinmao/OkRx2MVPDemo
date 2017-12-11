package com.lqm.okrx2mvpdemo.api;


import com.lqm.okrx2mvpdemo.helper.JsonConvert;
import com.lqm.okrx2mvpdemo.model.News;
import com.lqm.okrx2mvpdemo.model.NewsTimeLine;
import com.lqm.okrx2mvpdemo.model.SplashImage;
import com.lqm.okrx2mvpdemo.util.RxUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;

/**
 * @user  lqm
 * @desc  知乎模块相关的接口
 * .converter()：该方法是网络请求的转换器，功能是将网络请求的结果转换成我们需要的数据类型。
 * .adapt()：该方法是方法返回值的适配器，功能是将网络请求的Call<T>对象，适配成我们需要的Observable<T>对象。
 */
public class ZhihuService {
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";

    private static String getSplashImage = ZHIHU_BASE_URL+"start-image/1080*1920";
    private static String getLatestNews = ZHIHU_BASE_URL+"news/latest";
    private static String getBeforetNews = ZHIHU_BASE_URL+"news/before";
    private static String getDetailNews = ZHIHU_BASE_URL+"news";


    public static Observable<SplashImage> getSplashImage(){
        return RxUtils.request(HttpMethod.GET,getSplashImage,SplashImage.class);
    }

    public static Observable<NewsTimeLine> getLatestNews(){
        return RxUtils.request(HttpMethod.GET,getLatestNews,NewsTimeLine.class);
    }

    public static Observable<NewsTimeLine> getBeforetNews(String param){
        String tempUrl  = getBeforetNews+"/"+param;
        return OkGo.<NewsTimeLine>get(tempUrl)//
                .converter(new JsonConvert<NewsTimeLine>() {})//
                .adapt(new ObservableBody<NewsTimeLine>());//
    }

    public static Observable<News> getDetailNews(String param) {
        String tempUrl  = getDetailNews+"/"+param;
        return OkGo.<News>get(tempUrl)//
//                .params("id", param)//
                .converter(new JsonConvert<News>() {})//
                .adapt(new ObservableBody<News>());//

    }

}
