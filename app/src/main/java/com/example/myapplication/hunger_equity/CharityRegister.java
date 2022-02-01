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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.model.Charity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CharityRegister extends AppCompatActivity {
    List<Charity> charityList=new ArrayList<>();
    public static int count=0;
    public static int count2=0;
    public static int count23=0;
    private EditText inputcName,inputcOrgan,inputcPhone,inputcEmail,inputcAdress,inputcPassword,inputcPassword2;
    private Button cDone,tc;
    private TextView sign;
    private ImageButton look,look2,back_c;
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
        tc=(Button)findViewById(R.id.terms_conditions);
        back_c=(ImageButton)findViewById(R.id.charity_back_key);
        sign=(TextView)findViewById(R.id.sign_in_charity);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CharityRegister.this,CharityLogin.class);
                startActivity(in);
            }
        });

        back_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 CharityRegister.this.finish();
            }
        });



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

       mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                   Charity userDetails = dataSnapshot1.getValue(Charity.class);
                   charityList.add(userDetails);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        cDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

                if (checkBox.isChecked() && check()==false) {
                    String key = inputcName.getText().toString();
                    Charity charity = new Charity(inputcAdress.getText().toString(), inputcEmail.getText().toString(), inputcName.getText().toString(), inputcOrgan.getText().toString(),
                            inputcPassword.getText().toString(),
                            inputcPhone.getText().toString()
                    );
                    mFirebaseDatabase.child(key).setValue(charity);
                    Toast.makeText(CharityRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    Intent ion=new Intent(CharityRegister.this,CharityLogin.class);
                    startActivity(ion);
                }
                else
                {
                    Toast.makeText(CharityRegister.this, "UserName Already Taken Or You Have Not Completed The Field", Toast.LENGTH_SHORT).show();

                    inputcName.setError("UserName Already Taken Or You Have Not Completed The Field");
                }
            }
        });
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://developer.android.com/studio/terms"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    public boolean check()
    {
        String takenName=inputcName.getText().toString();
        String takenPassword=inputcPassword.getText().toString();
        Query checkUser=mFirebaseDatabase.orderByChild("c_Name").equalTo(takenName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {

                if (snapshot2.exists()) {
                    String passDB = snapshot2.child(takenName).child("c_Name").getValue(String.class);

                    if (passDB.equals(takenName)) {
                        count23=1;
                    } else {
                        count23=2;
                    }
                } else {
                    count23=2;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(count23==1)
        {
            return true;
        }
        return false;
    }

}