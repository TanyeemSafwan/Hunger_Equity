package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DonorLogin extends AppCompatActivity {
    Button login;
    EditText name,password;
    ImageButton eyes;
    TextView sign;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_login);
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
    }
}