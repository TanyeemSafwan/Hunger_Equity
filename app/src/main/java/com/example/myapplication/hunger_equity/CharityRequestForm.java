package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.model.CFeedModel;
import com.example.myapplication.hunger_equity.model.Charity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CharityRequestForm extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_request_form);
        ImageButton back=(ImageButton)findViewById(R.id.charity_form_back);
        Button cDone=(Button)findViewById(R.id.charity_form_done);
        CheckBox checkBox=(CheckBox)findViewById(R.id.charity_form_checkbox);
        EditText title=(EditText)findViewById(R.id.charity_form_title);
        EditText quantity=(EditText)findViewById(R.id.charity_form_quantity);
        EditText address=(EditText)findViewById(R.id.charity_form_address);
        date2=(EditText)findViewById(R.id.charity_form_date);
        EditText description=(EditText)findViewById(R.id.charity_form_description);
        String status="Active";

        SharedPreferences sp=getApplicationContext().getSharedPreferences("CharityInfo", Context.MODE_PRIVATE);

        String userName=sp.getString("name","");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityRequestForm.this.finish();
            }
        });
        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Charity_feed");


        cDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

                if (checkBox.isChecked()) {
                    String key = mFirebaseDatabase.push().getKey();
                    CFeedModel charity = new CFeedModel(userName, title.getText().toString(), quantity.getText().toString(), address.getText().toString(),
                            date2.getText().toString(),
                            description.getText().toString(),status
                    );
                    mFirebaseDatabase.child(key).setValue(charity);
                    Toast.makeText(CharityRequestForm.this, "Posted!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            CharityRequestForm.this.finish();
                        }
                    }, 1300);

                }
                else
                {
                    Toast.makeText(CharityRequestForm.this, "Please check CHECKBOX", Toast.LENGTH_SHORT).show();
                }
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CharityRequestForm.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.UK);
        date2.setText(dateFormat.format(myCalendar.getTime()));
    }

}