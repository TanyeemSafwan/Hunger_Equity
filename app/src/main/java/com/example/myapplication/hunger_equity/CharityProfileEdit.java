package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.hunger_equity.model.Charity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityProfileEdit extends AppCompatActivity {
    TextView name;
    EditText email,password,address,organ,phone;
    Button submit;
    DatabaseReference mFirebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_profile_edit);
        name=(TextView)findViewById(R.id.charity_update_name);
        email=(EditText)findViewById(R.id.charity_update_email);
        password=(EditText)findViewById(R.id.charity_update_password);
        organ=(EditText)findViewById(R.id.charity_update_organ);
        address=(EditText) findViewById(R.id.charity_update_address);
        phone=(EditText)findViewById(R.id.charity_update_phone);
        submit=(Button)findViewById(R.id.charity_update_submit);
        ImageButton back=(ImageButton)findViewById(R.id.charity_profile_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityProfileEdit.this.finish();
            }
        });

        Intent gIntent=getIntent();
        String userName=gIntent.getStringExtra("charityName");
        String userEmail=gIntent.getStringExtra("charityEmail");
        String userPhone=gIntent.getStringExtra("charityPhone");
        String userAddress=gIntent.getStringExtra("charityAddress");
        String userOrgan=gIntent.getStringExtra("charityOrgan");
        String userPassword=gIntent.getStringExtra("charityPassword");

        name.setText(userName);
        email.setText(userEmail);
        organ.setText(userOrgan);
        address.setText(userAddress);
        phone.setText(userPhone);

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Charity");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(userPassword.equals(password.getText().toString())) {
                    String key = userName;
                    Charity charity = new Charity(address.getText().toString(), email.getText().toString(), userName, organ.getText().toString(),
                            userPassword,
                            phone.getText().toString()
                    );
                    mFirebaseDatabase.child(key).setValue(charity);
                    CharityProfileEdit.this.finish();
                }
                else
                {
                    password.setError("Wrong Password");
                }
            }
        });



    }
}