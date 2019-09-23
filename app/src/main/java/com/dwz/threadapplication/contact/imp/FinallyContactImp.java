package com.dwz.threadapplication.contact.imp;

import android.app.Activity;
import android.os.Handler;
import android.provider.ContactsContract;

import com.dwz.threadapplication.contact.Config;
import com.dwz.threadapplication.contact.ContactPersonDao;
import com.dwz.threadapplication.contact.api.FinallContactDataListener;
import com.dwz.threadapplication.contact.api.FinallyContactListener;
import com.dwz.threadapplication.contact.data.MyContentObserver;
import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe
 * @DWZ
 */
public class FinallyContactImp implements FinallyContactListener {

    private Activity mContext;

    private  ContactInfoImp contactInfoImp;

    private FinallContactDataListener finallContactDataListener;

    private MyContentObserver myContentObser;

    public FinallyContactImp() {

    }

    public FinallyContactImp(Activity mContext,FinallContactDataListener finallContactDataListener) {
        this.mContext =mContext;
        this.finallContactDataListener = finallContactDataListener;
    }


    @Override
    public void registerContactChange() {
        if(Config.coreContactChange){
           myContentObser = new MyContentObserver(new Handler(),finallContactDataListener);
           mContext.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true,
                    myContentObser );
        }
    }

    @Override
    public void onCreate() {
        contactInfoImp = new ContactInfoImp(mContext,finallContactDataListener);
        boolean b = contactInfoImp.checkIsContionContactPermission();
        if(b){
            startServer();
            registerContactChange();
        }
    }

    @Override
    public void onDestory() {
        mContext.unbindService(contactInfoImp);
        if(Config.coreContactChange){
            if (myContentObser != null) {
                mContext.getContentResolver().unregisterContentObserver(myContentObser);
            }
        }
    }

    @Override
    public void startServer() {
        if (contactInfoImp == null) {
            contactInfoImp = new ContactInfoImp(mContext,finallContactDataListener);
        }
        contactInfoImp.startContactServer();
    }

    @Override
    public void reset(List<ContactPersonModel> upList) {
        // 处理完新添加的联系人  需要更新本地数据库
        ContactPersonDao.getInstance().upContacts(upList);
    }
}
