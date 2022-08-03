package com.example.blood_donantion_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView nav_fullname, nav_email, nav_type, nav_bloodgroup;
    private CircleImageView profile;
    DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drwerLayout);
        toolbar = findViewById(R.id.toolbca);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nav_fullname = navigationView.getHeaderView(0).findViewById(R.id.nav_userFullname);
        nav_email = navigationView.getHeaderView(0).findViewById(R.id.nav_userEmail);
        nav_type = navigationView.getHeaderView(0).findViewById(R.id.nav_userType);
        profile = navigationView.getHeaderView(0).findViewById(R.id.nav_user_image);
        nav_bloodgroup = navigationView.getHeaderView(0).findViewById(R.id.nav_userBloodgroup);

        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String name = snapshot.child("name").getValue().toString();
                    nav_fullname.setText(name);

                    String bloodgroup = snapshot.child("bloodgroup").getValue().toString();
                    nav_bloodgroup.setText(bloodgroup);

                    String email = snapshot.child("email").getValue().toString();
                    nav_email.setText(email);

                    String type = snapshot.child("type").getValue().toString();
                    nav_type.setText(type);
                    if (snapshot.hasChild("profilepictureuri"))
                    {
                        String imageurl = snapshot.child("profilepictureuri").getValue().toString();
                        Glide.with(getApplicationContext()).load(imageurl).into(profile);
                    }
                    else {
                        profile.setImageResource(R.drawable.profile);;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Donation App");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar
        ,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId())
       {
           case R.id.pofile:
               startActivity(new Intent(MainActivity.this, Profile.class));
               finish();

           case R.id.logout:
               FirebaseAuth mAuth = FirebaseAuth.getInstance();
               mAuth.signOut();
               startActivity(new Intent(MainActivity.this, Login.class));
               finish();
       }
       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}