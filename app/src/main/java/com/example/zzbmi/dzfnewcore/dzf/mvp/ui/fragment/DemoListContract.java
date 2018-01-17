package com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment;

import com.dazf.frame.base.IModel;

import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public class DemoListContract {
    interface Model extends IModel {
        List<String> getData();
    }
}
