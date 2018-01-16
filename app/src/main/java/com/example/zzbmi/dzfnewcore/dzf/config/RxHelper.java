package com.example.zzbmi.dzfnewcore.dzf.config;

import com.dazf.frame.base.IView;
import com.dazf.frame.base.RxBaseActivity;
import com.dazf.frame.base.RxBaseFragment;
import com.dazf.frame.baserx.ServerException;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * des:对服务器返回数据成功和失败处理
 * @author ZhuZiBo
 * @date 2018/1/10
 */

/**************使用例子******************/
/*_apiService.login(mobile, verifyCode)
        .compose(RxSchedulersHelper.io_main())
        .compose(RxResultHelper.handleResult())
        .//省略*/

public class RxHelper {
    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return tObservable -> tObservable.flatMap(result -> {
            if (result.success()) {
                return createData(result.datas);
            } else {
                return Observable.error(new ServerException(result.msg));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });

    }


    /**
     * rx绑定Activity与Fragment生命周期，避免内存泄漏
     * @param view
     * @param <T>
     * @return
     * @deprecated  已弃用此方式
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {
        if (view instanceof RxBaseActivity) {
            return ((RxAppCompatActivity) view).<T>bindToLifecycle();
        } else if (view instanceof RxBaseFragment) {
            return ((RxFragmentActivity) view).<T>bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }
    }
}
