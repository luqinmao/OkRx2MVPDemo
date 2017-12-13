package com.lqm.okrx2mvpdemo.api;

import com.lqm.okrx2mvpdemo.helper.JsonConvert;
import com.lqm.okrx2mvpdemo.model.pojoVO.WeiXinArticleVO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;

/**
 * user：lqm
 * desc：微信文章
 */

public class WeiXinService {

    public static final String TIANXIN_WEIXIN_ARTICLE = "https://api.tianapi.com/wxnew";

    public static Observable<WeiXinArticleVO> getArticleData(int page, int num){
        return OkGo.<WeiXinArticleVO>get(TIANXIN_WEIXIN_ARTICLE)
                .params("key","d54d8c95b85b8c4fe651976a731538e0")
                .params("word","精选")
                .params("rand", 1) //1:随机获取
                .params("page",page)
                .params("num",num)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new JsonConvert<WeiXinArticleVO>() {})
                .adapt(new ObservableBody<WeiXinArticleVO>());
    }


}
