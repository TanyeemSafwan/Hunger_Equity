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

import com.bumptech.glide.Glide;
import com.example.myapplication.hunger_equity.model.Charity;
import com.example.myapplication.hunger_equity.model.CharityModel;
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

public class CharityHome extends AppCompatActivity {
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
        setContentView(R.layout.activity_charity_home);
        TextView cName,cEmail,cPhone,cAdress;
        ImageView photo;
        Button edit;
        ImageButton back=(ImageButton)findViewById(R.id.charity_home_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityHome.this.finish();
            }
        });

        edit=(Button)findViewById(R.id.charity_user_edit);
        photo=(ImageView)findViewById(R.id.charity_user_photo);
        cName=(TextView) findViewById(R.id.charity_user_name);
        cEmail=(TextView) findViewById(R.id.charity_user_email);
        cPhone=(TextView) findViewById(R.id.charity_user_phone);
        cAdress=(TextView) findViewById(R.id.charity_user_address);

       /* Intent in=getIntent();
        String userName=in.getStringExtra("name");
        String userEmail=in.getStringExtra("email");
        String userPhone=in.getStringExtra("phone");
        String userAddress=in.getStringExtra("address");
        String userPassword=in.getStringExtra("password");
        String userOrgan=in.getStringExtra("organ");*/

        sp=getApplicationContext().getSharedPreferences("CharityInfo", Context.MODE_PRIVATE);

        String userName=sp.getString("name","");
        String userEmail=sp.getString("email","");
        String userPhone=sp.getString("phone","");
        String userAddress=sp.getString("address","");
        String userPassword=sp.getString("password","");
        String userOrgan=sp.getString("organ","");


        cName.setText(userName);
        cEmail.setText(userEmail);
        cPhone.setText(userPhone);
        cAdress.setText(userAddress);
       mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Pics").child(userName);
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
                Intent i=new Intent(CharityHome.this,CharityHomePic.class);
                i.putExtra("galleryName",userName);
                startActivity(i);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent=new Intent(CharityHome.this,CharityProfileEdit.class);
                startActivity(editIntent);
            }
        });
    }
}