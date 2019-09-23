package com.dwz.threadapplication.contact.data;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;


/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
@SuppressLint("Registered")
public class ContactPersonServer extends Service {

    private ContactBinder contactBinder;

    @Override
    public IBinder onBind(Intent intent) {
        return contactBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public ComponentName startService(Intent service) {

        return super.startService(service);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        contactBinder = new ContactBinder();
        ContactThread contactThread = new ContactThread(contactBinder);
        Thread thread = new Thread(contactThread);
        thread.start();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
