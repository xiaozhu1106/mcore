package com.dazf.frame.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import com.dazf.frame.baseapp.BaseApplication;
import com.dazf.frame.baserx.RxManager;
import com.dazf.frame.commonutils.TUtil;
import com.dazf.frame.commonutils.lifecycle.ActivityLifecycleable;
import com.trello.rxlifecycle2.android.ActivityEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 * 无需继承RxAppCompatActivity，即可实现rx生命周期绑定,解决单继承的问题
 */
public abstract class RxBaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView, ActivityLifecycleable {

    public P mPresenter;
    public Context mContext;
    public RxManager mRxManager;
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        //默认获取Presenter的无参构造，通过setView将View传入Presenter中
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.mContext = this;
            mPresenter.setView(this);
        }
        mContext = this;
        this.initView();
        BaseApplication.baseApplication.addActivity(this);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @NonNull
    @Override
    public Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView();


    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    //由于多构造的情况，还是自己new靠谱
    public P createPresenter(){
        return TUtil.getT(this, 0);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        mContext = null;
        if (unbinder != Unbinder.EMPTY) unbinder.unbind();
        unbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();
        this.mPresenter = null;
    }
}
