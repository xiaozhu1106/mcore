package com.example.zzbmi.dzfnewcore.dzf.mvp.model;

import com.dazf.frame.base.BaseModel;
import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.config.EmptyBean;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.IdeaContract;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;

/**
 * @author ZhuZiBo
 * @date 2018/1/15
 */
public class IdeaRebackModel extends BaseModel implements IdeaContract.Model {
    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<BaseResponse<EmptyBean>> reback(String vcont) {
        return mRepositoryManager
                .obtainRetrofitService(DZFApiService.class)
                .reback("801", "xwwy_app",  new SimpleDateFormat("yyyy-MM-dd").format(new Date()),vcont);
    }
}
