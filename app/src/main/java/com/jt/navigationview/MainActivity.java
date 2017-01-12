package com.jt.navigationview;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

/**
 * 抽屉布局
 * 导航视图和内容视图不在一个平面上
 * 导航视图覆盖在内容视图上
 *
 * 滑动出现或点击出现
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    /**
     * 抽屉
     */
    DrawerLayout drawerLayout;
    /**
     * 标题
     */
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        /*设置显示标题
         */
        toolbar.setTitle("测试");
        /**
         * 设置导航图标
         */
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        /**
         * 设置支持的动作栏
         */
        setSupportActionBar(toolbar);
        /**
         * 状态栏的抽屉开关  需要在strings文件中创建开关
         */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        /*
        开关同步
         */
        toggle.syncState();
        /**
         * 默认选择第一项
         * NavigationView中菜单的点击事件  必须设置setNavigationItemSelectedListener监听
         *
         * 按照以前重写onCreateOptionsMenu()点击事件无效
         */
        navigationView.setCheckedItem(R.id.first);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.first:
                        Log.d(TAG, "onNavigationItemSelected:首页 ");
                        toolbar.setTitle("首页");
                        break;
                    case R.id.save:
                        Log.d(TAG, "onNavigationItemSelected: 收藏");
                        toolbar.setTitle("收藏");
                        break;
                    case R.id.delete:
                        Log.d(TAG, "onNavigationItemSelected: 删除");
                        toolbar.setTitle("删除");
                        break;
                    case R.id.exit:
                        Log.d(TAG, "onNavigationItemSelected: 退出");
                        toolbar.setTitle("退出");
                        break;
                }
                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    /**
     * 设置图标点击打开抽屉
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                /**
                 * 判断是打开还是关闭
                 */
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)){

                    drawerLayout.openDrawer(Gravity.LEFT);

                }else {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
