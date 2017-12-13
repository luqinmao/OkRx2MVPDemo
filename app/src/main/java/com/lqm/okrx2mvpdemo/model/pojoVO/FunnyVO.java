package com.lqm.okrx2mvpdemo.model.pojoVO;

import com.lqm.okrx2mvpdemo.model.pojo.FunnyBean;

import java.io.Serializable;
import java.util.List;

/**
 * 笑话
 */

public class FunnyVO implements Serializable {

    private int code;
    private String msg;

    private List<FunnyBean> newslist;

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

    public List<FunnyBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<FunnyBean> newslist) {
        this.newslist = newslist;
    }

}
