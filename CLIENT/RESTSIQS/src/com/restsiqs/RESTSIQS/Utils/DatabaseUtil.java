package com.restsiqs.RESTSIQS.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Created by devouty on 2015/10/20.
 */
public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String DB_NAME = "restsiqs.db"; //���ݿ�����
    private static final int version = 1; //database version

    public DatabaseUtil(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.i("devouty","begin to exe dbhelper oncreate");
        String sql = "create table if not exists account(account varchar(255) not null , password varchar(255) not null ,flag int default 0);";
        db.execSQL(sql);
//        Log.i("devouty","begin to exe course");
        sql = "create table if not exists course(_id Integer primary key AUTOINCREMENT,courseId varchar(255) not null,credit double default -1,teacherId varchar(255),studentId varchar(255),courseName varchar(255),courseTime varchar(255),courseDate varchar(255),test1 double default -1,test2 double default -1,test3 double default -1,exercises1 double default -1,exercises2 double default -1,exercises3 double default -1,exercises4 double default -1,exercises5 double default -1,finalTest double default -1,dailyMark double default -1,sum double default -1);";
        db.execSQL(sql);
        Log.i("devouty","exe dbhelper oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void saveCourse(Context context, JSONObject jsonObject) {
        Cursor cursor = null;
        String sql = "delete from course;";

        JSONArray jsonArray = jsonObject.getJSONArray("result");
//        Object[] obj = jsonArray.toArray();
        int length = jsonArray.size();
        JSONObject obj;
        ContentValues cv = new ContentValues();
        SQLiteDatabase database = DatabaseUtil.this.getWritableDatabase();
        database.execSQL(sql);
        for (int i = 0; i < length; i++) {
            obj = (JSONObject) jsonArray.get(i);
            cv.put("courseId",obj.getString("courseId"));
            cv.put("credit",obj.getDouble("credit"));
            cv.put("teacherId",obj.getString("teacherId"));
            cv.put("studentId",obj.getString("studentId"));
            cv.put("courseName",obj.getString("courseName"));
            cv.put("courseTime",obj.getString("courseTime"));
            cv.put("courseDate",obj.getString("courseDate"));
            cv.put("test1",obj.getDouble("test1"));
            cv.put("test2",obj.getDouble("test2"));
            cv.put("test3",obj.getDouble("test3"));
            cv.put("exercises1",obj.getDouble("exercises1"));
            cv.put("exercises2",obj.getDouble("exercises2"));
            cv.put("exercises3",obj.getDouble("exercises3"));
            cv.put("exercises4",obj.getDouble("exercises4"));
            cv.put("exercises5",obj.getDouble("exercises5"));
            cv.put("finalTest",obj.getDouble("finalTest"));
            cv.put("dailyMark",obj.getDouble("dailyMark"));
            cv.put("sum",obj.getDouble("sum"));
            Object t = database.insert("course", null, cv);
            Log.i("devouty","insert into course:"+i+"  Name:"+obj.getString("courseName")+"----"+t);
//            Log.i("devouty","count:"+database.query("course",null,null,null,null,null,null).getCount());
        }
//        cursor = database.query("course",null,null,null,null,null,null);
        database.close();
//        database = DatabaseUtil.this.getReadableDatabase();
//        cursor = database.query("course",null,null,null,null,null,null);
//        return cursor;
    }
}