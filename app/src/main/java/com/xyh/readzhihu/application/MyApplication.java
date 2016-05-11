package com.xyh.readzhihu.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 向阳湖 on 2016/5/10.
 */
public class MyApplication extends Application {
    private static Context mContext;
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Fresco.initialize(this);
    }

    public static Context getContext() {
        Log.d(TAG, "-----getContext: mContext = "+mContext);
        return mContext;
    }

}
