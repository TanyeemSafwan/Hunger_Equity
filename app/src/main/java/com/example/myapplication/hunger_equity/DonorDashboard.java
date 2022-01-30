package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DonorDashboard extends AppCompatActivity {
    SharedPreferences sp;
    DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_dashboard);
        ImageButton profile=(ImageButton) findViewById(R.id.donor_dashboard_profile);
        ImageButton donate=(ImageButton)findViewById(R.id.donor_dashboard_donate);
        ImageButton logout=(ImageButton)findViewById(R.id.donor_dashboard_logout);
        ImageButton feed=(ImageButton)findViewById(R.id.donor_dashboard_feed);
        ImageButton notification=(ImageButton)findViewById(R.id.donor_dashboard_notifications);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorDashboard.this,DonorNotification.class));
            }
        });


        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorDashboard.this,DonationFeed.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonorLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorDashboard.this,DonorRequestForm.class));
            }
        });

        sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);
        String UserName=sp.getString("name","");

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("d_Pics").child(UserName);
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.child("imageUrl").getValue(String.class);

                Picasso.get().load(link).into(profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonorDashboard.this,DonorHome.class));
            }
        });
    }
}