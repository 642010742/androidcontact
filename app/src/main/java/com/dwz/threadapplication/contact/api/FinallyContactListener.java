package com.dwz.threadapplication.contact.api;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public interface FinallyContactListener {
    /**
     * 注册联系人变换监听
     */
    void registerContactChange();

    /**
     * 新建初始化(里面包含了开启获取手机联系人的服务)
     */
    void onCreate();

    /**
     * 关闭 服务
     */
    void onDestory();

    /**
     * 开启获取手机联系人服务
     */
    void startServer();
    /**
     * 重置（把新增的联系人数据更新到本地数据库）
     * @param upList   更改的联系人数据
     */
    void reset(List<ContactPersonModel> upList);

}
