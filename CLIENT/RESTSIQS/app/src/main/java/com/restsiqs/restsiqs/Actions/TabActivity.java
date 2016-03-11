package com.restsiqs.restsiqs.Actions;

/**
 * Created by devouty on 2016/3/6.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.restsiqs.restsiqs.R;
import com.restsiqs.restsiqs.Utils.Constant;
import com.restsiqs.restsiqs.Utils.DatabaseUtil;
import com.restsiqs.restsiqs.Utils.HTTPJSONGetter;
import com.restsiqs.restsiqs.Views.TabAdapter;
import java.util.ArrayList;


public class TabActivity extends FragmentActivity {

    private TabAdapter tabAdapter;

    private ViewPager viewPager;

    private TabLayout tab;

    private ArrayList<View> viewList;
    private String account;
    private String processURL;
    private Cursor cursor, noticeCursor, teCursor;
    private SimpleCursorAdapter simpleCursorAdapter, noticeSimpleCursorAdapter, teSimpleCursorAdapter;
    private DatabaseUtil databaseUtil;
    View view1, view2, view3;
    Runnable courseNetworkTask, noticeNetworkTask, teNetworkTask;
    ListView listView, noticeListView, teListView;
    long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.notice_list, null);
        view2 = inflater.inflate(R.layout.course_list, null);
        view3 = inflater.inflate(R.layout.te_list, null);
        listView = (ListView) view2.findViewById(R.id.courseList);
        noticeListView = (ListView) view1.findViewById(R.id.noticeList);
        teListView = (ListView) view3.findViewById(R.id.teList);
        exitTime = 0;
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        tabAdapter = new TabAdapter(viewList);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(tabAdapter);
        tab = (TabLayout) findViewById(R.id.sliding_tabs);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabsFromPagerAdapter(tabAdapter);

        account = getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/" + account;
        Toast.makeText(this, "欢迎回来!   " + account, Toast.LENGTH_LONG).show();
//        new Thread(courseNetworkTask).start();
        databaseUtil = new DatabaseUtil(TabActivity.this);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("course", null, null, null, null, null, null);
        noticeCursor = readableDatabase.query("notice", null, null, null, null, null, null);
        teCursor = readableDatabase.query("technologicalexam", null, null, null, null, null, null);
//        startManagingCursor(cursor);
//        Log.i("devouty_cl_cur","cursor's count:"+cursor.getCount());
        courseNetworkTask = new Runnable() {
            //@Override
            public void run() {
                try {
                    processURL = "http://" + Constant.IP + ":8080/RESTSIQS/course/student/"+account;
                    JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                    databaseUtil.saveCourse(jsonObject);
                    cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                } catch (Exception e) {
                    throw e;
                }
            }
        };
        noticeNetworkTask = new Runnable() {
            //@Override
            public void run() {
                try {
                    processURL = "http://" + Constant.IP + ":8080/RESTSIQS/notice/noticelist";
                    JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                    databaseUtil.saveNotice(jsonObject);
                    noticeCursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
                } catch (Exception e) {
                    throw e;
                }
            }
        };
        teNetworkTask = new Runnable() {
            //@Override
            public void run() {
                try {
                    processURL = "http://" + Constant.IP + ":8080/RESTSIQS/technologicalexam/student/" + account;
                    JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                    databaseUtil.saveTe(jsonObject);
                    teCursor = databaseUtil.getReadableDatabase().query("technologicalexam", null, null, null, null, null, null);
                } catch (Exception e) {
                    throw e;
                }
            }
        };

        //course
        if (cursor.getCount() == 0) {
            new Thread(courseNetworkTask).start();
            new Handler().postDelayed(new Runnable() {
                //@Override
                public void run() {
                    simpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.course_item, cursor, new String[]{"courseName","courseDate"}, new int[]{R.id.course_item_title,R.id.course_item_sum});
                    listView.setAdapter(simpleCursorAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(TabActivity.this, CourseDetailActivity.class);
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
                    simpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.course_item, cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                    listView.setAdapter(simpleCursorAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                            Intent intent = new Intent(TabActivity.this, CourseDetailActivity.class);
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

        //notice
        if (noticeCursor.getCount() == 0) {
            new Thread(noticeNetworkTask).start();
            new Handler().postDelayed(new Runnable() {
                //@Override
                public void run() {
                    noticeSimpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.notice_item, noticeCursor, new String[]{"noticeTitle"}, new int[]{R.id.notice_item_title});
                    noticeListView.setAdapter(noticeSimpleCursorAdapter);

                    noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
                            Intent intent = new Intent(TabActivity.this, NoticeDetailActivity.class);
                            cursor.moveToPosition(i);

                            intent.putExtra("noticeId", cursor.getString(1));
                            intent.putExtra("noticeTitle", cursor.getString(2));
                            intent.putExtra("noticeContext", cursor.getString(3));
                            intent.putExtra("noticeOperator", cursor.getString(4));
                            intent.putExtra("academyId", cursor.getString(5));
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
                    noticeSimpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.notice_item, noticeCursor, new String[]{"noticeTitle"}, new int[]{R.id.notice_item_title});
                    noticeListView.setAdapter(noticeSimpleCursorAdapter);

                    noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Cursor cursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
                            Intent intent = new Intent(TabActivity.this, NoticeDetailActivity.class);
                            cursor.moveToPosition(i);

                            intent.putExtra("noticeId", cursor.getString(1));
                            intent.putExtra("noticeTitle", cursor.getString(2));
                            intent.putExtra("noticeContext", cursor.getString(3));
                            intent.putExtra("noticeOperator", cursor.getString(4));
                            intent.putExtra("academyId", cursor.getString(5));
                            cursor.close();
                            startActivity(intent);
                        }
                    });
                }
            }, 500);
        }

        //te
        if (teCursor.getCount() == 0) {
            new Thread(teNetworkTask).start();
            new Handler().postDelayed(new Runnable() {
                //@Override
                public void run() {
                    teSimpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.te_item, teCursor, new String[]{"tName", "tDate", "tSorce"}, new int[]{R.id.tName, R.id.tDate, R.id.tSorce});
                    teListView.setAdapter(teSimpleCursorAdapter);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                //@Override
                public void run() {
//                    Log.i("devouty_cl_cur_f", "cursor's count:" + teCursor.getColumnName(0) + teCursor.getColumnName(1) + teCursor.getColumnName(2));
                    teSimpleCursorAdapter = new SimpleCursorAdapter(TabActivity.this, R.layout.te_item, teCursor, new String[]{"tName", "tDate", "tSorce"}, new int[]{R.id.tName, R.id.tDate, R.id.tSorce});
                    teListView.setAdapter(teSimpleCursorAdapter);
                }
            }, 500);
        }



    }

    //double click
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
            Toast.makeText(getApplicationContext(), "Exit with double click!",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    //menu
    private static final int ITEM_1 = Menu.FIRST;
    private static final int ITEM_2 = Menu.FIRST + 1;
    private static final int ITEM_3 = Menu.FIRST + 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_1, 0, "注销");
        menu.add(0, ITEM_2, 0, "更新信息");
        menu.add(0, ITEM_3, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        return super.onMenuOpened(featureId, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ITEM_1:
                Intent intent = new Intent(TabActivity.this, LoginActivity.class);
                startActivity(intent);
                TabActivity.this.finish();
                break;
            case ITEM_2:
                new Thread(courseNetworkTask).start();
                new Thread(noticeNetworkTask).start();
                new Thread(teNetworkTask).start();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Cursor courseCursor = databaseUtil.getReadableDatabase().query("course", null, null, null, null, null, null);
                        simpleCursorAdapter.changeCursor(courseCursor);
                        simpleCursorAdapter.notifyDataSetChanged();

                        Cursor noticeCursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
                        noticeSimpleCursorAdapter.changeCursor(noticeCursor);
                        noticeSimpleCursorAdapter.notifyDataSetChanged();

                        Cursor teCursor = databaseUtil.getReadableDatabase().query("technologicalexam", null, null, null, null, null, null);
                        teSimpleCursorAdapter.changeCursor(teCursor);
                        teSimpleCursorAdapter.notifyDataSetChanged();
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
