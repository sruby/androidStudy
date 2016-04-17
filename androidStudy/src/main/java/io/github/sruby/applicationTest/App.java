package io.github.sruby.applicationTest;

import android.app.Application;

/**
 * 作者: Sruby on 2016/4/3 0003 14:13
 * 描述:
 */
public class App extends Application{
    private String appData = "default";

    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    public void onCreate() {
        System.out.println("应用初始化!");
    }

}
