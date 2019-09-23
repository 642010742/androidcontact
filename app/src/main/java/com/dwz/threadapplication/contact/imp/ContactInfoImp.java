package com.dwz.threadapplication.contact.imp;

import android.app.Activity;
import android.content.ComponentName;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.dwz.threadapplication.contact.ContactPersonDao;
import com.dwz.threadapplication.contact.Utils;
import com.dwz.threadapplication.contact.api.FinallContactDataListener;
import com.dwz.threadapplication.contact.listener.ContactCallBack;
import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class ContactInfoImp extends ContactCallBack {

    public FinallContactDataListener finallContactDataListener;

    public ContactInfoImp(Activity mContext, FinallContactDataListener finallContactDataListener){
        super.mContext = mContext;
        this.finallContactDataListener = finallContactDataListener;
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service, List<ContactPersonModel> list) {
        finallContactDataListener.contactData(list, Utils.CompareDatabaseContactPersonDataVersion(list));
    }
}
