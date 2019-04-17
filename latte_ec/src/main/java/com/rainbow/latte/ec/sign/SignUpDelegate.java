package com.rainbow.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.rainbow.latte.delegate.LatteDelegate;
import com.rainbow.latte.ec.R;
import com.rainbow.latte.ec.R2;
import com.rainbow.latte.net.RestClient;
import com.rainbow.latte.net.callback.ISuccess;
import com.rainbow.latte.util.logger.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder().url("sign_up")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getEditableText().toString();
        final String email = mEmail.getEditableText().toString();
        final String phone = mPhone.getEditableText().toString();
        final String password = mPassword.getEditableText().toString();
        final String rePassword = mRePassword.getEditableText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少六位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
