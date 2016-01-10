package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSONObject;
import com.restsiqs.RESTSIQS.R;
import com.restsiqs.RESTSIQS.Utils.Constant;
import com.restsiqs.RESTSIQS.Utils.DatabaseUtil;
import com.restsiqs.RESTSIQS.Utils.HTTPJSONGetter;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.bottom_tabs_layout)
public class CourseListActivity extends Activity {
    //menu
    private static final int ITEM_1 = Menu.FIRST;
    private static final int ITEM_2 = Menu.FIRST + 1;
    private static final int ITEM_3 = Menu.FIRST + 2;
    @ViewById(R.id.courseList)
    ListView listView;
    @ViewById(R.id.im_course)
    View ic;
    @ViewById(R.id.im_notice)
    View in;
    @ViewById(R.id.im_te)
    View it;
    private String account;
    private String processURL;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private DatabaseUtil databaseUtil;
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            try {
                JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                databaseUtil.saveCourse(jsonObject);
                cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
            } catch (Exception e) {
                Log.i("devouty_course_update", e.getMessage());
                throw e;
            }
        }
    };
    //double click
    private long exitTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("课程列表");

        account = getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/" + account;
        Toast.makeText(this, "欢迎回来! ", Toast.LENGTH_LONG).show();
        databaseUtil = new DatabaseUtil(CourseListActivity.this);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("course", null, null, null, null, null, null);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CourseListActivity.this, view.getId() + "", Toast.LENGTH_LONG).show();
            }
        };

//        ic.setOnClickListener(listener);
//        it.setOnClickListener(listener);
//        in.setOnClickListener(listener);

        if (cursor.getCount() == 0) {
            new Thread(networkTask).start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity_.class);
                            cursor.moveToPosition(i);

                            intent.putExtra("courseId", cursor.getString(1));
                            intent.putExtra("credit", Double.toString(cursor.getDouble(2)));
                            intent.putExtra("teacherId", cursor.getString(3));
                            intent.putExtra("studentId", cursor.getString(4));
                            intent.putExtra("courseName", cursor.getString(5));
                            intent.putExtra("courseTime", cursor.getString(6));
                            intent.putExtra("courseDate", cursor.getString(7));
                            intent.putExtra("test1", Double.toString(cursor.getDouble(8)));
                            intent.putExtra("test2", Double.toString(cursor.getDouble(9)));
                            intent.putExtra("test3", Double.toString(cursor.getDouble(10)));
                            intent.putExtra("exercises1", Double.toString(cursor.getDouble(11)));
                            intent.putExtra("exercises2", Double.toString(cursor.getDouble(12)));
                            intent.putExtra("exercises3", Double.toString(cursor.getDouble(13)));
                            intent.putExtra("exercises4", Double.toString(cursor.getDouble(14)));
                            intent.putExtra("exercises5", Double.toString(cursor.getDouble(15)));
                            intent.putExtra("finalTest", Double.toString(cursor.getDouble(16)));
                            intent.putExtra("dailyMark", Double.toString(cursor.getDouble(17)));
                            intent.putExtra("sum", Double.toString(cursor.getDouble(18)));
                            cursor.close();
                            startActivity(intent);
                        }
                    });
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity_.class);
                            cursor.moveToPosition(i);

                            intent.putExtra("courseId", cursor.getString(1));
                            intent.putExtra("credit", Double.toString(cursor.getDouble(2)));
                            intent.putExtra("teacherId", cursor.getString(3));
                            intent.putExtra("studentId", cursor.getString(4));
                            intent.putExtra("courseName", cursor.getString(5));
                            intent.putExtra("courseTime", cursor.getString(6));
                            intent.putExtra("courseDate", cursor.getString(7));
                            intent.putExtra("test1", Double.toString(cursor.getDouble(8)));
                            intent.putExtra("test2", Double.toString(cursor.getDouble(9)));
                            intent.putExtra("test3", Double.toString(cursor.getDouble(10)));
                            intent.putExtra("exercises1", Double.toString(cursor.getDouble(11)));
                            intent.putExtra("exercises2", Double.toString(cursor.getDouble(12)));
                            intent.putExtra("exercises3", Double.toString(cursor.getDouble(13)));
                            intent.putExtra("exercises4", Double.toString(cursor.getDouble(14)));
                            intent.putExtra("exercises5", Double.toString(cursor.getDouble(15)));
                            intent.putExtra("finalTest", Double.toString(cursor.getDouble(16)));
                            intent.putExtra("dailyMark", Double.toString(cursor.getDouble(17)));
                            intent.putExtra("sum", Double.toString(cursor.getDouble(18)));
                            cursor.close();
                            startActivity(intent);
                        }
                    });
                }
            }, 500);
        }
    }

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
            Toast.makeText(getApplicationContext(), "双击退出!",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_1, 0, "注销");
        menu.add(0, ITEM_2, 0, "更新课程");
        menu.add(0, ITEM_3, 0, "退出程序");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ITEM_1:
                Intent intent = new Intent(CourseListActivity.this, LoginActivity_.class);
                startActivity(intent);
                CourseListActivity.this.finish();
                break;
            case ITEM_2:
                new Thread(networkTask).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                        simpleCursorAdapter.changeCursor(cursor);
                        simpleCursorAdapter.notifyDataSetChanged();
                    }
                }, 500);
                break;
            case ITEM_3:
                System.exit(0);
                break;
        }
        return true;
    }
}