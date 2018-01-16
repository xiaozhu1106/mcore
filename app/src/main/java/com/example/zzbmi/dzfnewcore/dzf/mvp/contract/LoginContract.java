package com.example.zzbmi.dzfnewcore.dzf.mvp.contract;

import android.app.Activity;

import com.dazf.frame.base.IModel;
import com.dazf.frame.base.IView;
import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.mvp.model.LoginBean;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author ZhuZiBo
 * @date 2018/1/10
 * 契约层，接口编程，提供presenter使用的方法
 */
public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Activity getActivity();
    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<List<LoginBean>>> login(String user, String pwd);
    }
}
