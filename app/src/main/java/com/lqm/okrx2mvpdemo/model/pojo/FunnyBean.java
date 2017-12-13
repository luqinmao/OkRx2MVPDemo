package com.lqm.okrx2mvpdemo.model.pojo;

import java.io.Serializable;

/**
 * user：lqm
 * desc：笑话
 */

public class FunnyBean implements Serializable {
    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}