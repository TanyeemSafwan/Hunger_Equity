package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DonorLogin extends AppCompatActivity {
    SharedPreferences sp;
    Button login;
    EditText name,password;
    ImageButton eyes;
    TextView sign;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_login);

        sp=sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);

        eyes=(ImageButton) findViewById(R.id.donor_eye_login);
        login=(Button)findViewById(R.id.donor_login);
        name=(EditText) findViewById(R.id.donor_login_name);
        password=(EditText) findViewById(R.id.donor_login_password);
        sign=(TextView)findViewById(R.id.donor_sign);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DonorLogin.this,DonorRegi.class);
                startActivity(i);
            }
        });



        eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==0)
                {
                    password.setTransformationMethod(null);
                    count=1;
                }
                else if(count==1)
                {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    count=0;
                }


                //inputcPassword.setTransformationMethod(null);
                //   count++;

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(validateName()==true && validatePassword()==true)
                {
                    isUser();
                }
            }
        });
    }
    private boolean validateName()
    {
        String pass=name.getText().toString();
        if(pass.isEmpty())
        {
            name.setError("Field cannot be empty");
            return false;
        }
        else
        {
            name.setError(null);
            return true;
        }
    }
    public boolean validatePassword()
    {
        String pass=password.getText().toString();
        if(pass.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }

    }
    public void isUser()
    {
        String takenName=name.getText().toString().trim();
        String takenPassword=password.getText().toString().trim();
        //System.out.println(takenName);
       // System.out.println(takenPassword);

        Query checkUser= FirebaseDatabase.getInstance().getReference("a_donor").orderByChild("d_Name").equalTo(takenName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                if (snapshot1.exists()) {
                    String passDB = snapshot1.child(takenName).child("d_Password").getValue(String.class);


                    if (passDB.equals(takenPassword)) {
                        String nameDB = snapshot1.child(takenName).child("d_Name").getValue(String.class);
                        String emailDB = snapshot1.child(takenName).child("d_Email").getValue(String.class);
                        String phoneDB = snapshot1.child(takenName).child("d_Phone").getValue(String.class);
                        String addressDB = snapshot1.child(takenName).child("d_Address").getValue(String.class);

                        Intent i = new Intent(DonorLogin.this, DonorDashboard.class);
                        /*i.putExtra("name",nameDB);
                        i.putExtra("email",emailDB);
                        i.putExtra("phone",phoneDB);
                        i.putExtra("address",addressDB);
                        i.putExtra("password",passDB);
                        */
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("name",nameDB);
                        editor.putString("email",emailDB);
                        editor.putString("phone",phoneDB);
                        editor.putString("address",addressDB);
                        editor.putString("password",passDB);
                        editor.commit();

                        startActivity(i);

                    } else {
                        Toast.makeText(DonorLogin.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        password.setError("Wrong password");
                    }
                } else {
                    Toast.makeText(DonorLogin.this, "NO Such User!", Toast.LENGTH_SHORT).show();

                    password.setError("No such user");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}