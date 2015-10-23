package com.restsiqs.RESTSIQS.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devouty on 2015/10/20.
 */
public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String DB_NAME = "restsiqs.db"; //数据库名称
    private static final int version = 1; //database version

    public DatabaseUtil(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists account(account varchar(255) not null , password varchar(255) not null ,flag int default 0);";
        db.execSQL(sql);
        sql = "create table if not exists course(courseId             varchar(255) not null," +
                "   credit               double default -1," +
                "   teacherId varchar(255) default '无'," +
                "   studentId varchar(255) default '无'," +
                "   courseName           varchar(255) default '无'," +
                "   courseTime           varchar(255) default '无'," +
                "   courseDate           varchar(255) default '无'," +
                "   test1                double default -1," +
                "   test2                double default -1," +
                "   test3                double default -1," +
                "   exercises1           double default -1," +
                "   exercises2           double default -1," +
                "   exercises3           double default -1," +
                "   exercises4           double default -1," +
                "   exercises5           double default -1," +
                "   finalTest            double default -1," +
                "   dailyMark            double default -1," +
                "   sum                  double default -1," +
                "   primary key (courseId));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}