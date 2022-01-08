package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class CharityRegister extends AppCompatActivity {
    List<Charity> CharityList=new ArrayList<>();
    public static int count=0;
    public static int count2=0;
    private EditText inputcName,inputcOrgan,inputcPhone,inputcEmail,inputcAdress,inputcPassword,inputcPassword2;
    private Button cDone;
    private ImageButton look,look2;
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
        look=(ImageButton)findViewById(R.id.charity_eye_1);
        look2=(ImageButton)findViewById(R.id.charity_eye_2);
        inputcPassword2=(EditText)findViewById(R.id.charity_password_2);


        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==0)
                {
                    inputcPassword.setTransformationMethod(null);
                    count=1;
                }
                else if(count==1)
                {
                    inputcPassword.setTransformationMethod(new PasswordTransformationMethod());
                    count=0;
                }


                //inputcPassword.setTransformationMethod(null);
                     //   count++;

            }
        });
        look2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count2==0)
                {
                    inputcPassword2.setTransformationMethod(null);
                    count2=1;
                }
                else if(count2==1)
                {
                    inputcPassword2.setTransformationMethod(new PasswordTransformationMethod());
                    count2=0;
                }

            }
        });


        //mFirebaseInstance = FirebaseDatabase.getInstance();
      //mFirebaseDatabase = mFirebaseInstance.getReference("Charity");
       mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Charity");



            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);


        cDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    String key = mFirebaseDatabase.push().getKey();
                    Charity charity = new Charity(inputcAdress.getText().toString(), inputcEmail.getText().toString(), inputcName.getText().toString(), inputcOrgan.getText().toString(),
                            inputcPassword.getText().toString(),
                            inputcPhone.getText().toString()
                    );
                    mFirebaseDatabase.child(key).setValue(charity);
                    finish();
                }
            }
        });
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}