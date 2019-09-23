package com.dwz.threadapplication.contact.api;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public interface FinallContactDataListener {
    /**
     *
     * @param list  手机联系人的全部数据
     * @param upList  新增和增加的联系人数据
     */
    void contactData(List<ContactPersonModel> list,List<ContactPersonModel> upList);
}
