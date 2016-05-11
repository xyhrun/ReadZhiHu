package com.xyh.readzhihu.util;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.xyh.readzhihu.application.MyApplication;

/**
 * Created by 向阳湖 on 2016/5/10.
 */
public class RequestManage {
    public static final int TIME_OUT = 10000;
    //时间重申
    public static final int TIME_OF_RETRY = 1;

    private static RequestQueue mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());

    public static void addQueue(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }

        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, TIME_OF_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public static void clearCache(Object tag) {
        mRequestQueue.getCache().clear();
    }
}
