package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.model.CFeedModel;
import com.example.myapplication.hunger_equity.model.DFeedModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorRequestForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_request_form);
        ImageButton back=(ImageButton)findViewById(R.id.donor_form_back);
        Button cDone=(Button)findViewById(R.id.donor_form_request);
        CheckBox checkBox=(CheckBox)findViewById(R.id.donor_form_checkbox);
        EditText title=(EditText)findViewById(R.id.donor_form_title);
        EditText quantity=(EditText)findViewById(R.id.donor_form_quantity);
        EditText pickup=(EditText)findViewById(R.id.donor_form_pickup);
        EditText time=(EditText)findViewById(R.id.donor_form_pickup_time);
        EditText date=(EditText)findViewById(R.id.donor_form_pickup_date);
        String status="Active";

        SharedPreferences sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);

        String userName=sp.getString("name","");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonorRequestForm.this.finish();
            }
        });
        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Donor_feed");


        cDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

                if (checkBox.isChecked()) {
                    String key = mFirebaseDatabase.push().getKey();
                    DFeedModel donor = new DFeedModel(userName, title.getText().toString(), quantity.getText().toString(), pickup.getText().toString(),
                            time.getText().toString(),
                            date.getText().toString(),status
                    );
                    mFirebaseDatabase.child(key).setValue(donor);
                    Toast.makeText(DonorRequestForm.this, "Posted!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            DonorRequestForm.this.finish();
                        }
                    }, 1300);

                }
                else
                {
                    Toast.makeText(DonorRequestForm.this, "Please check CHECKBOX", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}