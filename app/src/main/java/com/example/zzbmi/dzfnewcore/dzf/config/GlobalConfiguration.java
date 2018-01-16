package com.example.zzbmi.dzfnewcore.dzf.config;

import android.app.Application;
import android.content.Context;

import com.dazf.frame.http.GlobalConfigModule;
import com.dazf.frame.baseapp.AppDelegate;
import com.dazf.frame.http.ConfigModule;
import com.dazf.frame.http.IRepositoryManager;
import com.dazf.frame.http.RequestInterceptor;
import com.example.zzbmi.dzfnewcore.dzf.utils.DZFConfig;

import java.util.List;

import timber.log.Timber;


public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        //使用builder可以为框架配置一些配置信息
        builder.baseurl(DZFConfig.MOBILE_API)
                //可以额外添加各种拦截器
//                addInterceptor()
                //设置Log打印配置
                .printHttpLogLevel(RequestInterceptor.Level.ALL)
                //网络请求前后的额外处理，例如添加请求头信息，设置固定请求参数， 请求后先一步拿到请求结果
                .globalHttpHandler(new GlobalHttpHandlerImpl());
    }

    @Override
    public void registerComponents(Context context, IRepositoryManager repositoryManager) {

    }

    @Override
    public void injectAppLifecycle(Context context, List<AppDelegate.Lifecycle> lifecycles) {
        // AppDelegate.Lifecycle 的所有方法都会在基类Application对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        lifecycles.add(new AppDelegate.Lifecycle() {
            @Override
            public void onCreate(Application application) {
                //代理Application初始化的工作
                Timber.plant(new Timber.DebugTree());
            }

            @Override
            public void onTerminate(Application application) {

            }
        });
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        // ActivityLifecycleCallbacks 的所有方法都会在 Activity (包括三方库) 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

}
