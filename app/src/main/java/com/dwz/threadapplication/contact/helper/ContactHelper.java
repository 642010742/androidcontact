package com.dwz.threadapplication.contact.helper;


import android.app.Activity;

import com.dwz.threadapplication.contact.api.FinallContactDataListener;
import com.dwz.threadapplication.contact.api.FinallyContactListener;
import com.dwz.threadapplication.contact.imp.FinallyContactImp;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class ContactHelper {

    public static FinallyContactListener getFinallyContactImp(Activity activity, FinallContactDataListener finallContactDataListener){
        return new FinallyContactImp(activity,finallContactDataListener);
    }

}
