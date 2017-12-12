package com.lqm.okrx2mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.lqm.okrx2mvpdemo.R;
import com.lqm.okrx2mvpdemo.ui.adapter.ViewPagerFgAdapter;
import com.lqm.okrx2mvpdemo.ui.base.BaseActivity;
import com.lqm.okrx2mvpdemo.ui.base.BaseFragment;
import com.lqm.okrx2mvpdemo.ui.base.BasePresenter;
import com.lqm.okrx2mvpdemo.ui.fragment.NewsFragment;
import com.lqm.okrx2mvpdemo.ui.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.content_viewPager)
    ViewPager content_viewPager;

    private List<BaseFragment> fragmentList;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabView();
    }

    //初始化Tab滑动
    public void initTabView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new ZhihuFragment());
        fragmentList.add(new ZhihuFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new ZhihuFragment());
        content_viewPager.setOffscreenPageLimit(4);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        content_viewPager.setAdapter(new ViewPagerFgAdapter(
                getSupportFragmentManager(), fragmentList, "main_view_pager"));//给ViewPager设置适配器
        tabLayout.setupWithViewPager(content_viewPager);//将TabLayout和ViewPager关联起来
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.today_github) {
            String github_trending = "https://github.com/luqinmao/OkRx2MVPDemo";
            startActivity(WebViewActivity.newIntent(this, github_trending));
            return true;
        } else if (item.getItemId() == R.id.about_me) {
            String github_trending = "https://github.com/luqinmao";
            startActivity(WebViewActivity.newIntent(this, github_trending));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

