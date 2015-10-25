package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.restsiqs.RESTSIQS.R;
import com.restsiqs.RESTSIQS.Utils.Constant;
import com.restsiqs.RESTSIQS.Utils.DatabaseUtil;
import com.restsiqs.RESTSIQS.Utils.HTTPJSONGetter;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.course_list)
public class CourseListActivity extends Activity {
    String account;
    private String processURL;
    @ViewById(R.id.btnGetNotice)
    Button btnGetNotice;

    @ViewById(R.id.courseList)
    ListView listView;
private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private DatabaseUtil databaseUtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/" + account;
        Toast.makeText(this, "��ӭ������" + account, Toast.LENGTH_LONG).show();
//        new Thread(networkTask).start();
        databaseUtil = new DatabaseUtil(CourseListActivity.this);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("course", null, null, null, null, null, null);
        startManagingCursor(cursor);
//        Log.i("devouty_cl_cur","cursor's count:"+cursor.getCount());
        if(cursor.getCount() == 0)
        {
            new Thread(networkTask).start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Log.i("devouty_cl_cur_f","cursor's count:"+cursor.getCount());
                    simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                }
            }, 1000);
        }else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Log.i("devouty_cl_cur_f","cursor's count:"+cursor.getCount());
                    simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                }
            }, 1000);
        }
//        readableDatabase.close();
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
//        listView.setAdapter(simpleCursorAdapter);

//        setContentView(R.layout.course_list);
//        listView = (ListView) CourseListActivity.this.findViewById(R.id.courseList);
//        listView.setAdapter(simpleCursorAdapter);

    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            try {

                JSONObject jsonObject = HTTPJSONGetter.get(processURL);
//                Log.i("devouty_course_list",jsonObject.toJSONString());
//                Log.i("devouty_course_array",jsonObject.getJSONArray("result").toJSONString());
//                new DatabaseUtil(CourseListActivity.this).saveCourse(CourseListActivity.this, jsonObject);
                databaseUtil.saveCourse(CourseListActivity.this, jsonObject);

                cursor = databaseUtil.getReadableDatabase().query("course",null,null,null,null,null,null);
//                simpleCursorAdapter.changeCursorAndColumns(cursor,new String[]{"courseName"},new int[]{R.id.course_item_title});
//                databaseUtil.close();
//                SimpleCursorAdapter simpleCursorAdapter  = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item,cursor,new String[]{"courseName"},new int[]{R.id.course_item_title});
//                listView.setAdapter(simpleCursorAdapter);
            } catch (Exception e) {
                Log.i("devouty_course_update", e.getMessage());
                throw e;
            }
        }
    };
    @ItemClick(R.id.courseList)
    public void getCourseDetail()
    {

    }
    @Click(R.id.btnGetNotice)
    public void getNotice() {
        new Thread(networkTask).start();
        Cursor cursor = databaseUtil.getReadableDatabase().query("course",null,null,null,null,null,null);
        simpleCursorAdapter.changeCursorAndColumns(cursor,new String[]{"courseName"},new int[]{R.id.course_item_title});
        simpleCursorAdapter.notifyDataSetChanged();
    }

}