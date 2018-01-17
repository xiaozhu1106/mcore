package com.example.zzbmi.dzfnewcore.dzf.mvp.ui;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dazf.frame.ui.BaseActivity;
import com.example.zzbmi.dzfnewcore.R;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.LoginContract;
import com.example.zzbmi.dzfnewcore.dzf.mvp.presenter.LoginPresenter;
import com.example.zzbmi.dzfnewcore.dzf.utils.RSAUtils;

import butterknife.BindView;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_list_fragment)
    Button btn_list_fragment;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        btnLogin.setOnClickListener((view) -> mPresenter.toLogin(etAccount.getText().toString().trim(), RSAUtils.encrypt(etPwd.getText().toString().trim())));
        btn_list_fragment.setOnClickListener(view -> startActivity(DemoListActivity.class));
    }

    @Override
    public View getBindViewToStatusView() {
        return null;
    }
}
