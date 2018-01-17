package com.dazf.frame.ui;

import android.widget.Toast;

import com.dazf.frame.base.BasePresenter;
import com.dazf.frame.base.RxBaseFragment;
import com.dazf.frame.widget.dialog.ProgressView;

/**
 * @author ZhuZiBo
 * @date 2018/1/15
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxBaseFragment<P> {

    private ProgressView progressView;

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //用于提交，显示背景的loading
    @Override
    public void showLoading(String message) {
        progressView = ProgressView.showMessage(getActivity(), message);
    }

    @Override
    public void dismissLoading() {
        if (progressView != null) {
            progressView.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressView != null) {
            progressView.dismiss();
            progressView = null;
        }
    }
}
