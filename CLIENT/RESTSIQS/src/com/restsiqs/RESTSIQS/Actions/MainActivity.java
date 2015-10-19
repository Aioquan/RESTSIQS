package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.restsiqs.RESTSIQS.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(MainActivity.this, "输入正确的账户密码并登入", Toast.LENGTH_LONG).show();
    }


}
