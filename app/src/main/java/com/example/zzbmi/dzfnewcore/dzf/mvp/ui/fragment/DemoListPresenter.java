package com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment;

import com.dazf.frame.base.BasePresenter;
import com.dazf.frame.ui.IViewList;

import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public class DemoListPresenter extends BasePresenter<DemoListContract.Model, IViewList> {
    @Override
    protected DemoListContract.Model createModel() {
        return new DemoListModel();
    }


    void loadMore() {
        List<String> data = mModel.getData();
        mRootView.showSuccessData(data);
    }


    void refresh() {
        List<String> data = mModel.getData();
        mRootView.showSuccessData(data);
    }
}
