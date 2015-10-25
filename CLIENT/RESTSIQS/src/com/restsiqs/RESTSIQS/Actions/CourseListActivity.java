package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
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
        this.setTitle("Course List");
        account = getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/" + account;
        Toast.makeText(this, "Welcome back! " + account, Toast.LENGTH_LONG).show();
//        new Thread(networkTask).start();
        databaseUtil = new DatabaseUtil(CourseListActivity.this);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("course", null, null, null, null, null, null);
        startManagingCursor(cursor);
//        Log.i("devouty_cl_cur","cursor's count:"+cursor.getCount());
        if (cursor.getCount() == 0) {
            new Thread(networkTask).start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Log.i("devouty_cl_cur_f","cursor's count:"+cursor.getCount());
                    simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                }
            }, 1000);
        } else {
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

                cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
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
    public void getCourseDetail() {
        int i = listView.getSelectedItemPosition();
        Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
        Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity_.class);
        cursor.moveToPosition(i);

        intent.putExtra("courseId", cursor.getString(1));
        intent.putExtra("credit", cursor.getDouble(2));
        intent.putExtra("teacherId", cursor.getString(3));
        intent.putExtra("studentId", cursor.getString(4));
        intent.putExtra("courseName", cursor.getString(5));
        intent.putExtra("courseTime", cursor.getString(6));
        intent.putExtra("courseDate", cursor.getString(7));
        intent.putExtra("test1", cursor.getDouble(8));
        intent.putExtra("test2", cursor.getDouble(9));
        intent.putExtra("test3", cursor.getDouble(10));
        intent.putExtra("exercises1", cursor.getDouble(11));
        intent.putExtra("exercises2", cursor.getDouble(12));
        intent.putExtra("exercises3", cursor.getDouble(13));
        intent.putExtra("exercises4", cursor.getDouble(14));
        intent.putExtra("exercises5", cursor.getDouble(15));
        intent.putExtra("finalTest", cursor.getDouble(16));
        intent.putExtra("dailyMark", cursor.getDouble(17));
        intent.putExtra("sum", cursor.getDouble(18));
        startActivity(intent);
    }

    @Click(R.id.btnGetNotice)
    public void getNotice() {
        new Thread(networkTask).start();
        Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
        simpleCursorAdapter.changeCursorAndColumns(cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
        simpleCursorAdapter.notifyDataSetChanged();
    }

    //    private static boolean isExit = false;
//    private void exit() {
//        if (!isExit) {
//            isExit = true;
//            Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                    Toast.LENGTH_SHORT).show();
//            // 利用handler延迟发送更改状态信息
//            handler.sendEmptyMessageDelayed(0, 2000);
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
//    Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            isExit = false;
//        }
//    };
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}