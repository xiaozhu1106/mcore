package com.example.zzbmi.dzfnewcore.dzf.config;

import java.io.Serializable;

/**
 * des:封装服务器数据的基本格式
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class BaseResponse<T> implements Serializable {
    public T datas;
    public String resnumber;
    public String msg;

    public boolean success() {
        return "0".equals(resnumber);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "success='" + resnumber + '\'' +
                ", msg='" + msg + '\'' +
                ", obj=" + datas +
                '}';
    }

}
