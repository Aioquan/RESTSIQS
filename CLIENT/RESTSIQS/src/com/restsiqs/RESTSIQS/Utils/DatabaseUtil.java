package com.restsiqs.RESTSIQS.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devouty on 2015/10/20.
 */
public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String DB_NAME = "restsiqs.db"; //Êý¾Ý¿âÃû³Æ
    private static final int version = 1; //database version

    public DatabaseUtil(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists account(account varchar(255) not null , password varchar(255) not null ,flag int not null );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}