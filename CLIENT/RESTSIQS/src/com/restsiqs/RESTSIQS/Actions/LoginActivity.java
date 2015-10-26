package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.restsiqs.RESTSIQS.R;
import com.restsiqs.RESTSIQS.Utils.Constant;
import com.restsiqs.RESTSIQS.Utils.DatabaseUtil;
import com.restsiqs.RESTSIQS.Utils.HTTPJSONGetter;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.login)
public class LoginActivity extends Activity {

    @ViewById(R.id.account)
    EditText etAccount;

    @ViewById(R.id.password)
    EditText etPassword;

    private String account;
    private String password;

    @ViewById
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {

            String restURL;
            String processURL = "http://" + Constant.IP + ":8080/RESTSIQS/student/";
            restURL = processURL + account;
            JSONObject jsonObject = HTTPJSONGetter.get(restURL);
//            if (jsonObject.getJSONArray("result").toJSONString().equals("[null]")) {
//                    Toast.makeText(LoginActivity.this, "Account|password error!", Toast.LENGTH_LONG).show();
//
//            } else
            if (((JSONObject) jsonObject.getJSONArray("result").get(0)).getString("studentPassword").equals(password)) {
//                  get account & password into database

                DatabaseUtil databaseUtil = new DatabaseUtil(LoginActivity.this);
                SQLiteDatabase writableDatabase;
                writableDatabase = databaseUtil.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("account", account);
                cv.put("password", password);
                cv.put("flag", 1);
                writableDatabase.insert("account", null, cv);
                Cursor cursor = writableDatabase.query("account", null, null, null, null, null, null);
//                  jump to course list
                Intent intent = new Intent(LoginActivity.this, CourseListActivity_.class);
                intent.putExtra("account", account);
//                    Log.i("devouty","finish account insert now its' count is:"+writableDatabase.query("account",null,null,null,null,null,null).getCount());
                writableDatabase.close();
                databaseUtil.close();
                cursor.close();
                startActivity(intent);
                LoginActivity.this.finish();
            } else {
                Toast.makeText(LoginActivity.this, "Account|password error!", Toast.LENGTH_LONG).show();
                etPassword.setText("");
            }
        }
    };

    @Click(R.id.btnLogin)
    public void login() {
        account = etAccount.getText().toString();
        password = etPassword.getText().toString();
        if (account.length() == 0) {
            Toast.makeText(LoginActivity.this, "Account is null!", Toast.LENGTH_LONG).show();
        } else if (password.length() == 0) {
            Toast.makeText(LoginActivity.this, "Password is null!", Toast.LENGTH_LONG).show();
        } else {
            try {
                new Thread(networkTask).start();
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Account|password error!", Toast.LENGTH_LONG).show();
                etPassword.setText("");
            }
        }
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