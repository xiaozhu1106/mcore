package com.dazf.frame.base;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public interface IView {
    //    /*******内嵌加载*******/
    void showLoading(String message);

    void dismissLoading();

    void showToast(String message);
}
