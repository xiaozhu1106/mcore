package com.dazf.frame.ui;

import com.dazf.frame.base.IView;

import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public interface IViewList extends IView {
    void showSuccessData(List datas);
    void showErrorData();
    void setData(List data);
    void addData(List data);
}
