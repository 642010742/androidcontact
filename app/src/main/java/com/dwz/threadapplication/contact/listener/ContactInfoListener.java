package com.dwz.threadapplication.contact.listener;

import android.content.ComponentName;
import android.os.IBinder;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public interface ContactInfoListener {


    void startContactServer();

    void onServiceConnected(ComponentName name, IBinder service, List<ContactPersonModel> list);

    void onServiceDisconnected(ComponentName name);


}
