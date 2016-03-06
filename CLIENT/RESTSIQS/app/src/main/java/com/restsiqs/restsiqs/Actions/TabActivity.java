package com.restsiqs.restsiqs.Actions;

/**
 * Created by devouty on 2016/3/6.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.restsiqs.restsiqs.R;
import com.restsiqs.restsiqs.Utils.TabFlag;
import com.restsiqs.restsiqs.Views.TabAdapter;

import java.util.ArrayList;


public class TabActivity extends FragmentActivity {

    private TabAdapter tabAdapter;

    private ViewPager viewPager;

    private TabLayout tab;

    private ArrayList<View> viewList;
    View view1,view2,view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.notice_list, null);
        view2 = inflater.inflate(R.layout.course_list,null);
        view3 = inflater.inflate(R.layout.notice_list, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        tabAdapter = new TabAdapter(viewList);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(tabAdapter);
        tab = (TabLayout) findViewById(R.id.sliding_tabs);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabsFromPagerAdapter(tabAdapter);

    }


}