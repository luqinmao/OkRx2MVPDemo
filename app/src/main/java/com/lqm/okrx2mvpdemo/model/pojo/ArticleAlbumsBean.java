package com.lqm.okrx2mvpdemo.model.pojo;

import java.io.Serializable;

/**
 * user：lqm
 * desc：资讯专辑
 */

public class ArticleAlbumsBean implements Serializable {
    /**
     * id : 329
     * title : BATTLE PRO 2017
     * pic : https://oos.jamyo.net/20170305/1488682764684275.jpeg
     * watchCount : 9.4万
     */

    private int id;
    private String title;
    private String pic;
    private String watchCount;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWatchCount() {
        if (!watchCount.contains("万")){
            int tempInt = Integer.parseInt(watchCount);
            if (tempInt >= 10000){
                double n = (double)tempInt/10000;
                String result = String.format("%.2f",n); //保留两位小数
                return result+"万";
            }else{
                return watchCount;
            }
        }else{
            return watchCount;
        }
    }

    public void setWatchCount(String watchCount) {
        this.watchCount = watchCount;
    }
}
