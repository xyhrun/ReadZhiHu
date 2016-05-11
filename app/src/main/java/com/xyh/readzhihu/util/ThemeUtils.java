package com.xyh.readzhihu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.stylingandroid.prism.Prism;
import com.xyh.readzhihu.application.MyApplication;

/**
 * Created by 向阳湖 on 2016/5/10.
 */
public class ThemeUtils {
    private static final String TAG = "ThemeUtils";
    private static int defaultThemeColor = Color.rgb(00, 205, 110);
    private static Context context = MyApplication.getContext();

    //Prism主题动态切换框架 http://gold.xitu.io/entry/55fe37a9ddb263b5605de13f
    public static void setThemeColor(Prism prism, int color) {
        prism.setColor(color);
        SharedPreferences.Editor editor = context.getSharedPreferences("Theme_Color", Context.MODE_PRIVATE).edit();
        editor.putInt("theme_color", color);
        editor.commit();
    }

    public static int getThemeColor() {
        Log.d(TAG, "-----getThemeColor: context = "+context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("Theme_Color", Context.MODE_PRIVATE);
        int themeColor = sharedPreferences.getInt("theme_color", defaultThemeColor);
        return themeColor;
    }
}
