package com.dwz.threadapplication.contact.listener;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public interface CallBack {

    void startRead();
    void fianlData(List<ContactPersonModel> list);

}
