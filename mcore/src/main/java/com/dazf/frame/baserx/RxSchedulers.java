package com.dazf.frame.baserx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava调度管理
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> io_main() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
