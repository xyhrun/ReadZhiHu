package com.xyh.readzhihu.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.xyh.readzhihu.R;
import com.xyh.readzhihu.util.RequestManage;

/**
 * Created by 向阳湖 on 2016/5/10.
 */

//继承AppCompatActivity 可以使用setSupportActionBar(Toolbar toolbar);
public class BaseActivity extends AppCompatActivity {
    //私有成员能被继承吗?
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_right_in, R.anim.anim_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestManage.cancelAll(this);
    }

    public void replaceFragment(int id, Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public void executeRequest(Request<?> request) {
        RequestManage.addQueue(request, this);
    }
}
