package com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment;

import android.os.Handler;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dazf.frame.ui.BaseListFragment;
import com.dazf.frame.widget.status.PageState;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public class DemoListFragment extends BaseListFragment<DemoListPresenter> {
    Handler handler = new Handler();

    @Override
    public BaseQuickAdapter getAdapter() {
        return new DemoListAdapter(getData());
    }


    @Override
    protected int getMode() {
        return MODE_BOTH;
    }


    @Override
    public int getLoadMorePageSize() {
        return 15;
    }

    @Override
    protected void refresh() {
        super.refresh();
        //实际使用时 ，业务逻辑放在P层
        handler.postDelayed(() -> mPresenter.refresh(), 3000);
    }


    @Override
    public void init() {
        super.init();
        //实际使用时 ，业务逻辑放在P层
        handler.postDelayed(() -> {
            setPageState(PageState.SUCCESS);
            mPresenter.loadMore();
        }, 3000);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        //实际使用时 ，业务逻辑放在P层
        handler.postDelayed(() -> mPresenter.refresh(), 3000);
    }
}
