package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.adapter.CharityFeedAdapter;
import com.example.myapplication.hunger_equity.adapter.DonorFeedAdapter;
import com.example.myapplication.hunger_equity.model.CFeedModel;
import com.example.myapplication.hunger_equity.model.DFeedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DonationFeed extends AppCompatActivity {
    SharedPreferences sp;
    String UserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        DatabaseReference databaseReference;
        DonorFeedAdapter adapter;
        ArrayList<CFeedModel> list;
        SharedPreferences sp;
        sp=getApplicationContext().getSharedPreferences("DonorInfo", Context.MODE_PRIVATE);
        UserName=sp.getString("name","");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_feed);




        recyclerView=findViewById(R.id.donor_feed_list);
        databaseReference= FirebaseDatabase.getInstance().getReference("Charity_feed");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(DonationFeed.this);
        recyclerView.setLayoutManager(layoutmanager);

        ImageButton back=(ImageButton)findViewById(R.id.donor_feed_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationFeed.this.finish();
            }
        });

        list=new ArrayList<>();
        adapter=new DonorFeedAdapter(DonationFeed.this,list,UserName);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CFeedModel model=dataSnapshot.getValue(CFeedModel.class);
                    if(model.getStatus().equals("Active"))
                    {
                        list.add(model);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void announce()
    {

        Toast.makeText(DonationFeed.this, "Request Complete!", Toast.LENGTH_SHORT).show();
    }
}