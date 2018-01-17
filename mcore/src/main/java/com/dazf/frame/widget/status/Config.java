package com.dazf.frame.widget.status;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.dazf.frame.R;

/**
 * 全局配置的Class，对所有使用到的地方有效
 */
public class Config {

    String emptyStr = "暂无数据";
    String errorStr = "加载失败，请稍后重试···";
    String netwrokStr = "无网络连接，请检查网络···";
    String reloadBtnStr = "点击重试";
    int emptyImgId = R.drawable.empty;
    int errorImgId = R.drawable.error;
    int networkImgId = R.drawable.no_network;
    int reloadBtnId = R.drawable.selector_btn_back_gray;
    int tipTextSize = 14;
    int buttonTextSize = 14;
    int tipTextColor = R.color.base_text_color_light;
    int buttonTextColor = R.color.base_text_color_light;
    int buttonWidth = -1;
    int buttonHeight = -1;
    int loadingLayoutId = R.layout.widget_loading_page;
    View loadingView = null;
    int backgroundColor = R.color.base_loading_background;

    public Config setErrorText(@NonNull String text) {

        errorStr = text;
        return this;
    }

    public Config setEmptyText(@NonNull String text) {

        emptyStr = text;
        return this;
    }

    public Config setNoNetworkText(@NonNull String text) {

        netwrokStr = text;
        return this;
    }

    public Config setReloadButtonText(@NonNull String text) {

        reloadBtnStr = text;
        return this;
    }

    /**
     * 设置所有提示文本的字体大小
     *
     * @param sp
     * @return
     */
    public Config setAllTipTextSize(int sp) {

        tipTextSize = sp;
        return this;
    }

    /**
     * 设置所有提示文本的字体颜色
     *
     * @param color
     * @return
     */
    public Config setAllTipTextColor(@ColorRes int color) {

        tipTextColor = color;
        return this;
    }

    public Config setReloadButtonTextSize(int sp) {

        buttonTextSize = sp;
        return this;
    }

    public Config setReloadButtonTextColor(@ColorRes int color) {

        buttonTextColor = color;
        return this;
    }

    public Config setReloadButtonBackgroundResource(@DrawableRes int id) {

        reloadBtnId = id;
        return this;
    }

    public Config setReloadButtonWidthAndHeight(int width_dp, int height_dp) {

        buttonWidth = width_dp;
        buttonHeight = height_dp;
        return this;
    }

    public Config setErrorImage(@DrawableRes int id) {

        errorImgId = id;
        return this;
    }

    public Config setEmptyImage(@DrawableRes int id) {

        emptyImgId = id;
        return this;
    }

    public Config setNoNetworkImage(@DrawableRes int id) {

        networkImgId = id;
        return this;
    }

    public Config setLoadingPageLayout(@LayoutRes int id) {

        loadingLayoutId = id;
        return this;
    }

    public Config setLoadingPageView(View view) {

        this.loadingView = view;
        return this;
    }

    public Config setAllPageBackgroundColor(@ColorRes int color) {

        backgroundColor = color;
        return this;
    }
}