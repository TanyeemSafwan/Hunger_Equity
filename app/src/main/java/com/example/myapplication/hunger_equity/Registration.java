package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {
    Button chooseButton1,chooseButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        chooseButton1=(Button) findViewById(R.id.charity_button);
        chooseButton2=(Button) findViewById(R.id.donor_button);

        chooseButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(Registration.this, DonorLogin.class);
                Registration.this.startActivity(Intent);

            }
        });

        chooseButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,CharityLogin.class);
                startActivity(intent);
            }
        });


    }
}