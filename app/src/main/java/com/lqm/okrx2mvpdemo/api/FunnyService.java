package com.lqm.okrx2mvpdemo.api;

import com.lqm.okrx2mvpdemo.helper.JsonConvert;
import com.lqm.okrx2mvpdemo.model.Funny;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;

/**
 * user：lqm
 * desc：笑话
 */

public class FunnyService {

    public static final String TIANXIN_FUNNY = "https://api.tianapi.com/txapi/joke";

    public static Observable<Funny> getFunnyData(int page, int num){
        return OkGo.<Funny>get(TIANXIN_FUNNY)
                .params("key","d54d8c95b85b8c4fe651976a731538e0")
                .params("page",page)
                .params("num",num)
                .converter(new JsonConvert<Funny>() {})
                .adapt(new ObservableBody<Funny>());
    }


}
