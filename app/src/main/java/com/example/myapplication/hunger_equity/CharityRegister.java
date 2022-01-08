package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class CharityRegister extends AppCompatActivity {
    private EditText inputcName,inputcOrgan,inputcPhone,inputcEmail,inputcAdress,inputcPassword;
    private Button cDone;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_register);

       inputcAdress=(EditText) findViewById(R.id.charity_address);
       inputcEmail=(EditText) findViewById(R.id.charity_email);
       inputcName=(EditText) findViewById(R.id.charity_name);
        inputcOrgan=(EditText) findViewById(R.id.charity_organ);
        inputcPhone=(EditText) findViewById(R.id.charity_phone);
        inputcPassword=(EditText) findViewById(R.id.charity_password);
        cDone=(Button)findViewById(R.id.charity_create);

        //mFirebaseInstance = FirebaseDatabase.getInstance();
      //mFirebaseDatabase = mFirebaseInstance.getReference("Charity");
       mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Charity");

        cDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=mFirebaseDatabase.push().getKey();
             Charity charity=new Charity(inputcAdress.getText().toString(),inputcEmail.getText().toString(),inputcName.getText().toString(),inputcOrgan.getText().toString(),
                     inputcPassword.getText().toString(),
                     inputcPhone.getText().toString()
                    );
             mFirebaseDatabase.child(key).setValue(charity);
                finish();
            }
        });

    }
}