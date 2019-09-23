package com.dwz.threadapplication;

import android.app.Application;
import android.content.Context;

import com.dwz.threadapplication.contact.Contacts;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class App extends Application {

    public static App app;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        app = this;
        Contacts.init(context,true);
    }


    public static Context getContext(){
        return context;
    }

    public static App getApp(){
        return app;
    }
}

