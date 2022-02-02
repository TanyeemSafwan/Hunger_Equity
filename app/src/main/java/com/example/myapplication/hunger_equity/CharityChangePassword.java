package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CharityChangePassword extends AppCompatActivity {
    public static int count=0;
    public static int count2=0;
    public static int count3=0;
    EditText passOld,passNew,passConfirm;
    Button confirm;
    ImageButton eyeOld,eyeNew,eyeConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_change_password);
        passOld=(EditText) findViewById(R.id.change_password_old);
        passNew=(EditText) findViewById(R.id.change_password_new);
        passConfirm=(EditText) findViewById(R.id.change_password_new2);
        confirm=(Button)findViewById(R.id.charity_password_change);
        eyeOld= (ImageButton) findViewById(R.id.charity_change_eye_1);
        eyeNew= (ImageButton) findViewById(R.id.charity_change_eye_2);
        eyeConfirm= (ImageButton) findViewById(R.id.charity_change_eye_2_2);

        Toast.makeText(CharityChangePassword.this, "Under Construction!", Toast.LENGTH_SHORT).show();

        eyeOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==0)
                {
                    passOld.setTransformationMethod(null);
                    count=1;
                }
                else if(count==1)
                {
                    passOld.setTransformationMethod(new PasswordTransformationMethod());
                    count=0;
                }

            }
        });
        eyeNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count2==0)
                {
                    passNew.setTransformationMethod(null);
                    count2=1;
                }
                else if(count2==1)
                {
                    passNew.setTransformationMethod(new PasswordTransformationMethod());
                    count2=0;
                }

            }
        });
        eyeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count3==0)
                {
                    passConfirm.setTransformationMethod(null);
                    count3=1;
                }
                else if(count3==1)
                {
                    passConfirm.setTransformationMethod(new PasswordTransformationMethod());
                    count3=0;
                }

            }
        });
    }
}