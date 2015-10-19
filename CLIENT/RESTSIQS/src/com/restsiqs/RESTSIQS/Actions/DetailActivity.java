package com.restsiqs.RESTSIQS.Actions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.restsiqs.RESTSIQS.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        String[] str = {"课程名：数学", "总分：998", "教师：周老师", "课程id：10010"};
        for (int i = 0; i < str.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("context", str[i]);
            data.add(map);
        }
        ListView lv = (ListView) findViewById(R.id.detail_list);
        SimpleAdapter sa = new SimpleAdapter(DetailActivity.this, data, R.layout.detail_item, new String[]{"context"}, new int[]{R.id.detail_item_text});
        lv.setAdapter(sa);
        //setListAdapter(new ArrayAdapter<String>(DetailActivity.this,R.layout.detail_item, data));
    }


}
