package com.dwz.threadapplication.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author dongweizhou
 * @createTime 2019/9/9
 * @describe   原本是想通过本地数据库来存在 用户联系人信息（突然发现 联系人更改 只需要重新查下系统表就行了额。。。）
 * @DWZ
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static final String DROP_TABLE = "drop table if exists ";
    private static DBHelper instance = null;
    private static SQLiteDatabase db = null;

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper(Config.context, Config.DB_NAME, null, Config.DB_VERSION);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getDatabase() {
        if (db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static synchronized void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_message = "create table contact_message (id int primary key,contactName varchar(200)" +
                ",contactPhoneNumber varchar(5000),contactEmail varchar(50),letter varchar(5)," +
                "qrPath varchar(100),phoneCardType int(1),version int)";
        db.execSQL(sql_message);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
