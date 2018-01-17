package com.example.zzbmi.dzfnewcore.dzf.mvp.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dazf.frame.ui.BaseActivity;
import com.example.zzbmi.dzfnewcore.R;
import com.example.zzbmi.dzfnewcore.dzf.mvp.presenter.IdeaRebackPresenter;

import butterknife.BindView;

/**
 * @author ZhuZiBo
 * @date 2018/1/15
 */
public class IdeaRebackActivity extends BaseActivity<IdeaRebackPresenter> {

    @BindView(R.id.et_reback)
    EditText etReback;
    @BindView(R.id.btn_reback)
    Button btnReback;
    @Override
    public int getLayoutId() {
        return R.layout.activity_reback;
    }

    @Override
    public void init() {
        btnReback.setOnClickListener(view -> mPresenter.toIdeaReback(etReback.getText().toString().trim()));
    }

    @Override
    public View getBindViewToStatusView() {
        return null;
    }
}
