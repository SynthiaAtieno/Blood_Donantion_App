package com.example.blood_donantion_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    TextView title, slogan;
    Animation top_anim, bottom_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        slogan = findViewById(R.id.slogan);

        top_anim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logo.setAnimation(top_anim);
        title.setAnimation(bottom_anim);
        slogan.setAnimation(bottom_anim);


    }
}