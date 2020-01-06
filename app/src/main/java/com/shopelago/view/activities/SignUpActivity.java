package com.shopelago.view.activities;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.shopelago.factory.SignUpViewModelFactory;
import com.shopelago.models.Response;
import com.shopelago.models.requests.registration.CreateNewUser;
import com.shopelago.utils.Constant;
import com.shopelago.utils.Responses;
import com.shopelago.viewmodels.SignUpViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @Inject
    SignUpViewModelFactory viewModelFactory;
    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRepeatPassword)
    EditText etRepeatPassword;
    @BindView(R.id.tvTerms)
    TextView tvTerms;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.btnRegisterFacebook)
    LinearLayout btnRegisterFacebook;
    @BindView(R.id.btnRegisterGoogle)
    LinearLayout btnRegisterGoogle;

    private Config config;
    private SignUpViewModel viewModel;
    private Dialog dialog;
    private CreateNewUser model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        config = new Config();
        ((ShopelagoApplication) getApplication()).getAppComponent().doSignUpInjection(this);
        initDialog();

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel.class);
        viewModel.response().observe(this, this::consumeResponse);
    }

    private void initDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.modal_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.btnRegister)
    void Register(){
        if (isValid()) {
            if (!Constant.checkInternetConnection(this)) {
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            } else {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String fullname = etFullName.getText().toString();
//                String kode_referal = etEmail.getText().toString();
                model = null;
                model = new CreateNewUser(email, password, fullname, null);
                viewModel.hitCreateNewUser(model);
            }
        }
    }

    private boolean isValid() {
        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String rpassword = etRepeatPassword.getText().toString();

        if(TextUtils.isEmpty(fullName)){
            etFullName.setError(getString(R.string.mandatory));
            return false;
        }

        if(TextUtils.isEmpty(email)){
            etEmail.setError(getString(R.string.mandatory));
            return false;
        }

        if(TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.mandatory));
            return false;
        }

        if(TextUtils.isEmpty(rpassword)){
            etRepeatPassword.setError(getString(R.string.mandatory));
            return false;
        }

        if(!password.equals(rpassword)){
            etPassword.setError(getString(R.string.mandatory));
            etRepeatPassword.setError(getString(R.string.mandatory));
            return false;
        }

        return true;
    }

    private void showCheckEmailModal(){
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.modal_check_email);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
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
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
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
                showCheckEmailModal();
            } else {
                Toast.makeText(SignUpActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }
}
