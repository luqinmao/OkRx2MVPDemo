package com.lqm.okrx2mvpdemo.model.pojoVO;

import com.lqm.okrx2mvpdemo.model.pojo.Stories;
import com.lqm.okrx2mvpdemo.model.pojo.TopStories;

import java.io.Serializable;
import java.util.List;

/**
 * autour: lqm
 * desc: 知乎
 */
public class NewsTimeLine implements Serializable {

    private String date;
    private List<Stories> stories;
    private List<TopStories> top_stories;

    public String getDate() {
        return date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public List<TopStories> getTop_stories() {
        return top_stories;
    }

    @Override
    public String toString() {
        return "NewsTimeLine{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
