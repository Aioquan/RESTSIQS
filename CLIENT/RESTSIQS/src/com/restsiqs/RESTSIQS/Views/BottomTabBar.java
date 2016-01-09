package com.restsiqs.RESTSIQS.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.restsiqs.RESTSIQS.R;


/**
 * Created by devouty on 2016/1/9.
 */
public class BottomTabBar extends LinearLayout {
    //--implements two kinds of constructions--
    public  BottomTabBar(Context context) {
        super(context);

        init(context);
    }


    public BottomTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);

        LayoutInflater mInflater = LayoutInflater.from(context);

        View v = mInflater.inflate(R.layout.bottom_tabs_layout, null);

        addView(v,lp);
    }
}