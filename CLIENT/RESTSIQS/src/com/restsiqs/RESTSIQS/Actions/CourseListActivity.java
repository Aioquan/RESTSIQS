package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.restsiqs.RESTSIQS.R;
import org.androidannotations.annotations.EActivity;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.course_list)
public class CourseListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,
                getIntent().getStringExtra("entity"),Toast.LENGTH_LONG);
    }
}