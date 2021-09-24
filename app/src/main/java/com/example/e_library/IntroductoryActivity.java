package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView app_name;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        app_name=findViewById(R.id.app_name);
        lottieAnimationView=findViewById(R.id.lottie);

        app_name.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(IntroductoryActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}