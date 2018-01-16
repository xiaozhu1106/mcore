package com.example.zzbmi.dzfnewcore.dzf.mvp.presenter;


import android.content.Intent;

import com.dazf.frame.base.BasePresenter;
import com.dazf.frame.baserx.RxSubscriber;
import com.dazf.frame.commonutils.lifecycle.RxLifecycleUtils;
import com.example.zzbmi.dzfnewcore.dzf.config.RxHelper;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.LoginContract;
import com.example.zzbmi.dzfnewcore.dzf.mvp.model.LoginBean;
import com.example.zzbmi.dzfnewcore.dzf.mvp.model.LoginModel;
import com.example.zzbmi.dzfnewcore.dzf.mvp.ui.IdeaRebackActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 负责处理登录逻辑
     * @param account
     * @param pwd
     */
    public void toLogin(String account, String pwd) {
        mModel.login(account, pwd)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁，防止内存泄漏
                .compose(RxHelper.handleResult())   //对数据进行预处理
                .subscribe(new RxSubscriber<List<LoginBean>>(mRootView) {
                    @Override
                    protected void _onNext(List<LoginBean> loginBeans) {

                        mRootView.showToast("成功：" + loginBeans.get(0).getUser().getUname());
//                        mRootView.showToast("成功：" + loginBeans.msg);
//                        SaveCorpAllData.saveAllData(loginBeans.get(0));
                        mRootView.getActivity().startActivity(new Intent(mRootView.getActivity(), IdeaRebackActivity.class));
                    }

                    @Override
                    protected void _onError(String message) {
                        mRootView.showToast("失败：" + message);
                    }
                });
    }

    /**
     * 已移除Dagger2，中小型项目使用类爆炸，得不偿失，这里需要手动new Model获取实例
     * @return
     */
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
