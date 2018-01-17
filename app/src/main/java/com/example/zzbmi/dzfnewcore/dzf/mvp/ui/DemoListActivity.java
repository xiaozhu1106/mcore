package com.example.zzbmi.dzfnewcore.dzf.mvp.ui;

import android.view.View;

import com.dazf.frame.ui.BaseActivity;
import com.example.zzbmi.dzfnewcore.R;
import com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment.DemoListFragment;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public class DemoListActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_list;
    }

    @Override
    public void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_list_container, new DemoListFragment()).commit();
    }

    @Override
    public View getBindViewToStatusView() {
        return null;
    }
}
