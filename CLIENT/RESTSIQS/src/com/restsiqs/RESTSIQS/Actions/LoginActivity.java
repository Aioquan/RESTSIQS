package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSONException;
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

    private String processURL = "http://"+ Constant.IP +":8080/RESTSIQS/student/";
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {

            try {

                String restURL;
                restURL = processURL+account;
                JSONObject jsonObject = HTTPJSONGetter.get(restURL);

                Log.i("devouty_password", "password:" + password);
                if(((JSONObject)jsonObject.getJSONArray("result").get(0)).getString("studentPassword").equals(password)) {
//                  get account & password into database

                    DatabaseUtil databaseUtil = new DatabaseUtil(LoginActivity.this);
                    SQLiteDatabase writableDatabase = null;
                    writableDatabase = databaseUtil.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("account", account);
                    cv.put("password", password);
                    cv.put("flag",1);
                    writableDatabase.insert("account", null, cv);
                    Cursor cursor = writableDatabase.query("account", null, null, null, null, null, null);
//                  jump to course list
                    Intent intent = new Intent(LoginActivity.this, CourseListActivity_.class);
                    intent.putExtra("account", account);
                    Log.i("devouty","finish account insert now its' count is:"+writableDatabase.query("account",null,null,null,null,null,null).getCount());
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else
                {
                    Toast.makeText(LoginActivity.this,"’À∫≈ªÚ√‹¬Î¥ÌŒÛ£¨«Î÷ÿ ‰",Toast.LENGTH_LONG);
                    etPassword.setText("");
                }


            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Log.i("devouty", e.getMessage());
            }catch (Exception e)
            {
                Log.i("devouty_exception",e.getMessage());
            }
        }
    };
    @Click(R.id.btnLogin)
    public void login() {
        account = etAccount.getText().toString();
        password = etPassword.getText().toString();
        if(account.length() == 0)
        {
            Toast.makeText(LoginActivity.this,"’À∫≈≤ªƒ‹Œ™ø’",Toast.LENGTH_LONG);
        }else if(password.length() == 0)
        {
            Toast.makeText(LoginActivity.this,"√‹¬Î≤ªƒ‹Œ™ø’",Toast.LENGTH_LONG);
        }else
            new Thread(networkTask).start();
    }
}