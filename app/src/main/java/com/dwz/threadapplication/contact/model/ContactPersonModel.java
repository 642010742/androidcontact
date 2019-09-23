package com.dwz.threadapplication.contact.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * @author dongweizhou
 * @createTime 2019/9/5
 * @describe
 * @DWZ
 */
public class ContactPersonModel implements Serializable {


    /**
     * 联系人姓名
     */
    private String contactName;

    public static final String CONTACT_NAME_="contactName";
    public static final int CONTACT_NAME=1;

    /**
     * 联系人电话
     */
    private List<String> contactPhoneNumber;

    public static final String CONTACT_PHONENUMBER_ ="contactPhoneNumber";
    public static final int CONTACT_PHONENUMBER =2;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

   public static final String CONTACT_EMAIL_ ="contactEmail";
    public static final int CONTACT_EMAIL =3;

    /**
     * 用户姓名的首字母
     */
    private String letter;

    public static final String LETTER_ ="letter";
    public static final int LETTER =4;

    /**
     * 用户信息的二维码
     */
    private String qrPath;

    public static final String QRPATH_ ="qrPath";
    public static final int QRPATH =5;

    /**
     * 手机卡类型
     */
    private String phoneCardType;

    public static final String PHONECARD_TYPE_ ="phoneCardType";
    public static final int PHONECARD_TYPE =6;

    /**
     * 数据 版本
     * @return
     */

    public int version;

    public static final String VERSION_ ="version";
    public static final int VERSION =7;

    /**
     * 是新增的联系人
     */
    public boolean add;

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    /**
     * 用户头像
     */
    public Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public List<String> getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(List<String> contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getQrPath() {
        return qrPath;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    public String getPhoneCardType() {
        return phoneCardType;
    }

    public void setPhoneCardType(String phoneCardType) {
        this.phoneCardType = phoneCardType;
    }
}
