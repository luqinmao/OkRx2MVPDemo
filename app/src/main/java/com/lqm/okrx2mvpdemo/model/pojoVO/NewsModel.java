package com.lqm.okrx2mvpdemo.model.pojoVO;

import com.lqm.okrx2mvpdemo.model.pojo.NewslistBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luqinmao on 2017/5/30.
 * 天行新闻model
 */

public class NewsModel implements Serializable {


    private int code;
    private String msg;

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

}