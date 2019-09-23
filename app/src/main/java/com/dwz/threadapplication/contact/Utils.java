package com.dwz.threadapplication.contact;

import android.text.TextUtils;
import android.util.Log;

import com.dwz.threadapplication.contact.model.ContactPersonModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/10
 * @describe
 * @DWZ
 */
public class Utils {

    /**
     * 比较数据库版本
     * @param phonePersonList
     * @return
     */
    public static List<ContactPersonModel> CompareDatabaseContactPersonDataVersion
            (List<ContactPersonModel> phonePersonList){
        boolean isFist = false;
        List<ContactPersonModel> upList = new ArrayList<>();
        List<ContactPersonModel> contactPersonModels = ContactPersonDao.getInstance().queryAll();
        Log.e("CONTACT_ZMF","=数据库数据="+new Gson().toJson(contactPersonModels));
        HashMap<String,Integer> hashMap = new HashMap<>();
        if (contactPersonModels != null) {
            for (int i = 0; i < contactPersonModels.size(); i++) {
                ContactPersonModel contactPersonModel = contactPersonModels.get(i);
                List<String> contactPhoneNumber = contactPersonModel.getContactPhoneNumber();
                String phone = contactPhoneNumber.size()>0?contactPhoneNumber.get(0):"";
                int version = contactPersonModel.getVersion();
                Log.e("Contacnt_DWZ",contactPersonModel.getContactName()+"========数据库版本号=====1===="+new Gson().toJson(contactPhoneNumber));
                if (!TextUtils.isEmpty(phone)) {
                    hashMap.put(phone,version);
                    Log.e("Contacnt_DWZ","========数据库版本号========="+phone+"==="+version);
                }
            }
        }else{
            Log.e("SQLLITE","========数据库不存在插入数据库======");
            ContactPersonDao.getInstance().add(phonePersonList);
            isFist = true;
        }

        if(!isFist){
            for (int i = 0; i < phonePersonList.size(); i++) {
                ContactPersonModel contactPersonModel = phonePersonList.get(i);
                int version = contactPersonModel.getVersion();
                List<String> contactPhoneNumber = contactPersonModel.getContactPhoneNumber();
                String phone = contactPhoneNumber.size()>0?contactPhoneNumber.get(0):"";
                if (!TextUtils.isEmpty(phone)) {
                    Integer integer = hashMap.get(phone);
                    Log.e("CONTACT_ZMF","=======数据库的版本号======="+version+"==="+integer);
                    if (integer == null) {
                        // 电话号码 数据库没有 默认为新增数据 （更改联系人电话号码 认为是新增）
                        contactPersonModel.setAdd(true);
                        ContactPersonDao.getInstance().add(contactPersonModel);
                        upList.add(contactPersonModel);
                    }else{
                        if (version>integer) {
                            contactPersonModel.setAdd(false);
                            upList.add(contactPersonModel);
                        }
                    }
                }
            }
        }
        return upList;
    }

}
