package com.restsiqs.restsiqs.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.restsiqs.restsiqs.R;

//@EActivity(R.layout.notice_detail)
public class NoticeDetailActivity extends Activity {

//    @ViewById(R.id.notice_detail_title)
    TextView noticeDetailTitle;
//    @ViewById(R.id.notice_detail_context)
    TextView noticeDetailContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail);
        noticeDetailTitle = (TextView)findViewById(R.id.notice_detail_title);
        noticeDetailContext = (TextView)findViewById(R.id.notice_detail_context);
        final Intent intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            //@Override
            public void run() {
                noticeDetailTitle.setText(intent.getStringExtra("noticeTitle"));
                noticeDetailContext.setText(intent.getStringExtra("noticeContext"));
            }
        },500);
    }


}
