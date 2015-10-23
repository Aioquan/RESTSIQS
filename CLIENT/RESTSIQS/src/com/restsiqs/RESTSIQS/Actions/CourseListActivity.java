package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.restsiqs.RESTSIQS.R;
import com.restsiqs.RESTSIQS.Utils.Constant;
import com.restsiqs.RESTSIQS.Utils.HTTPJSONGetter;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.course_list)
public class CourseListActivity extends Activity {
    String account;
    private String processURL ;
    @ViewById(R.id.btnGetNotice)
    Button btnGetNotice;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        processURL = "http://"+ Constant.IP +":8080/RESTSIQS/course/student/"+account;
        Toast.makeText(this,"ª∂”≠ªÿ¿¥£°"+account,Toast.LENGTH_LONG);
        new Thread(networkTask).start();
    }
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            try {
                JSONObject jsonObject = HTTPJSONGetter.get(processURL);
                Log.i("devouty_course_list",jsonObject.toJSONString());
            }catch (Exception e)
            {

            }
        }
    };
    @Click(R.id.btnGetNotice)
    public void getNotice()
    {
        new Thread(networkTask).start();
    }

}