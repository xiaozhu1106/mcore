package com.example.zzbmi.dzfnewcore.dzf.mvp.contract;

import com.dazf.frame.base.IModel;
import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.config.EmptyBean;

import io.reactivex.Observable;

/**
 * @author ZhuZiBo
 * @date 2018/1/15
 * 契约层，接口编程，提供presenter使用的方法
 */
public class IdeaContract {

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    public interface Model extends IModel {
        Observable<BaseResponse<EmptyBean>> reback(String vcont);
    }
}
