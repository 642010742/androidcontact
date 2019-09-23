package com.dwz.threadapplication.contact.data;

import android.os.Binder;

import com.dwz.threadapplication.contact.model.ContactPersonModel;
import com.dwz.threadapplication.contact.listener.CallBack;
import com.dwz.threadapplication.contact.listener.CallBack1;

import java.util.List;


/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public class ContactBinder extends Binder implements CallBack {

    public CallBack1 callBack1;

    public void setCallBack1(CallBack1 callBack1) {
        this.callBack1 = callBack1;
    }

    @Override
    public void startRead() {

    }

    @Override
    public void fianlData(List<ContactPersonModel> list) {
        if(callBack1 != null){
            callBack1.getContactDatas(list);
        }
    }
}
