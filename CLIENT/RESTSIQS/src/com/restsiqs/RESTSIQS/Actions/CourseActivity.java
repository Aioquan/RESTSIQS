package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.restsiqs.RESTSIQS.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        this.setTitle("当前成绩");
        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 15; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("title", "数学");
            item.put("context", "分数：998");
            data.add(item);
        }
        SimpleAdapter sa = new SimpleAdapter(CourseActivity.this, data, R.layout.my_item, new String[]{"title", "context"}, new int[]{R.id.item_title, R.id.item_context});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CourseActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(CourseActivity.this, "这里显示学生的当前成绩，点击一项显示详细信息.点击获取公告获取服务器公告", Toast.LENGTH_LONG).show();
    }


}
