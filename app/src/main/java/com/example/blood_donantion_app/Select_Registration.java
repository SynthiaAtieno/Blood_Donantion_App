package com.example.blood_donantion_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Select_Registration extends AppCompatActivity {

    AppCompatButton donor, reciepient;
    TextView haveAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        donor = findViewById(R.id.donor_btn);
        reciepient = findViewById(R.id.reciepient_btn);
        haveAccount = findViewById(R.id.back_btn);

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DonorRegistration.class));
                finish();
            }
        });

        reciepient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Recipient_Registarion.class));
                finish();
            }
        });
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}