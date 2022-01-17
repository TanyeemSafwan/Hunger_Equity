package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DonorHome extends AppCompatActivity {
    SharedPreferences sp;
    private DatabaseReference mFirebaseDatabase;
    String link;
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);
        TextView dName,dEmail,dPhone,dAdress;
        Button edit;
        ImageButton back=(ImageButton)findViewById(R.id.donor_home_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonorHome.this.finish();
            }
        });

        edit=(Button)findViewById(R.id.donor_user_edit);
        dName=(TextView) findViewById(R.id.donor_user_name);
        dEmail=(TextView) findViewById(R.id.donor_user_email);
        dPhone=(TextView) findViewById(R.id.donor_user_phone);
        dAdress=(TextView) findViewById(R.id.donor_user_address);
        ImageView photo;
        photo=(ImageView)findViewById(R.id.donor_user_photo);

        /*Intent in=getIntent();
        String userName=in.getStringExtra("name");
        String userEmail=in.getStringExtra("email");
        String userPhone=in.getStringExtra("phone");
        String userAddress=in.getStringExtra("address");
        String userPassword=in.getStringExtra("password");*/
        sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);
        String userName=sp.getString("name","");
        String userEmail=sp.getString("email","");
        String userPhone=sp.getString("phone","");
        String userAddress=sp.getString("address","");
        String userPassword=sp.getString("password","");

        dName.setText(userName);
        dEmail.setText(userEmail);
        dPhone.setText(userPhone);
        dAdress.setText(userAddress);
        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("d_Pics").child(userName);
        //  CharityModel model=new CharityModel(mFirebaseDatabase.child(userName).toString());
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                link = snapshot.child("imageUrl").getValue(String.class);

                Picasso.get().load(link).into(photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DonorHome.this,DonorHomePic.class);
                i.putExtra("galleryName",userName);
                startActivity(i);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent=new Intent(DonorHome.this,DonorProfileEdit.class);
                /*editIntent.putExtra("name",userName);
                editIntent.putExtra("email",userEmail);
                editIntent.putExtra("phone",userPhone);
                editIntent.putExtra("address",userAddress);
                editIntent.putExtra("password",userPassword);*/
                startActivity(editIntent);
            }
        });
    }
}