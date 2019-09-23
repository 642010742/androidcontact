package com.dwz.threadapplication.contact.listener;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public interface DBListener {
    List<ContactPersonModel> queryAll();
    List<ContactPersonModel> queryOnlyOne(String condition);
    void delAll();
    void delOnlyOne(String[] conditions);
    void upContacts(List<ContactPersonModel> list);
    void add(List<ContactPersonModel> list);
    void add(ContactPersonModel contactPersonModel);

}
