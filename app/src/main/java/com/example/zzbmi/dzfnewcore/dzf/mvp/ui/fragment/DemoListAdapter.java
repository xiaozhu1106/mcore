package com.example.zzbmi.dzfnewcore.dzf.mvp.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zzbmi.dzfnewcore.R;

import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/17
 */
class DemoListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    DemoListAdapter(List<String> data) {
        super(R.layout.item_list_demo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv, item);
    }
}
