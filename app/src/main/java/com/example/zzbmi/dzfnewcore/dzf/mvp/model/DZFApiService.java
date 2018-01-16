package com.example.zzbmi.dzfnewcore.dzf.mvp.model;

import com.example.zzbmi.dzfnewcore.dzf.config.BaseResponse;
import com.example.zzbmi.dzfnewcore.dzf.config.EmptyBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DZFApiService {
    @FormUrlEncoded
    @POST("busidata!dealData.action")
    Observable<BaseResponse<List<LoginBean>>> login(@Field("operate") String operate, @Field("user") String user, @Field("pwd") String pwd);


    @FormUrlEncoded
    @POST("busidata!dealData.action")
    Observable<BaseResponse<EmptyBean>> reback(@Field("operate") String operate, @Field("ssend") String ssend, @Field("dsdate") String dsdate, @Field("vcont") String vcont);
}