package com.xyh.readzhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.xyh.readzhihu.R;
import com.xyh.readzhihu.util.ThemeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 向阳湖 on 2016/5/10.
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.splash_background)
    RelativeLayout background;

    @Bind(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        background.setBackgroundColor(ThemeUtils.getThemeColor());
        shimmerFrameLayout.startShimmerAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },1500);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.anim_none);
    }
}
