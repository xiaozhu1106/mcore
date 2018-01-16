package com.example.zzbmi.dzfnewcore.dzf.mvp.ui;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.zzbmi.dzfnewcore.R;
import com.example.zzbmi.dzfnewcore.dzf.BaseActivity;
import com.example.zzbmi.dzfnewcore.dzf.utils.RSAUtils;
import com.example.zzbmi.dzfnewcore.dzf.mvp.contract.LoginContract;
import com.example.zzbmi.dzfnewcore.dzf.mvp.presenter.LoginPresenter;

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

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        btnLogin.setOnClickListener((view) -> {
            mPresenter.toLogin(etAccount.getText().toString().trim(), RSAUtils.encrypt(etPwd.getText().toString().trim()));
        });
    }
}
