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
import com.restsiqs.RESTSIQS.R;
import com.restsiqs.RESTSIQS.Utils.DatabaseUtil;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.login)
public class LoginActivity extends Activity {

    @ViewById(R.id.account)
    EditText etAccount;

    @ViewById(R.id.password)
    EditText etPassword;

    @ViewById
    Button btnLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String processURL = "http://192.16.137.1:8080/RESTSIQS/student/";
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            HttpEntity entity = null;
            try {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                String restURL;
                restURL = processURL+account;
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet request = new HttpGet(restURL);
//            Log.i("devouty", "test:" + request.getURI());
                HttpResponse response = httpclient.execute(request);
//                Log.i("devouty", "response:" + response.getStatusLine());
                entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                Log.i("devouty", "test:" + response.getStatusLine()+"----------"+restURL);
                Log.i("devouty", "test:" + result);

//      get account & password into database
                DatabaseUtil databaseUtil = new DatabaseUtil(LoginActivity.this);
                SQLiteDatabase writableDatabase = null;
                writableDatabase = databaseUtil.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("account", account);
                cv.put("password", password);
                writableDatabase.insert("account", null, cv);
                Cursor cursor = writableDatabase.query("account", null, null, null, null, null, null);
//      jump to course list
                Intent intent = new Intent(LoginActivity.this, CourseListActivity_.class);
                intent.putExtra("account", account);

//            intent.putExtra("entity",EntityUtils.toString(entity));
                startActivity(intent);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.i("devouty", "ClientProtocolException");
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("devouty", e.getMessage());
            }
        }
    };

    @Click(R.id.btnLogin)
    public void login() {
        new Thread(networkTask).start();
    }
}