package com.xyh.readzhihu.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xyh.readzhihu.R;
import com.xyh.readzhihu.application.MyApplication;
import com.xyh.readzhihu.util.ThemeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 向阳湖 on 2016/5/10.
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.drawerLayout_id)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.toolBar_id)
    Toolbar mToolbar;

    @Bind(R.id.frameMenu_id)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;
    private com.rey.material.app.DatePickerDialog pickerDialog;
    private long selectedTime;
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        mToolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        setSupportActionBar(mToolbar);
        //并不是直接返回出去???
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //是否可以显示标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mToolbar.setTitle(R.string.toolbar_title);

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none,R.anim.main_out);
    }

    public void closeDrawerLayout() {
        mDrawerLayout.closeDrawers();
    }

    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.calendar) {
            showCalendar();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCalendar() {
        long maxTime = System.currentTimeMillis();
        pickerDialog = new com.rey.material.app.DatePickerDialog(this);
        Calendar calendar = pickerDialog.getCalendar();
        calendar.setTimeInMillis(maxTime);
        calendar.add(Calendar.MONTH, -1);
        long minTime = calendar.getTimeInMillis();
        pickerDialog.dateRange(minTime, maxTime).date(selectedTime).positiveAction("确定")
                .positiveActionClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Date selectedDate = new Date(pickerDialog.getDate());
                        selectedTime = pickerDialog.getDate();
                        String selectedTimeStr = sdf.format(selectedDate);
                        //需要添加依赖 compile 'com.github.orhanobut:logger:v11'
//                        Log
                        pickerDialog.dismiss();
                    }
                }).negativeAction("取消").negativeActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDialog.dismiss();
            }
        }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //2秒之内点两下返回就会退出.否则再退出就会继续提示消息  若是>5000 则5秒之内点两下就会退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(MyApplication.getContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            //没有return true 退一次就会直接退出的
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();//该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
    }


    /**
     * 设备配置改变时
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
