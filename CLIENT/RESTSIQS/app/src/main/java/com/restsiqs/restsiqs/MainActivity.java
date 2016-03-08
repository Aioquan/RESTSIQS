package com.restsiqs.restsiqs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.restsiqs.restsiqs.Actions.LoginActivity;
import com.restsiqs.restsiqs.Actions.TabActivity;
import com.restsiqs.restsiqs.Utils.DatabaseUtil;


public class MainActivity extends Activity {
    boolean loginFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseUtil databaseUtil = new DatabaseUtil(MainActivity.this);
        SQLiteDatabase database = null;
        database = databaseUtil.getWritableDatabase();
        final Cursor cursor = database.query("account", null, null, null, null, null, null);
        if (cursor.moveToFirst() == false) {
            loginFlag = false;
        } else {
            loginFlag = true;
        }
        database.close();
        databaseUtil.close();
        new Handler().postDelayed(new Runnable() {
            //@Override
            public void run() {

                if (MainActivity.this.loginFlag) {
                    //is signed in
                    Intent intent = new Intent(MainActivity.this, TabActivity.class);
                    intent.putExtra("account", cursor.getString(cursor.getColumnIndex("account")));
                    startActivity(intent);
                    MainActivity.this.finish();
                } else {
                    //need to sign in
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        }, 2500);
//        Intent intent = new Intent(MainActivity.this, TabActivity.class);
//        startActivity(intent);
    }
}
