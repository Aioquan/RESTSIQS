package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.restsiqs.RESTSIQS.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NoticeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ListView lv = (ListView) findViewById(R.id.notice_list);
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("title", "关于返还学费的通知");
            data.add(item);
        }
        SimpleAdapter sa = new SimpleAdapter(NoticeActivity.this, data, R.layout.detail_item, new String[]{"title"}, new int[]{R.id.detail_item_text});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                startActivity(intent);
            }
        });
    }


}
