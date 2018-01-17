package com.dazf.frame.widget.status;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dazf.frame.R;


/**
 * create by Weavey
 * on date 2016-03-12
 * TODO
 */
public class StatusLayout extends FrameLayout {

    private PageState state;

    private Context mContext;
    private View loadingPage;
    private View errorPage;
    private View emptyPage;
    private View networkPage;
    private View defineLoadingPage;

    private ImageView errorImg;
    private ImageView emptyImg;
    private ImageView networkImg;

    private TextView errorText;
    private TextView emptyText;
    private TextView networkText;

    private TextView errorReloadBtn;
    private TextView networkReloadBtn;

    private View contentView;
    private OnReloadListener listener;
    private boolean isFirstVisible; //是否一开始显示contentview，默认不显示
    private int pageBackground;

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StatusLayout);
        isFirstVisible = a.getBoolean(R.styleable.StatusLayout_isFirstVisible, false);
        pageBackground = a.getColor(R.styleable.StatusLayout_pageBackground, StatusUtils.getColor(mContext, R.color
                .base_loading_background));
        a.recycle();
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public StatusLayout(Context context) {
        super(context);
        this.mContext = context;
    }


    /**
     * 直接在Activity或Fragment或View中注入状态布局
     * @param activityOrFragmentOrView
     * @return
     */
    public static StatusLayout generate(Object activityOrFragmentOrView) {
        Context context;
        ViewGroup contentParent;
        if (activityOrFragmentOrView instanceof Activity) {
            Activity activity = (Activity) activityOrFragmentOrView;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (activityOrFragmentOrView instanceof Fragment) {
            Fragment fragment = (Fragment) activityOrFragmentOrView;
            context = fragment.getActivity();
            View rootView = fragment.getView();
            if (rootView == null) {
                throw new IllegalArgumentException("Fragment has not a rootView");
            }
            contentParent = (ViewGroup) (rootView.getParent());
        } else if (activityOrFragmentOrView instanceof View) {
            View view = (View) activityOrFragmentOrView;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
        }

        int childCount = contentParent.getChildCount();
        //get contentParent
        int index = 0;
        View oldContent;
        if (activityOrFragmentOrView instanceof View) {
            oldContent = (View) activityOrFragmentOrView;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);

        StatusLayout statusLayout = new StatusLayout(context);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(statusLayout, index, lp);

        statusLayout.setContentView(oldContent);
        return statusLayout;
    }

    /**
     * 如果初始化时没有子控件，此方法永远不会被调用
     * xml布局中使用时会调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }
        contentView = this.getChildAt(0);
        if (!isFirstVisible) {
            contentView.setVisibility(View.GONE);
        }
        build();
    }


    /**
     * 代码中设置ContentView时执行
     * 与onFinishInflate互斥，只有在onFinish未执行时，才会执行此方法
     * @param view
     */
    private void setContentView(View view) {
        contentView = view;
        if (getChildCount() != 0 ) {
            throw new IllegalStateException("LoadingLayout error");
        }
        this.addView(view, 0);
        if (!isFirstVisible && contentView != null) {
            contentView.setVisibility(View.GONE);
        }
        build();

    }

    private void build() {
        //获取全局配置
        Config mConfig = StatusLayoutManager.getInstance().getConfig();
        if (mConfig.loadingView == null) {
            loadingPage = LayoutInflater.from(mContext).inflate(mConfig.loadingLayoutId, null);
        } else {
            loadingPage = mConfig.loadingView;
        }
        errorPage = LayoutInflater.from(mContext).inflate(R.layout.widget_error_page, null);
        emptyPage = LayoutInflater.from(mContext).inflate(R.layout.widget_empty_page, null);
        networkPage = LayoutInflater.from(mContext).inflate(R.layout.widget_nonetwork_page, null);
        defineLoadingPage = null;

        loadingPage.setBackgroundColor(pageBackground);
        errorPage.setBackgroundColor(pageBackground);
        emptyPage.setBackgroundColor(pageBackground);
        networkPage.setBackgroundColor(pageBackground);

        errorText = StatusUtils.findViewById(errorPage, R.id.error_text);
        emptyText = StatusUtils.findViewById(emptyPage, R.id.empty_text);
        networkText = StatusUtils.findViewById(networkPage, R.id.no_network_text);

        errorImg = StatusUtils.findViewById(errorPage, R.id.error_img);
        emptyImg = StatusUtils.findViewById(emptyPage, R.id.empty_img);
        networkImg = StatusUtils.findViewById(networkPage, R.id.no_network_img);

        errorReloadBtn = StatusUtils.findViewById(errorPage, R.id.error_reload_btn);
        networkReloadBtn = StatusUtils.findViewById(networkPage, R.id.no_network_reload_btn);

        errorReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });
        networkReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });

        errorText.setText(mConfig.errorStr);
        emptyText.setText(mConfig.emptyStr);
        networkText.setText(mConfig.netwrokStr);

        errorText.setTextSize(mConfig.tipTextSize);
        emptyText.setTextSize(mConfig.tipTextSize);
        networkText.setTextSize(mConfig.tipTextSize);

        errorText.setTextColor(StatusUtils.getColor(mContext, mConfig.tipTextColor));
        emptyText.setTextColor(StatusUtils.getColor(mContext, mConfig.tipTextColor));
        networkText.setTextColor(StatusUtils.getColor(mContext, mConfig.tipTextColor));

        errorImg.setImageResource(mConfig.errorImgId);
        emptyImg.setImageResource(mConfig.emptyImgId);
        networkImg.setImageResource(mConfig.networkImgId);


        errorReloadBtn.setBackgroundResource(mConfig.reloadBtnId);
        networkReloadBtn.setBackgroundResource(mConfig.reloadBtnId);

        errorReloadBtn.setText(mConfig.reloadBtnStr);
        networkReloadBtn.setText(mConfig.reloadBtnStr);

        errorReloadBtn.setTextSize(mConfig.buttonTextSize);
        networkReloadBtn.setTextSize(mConfig.buttonTextSize);

        errorReloadBtn.setTextColor(StatusUtils.getColor(mContext, mConfig.buttonTextColor));
        networkReloadBtn.setTextColor(StatusUtils.getColor(mContext, mConfig.buttonTextColor));

        if (mConfig.buttonHeight != -1) {

            errorReloadBtn.setHeight(StatusUtils.dp2px(mContext, mConfig.buttonHeight));
            networkReloadBtn.setHeight(StatusUtils.dp2px(mContext, mConfig.buttonHeight));
        }
        if (mConfig.buttonWidth != -1) {

            errorReloadBtn.setWidth(StatusUtils.dp2px(mContext, mConfig.buttonWidth));
            networkReloadBtn.setWidth(StatusUtils.dp2px(mContext, mConfig.buttonWidth));
        }

        this.addView(networkPage);
        this.addView(emptyPage);
        this.addView(errorPage);
        this.addView(loadingPage);
    }

    public void setStatus(PageState status) {

        this.state = status;

        switch (status) {
            case SUCCESS:

                contentView.setVisibility(View.VISIBLE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case LOADING:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.VISIBLE);
                } else {
                    loadingPage.setVisibility(View.VISIBLE);
                }
                break;

            case EMPTY:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.VISIBLE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case ERROR:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.VISIBLE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case NO_NETWORK:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.VISIBLE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            default:
                break;
        }

    }

    /**
     * 返回当前状态{Success, Empty, Error, No_Network, Loading}
     *
     * @return
     */
    public PageState getStatus() {

        return state;
    }

    /**
     * 设置Empty状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public StatusLayout setEmptyText(String text) {

        emptyText.setText(text);
        return this;
    }

    /**
     * 设置Error状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public StatusLayout setErrorText(String text) {

        errorText.setText(text);
        return this;
    }

    /**
     * 设置No_Network状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public StatusLayout setNoNetworkText(String text) {

        networkText.setText(text);
        return this;
    }

    /**
     * 设置Empty状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setEmptyImage(@DrawableRes int id) {


        emptyImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Error状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setErrorImage(@DrawableRes int id) {

        errorImg.setImageResource(id);
        return this;
    }

    /**
     * 设置No_Network状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setNoNetworkImage(@DrawableRes int id) {

        networkImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Empty状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public StatusLayout setEmptyTextSize(int sp) {

        emptyText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Error状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public StatusLayout setErrorTextSize(int sp) {

        errorText.setTextSize(sp);
        return this;
    }

    /**
     * 设置No_Network状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public StatusLayout setNoNetworkTextSize(int sp) {

        networkText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Empty状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public StatusLayout setEmptyImageVisible(boolean bool) {

        if (bool) {
            emptyImg.setVisibility(View.VISIBLE);
        } else {
            emptyImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置Error状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public StatusLayout setErrorImageVisible(boolean bool) {

        if (bool) {
            errorImg.setVisibility(View.VISIBLE);
        } else {
            errorImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置No_Network状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public StatusLayout setNoNetworkImageVisible(boolean bool) {

        if (bool) {
            networkImg.setVisibility(View.VISIBLE);
        } else {
            networkImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置ReloadButton的文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public StatusLayout setReloadButtonText(@NonNull String text) {

        errorReloadBtn.setText(text);
        networkReloadBtn.setText(text);
        return this;
    }

    /**
     * 设置ReloadButton的文本字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public StatusLayout setReloadButtonTextSize(int sp) {

        errorReloadBtn.setTextSize(sp);
        networkReloadBtn.setTextSize(sp);
        return this;
    }

    /**
     * 设置ReloadButton的文本颜色，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setReloadButtonTextColor(@ColorRes int id) {

        errorReloadBtn.setTextColor(StatusUtils.getColor(mContext, id));
        networkReloadBtn.setTextSize(StatusUtils.getColor(mContext, id));
        return this;
    }

    /**
     * 设置ReloadButton的背景，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setReloadButtonBackgroundResource(@DrawableRes int id) {

        errorReloadBtn.setBackgroundResource(id);
        networkReloadBtn.setBackgroundResource(id);
        return this;
    }

    /**
     * 设置ReloadButton的监听器
     *
     * @param listener
     * @return
     */
    public StatusLayout setOnReloadListener(OnReloadListener listener) {

        this.listener = listener;
        return this;
    }

    /**
     * 自定义加载页面，仅对当前所在的Activity有效
     *
     * @param view
     * @return
     */
    public StatusLayout setLoadingPage(View view) {

        defineLoadingPage = view;
        this.removeView(loadingPage);
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }

    /**
     * 自定义加载页面，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public StatusLayout setLoadingPage(@LayoutRes int id) {

        this.removeView(loadingPage);
        View view = LayoutInflater.from(mContext).inflate(id, null);
        defineLoadingPage = view;
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }

    /**
     * 设置各种状态下view的背景色，仅对当前所在的地方有效
     *
     * @param color
     * @return
     */
    public StatusLayout setDefineBackgroundColor(@ColorRes int color) {

        if (defineLoadingPage == null) {
            loadingPage.setBackgroundColor(StatusUtils.getColor(mContext, color));
        } else {
            defineLoadingPage.setBackgroundColor(StatusUtils.getColor(mContext, color));
        }
        errorPage.setBackgroundColor(StatusUtils.getColor(mContext, color));
        emptyPage.setBackgroundColor(StatusUtils.getColor(mContext, color));
        networkPage.setBackgroundColor(StatusUtils.getColor(mContext, color));
        return this;
    }

    /**
     * 获取当前自定义的loadingpage
     *
     * @return
     */
    public View getLoadingPage() {

        return defineLoadingPage;
    }

    /**
     * 获取全局使用的loadingpage
     *
     * @return
     */
    public View getGlobalLoadingPage() {

        return loadingPage;
    }


    public interface OnReloadListener {
        void onReload(View v);
    }
}
