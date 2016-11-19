package com.anakin.ireader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.anakin.ireader.adapter.MainViewPagetAdapter;
import com.anakin.ireader.config.PagerConfig;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {


    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private TableLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        //初始化ViewPager
        MainViewPagetAdapter pagetAdapter = new MainViewPagetAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagetAdapter);
        mViewPager.setOffscreenPageLimit(PagerConfig.titles.length); //超出屏幕的限制

    }

    private void initEvent() {


    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_main);
        mTabs = (TableLayout) findViewById(R.id.tabs);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);  //悬浮按钮

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);    // toolbar
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "我是SnackBar ^_^", Snackbar.LENGTH_LONG)
                        .setAction("设置Action", null).show();
            }
        });

        toolbar.setOnMenuItemClickListener(this);   // toolbar 监听

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);  //DrawerLayout
        // 左侧菜单的开关监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // 左侧菜单导航的头
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * 左侧菜单的点击事件
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String msg = "点击了左侧菜单的";
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            msg += "Click nav_camera";
        } else if (id == R.id.nav_gallery) {
            msg += "Click nav_gallery";
        } else if (id == R.id.nav_slideshow) {
            msg += "Click nav_slideshow";
        } else if (id == R.id.nav_manage) {
            msg += "Click nav_manage";
        } else if (id == R.id.nav_share) {
            msg += "Click nav_share";
        } else if (id == R.id.nav_send) {
            msg += "Click nav_send";
        }

        if (!msg.equals("")) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 更多菜单的展开menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 右侧更多选项的点击事件
     * @param menuItem
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String msg = "右侧更多选项点击了";
        switch (menuItem.getItemId()) {
            case R.id.action_edit:
                msg += " edit";
                break;
            case R.id.action_share:
                msg += " share";
                break;
            case R.id.action_settings:
                msg += " setting";
                break;
        }

        if (!msg.equals("")) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
