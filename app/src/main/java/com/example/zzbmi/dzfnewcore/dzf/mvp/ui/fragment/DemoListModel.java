package com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment;

import com.dazf.frame.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
public class DemoListModel extends BaseModel implements DemoListContract.Model {
    /**
     * 负责获取、处理数据
     * @return
     */
    public List<String> getData() {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arr.add("我是一个Demo:" + i);
        }
        return arr;
    }
}
