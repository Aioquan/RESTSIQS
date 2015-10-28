package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.restsiqs.RESTSIQS.R;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by devouty on 2015/10/20.
 */
@EActivity(R.layout.course_detail)
public class CourseDetailActivity extends Activity {
    @ViewById(R.id.courseName)
    TextView courseName;
    @ViewById(R.id.sum)
    TextView sum;
    @ViewById(R.id.courseCredit)
    TextView courseCredit;
    @ViewById(R.id.courseTime)
    TextView courseTime;
    @ViewById(R.id.courseDate)
    TextView courseDate;
    @ViewById(R.id.test1)
    TextView test1;
    @ViewById(R.id.test2)
    TextView test2;
    @ViewById(R.id.test3)
    TextView test3;
    @ViewById(R.id.exercises1)
    TextView exercises1;
    @ViewById(R.id.exercises2)
    TextView exercises2;
    @ViewById(R.id.exercises3)
    TextView exercises3;
    @ViewById(R.id.exercises4)
    TextView exercises4;
    @ViewById(R.id.exercises5)
    TextView exercises5;
    @ViewById(R.id.finalTest)
    TextView finalTest;
    @ViewById(R.id.dailyMark)
    TextView dailyMark;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //18
        Intent intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                courseName.setText(courseName.getText()+intent.getStringExtra("courseName"));
                sum.setText(sum.getText()+intent.getStringExtra("sum"));
                courseCredit.setText(courseCredit.getText()+intent.getStringExtra("courseCredit"));
                courseTime.setText(courseTime.getText()+intent.getStringExtra("courseTime"));
                courseDate.setText(courseDate.getText()+intent.getStringExtra("courseDate"));
                test1.setText(test1.getText()+intent.getStringExtra("test1"));
                test2.setText(test2.getText()+intent.getStringExtra("test2"));
                test3.setText(test3.getText()+intent.getStringExtra("test3"));
                exercises1.setText(exercises1.getText()+intent.getStringExtra("exercises1"));
                exercises2.setText(exercises2.getText()+intent.getStringExtra("exercises2"));
                exercises3.setText(exercises3.getText()+intent.getStringExtra("exercises3"));
                exercises4.setText(exercises4.getText()+intent.getStringExtra("exercises4"));
                exercises5.setText(exercises5.getText()+intent.getStringExtra("exercises5"));
                finalTest.setText(finalTest.getText()+intent.getStringExtra("finalTest"));
                dailyMark.setText(dailyMark.getText()+intent.getStringExtra("dailyMark"));
            }
        }, 500);
    }
}