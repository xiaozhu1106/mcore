package com.dazf.frame.http;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

import static com.dazf.frame.commonutils.Preconditions.checkNotNull;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class GlobalConfigModule {
    private HttpUrl mApiUrl;
    private GlobalHttpHandler mHandler;
    private List<Interceptor> mInterceptors;
    private ResponseErrorListener mErroListener;
    private RequestInterceptor.Level mPrintHttpLogLevel;

    /**
     * @author: jess
     * @date 8/5/16 11:03 AM
     * @description: 设置baseurl
     */
    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
        this.mErroListener = builder.responseErroListener;
        this.mPrintHttpLogLevel = builder.printHttpLogLevel;
    }

    public static Builder builder() {
        return new Builder();
    }


    public List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }

    public HttpUrl provideBaseUrl() {
        return mApiUrl;
    }


    public GlobalHttpHandler provideGlobalHttpHandler() {
        return mHandler == null ? GlobalHttpHandler.EMPTY : mHandler;//打印请求信息
    }



    /**
     * 提供处理Rxjava错误的管理器的回调
     *
     * @return
     */
    public ResponseErrorListener provideResponseErroListener() {
        return mErroListener == null ? ResponseErrorListener.EMPTY : mErroListener;
    }

    @Nullable
    public RequestInterceptor.Level providePrintHttpLogLevel() {
        return mPrintHttpLogLevel;
    }


    public static final class Builder {
        private HttpUrl apiUrl = HttpUrl.parse("https://api.github.com/");
        private GlobalHttpHandler handler;
        private List<Interceptor> interceptors = new ArrayList<>();
        private ResponseErrorListener responseErroListener;
        private RequestInterceptor.Level printHttpLogLevel;

        private Builder() {
        }

        public Builder baseurl(String baseurl) {//基础url
            if (TextUtils.isEmpty(baseurl)) {
                throw new IllegalArgumentException("baseurl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public Builder globalHttpHandler(GlobalHttpHandler handler) {//用来处理http响应结果
            this.handler = handler;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {//动态添加任意个interceptor
            this.interceptors.add(interceptor);
            return this;
        }


        public Builder responseErroListener(ResponseErrorListener listener) {//处理所有Rxjava的onError逻辑
            this.responseErroListener = listener;
            return this;
        }


        public Builder printHttpLogLevel(RequestInterceptor.Level printHttpLogLevel) { //是否让框架打印 Http 的请求和响应信息
            if (printHttpLogLevel == null)
                throw new NullPointerException("printHttpLogLevel == null. Use RequestInterceptor.Level.NONE instead.");
            this.printHttpLogLevel = printHttpLogLevel;
            return this;
        }


        public GlobalConfigModule build() {
            checkNotNull(apiUrl, "baseurl is required");
            return new GlobalConfigModule(this);
        }


    }


}
