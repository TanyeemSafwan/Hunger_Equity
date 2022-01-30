package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CharityDashboard extends AppCompatActivity {
    DatabaseReference mFirebaseDatabase;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_dashboard);
        ImageButton profile= (ImageButton) findViewById(R.id.charity_dashboard_profile);
        ImageButton request=(ImageButton)findViewById(R.id.charity_dashboard_request);
        ImageButton logout=(ImageButton)findViewById(R.id.charity_dashboard_logout);
        ImageButton feed=(ImageButton)findViewById(R.id.charity_dashboard_feed);
        ImageButton notification=(ImageButton)findViewById(R.id.charity_dashboard_notification);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharityDashboard.this,CharityNotification.class));
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharityDashboard.this,CharityFeed.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CharityLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharityDashboard.this,CharityRequestForm.class));
            }
        });
        sp=getApplicationContext().getSharedPreferences("CharityInfo", Context.MODE_PRIVATE);
        String UserName=sp.getString("name","");
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Pics").child(UserName);
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
                startActivity(new Intent(CharityDashboard.this,CharityHome.class));
            }
        });


    }
}