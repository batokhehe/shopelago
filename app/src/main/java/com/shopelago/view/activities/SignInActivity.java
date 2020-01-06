package com.shopelago.view.activities;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.ShopelagoApplication;
import com.shopelago.config.ApiResponse;
import com.shopelago.config.Config;
import com.shopelago.config.HawkHelper;
import com.shopelago.factory.SignInViewModelFactory;
import com.shopelago.models.Response;
import com.shopelago.models.User;
import com.shopelago.models.requests.login.VerifyLogin;
import com.shopelago.utils.Constant;
import com.shopelago.utils.Responses;
import com.shopelago.viewmodels.SignInViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {
    @Inject
    SignInViewModelFactory viewModelFactory;
    @BindView(R.id.etUserId)
    EditText etUserId;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.cbRememberMe)
    CheckBox cbRememberMe;
    @BindView(R.id.tvForgetPassword)
    TextView tvForgetPassword;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnLoginFacebook)
    LinearLayout btnLoginFacebook;
    @BindView(R.id.btnLoginGoogle)
    LinearLayout btnLoginGoogle;
    private Config config;
    private SignInViewModel viewModel;
    private Dialog dialog;
    private VerifyLogin model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        initDialog();
        ((ShopelagoApplication) getApplication()).getAppComponent().doSignInInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel.class);
        viewModel.response().observe(this, this::consumeResponse);
    }

    private void openActivity(Class targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    private void initDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.modal_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.tvRegister)
    public void Register(){
        openActivity(SignUpActivity.class);
    }

    @OnClick(R.id.tvForgetPassword)
    public void ForgotPassword(){
        openActivity(ForgotPasswordActivity.class);
    }

    @OnClick(R.id.btnLogin)
    public void SignIn(){
        if (isValid()) {
            if (!Constant.checkInternetConnection(this)) {
                Toast.makeText(SignInActivity.this, getResources().getString(R.string.error_conn), Toast.LENGTH_SHORT).show();
            } else {
                String userId = etUserId.getText().toString();
                String password = etPassword.getText().toString();
                model = null;
                model = new VerifyLogin(userId, password);
                viewModel.hitVerifyLogin(model);
            }
        }
    }

    private boolean isValid() {
        String userId = etUserId.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(userId)){
            etUserId.setError(getString(R.string.mandatory));
            return false;
        }

        if(TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.mandatory));
            return false;
        }
        
        return true;
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                dialog.show();
                break;
            case SUCCESS:
                dialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;
            case ERROR:
                dialog.dismiss();
                Toast.makeText(SignInActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }/*
     * method to handle success response
     * */
    private void renderSuccessResponse(JsonElement response) {
        if (response != null) {
            Response model = Responses.parser(response);
            if (model.getResponse()){
//                User user = new User("batok", "batok@gmail.com", "token_tes");
                User user = new User("", "", model.getToken());
                HawkHelper.SetUser(user);
                openActivity(MainActivity.class);
            } else {
                Toast.makeText(SignInActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Log.d("SignIn", "renderSuccessResponse: " + model.getResponse());
        } else {
            Toast.makeText(SignInActivity.this, getResources().getString(R.string.error_conn), Toast.LENGTH_SHORT).show();
        }
    }
}
