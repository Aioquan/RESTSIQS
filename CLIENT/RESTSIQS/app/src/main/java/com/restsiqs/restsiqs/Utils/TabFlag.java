package com.restsiqs.restsiqs.Utils;

/**
 * Created by devouty on 2016/3/6.
 */
public class TabFlag {
    private static Integer flag;


    public static Integer getFlag() {
        if(flag == null)
        {
            flag = new Integer(2);
        }
        return flag;

    }

    public static void setFlag(int flag) {
        if(TabFlag.flag == null)
        {
            flag = new Integer(2);
        }
        TabFlag.flag = flag;
    }
}
