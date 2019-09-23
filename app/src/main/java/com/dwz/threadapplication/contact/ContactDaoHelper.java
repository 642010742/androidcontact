package com.dwz.threadapplication.contact;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dwz.threadapplication.contact.model.ContactPersonModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/10
 * @describe
 * @DWZ
 */
public class ContactDaoHelper {


    public static SQLiteDatabase database;

    public static ContactDaoHelper contactDaoHelper;

    public static ContactDaoHelper getInstance(){
        if (contactDaoHelper == null) {
            contactDaoHelper = new ContactDaoHelper();
        }
        return contactDaoHelper;
    }

    public ContactDaoHelper() {
        database = DBHelper.getDatabase();
    }



    public List<ContactPersonModel> query_(String phone) {
        Cursor cursor = database.query(Config.TB_CONTACT, null, ContactPersonModel.CONTACT_PHONENUMBER + "=?",
                new String[]{phone}, null, null, null);
        List<ContactPersonModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactPersonModel bean = new ContactPersonModel();
            bean.setContactEmail(cursor.getString(ContactPersonModel.CONTACT_EMAIL));
            bean.setContactName(cursor.getString(ContactPersonModel.CONTACT_NAME));
            bean.setContactPhoneNumber(cursor.getString(ContactPersonModel.CONTACT_PHONENUMBER)
                    != null? Arrays.asList(cursor.getString(ContactPersonModel.CONTACT_PHONENUMBER).split(",")):new ArrayList<String>());
            bean.setLetter(cursor.getString(ContactPersonModel.LETTER));
            bean.setQrPath(cursor.getString(ContactPersonModel.QRPATH));
            bean.setPhoneCardType(cursor.getString(ContactPersonModel.PHONECARD_TYPE));
            bean.setVersion(cursor.getInt(ContactPersonModel.VERSION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public List<ContactPersonModel> query_all() {
        Cursor cursor = database.query(Config.TB_CONTACT, null, null,
                null, null, null, null);
        List<ContactPersonModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactPersonModel bean = new ContactPersonModel();
            bean.setContactEmail(cursor.getString(ContactPersonModel.CONTACT_EMAIL));
            bean.setContactName(cursor.getString(ContactPersonModel.CONTACT_NAME));
            bean.setContactPhoneNumber(cursor.getString(ContactPersonModel.CONTACT_PHONENUMBER)
                    != null?Arrays.asList(cursor.getString(ContactPersonModel.CONTACT_PHONENUMBER).split(",")):new ArrayList<String>());
            bean.setLetter(cursor.getString(ContactPersonModel.LETTER));
            bean.setQrPath(cursor.getString(ContactPersonModel.QRPATH));
            bean.setPhoneCardType(cursor.getString(ContactPersonModel.PHONECARD_TYPE));
            bean.setVersion(cursor.getInt(ContactPersonModel.VERSION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public void add_(final List<ContactPersonModel> list){
        if (list ==null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    ContactPersonModel contactPersonModel = list.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ContactPersonModel.CONTACT_EMAIL_,contactPersonModel.getContactEmail());
                    contentValues.put(ContactPersonModel.CONTACT_NAME_,contactPersonModel.getContactName());
                    contentValues.put(ContactPersonModel.PHONECARD_TYPE_,contactPersonModel.getPhoneCardType());
                    List<String> contactPhoneNumber = contactPersonModel.getContactPhoneNumber();
                    if (contactPhoneNumber != null &&contactPhoneNumber.size() >=1) {
                        for (int k = 0; k < contactPhoneNumber.size(); k++) {
                            if (k==0) {
                                stringBuffer.append(contactPhoneNumber.get(0));
                            }else{
                                stringBuffer.append(","+contactPhoneNumber.get(k));
                            }
                        }
                    }
                    contentValues.put(ContactPersonModel.CONTACT_PHONENUMBER_,stringBuffer.toString());
                    stringBuffer.setLength(0);
                    contentValues.put(ContactPersonModel.QRPATH_,contactPersonModel.getQrPath());
                    contentValues.put(ContactPersonModel.LETTER_,contactPersonModel.getLetter());
                    contentValues.put(ContactPersonModel.VERSION_,contactPersonModel.getVersion());
                    long insert = database.insert(Config.TB_CONTACT, null, contentValues);
                    Log.e("SQL","======插入数据库===="+insert);
                }
            }
        }).start();
    }

    /**
     * 更新
     */
    public void up_(final List<ContactPersonModel> list){

        if (list == null) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    ContactPersonModel contactPersonModel = list.get(i);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ContactPersonModel.CONTACT_EMAIL_,contactPersonModel.getContactEmail());
                    contentValues.put(ContactPersonModel.CONTACT_NAME_,contactPersonModel.getContactName());
                    contentValues.put(ContactPersonModel.PHONECARD_TYPE_,contactPersonModel.getPhoneCardType());
                    List<String> contactPhoneNumber = contactPersonModel.getContactPhoneNumber();
                    if (contactPhoneNumber != null &&contactPhoneNumber.size() >=1) {
                        for (int k = 0; k < contactPhoneNumber.size(); k++) {
                            if (k==0) {
                                stringBuffer.append(contactPhoneNumber.get(0));
                            }else{
                                stringBuffer.append(","+contactPhoneNumber.get(k));
                            }
                        }
                    }
                    contentValues.put(ContactPersonModel.CONTACT_PHONENUMBER_,stringBuffer.toString());
                    stringBuffer.setLength(0);
                    contentValues.put(ContactPersonModel.QRPATH_,contactPersonModel.getQrPath());
                    contentValues.put(ContactPersonModel.LETTER_,contactPersonModel.getLetter());
                    contentValues.put(ContactPersonModel.VERSION_,contactPersonModel.getVersion());

                    if (contactPersonModel.isAdd()) {
                        long insert = database.insert(Config.TB_CONTACT, null, contentValues);
                        Log.e("SQL","======新增数据库===="+insert);
                    }else{
                        if(contactPhoneNumber != null && contactPhoneNumber.size()>=0){
                            long insert = database.update(Config.TB_CONTACT,
                                    contentValues, "contactPhoneNumber = ?",
                                    new String[] {contactPersonModel.getContactPhoneNumber().get(0)});
                            Log.e("SQL","======更新数据库===="+insert);
                        }else{
                            long insert = database.update(Config.TB_CONTACT,
                                    contentValues, "contactName = ?",
                                    new String[] {contactPersonModel.getContactName()});
                            Log.e("SQL","======更新数据库===="+insert);
                        }
                    }
                }
            }
        }).start();
    }
}
