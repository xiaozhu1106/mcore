package com.dazf.frame.baseapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import java.util.ArrayList;


/**
 * 本项目由
 * mvp
 * +retrofit
 * +rxjava2
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class BaseApplication extends Application{
    private AppDelegate mAppDelegate;
    public static BaseApplication baseApplication;
    private ArrayList<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        mAppDelegate = new AppDelegate(this);
        mAppDelegate.onCreate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        this.mAppDelegate.onTerminate();
    }

    public static Context getAppContext() {
        return baseApplication;
    }


    public AppDelegate getAppDelegate() {
        return mAppDelegate;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
//		App.getInstance().destroyMap();
        System.exit(0);
    }


    //销毁某个activity实例
    public void remove(Class<? extends Activity> t) {
        for (Activity activity : activityList) {
            if (activity.getClass() == t) {
                activity.finish();
            }
        }
    }

    public boolean haveActivity(Class<? extends Activity> t) {
        for (Activity activity : activityList) {
            if (activity.getClass() == t) {
                return true;
            }
        }
        return false;
    }

    public void clearActivityList() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

}
