package com.dwz.threadapplication.contact.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.dwz.threadapplication.contact.Config;
import com.dwz.threadapplication.contact.model.ContactPersonModel;
import com.dwz.threadapplication.contact.listener.CallBack;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public class ContactThread implements Runnable{

    public boolean isRunning= false;
    public CallBack callBack;

    private ContentResolver mContentResolver;

    public ContactThread(CallBack callBack){
        this.callBack = callBack;
       mContentResolver = Config.context.getContentResolver();
    }

    public ContactThread(Context context, CallBack callBack){
        this.callBack = callBack;
        mContentResolver = context.getContentResolver();
    }


    public ContactThread(ContentResolver contentResolver, CallBack callBack){
        this.callBack = callBack;
        mContentResolver = contentResolver;
    }


    @Override
    public void run() {
        if(isRunning){
            return;
        }
        isRunning =true;
        if(callBack != null){
            callBack.startRead();
        }
        final Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //指定获取_id和display_name两列数据，display_name即为姓名
        final String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };

        List<ContactPersonModel> list = new ArrayList<>();
        Cursor query = mContentResolver.query(uri, projection, null, null, null);
        while (query != null && query.moveToNext()) {
            ContactPersonModel contactPersonModel = new ContactPersonModel();
            String string1 = query.getString(1);
            String string2 = query.getString(query.getColumnIndex(ContactsContract.Contacts._ID));
            contactPersonModel.setContactName(string1);
            contactPersonModel.setBitmap(userBitmap(Long.valueOf(string2)));
            Cursor phones = mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ string2,
                    null, null);
            assert phones != null;
            List<String> phoneList = new ArrayList<>();
            while(phones.moveToNext())
            {
                String phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneList.add(phoneNumber);
                Log.e("Contact_BENBEN",string1+"=========电话号码======="+phoneNumber);
                //添加Phone的信息
            }

            contactPersonModel.setContactPhoneNumber(phoneList);

            getRawContacts(Long.parseLong(string2),contactPersonModel);


            phones.close();

            list.add(contactPersonModel);

        }
        if(callBack != null){
            callBack.fianlData(list);
        }
        assert query != null;
        query.close();
    }

    /**
     * 获取手机联系人 原始数据 更改版本 version
     * @param rawContactId
     */
    public void getRawContacts(long rawContactId,ContactPersonModel contactPersonModel){
        Uri rawContactUri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId);
        Uri entityUri = Uri.withAppendedPath(rawContactUri, ContactsContract.RawContacts.Entity.CONTENT_DIRECTORY);
        Cursor c = mContentResolver.query(entityUri,
                new String[]{ContactsContract.RawContacts.SOURCE_ID, ContactsContract.RawContacts.Entity.DATA_ID, ContactsContract.RawContacts.Entity.MIMETYPE, ContactsContract.RawContacts.Entity.DATA1
                        ,ContactsContract.RawContacts.VERSION
                        ,ContactsContract.RawContacts.ACCOUNT_NAME},
                null, null, null);
        try {
            assert c != null;
            while (c.moveToNext()) {
                if (!c.isNull(1) && !c.isNull(3)) {
                    String string = c.getString(4);
                    contactPersonModel.setVersion(Integer.valueOf(string));
                }
            }
        } finally {
            assert c != null;
            c.close();
        }
    }

    /**
     * 用户头像
     * @param contactId
     */
    public Bitmap userBitmap(long contactId){
        try {
            ContentResolver cr = Config.context.getContentResolver();
            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                    contactId);
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
            Bitmap photo = BitmapFactory.decodeStream(input);
            Log.e("BITMAP","============联系人头像================"+photo);
            return photo;
        }catch (Exception e){
            return null;
        }
    }
}
