package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CharityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_home);
        TextView cName,cEmail,cPhone,cAdress;
        cName=(TextView) findViewById(R.id.textView);
        cEmail=(TextView) findViewById(R.id.textView2);
        cPhone=(TextView) findViewById(R.id.textView3);
        cAdress=(TextView) findViewById(R.id.textView4);

        Intent in=getIntent();
        String userName=in.getStringExtra("name");
        String userEmail=in.getStringExtra("email");
        String userPhone=in.getStringExtra("phone");
        String userAddress=in.getStringExtra("address");

        cName.setText(userName);
        cEmail.setText(userEmail);
        cPhone.setText(userPhone);
        cAdress.setText(userAddress);
    }
}