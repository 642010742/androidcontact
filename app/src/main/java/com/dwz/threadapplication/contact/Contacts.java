package com.dwz.threadapplication.contact;

import android.content.Context;

/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public class Contacts {


    public static void init(Context context){
       init(context,false);
    }

    public static void init(Context context,boolean isCoreContactChange){
        if (context ==null) {
            throw new NullPointerException("请初始化Context!!!");
        }
        Config.context = context;
        Config.coreContactChange = isCoreContactChange;
        DBHelper.getDatabase();
    }

}
