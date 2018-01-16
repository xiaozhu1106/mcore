package com.example.zzbmi.dzfnewcore.dzf.mvp.presenter;

import com.dazf.frame.base.BasePresenter;
import com.dazf.frame.base.IView;
import com.dazf.frame.baserx.RxSubscriber;
import com.dazf.frame.commonutils.lifecycle.RxLifecycleUtils;
import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.config.EmptyBean;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.IdeaContract;
import com.example.zzbmi.dzfnewcore.dzf.mvp.model.IdeaRebackModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * @author ZhuZiBo
 * @date 2018/1/15
 */
public class IdeaRebackPresenter extends BasePresenter<IdeaContract.Model, IView> {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected IdeaContract.Model createModel() {
        return new IdeaRebackModel();
    }


    public void toIdeaReback(String vcont) {
        mModel.reback(vcont)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁，防止内存泄漏
//                .compose(RxHelper.handleResult())   //对数据进行预处理
                //如果数据datas为空，获取成功状态需要msg信息时，不要做预处理，rx的next方法中不支持传入null值
                .subscribe(new RxSubscriber<BaseResponse<EmptyBean>>() {
                    @Override
                    protected void _onNext(BaseResponse<EmptyBean> emptyBean) {
                        mRootView.showToast("成功：");
                    }

                    @Override
                    protected void _onError(String message) {
                        mRootView.showToast("失败：" + message);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
