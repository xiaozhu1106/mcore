package com.dazf.frame.baseapp;

import android.app.Application;

import com.dazf.frame.http.ConfigModule;
import com.dazf.frame.http.ManifestParser;
import com.dazf.frame.http.RepositoryManager;

import java.util.ArrayList;
import java.util.List;

/**
 * AppDelegate可以代理Application的生命周期,在对应的生命周期,执行对应的逻辑,因为Java只能单继承
 * 而我的框架要求Application要继承于BaseApplication
 * 所以当遇到某些三方库需要继承于它的Application的时候,就只有自定义Application继承于三方库的Application
 * 再将BaseApplication的代码复制进去,而现在就不用再复制代码,只用在对应的生命周期调用AppDelegate对应的方法(Application一定要实现APP接口)
 *
 * @author ZhuZiBo
 * @date 2018/1/10
 */

public class AppDelegate  {
    private Application mApplication;
    private ActivityLifecycle mActivityLifecycle;
    private final List<ConfigModule> mModules;
    private List<Lifecycle> mLifecycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();


    AppDelegate(Application application) {
        this.mApplication = application;
        this.mModules = new ManifestParser(mApplication).parse();
        mActivityLifecycle = new ActivityLifecycle(AppManager.getInstance());
        for (ConfigModule module : mModules) {
            module.injectAppLifecycle(mApplication, mLifecycles);
            // 将个人实现的 Activity 的回调 存入List mActivityLifecycles（此时还未注册回调）
            module.injectActivityLifecycle(mApplication, mActivityLifecycles);
        }
    }


    void onCreate() {
        //注册框架中的Activity监听
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycle);
        //注册配置中的ConfigModule,遍历 mActivityLifecycles,注册所有activity生命周期回调，有N个ConfigModule的实现类，就会注册N次（这里有无限的可能性，看个人实现）
        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            mApplication.registerActivityLifecycleCallbacks(lifecycle);
        }
        //注册配置的的
        for (ConfigModule module : mModules) {
            module.registerComponents(mApplication, RepositoryManager.getInstance());
        }
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onCreate(mApplication);
        }
    }


    void onTerminate() {
        if (mActivityLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        this.mActivityLifecycle = null;
        this.mApplication = null;
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onTerminate(mApplication);
        }
    }


    public interface Lifecycle {
        void onCreate(Application application);
        void onTerminate(Application application);
    }

}

