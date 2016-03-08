package com.restsiqs.restsiqs.Views;

/**
 * Created by devouty on 2016/3/6.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.restsiqs.restsiqs.Actions.CourseDetailActivity;
import com.restsiqs.restsiqs.R;
import com.restsiqs.restsiqs.Utils.Constant;
import com.restsiqs.restsiqs.Utils.DatabaseUtil;
import com.restsiqs.restsiqs.Utils.HTTPJSONGetter;


/**
 * Created by Administrator on 2015/7/30.
 */
public class TabFragment extends Fragment {
    private String account;
    private String processURL;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Activity parentActivity;
    private DatabaseUtil databaseUtil;
    private ListView listView;


    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static TabFragment changeFragment(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
//        TabFragment pageFragment = new TabFragment();
        TabFragment pageFragment = null;
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = getActivity();
        mPage = getArguments().getInt(ARG_PAGE);
        account = parentActivity.getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/" + account;
        Toast.makeText(parentActivity, "Welcome back! " + account, Toast.LENGTH_LONG).show();
//        new Thread(networkTask).start();
        databaseUtil = new DatabaseUtil(parentActivity);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("course", null, null, null, null, null, null);
//        startManagingCursor(cursor);
//        Log.i("devouty_cl_cur","cursor's count:"+cursor.getCount());
        if (cursor.getCount() == 0) {
            new Thread(networkTask).start();
            new Handler().postDelayed(new Runnable() {
                //@Override
                public void run() {
//                    Log.i("devouty_cl_cur_f","cursor's count:"+cursor.getCount());
                    simpleCursorAdapter = new SimpleCursorAdapter(parentActivity, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(parentActivity, CourseDetailActivity.class);
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
                //@Override
                public void run() {
//                    Log.i("devouty_cl_cur_f","cursor's count:"+cursor.getCount());
                    simpleCursorAdapter = new SimpleCursorAdapter(parentActivity, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(parentActivity, CourseDetailActivity.class);
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

//        readableDatabase.close();
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
//        listView.setAdapter(simpleCursorAdapter);

//        setContentView(R.layout.course_list);
//        listView = (ListView) CourseListActivity.this.findViewById(R.id.courseList);
//        listView.setAdapter(simpleCursorAdapter);

    }

    Runnable networkTask = new Runnable() {
        //@Override
        public void run() {
            try {
                JSONObject jsonObject = HTTPJSONGetter.get(processURL);
//                Log.i("devouty_course_list",jsonObject.toJSONString());
//                Log.i("devouty_course_array",jsonObject.getJSONArray("result").toJSONString());
//                new DatabaseUtil(CourseListActivity.this).saveCourse(CourseListActivity.this, jsonObject);
                databaseUtil.saveCourse(jsonObject);
                cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
//                simpleCursorAdapter.changeCursorAndColumns(cursor,new String[]{"courseName"},new int[]{R.id.course_item_title});
//                databaseUtil.close();
//                SimpleCursorAdapter simpleCursorAdapter  = new SimpleCursorAdapter(CourseListActivity.this, R.layout.course_item,cursor,new String[]{"courseName"},new int[]{R.id.course_item_title});
//                listView.setAdapter(simpleCursorAdapter);
            } catch (Exception e) {
                Log.i("devouty_course_update", e.getMessage());
                //throw e;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.notice_list, container, false);
        listView = (ListView) view.findViewById(R.id.noticeList);

        return view;
    }

}