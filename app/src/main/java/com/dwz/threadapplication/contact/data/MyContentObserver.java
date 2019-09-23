package com.dwz.threadapplication.contact.data;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.dwz.threadapplication.contact.Utils;
import com.dwz.threadapplication.contact.api.FinallContactDataListener;
import com.dwz.threadapplication.contact.listener.CallBack;
import com.dwz.threadapplication.contact.listener.ContactDataChangeListener;
import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public class MyContentObserver extends ContentObserver  {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */

    private FinallContactDataListener finallContactDataListener;
    private long cutTime = System.currentTimeMillis();
    public MyContentObserver(Handler handler, FinallContactDataListener finallContactDataListener) {
        super(handler);
        this.finallContactDataListener = finallContactDataListener;
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        // 使用同步锁  保证数据在一次请求内一致
        synchronized (MyContentObserver.this){
            if(finallContactDataListener != null){
                if (System.currentTimeMillis()-cutTime>1000) {
                    ContactThread contactThread = new ContactThread(new CallBack() {
                        @Override
                        public void startRead() {

                        }

                        @Override
                        public void fianlData(List<ContactPersonModel> list) {
                            finallContactDataListener.contactData(list, Utils.CompareDatabaseContactPersonDataVersion(list));
                        }
                    });
                    Thread thread = new Thread(contactThread);
                    thread.start();
                }
                cutTime = System.currentTimeMillis();
            }
        }
        Log.e("onChange","===========2======"+selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        Log.e("onChange",uri+"========3========="+selfChange);
    }
}
