package com.dwz.threadapplication.contact;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class Config {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    /**
     * 是否关系联系人的变化
     */
    public static boolean coreContactChange;

    /**
     * 数据库 名字
     */
     static final String DB_NAME = "contact_info.db";
    /**
     * 数据库版本
     */
     static final int DB_VERSION = 1;


    /**
     * 联系人表的名字
     */
    static final String TB_CONTACT = "contact_message";

}
