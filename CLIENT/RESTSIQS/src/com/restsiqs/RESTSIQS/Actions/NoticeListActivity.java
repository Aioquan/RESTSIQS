package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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
@EActivity(R.layout.notice_list)
public class NoticeListActivity extends Activity {
    private String processURL;
    private DatabaseUtil databaseUtil;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;

    @ViewById(R.id.notice_list)
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Course List");
//        account = getIntent().getStringExtra("account");
        processURL = "http://" + Constant.IP + ":8080/RESTSIQS/notice/noticelist";
        databaseUtil = new DatabaseUtil(NoticeListActivity.this);
        SQLiteDatabase readableDatabase = databaseUtil.getReadableDatabase();
        cursor = readableDatabase.query("notice", null, null, null, null, null, null);
        if(cursor.getCount() == 0)
            new Thread(networkTask).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                simpleCursorAdapter = new SimpleCursorAdapter(NoticeListActivity.this, R.layout.notice_item, cursor, new String[]{"noticeTitle"}, new int[]{R.id.notice_item_title});
                listView.setAdapter(simpleCursorAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Cursor cursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
                        Intent intent = new Intent(NoticeListActivity.this, NoticeDetailActivity_.class);
                        cursor.moveToPosition(i);

                        intent.putExtra("noticeId",cursor.getString(1));
                        intent.putExtra("noticeTitle",cursor.getString(2));
                        intent.putExtra("noticeContext",cursor.getString(3));
                        intent.putExtra("noticeOperator",cursor.getString(4));
                        intent.putExtra("academyId",cursor.getString(5));
                        cursor.close();
                        startActivity(intent);
                    }
                });
            }
        },500);
    }
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            try {
                JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                databaseUtil.saveNotice(jsonObject);
                cursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
            } catch (Exception e) {
                Log.i("devouty_notice_update", e.getMessage());
                throw e;
            }
        }
    };

    //menu
    private static final int ITEM_1 = Menu.FIRST;
    private static final int ITEM_2 = Menu.FIRST + 1;
    private static final int ITEM_3 = Menu.FIRST + 2;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_1, 0, "Logout");
        menu.add(0, ITEM_2, 0, "Update notices");
        menu.add(0, ITEM_3, 0, "Exit");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ITEM_1:
                Intent intent = new Intent(NoticeListActivity.this, LoginActivity_.class);
                startActivity(intent);
                NoticeListActivity.this.finish();
                break;
            case ITEM_2:
                new Thread(networkTask).start();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Cursor cursor = databaseUtil.getReadableDatabase().query("notice", null, null, null, null, null, null);
//                        Log.i("devouty_refrash",cursor.getCount()+"");
//                        simpleCursorAdapter.changeCursorAndColumns(cursor, new String[]{"courseName"}, new int[]{R.id.course_item_title});
                        simpleCursorAdapter.changeCursor(cursor);
                        simpleCursorAdapter.notifyDataSetChanged();
//                        cursor.close();
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