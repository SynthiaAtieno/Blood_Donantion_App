package com.example.blood_donantion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private TextView registertv, forgotpassword;
    AppCompatButton login;
    private ProgressDialog loader;
    TextInputEditText loginemail, loginpassword;

    private FirebaseAuth.AuthStateListener authStateListener;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null)
                {
                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();
                }
            }
        };
        setContentView(R.layout.activity_login);
        registertv = findViewById(R.id.back_btn);
        login = findViewById(R.id.Login_btn);
        mAuth = FirebaseAuth.getInstance();
        loginemail = findViewById(R.id.loginEmail);
        loginpassword = findViewById(R.id.loginPassword);
        forgotpassword = findViewById(R.id.fogortPassword);
        loader = new ProgressDialog(this);

        registertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Select_Registration.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginemail.getText().toString().trim();
                String password = loginpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    loginemail.setError("Email is Required");
                    loginemail.requestFocus();
                }
                if (TextUtils.isEmpty(password))
                {
                    loginpassword.setError("Email is Required");
                    loginpassword.requestFocus();
                }
                else {
                    loader.setCanceledOnTouchOutside(false);
                    loader.setMessage("Signing In");
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            loader.dismiss();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}