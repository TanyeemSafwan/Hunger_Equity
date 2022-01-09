package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class DonorRegi extends AppCompatActivity {
    List<Donor> donorList=new ArrayList<>();
    public static int count=0;
    public static int count2=0;
    private EditText inputdName,inputdPhone,inputdEmail,inputdAdress,inputdPassword,inputdPassword2;
    private Button dDone,tcd;
    private ImageButton look_d,look2_d,back;
    private DatabaseReference dFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_regi);
        inputdAdress=(EditText)findViewById(R.id.donor_address);
        inputdEmail=(EditText) findViewById(R.id.donor_email);
        inputdName=(EditText) findViewById(R.id.donor_name);
        inputdPhone=(EditText) findViewById(R.id.donor_phone);
        inputdPassword=(EditText) findViewById(R.id.donor_password);
        dDone=(Button)findViewById(R.id.donor_create);
        look_d=(ImageButton)findViewById(R.id.donor_eye_1);
        look2_d=(ImageButton)findViewById(R.id.donor_eye_2);
        inputdPassword2=(EditText)findViewById(R.id.donor_password_2);
        tcd=(Button)findViewById(R.id.terms_conditions_donor);
        back=(ImageButton)findViewById(R.id.donor_back_key);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(DonorRegi.this,Registration.class);
                startActivity(inte);
            }
        });

        look_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==0)
                {
                    inputdPassword.setTransformationMethod(null);
                    count=1;
                }
                else if(count==1)
                {
                    inputdPassword.setTransformationMethod(new PasswordTransformationMethod());
                    count=0;
                }


                //inputcPassword.setTransformationMethod(null);
                //   count++;

            }
        });


        look2_d.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          if(count2==0)
           {
              inputdPassword2.setTransformationMethod(null);
              count2=1;
            }
          else if(count2==1)
           {
               inputdPassword2.setTransformationMethod(new PasswordTransformationMethod());
               count2=0;
           }

           }
        });
        dFirebaseDatabase= FirebaseDatabase.getInstance().getReference("a_donor");

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox_donor);



        dDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vie) {
                if (checkBox1.isChecked()) {
                    String key = dFirebaseDatabase.push().getKey();
                    Donor donor = new Donor(inputdAdress.getText().toString(), inputdEmail.getText().toString(), inputdName.getText().toString(),
                            inputdPassword.getText().toString(),
                            inputdPhone.getText().toString()
                    );
                    dFirebaseDatabase.child(key).setValue(donor);

                }
            }
        });
        tcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://developer.android.com/studio/terms"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });





    }
}