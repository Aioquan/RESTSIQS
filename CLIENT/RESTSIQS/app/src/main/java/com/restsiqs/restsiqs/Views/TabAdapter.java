package com.restsiqs.restsiqs.Views;

/**
 * Created by devouty on 2016/3/6.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.restsiqs.restsiqs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class TabAdapter extends PagerAdapter {
    ArrayList<View> viewList;
    public TabAdapter(ArrayList<View> viewList)
    {
        this.viewList = viewList;
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));

        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position)
        {
            case 0:title = "学院公告";
                break;
            case 1:title = "课程信息";
                break;
            case 2:title = "技能考试";
                break;
        }
        return title;
    }
}