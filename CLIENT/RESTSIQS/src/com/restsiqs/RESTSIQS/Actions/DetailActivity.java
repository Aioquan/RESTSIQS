package com.restsiqs.RESTSIQS.Actions;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ArrayList<Map<String,Object>> data= new ArrayList<Map<String,Object>>();
        String[] str = {"课程名：数学","总分：998","教师：周老师","课程id：10010"};
        for(int i = 0;i<str.length;i++)
        {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("context",str[i]);
            data.add(map);
        }
        ListView lv = (ListView) findViewById(R.id.detail_list);
        SimpleAdapter sa = new SimpleAdapter(DetailActivity.this,data,R.layout.detail_item,new String[]{"context"},new int[]{R.id.detail_item_text});
        lv.setAdapter(sa);
        //setListAdapter(new ArrayAdapter<String>(DetailActivity.this,R.layout.detail_item, data));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
