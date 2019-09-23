package com.dwz.threadapplication.contact.listener;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.dwz.threadapplication.contact.data.ContactBinder;
import com.dwz.threadapplication.contact.data.ContactPersonServer;
import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public abstract class ContactCallBack implements ContactInfoListener, ServiceConnection,PermissionListener{


    public static final int REQUEST = 150;

    public Activity mContext;

    public ContactCallBack(){

    }

    public ContactCallBack(Activity context){
        mContext = context;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean checkIsContionContactPermission() {
        if(mContext != null){
            if (mContext.checkSelfPermission(Manifest.permission.WRITE_CONTACTS)==0
            && mContext.checkSelfPermission(Manifest.permission.READ_CONTACTS)==0
            && mContext.checkSelfPermission(Manifest.permission.GET_ACCOUNTS)==0) {
                return true;
            }else{
                requestPermission();
            }
        }
        return false;
    }

    @Override
    public void startContactServer() {
        if (mContext != null) {
                mContext.bindService(new Intent(mContext, ContactPersonServer.class),this, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onServiceConnected(final ComponentName name, final IBinder service) {
        ContactBinder contactBinder = (ContactBinder) service;
        contactBinder.setCallBack1(new CallBack1() {
            @Override
            public void getContactDatas(List<ContactPersonModel> list) {
                // 联系人列表
                onServiceConnected(name,service,list);
            }
        });

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
            onServiceDisconnected(name);
    }

    @Override
    public boolean requestPermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_CONTACTS
                ,Manifest.permission.READ_CONTACTS
                ,Manifest.permission.GET_ACCOUNTS
        };
        mContext.requestPermissions(permissions,REQUEST);
        return false;
    }
}
