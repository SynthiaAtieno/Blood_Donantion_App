package com.example.blood_donantion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    Toolbar toolbar;
    TextView type, name, email, idnumber, phonenumber, bloodgroup;
    AppCompatButton button;
    CircleImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbca);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        idnumber = findViewById(R.id.idnumber);
        phonenumber = findViewById(R.id.phonenumber);
        bloodgroup = findViewById(R.id.bloodgroup);
        email = findViewById(R.id.email);
        button = findViewById(R.id.back_btn);
        profile = findViewById(R.id.profileImage);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    type.setText(snapshot.child("type").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    idnumber.setText(snapshot.child("id").getValue().toString());
                    bloodgroup.setText(snapshot.child("bloodgroup").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    phonenumber.setText(snapshot.child("phoneNo").getValue().toString());
                    String imageUrl = snapshot.child("profilepictureuri").getValue().toString();

                    Glide.with(getApplicationContext()).load(imageUrl).into(profile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}