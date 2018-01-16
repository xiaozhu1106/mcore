package com.example.zzbmi.dzfnewcore.dzf.mvp.model;

import com.dazf.frame.base.BaseModel;
import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.LoginContract;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author ZhuZiBo
 * @date 2018/1/10
 * model具有请求数据的能力，自己处理数据
 */

public class LoginModel extends BaseModel implements LoginContract.Model{

    @Override
    public Observable<BaseResponse<List<LoginBean>>> login(String user, String pwd){
        return mRepositoryManager
                .obtainRetrofitService(DZFApiService.class)
                .login("1",user, pwd);
    }

}
