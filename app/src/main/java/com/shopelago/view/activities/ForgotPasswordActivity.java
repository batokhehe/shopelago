package com.shopelago.view.activities;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.shopelago.R;
import com.shopelago.ShopelagoApplication;
import com.shopelago.config.ApiResponse;
import com.shopelago.config.Config;
import com.shopelago.config.HawkHelper;
import com.shopelago.factory.ForgotPasswordViewModelFactory;
import com.shopelago.models.ForgotPassword;
import com.shopelago.models.Response;
import com.shopelago.models.requests.forgot_password.ResetPassword;
import com.shopelago.utils.Constant;
import com.shopelago.utils.Responses;
import com.shopelago.viewmodels.ForgotPasswordViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Inject
    ForgotPasswordViewModelFactory viewModelFactory;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etOtpCode)
    EditText etOtpCode;
    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;
    @BindView(R.id.confirmationLayout)
    LinearLayout confirmationLayout;
    @BindView(R.id.changePassLayout)
    LinearLayout changePassLayout;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
    @BindView(R.id.secondLayout)
    LinearLayout secondLayout;
    @BindView(R.id.labelMainLayout)
    LinearLayout labelMainLayout;
    @BindView(R.id.labelSecondLayout)
    LinearLayout labelSecondLayout;
    @BindView(R.id.labelThirdLayout)
    LinearLayout labelThirdLayout;
    @BindView(R.id.tvResendOtp)
    TextView tvResendOtp;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRepeatPassword)
    EditText etRepeatPassword;
    @BindView(R.id.labelFourthLayout)
    LinearLayout labelFourthLayout;
    @BindView(R.id.tvBackToLogin)
    TextView tvBackToLogin;

    private Config config;
    private ForgotPasswordViewModel viewModel;
    private Dialog dialog;
    private ResetPassword model;
    private STATE state = STATE.EMAIL_VERIFICATION;

    public enum STATE {
        EMAIL_VERIFICATION,
        OTP_VERIFICATION,
        NEW_PASSWORD
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        config = new Config();
        ((ShopelagoApplication) getApplication()).getAppComponent().doForgotPasswordInjection(this);
        initDialog();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForgotPasswordViewModel.class);
        viewModel.response().observe(this, this::consumeResponse);

    }

    private void initDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.modal_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.tvBackToLogin)
    void backToLogin(){
        onBackPressed();
    }

    @OnClick(R.id.btnResetPassword)
    void ResetPassword(){
        if (isValid()) {
            if(state == STATE.EMAIL_VERIFICATION) {
                if (!Constant.checkInternetConnection(this)) {
                    Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                } else {
                    String email = etEmail.getText().toString();
                    model = null;
                    model = new ResetPassword();
                    model.setEmail(email);
                    viewModel.hitEmailConfirmation(model);
                }
            }else if(state == STATE.OTP_VERIFICATION){
                dialog.show();
                ForgotPassword forgotPassword = HawkHelper.GetForgotPassword();
                String otp = etOtpCode.getText().toString();
                if(otp.equals(forgotPassword.getOtpCode())){
                    state = STATE.NEW_PASSWORD;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            openLayout();
                        }
                    }, 2000);

                }else{
                    dialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error_otp_code), Toast.LENGTH_SHORT).show();
                }
            }else{
                if (!Constant.checkInternetConnection(this)) {
                    Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                } else {
                    String email = etEmail.getText().toString();
                    String otp = etOtpCode.getText().toString();
                    String password = etPassword.getText().toString();
                    model = null;
                    model = new ResetPassword();
                    model.setEmail(email);
                    model.setOtp(otp);
                    model.setPassword(password);
                    viewModel.hitNewPassword(model);
                }
            }
        }
    }

    @OnClick(R.id.tvResendOtp)
    void ResendEmail(){
        if (!Constant.checkInternetConnection(this)) {
            Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        } else {
            ForgotPassword forgotPassword = HawkHelper.GetForgotPassword();
            String email = forgotPassword.getEmail();
            model = null;
            model = new ResetPassword();
            model.setEmail(email);
            viewModel.hitEmailConfirmation(model);
        }
    }

    private boolean isValid() {
        if(state == STATE.EMAIL_VERIFICATION){
            etEmail.setError(null);
            String email = etEmail.getText().toString();
            if(TextUtils.isEmpty(email)){
                etEmail.setError(getString(R.string.mandatory));
                etEmail.requestFocus();
                return false;
            }
        } else if(state == STATE.OTP_VERIFICATION) {
            etOtpCode.setError(null);
            String otp = etOtpCode.getText().toString();
            if(TextUtils.isEmpty(otp)){
                etOtpCode.setError(getString(R.string.mandatory));
                etOtpCode.requestFocus();
                return false;
            }
        }else{
            etPassword.setError(null);
            etRepeatPassword.setError(null);
            String password = etPassword.getText().toString();
            String rpassword = etRepeatPassword.getText().toString();

            if(TextUtils.isEmpty(password)){
                etPassword.setError(getString(R.string.mandatory));
                return false;
            }

            if(TextUtils.isEmpty(rpassword)){
                etRepeatPassword.setError(getString(R.string.mandatory));
                return false;
            }

            if(!password.equals(rpassword)){
                etRepeatPassword.setError(getString(R.string.invalid_repeat_password));
                return false;
            }

        }

        return true;
    }

    private void openLayout(){
        if(state == STATE.EMAIL_VERIFICATION){
            confirmationLayout.setVisibility(View.VISIBLE);
            changePassLayout.setVisibility(View.GONE);

            mainLayout.setVisibility(View.VISIBLE);
            labelMainLayout.setVisibility(View.VISIBLE);
            secondLayout.setVisibility(View.GONE);
            labelSecondLayout.setVisibility(View.GONE);
            labelThirdLayout.setVisibility(View.GONE);
        } else if(state == STATE.OTP_VERIFICATION) {
            confirmationLayout.setVisibility(View.VISIBLE);
            changePassLayout.setVisibility(View.GONE);

            mainLayout.setVisibility(View.GONE);
            labelMainLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.VISIBLE);
            labelSecondLayout.setVisibility(View.VISIBLE);
            labelThirdLayout.setVisibility(View.GONE);
        }else{
            confirmationLayout.setVisibility(View.GONE);
            changePassLayout.setVisibility(View.VISIBLE);

            labelMainLayout.setVisibility(View.GONE);
            labelSecondLayout.setVisibility(View.GONE);
            labelThirdLayout.setVisibility(View.VISIBLE);
        }
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
                Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
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
                //get data {code, email}
                if(!model.getData().isEmpty()){
                    try {
                        if(state == STATE.EMAIL_VERIFICATION || state == STATE.OTP_VERIFICATION){
                            JSONObject obj = new JSONObject(model.getData());
                            String otp = obj.getString("code") != null ? obj.getString("code") : "";
                            String email = obj.getString("email") != null ? obj.getString("email") : "";
                            ForgotPassword forgotPassword = new ForgotPassword();
                            forgotPassword.setOtpCode(otp);
                            forgotPassword.setEmail(email);
                            HawkHelper.SetForgotPassword(forgotPassword);
                            state = STATE.OTP_VERIFICATION;
                            etOtpCode.setText("");
                            openLayout();
                        } else if (state == STATE.NEW_PASSWORD){
                            finished();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "No data provided.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(ForgotPasswordActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

    private void finished(){
        confirmationLayout.setVisibility(View.GONE);
        changePassLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.GONE);
        labelMainLayout.setVisibility(View.GONE);
        secondLayout.setVisibility(View.GONE);
        labelSecondLayout.setVisibility(View.GONE);
        labelThirdLayout.setVisibility(View.GONE);
        btnResetPassword.setVisibility(View.GONE);
        labelFourthLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
