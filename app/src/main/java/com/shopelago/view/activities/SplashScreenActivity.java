package com.shopelago.view.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.config.HawkHelper;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivOpening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivOpening = findViewById(R.id.ivOpening);
        Glide.with(this).asGif().load(R.drawable.opening).into(ivOpening);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HawkHelper.DeleteUser();
                Intent intent = new Intent (SplashScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
