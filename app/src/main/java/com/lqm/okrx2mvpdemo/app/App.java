package com.lqm.okrx2mvpdemo.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by luqinmao on 2017/12/11.
 */

public class App extends Application {

    public static List<Activity> activities = new LinkedList<>();
    public static Context CONTEXT ;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this.getApplicationContext();

    }

    /**
     * 完全退出
     * 一般用于“退出程序”功能
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}
