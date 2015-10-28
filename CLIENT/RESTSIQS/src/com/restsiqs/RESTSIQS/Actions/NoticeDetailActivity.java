package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.restsiqs.RESTSIQS.R;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.notice_detail)
public class NoticeDetailActivity extends Activity {

    @ViewById(R.id.notice_detail_title)
    TextView noticeDetailTitle;
    @ViewById(R.id.notice_detail_context)
    TextView noticeDetailContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                noticeDetailTitle.setText(intent.getStringExtra("noticeTitle"));
                noticeDetailContext.setText(intent.getStringExtra("noticeContext"));
            }
        },500);
    }


}
