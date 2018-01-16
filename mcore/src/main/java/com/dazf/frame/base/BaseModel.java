package com.dazf.frame.base;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.dazf.frame.http.IRepositoryManager;
import com.dazf.frame.http.RepositoryManager;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class BaseModel implements IModel, LifecycleObserver {

    protected IRepositoryManager mRepositoryManager = RepositoryManager.getInstance();
            //用于管理网络请求层,以及数据缓存层

//    public BaseModel(IRepositoryManager repositoryManager) {
//        this.mRepositoryManager = repositoryManager;
//    }

    /**
     * 在框架中 {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}
