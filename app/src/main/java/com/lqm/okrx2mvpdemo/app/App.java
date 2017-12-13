package com.lqm.okrx2mvpdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by luqinmao on 2017/12/11.
 */

public class App extends Application {


    public static Context CONTEXT ;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this.getApplicationContext();

    }



}
