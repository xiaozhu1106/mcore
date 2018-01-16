package com.dazf.frame.http;

import com.dazf.frame.baseapp.BaseApplication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 用来管理网络请求层,以及数据缓存层,以后可能添加数据库请求层
 * 需要在{@link ConfigModule}的实现类中先inject需要的服务
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class RepositoryManager implements IRepositoryManager {

    private HttpUrl mApiUrl;
    private GlobalHttpHandler mHandler;
    private List<Interceptor> mInterceptors;
    private ResponseErrorListener mErroListener;
    private RequestInterceptor.Level mPrintHttpLogLevel;

    private final Map<String, Object> mRetrofitServiceCache = new LinkedHashMap<>();

    private static RepositoryManager instance = null;
    private Retrofit mRetrofit;

    private RepositoryManager(){
        GlobalConfigModule globalConfigModule = getGlobalConfigModule();
        this.mApiUrl = globalConfigModule.provideBaseUrl();
        this.mHandler = globalConfigModule.provideGlobalHttpHandler();
        this.mInterceptors = globalConfigModule.provideInterceptors();
        this.mErroListener = globalConfigModule.provideResponseErroListener();
        this.mPrintHttpLogLevel = globalConfigModule.providePrintHttpLogLevel();
        ClientModule clientModule = new ClientModule();

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        Interceptor interceptor = new RequestInterceptor(mHandler, mPrintHttpLogLevel);
        OkHttpClient okHttpClient = clientModule.provideClient(okhttpBuilder, interceptor, mInterceptors, mHandler);

        mRetrofit = clientModule.provideRetrofit(retrofitBuilder, okHttpClient, mApiUrl);
    }

    public static RepositoryManager getInstance() {
        if (instance == null) {
            synchronized (RepositoryManager.class) {
                if (instance == null) {
                    instance = new RepositoryManager();
                }
            }
        }
        return instance;
    }


    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        T retrofitService = (T) mRetrofitServiceCache.get(service.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = mRetrofit.create(service);
            mRetrofitServiceCache.put(service.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }


    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     * 需要在AndroidManifest中声明{@link ConfigModule}的实现类,和Glide的配置方式相似
     *
     * @return
     */
    private GlobalConfigModule getGlobalConfigModule() {
        List<ConfigModule> modules = new ManifestParser(BaseApplication.baseApplication).parse();
        GlobalConfigModule.Builder builder = GlobalConfigModule.builder();
        for (ConfigModule module : modules) {
            module.applyOptions(BaseApplication.getAppContext(), builder);
        }
        return builder.build();
    }
}
