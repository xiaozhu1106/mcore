package com.dazf.frame.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dazf.frame.baserx.RxManager;
import com.dazf.frame.commonutils.TUtil;
import com.dazf.frame.commonutils.lifecycle.FragmentLifecycleable;
import com.dazf.frame.widget.status.PageState;
import com.dazf.frame.widget.status.StatusLayout;
import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * des:基类fragment
 *
 * @author ZhuZiBo
 * @date 2018/1/10
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleFragment extends RxBaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void createPresenter() {
//        mPresenter.setView(this, mModel);
//    }
//
//    @Override
//    public void init() {
//    }
//}
//2.普通模式
//public class SampleFragment extends RxBaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void createPresenter() {
//    }
//
//    @Override
//    public void init() {
//    }
//}
public abstract class RxBaseFragment<P extends BasePresenter> extends Fragment implements IView, FragmentLifecycleable {
    public View rootView;
    protected P mPresenter;
    public Context mContext;
    public RxManager mRxManager;
    protected RxBaseActivity mActivity;
    private Unbinder unbinder;
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    private StatusLayout statusLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false);
        mRxManager = new RxManager();
        mActivity = (RxBaseActivity) getActivity();
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.mContext = getActivity();
            mPresenter.setView(this);
        }
        mContext = getActivity();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    //获取布局文件
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void init();

    //是否绑定状态布局
    protected abstract boolean isBindStatusView();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public P createPresenter() {
        return TUtil.getT(this, 0);
    }

    /**
     * 指定当前页面状态
     * @param state
     */
    public void setPageState(PageState state) {
        if (isBindStatusView()) {
            if (statusLayout == null) {
                statusLayout = StatusLayout.generate(this);
            }
            statusLayout.setStatus(state);
        } else {
            throw new IllegalStateException("no bind StatusView, can not set current page status");
        }
    }


    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
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
        intent.setClass(getActivity(), cls);
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
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRxManager.clear();
        if (unbinder != Unbinder.EMPTY) unbinder.unbind();
        unbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        this.mPresenter = null;

    }


}
