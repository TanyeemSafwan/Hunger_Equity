package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DonorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);
        TextView dName,dEmail,dPhone,dAdress;
        dName=(TextView) findViewById(R.id.donor_user_name);
        dEmail=(TextView) findViewById(R.id.donor_user_email);
        dPhone=(TextView) findViewById(R.id.donor_user_phone);
        dAdress=(TextView) findViewById(R.id.donor_user_address);

        Intent in=getIntent();
        String userName=in.getStringExtra("name");
        String userEmail=in.getStringExtra("email");
        String userPhone=in.getStringExtra("phone");
        String userAddress=in.getStringExtra("address");

        dName.setText(userName);
        dEmail.setText(userEmail);
        dPhone.setText(userPhone);
        dAdress.setText(userAddress);
    }
}