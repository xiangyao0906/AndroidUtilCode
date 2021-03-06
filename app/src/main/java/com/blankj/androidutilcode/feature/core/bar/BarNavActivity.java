package com.blankj.androidutilcode.feature.core.bar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.blankj.androidutilcode.R;
import com.blankj.androidutilcode.base.BaseBackActivity;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SpanUtils;

import java.util.Random;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/13
 *     desc  : demo about BarUtils
 * </pre>
 */
public class BarNavActivity extends BaseBackActivity {

    private Random random = new Random();

    private TextView tvAboutNav;

    public static void start(Context context) {
        Intent starter = new Intent(context, BarNavActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bar_nav;
    }


    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        rootLayout.setBackgroundColor(Color.GRAY);
        getToolBar().setTitle(getString(R.string.demo_bar));

        tvAboutNav = findViewById(R.id.tv_about_nav);
        findViewById(R.id.btn_nav_show).setOnClickListener(this);
        findViewById(R.id.btn_nav_hide).setOnClickListener(this);
        findViewById(R.id.btn_nav_set_color).setOnClickListener(this);
        updateAboutNav();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nav_show:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    BarUtils.setNavBarVisibility(this, true);
                }
                break;
            case R.id.btn_nav_hide:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    BarUtils.setNavBarVisibility(this, false);
                }
                break;
            case R.id.btn_nav_set_color:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    BarUtils.setNavBarColor(this, Color.argb(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                }
                break;
        }
        updateAboutNav();
    }

    private void updateAboutNav() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvAboutNav.setText(new SpanUtils()
                    .appendLine("navHeight: " + BarUtils.getNavBarHeight())
                    .appendLine("isNavBarVisible: " + BarUtils.isNavBarVisible(this))
                    .appendLine("getNavBarColor: #" + Integer.toHexString(BarUtils.getNavBarColor(this)))
                    .append("isSupportNavBar: " + BarUtils.isSupportNavBar())
                    .create()
            );
        } else {
            tvAboutNav.setText(new SpanUtils()
                    .appendLine("navHeight: " + BarUtils.getNavBarHeight())
                    .appendLine("isNavBarVisible: " + BarUtils.isNavBarVisible(this))
                    .append("isSupportNavBar: " + BarUtils.isSupportNavBar())
                    .create()
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        BarUtils.setNavBarVisibility(this, BarUtils.isNavBarVisible(this));
    }
}
